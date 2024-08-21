package p012io.grpc.internal;

import androidx.core.app.NotificationManagerCompat;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import p012io.grpc.Attributes;
import p012io.grpc.ClientStreamTracer;
import p012io.grpc.Compressor;
import p012io.grpc.Deadline;
import p012io.grpc.DecompressorRegistry;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.Status;
import p012io.grpc.internal.ClientStreamListener;
import p012io.grpc.internal.HedgingPolicy;
import p012io.grpc.internal.RetryPolicy;
import p012io.grpc.internal.StreamListener;

/* renamed from: io.grpc.internal.RetriableStream */
abstract class RetriableStream<ReqT> implements ClientStream {
    /* access modifiers changed from: private */
    public static final Status CANCELLED_BECAUSE_COMMITTED = Status.CANCELLED.withDescription("Stream thrown away because RetriableStream committed");
    static final Metadata.Key<String> GRPC_PREVIOUS_RPC_ATTEMPTS = Metadata.Key.m262of("grpc-previous-rpc-attempts", Metadata.ASCII_STRING_MARSHALLER);
    static final Metadata.Key<String> GRPC_RETRY_PUSHBACK_MS = Metadata.Key.m262of("grpc-retry-pushback-ms", Metadata.ASCII_STRING_MARSHALLER);
    /* access modifiers changed from: private */
    public static Random random = new Random();
    /* access modifiers changed from: private */
    public final Executor callExecutor;
    /* access modifiers changed from: private */
    public final long channelBufferLimit;
    /* access modifiers changed from: private */
    public final ChannelBufferMeter channelBufferUsed;
    /* access modifiers changed from: private */
    public final InsightBuilder closedSubstreamsInsight = new InsightBuilder();
    private final Metadata headers;
    /* access modifiers changed from: private */
    public HedgingPolicy hedgingPolicy;
    private final HedgingPolicy.Provider hedgingPolicyProvider;
    /* access modifiers changed from: private */
    public boolean isHedging;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    /* access modifiers changed from: private */
    public ClientStreamListener masterListener;
    /* access modifiers changed from: private */
    public final MethodDescriptor<ReqT, ?> method;
    /* access modifiers changed from: private */
    public long nextBackoffIntervalNanos;
    /* access modifiers changed from: private */
    public final AtomicBoolean noMoreTransparentRetry = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final long perRpcBufferLimit;
    /* access modifiers changed from: private */
    public long perRpcBufferUsed;
    /* access modifiers changed from: private */
    public RetryPolicy retryPolicy;
    /* access modifiers changed from: private */
    public final RetryPolicy.Provider retryPolicyProvider;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduledExecutorService;
    /* access modifiers changed from: private */
    public FutureCanceller scheduledHedging;
    /* access modifiers changed from: private */
    public FutureCanceller scheduledRetry;
    /* access modifiers changed from: private */
    public volatile State state = new State(new ArrayList(8), Collections.emptyList(), (Collection<Substream>) null, (Substream) null, false, false, false, 0);
    /* access modifiers changed from: private */
    @Nullable
    public final Throttle throttle;

    /* renamed from: io.grpc.internal.RetriableStream$BufferEntry */
    private interface BufferEntry {
        void runWith(Substream substream);
    }

    /* access modifiers changed from: package-private */
    public abstract ClientStream newSubstream(ClientStreamTracer.Factory factory, Metadata metadata);

    /* access modifiers changed from: package-private */
    public abstract void postCommit();

    /* access modifiers changed from: package-private */
    @CheckReturnValue
    @Nullable
    public abstract Status prestart();

    RetriableStream(MethodDescriptor<ReqT, ?> methodDescriptor, Metadata metadata, ChannelBufferMeter channelBufferMeter, long j, long j2, Executor executor, ScheduledExecutorService scheduledExecutorService2, RetryPolicy.Provider provider, HedgingPolicy.Provider provider2, @Nullable Throttle throttle2) {
        this.method = methodDescriptor;
        this.channelBufferUsed = channelBufferMeter;
        this.perRpcBufferLimit = j;
        this.channelBufferLimit = j2;
        this.callExecutor = executor;
        this.scheduledExecutorService = scheduledExecutorService2;
        this.headers = metadata;
        this.retryPolicyProvider = (RetryPolicy.Provider) Preconditions.checkNotNull(provider, "retryPolicyProvider");
        this.hedgingPolicyProvider = (HedgingPolicy.Provider) Preconditions.checkNotNull(provider2, "hedgingPolicyProvider");
        this.throttle = throttle2;
    }

    /* access modifiers changed from: private */
    @CheckReturnValue
    @Nullable
    public Runnable commit(Substream substream) {
        final Future<?> future;
        final Future<?> future2;
        synchronized (this.lock) {
            if (this.state.winningSubstream != null) {
                return null;
            }
            final Collection<Substream> collection = this.state.drainedSubstreams;
            this.state = this.state.committed(substream);
            this.channelBufferUsed.addAndGet(-this.perRpcBufferUsed);
            FutureCanceller futureCanceller = this.scheduledRetry;
            if (futureCanceller != null) {
                Future<?> markCancelled = futureCanceller.markCancelled();
                this.scheduledRetry = null;
                future = markCancelled;
            } else {
                future = null;
            }
            FutureCanceller futureCanceller2 = this.scheduledHedging;
            if (futureCanceller2 != null) {
                Future<?> markCancelled2 = futureCanceller2.markCancelled();
                this.scheduledHedging = null;
                future2 = markCancelled2;
            } else {
                future2 = null;
            }
            final Substream substream2 = substream;
            AnonymousClass1CommitTask r3 = new Runnable() {
                public void run() {
                    for (Substream substream : collection) {
                        if (substream != substream2) {
                            substream.stream.cancel(RetriableStream.CANCELLED_BECAUSE_COMMITTED);
                        }
                    }
                    Future future = future;
                    if (future != null) {
                        future.cancel(false);
                    }
                    Future future2 = future2;
                    if (future2 != null) {
                        future2.cancel(false);
                    }
                    RetriableStream.this.postCommit();
                }
            };
            return r3;
        }
    }

    /* access modifiers changed from: private */
    public void commitAndRun(Substream substream) {
        Runnable commit = commit(substream);
        if (commit != null) {
            commit.run();
        }
    }

    /* access modifiers changed from: private */
    public Substream createSubstream(int i) {
        Substream substream = new Substream(i);
        final BufferSizeTracer bufferSizeTracer = new BufferSizeTracer(substream);
        substream.stream = newSubstream(new ClientStreamTracer.Factory() {
            public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo streamInfo, Metadata metadata) {
                return bufferSizeTracer;
            }
        }, updateHeaders(this.headers, i));
        return substream;
    }

    /* access modifiers changed from: package-private */
    public final Metadata updateHeaders(Metadata metadata, int i) {
        Metadata metadata2 = new Metadata();
        metadata2.merge(metadata);
        if (i > 0) {
            metadata2.put(GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(i));
        }
        return metadata2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0056, code lost:
        r2 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005e, code lost:
        if (r2.hasNext() == false) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
        r3 = (p012io.grpc.internal.RetriableStream.BufferEntry) r2.next();
        r4 = r7.state;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006a, code lost:
        if (r4.winningSubstream == null) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006e, code lost:
        if (r4.winningSubstream == r8) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0073, code lost:
        if (r4.cancelled == false) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0077, code lost:
        if (r4.winningSubstream != r8) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0079, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007a, code lost:
        com.google.common.base.Preconditions.checkState(r0, "substream should be CANCELLED_BECAUSE_COMMITTED already");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0080, code lost:
        r3.runWith(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0084, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drain(p012io.grpc.internal.RetriableStream.Substream r8) {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
            r2 = 0
        L_0x0003:
            java.lang.Object r3 = r7.lock
            monitor-enter(r3)
            io.grpc.internal.RetriableStream$State r4 = r7.state     // Catch:{ all -> 0x0087 }
            io.grpc.internal.RetriableStream$Substream r5 = r4.winningSubstream     // Catch:{ all -> 0x0087 }
            if (r5 == 0) goto L_0x0019
            io.grpc.internal.RetriableStream$Substream r5 = r4.winningSubstream     // Catch:{ all -> 0x0087 }
            if (r5 == r8) goto L_0x0019
            monitor-exit(r3)     // Catch:{ all -> 0x0087 }
            io.grpc.internal.ClientStream r8 = r8.stream
            io.grpc.Status r0 = CANCELLED_BECAUSE_COMMITTED
            r8.cancel(r0)
            return
        L_0x0019:
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r5 = r4.buffer     // Catch:{ all -> 0x0087 }
            int r5 = r5.size()     // Catch:{ all -> 0x0087 }
            if (r2 != r5) goto L_0x0029
            io.grpc.internal.RetriableStream$State r8 = r4.substreamDrained(r8)     // Catch:{ all -> 0x0087 }
            r7.state = r8     // Catch:{ all -> 0x0087 }
            monitor-exit(r3)     // Catch:{ all -> 0x0087 }
            return
        L_0x0029:
            boolean r5 = r8.closed     // Catch:{ all -> 0x0087 }
            if (r5 == 0) goto L_0x002f
            monitor-exit(r3)     // Catch:{ all -> 0x0087 }
            return
        L_0x002f:
            int r5 = r2 + 128
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r6 = r4.buffer     // Catch:{ all -> 0x0087 }
            int r6 = r6.size()     // Catch:{ all -> 0x0087 }
            int r5 = java.lang.Math.min(r5, r6)     // Catch:{ all -> 0x0087 }
            if (r1 != 0) goto L_0x0049
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0087 }
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r4 = r4.buffer     // Catch:{ all -> 0x0087 }
            java.util.List r2 = r4.subList(r2, r5)     // Catch:{ all -> 0x0087 }
            r1.<init>(r2)     // Catch:{ all -> 0x0087 }
            goto L_0x0055
        L_0x0049:
            r1.clear()     // Catch:{ all -> 0x0087 }
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r4 = r4.buffer     // Catch:{ all -> 0x0087 }
            java.util.List r2 = r4.subList(r2, r5)     // Catch:{ all -> 0x0087 }
            r1.addAll(r2)     // Catch:{ all -> 0x0087 }
        L_0x0055:
            monitor-exit(r3)     // Catch:{ all -> 0x0087 }
            java.util.Iterator r2 = r1.iterator()
        L_0x005a:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0084
            java.lang.Object r3 = r2.next()
            io.grpc.internal.RetriableStream$BufferEntry r3 = (p012io.grpc.internal.RetriableStream.BufferEntry) r3
            io.grpc.internal.RetriableStream$State r4 = r7.state
            io.grpc.internal.RetriableStream$Substream r6 = r4.winningSubstream
            if (r6 == 0) goto L_0x0071
            io.grpc.internal.RetriableStream$Substream r6 = r4.winningSubstream
            if (r6 == r8) goto L_0x0071
            goto L_0x0084
        L_0x0071:
            boolean r6 = r4.cancelled
            if (r6 == 0) goto L_0x0080
            io.grpc.internal.RetriableStream$Substream r1 = r4.winningSubstream
            if (r1 != r8) goto L_0x007a
            r0 = 1
        L_0x007a:
            java.lang.String r8 = "substream should be CANCELLED_BECAUSE_COMMITTED already"
            com.google.common.base.Preconditions.checkState(r0, r8)
            return
        L_0x0080:
            r3.runWith(r8)
            goto L_0x005a
        L_0x0084:
            r2 = r5
            goto L_0x0003
        L_0x0087:
            r8 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0087 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.RetriableStream.drain(io.grpc.internal.RetriableStream$Substream):void");
    }

    public final void start(ClientStreamListener clientStreamListener) {
        Throttle throttle2;
        this.masterListener = clientStreamListener;
        Status prestart = prestart();
        if (prestart != null) {
            cancel(prestart);
            return;
        }
        synchronized (this.lock) {
            this.state.buffer.add(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.start(new Sublistener(substream));
                }
            });
        }
        boolean z = false;
        Substream createSubstream = createSubstream(0);
        if (this.hedgingPolicy == null) {
            z = true;
        }
        Preconditions.checkState(z, "hedgingPolicy has been initialized unexpectedly");
        this.hedgingPolicy = this.hedgingPolicyProvider.get();
        if (!HedgingPolicy.DEFAULT.equals(this.hedgingPolicy)) {
            this.isHedging = true;
            this.retryPolicy = RetryPolicy.DEFAULT;
            FutureCanceller futureCanceller = null;
            synchronized (this.lock) {
                this.state = this.state.addActiveHedge(createSubstream);
                if (hasPotentialHedging(this.state) && ((throttle2 = this.throttle) == null || throttle2.isAboveThreshold())) {
                    futureCanceller = new FutureCanceller(this.lock);
                    this.scheduledHedging = futureCanceller;
                }
            }
            if (futureCanceller != null) {
                futureCanceller.setFuture(this.scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller), this.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
            }
        }
        drain(createSubstream);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
        if (r1 == null) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        r1.cancel(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        r2.setFuture(r5.scheduledExecutorService.schedule(new p012io.grpc.internal.RetriableStream.HedgingRunnable(r5, r2), (long) r6.intValue(), java.util.concurrent.TimeUnit.MILLISECONDS));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void pushbackHedging(@javax.annotation.Nullable java.lang.Integer r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L_0x0003
            return
        L_0x0003:
            int r0 = r6.intValue()
            if (r0 >= 0) goto L_0x000d
            r5.freezeHedging()
            return
        L_0x000d:
            java.lang.Object r0 = r5.lock
            monitor-enter(r0)
            io.grpc.internal.RetriableStream$FutureCanceller r1 = r5.scheduledHedging     // Catch:{ all -> 0x0040 }
            if (r1 != 0) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x0016:
            java.util.concurrent.Future r1 = r1.markCancelled()     // Catch:{ all -> 0x0040 }
            io.grpc.internal.RetriableStream$FutureCanceller r2 = new io.grpc.internal.RetriableStream$FutureCanceller     // Catch:{ all -> 0x0040 }
            java.lang.Object r3 = r5.lock     // Catch:{ all -> 0x0040 }
            r2.<init>(r3)     // Catch:{ all -> 0x0040 }
            r5.scheduledHedging = r2     // Catch:{ all -> 0x0040 }
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x002a
            r0 = 0
            r1.cancel(r0)
        L_0x002a:
            java.util.concurrent.ScheduledExecutorService r0 = r5.scheduledExecutorService
            io.grpc.internal.RetriableStream$HedgingRunnable r1 = new io.grpc.internal.RetriableStream$HedgingRunnable
            r1.<init>(r2)
            int r6 = r6.intValue()
            long r3 = (long) r6
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS
            java.util.concurrent.ScheduledFuture r6 = r0.schedule(r1, r3, r6)
            r2.setFuture(r6)
            return
        L_0x0040:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.RetriableStream.pushbackHedging(java.lang.Integer):void");
    }

    /* renamed from: io.grpc.internal.RetriableStream$HedgingRunnable */
    private final class HedgingRunnable implements Runnable {
        final FutureCanceller scheduledHedgingRef;

        HedgingRunnable(FutureCanceller futureCanceller) {
            this.scheduledHedgingRef = futureCanceller;
        }

        public void run() {
            RetriableStream.this.callExecutor.execute(new Runnable() {
                public void run() {
                    FutureCanceller futureCanceller;
                    boolean z;
                    Substream access$200 = RetriableStream.this.createSubstream(RetriableStream.this.state.hedgingAttemptCount);
                    synchronized (RetriableStream.this.lock) {
                        futureCanceller = null;
                        z = false;
                        if (HedgingRunnable.this.scheduledHedgingRef.isCancelled()) {
                            z = true;
                        } else {
                            State unused = RetriableStream.this.state = RetriableStream.this.state.addActiveHedge(access$200);
                            if (!RetriableStream.this.hasPotentialHedging(RetriableStream.this.state) || (RetriableStream.this.throttle != null && !RetriableStream.this.throttle.isAboveThreshold())) {
                                State unused2 = RetriableStream.this.state = RetriableStream.this.state.freezeHedging();
                                FutureCanceller unused3 = RetriableStream.this.scheduledHedging = null;
                            } else {
                                RetriableStream retriableStream = RetriableStream.this;
                                futureCanceller = new FutureCanceller(RetriableStream.this.lock);
                                FutureCanceller unused4 = retriableStream.scheduledHedging = futureCanceller;
                            }
                        }
                    }
                    if (z) {
                        access$200.stream.cancel(Status.CANCELLED.withDescription("Unneeded hedging"));
                        return;
                    }
                    if (futureCanceller != null) {
                        futureCanceller.setFuture(RetriableStream.this.scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller), RetriableStream.this.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
                    }
                    RetriableStream.this.drain(access$200);
                }
            });
        }
    }

    public final void cancel(Status status) {
        Substream substream = new Substream(0);
        substream.stream = new NoopClientStream();
        Runnable commit = commit(substream);
        if (commit != null) {
            this.masterListener.closed(status, new Metadata());
            commit.run();
            return;
        }
        this.state.winningSubstream.stream.cancel(status);
        synchronized (this.lock) {
            this.state = this.state.cancelled();
        }
    }

    private void delayOrExecute(BufferEntry bufferEntry) {
        Collection<Substream> collection;
        synchronized (this.lock) {
            if (!this.state.passThrough) {
                this.state.buffer.add(bufferEntry);
            }
            collection = this.state.drainedSubstreams;
        }
        for (Substream runWith : collection) {
            bufferEntry.runWith(runWith);
        }
    }

    public final void writeMessage(InputStream inputStream) {
        throw new IllegalStateException("RetriableStream.writeMessage() should not be called directly");
    }

    /* access modifiers changed from: package-private */
    public final void sendMessage(final ReqT reqt) {
        State state2 = this.state;
        if (state2.passThrough) {
            state2.winningSubstream.stream.writeMessage(this.method.streamRequest(reqt));
        } else {
            delayOrExecute(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.writeMessage(RetriableStream.this.method.streamRequest(reqt));
                }
            });
        }
    }

    public final void request(final int i) {
        State state2 = this.state;
        if (state2.passThrough) {
            state2.winningSubstream.stream.request(i);
        } else {
            delayOrExecute(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.request(i);
                }
            });
        }
    }

    public final void flush() {
        State state2 = this.state;
        if (state2.passThrough) {
            state2.winningSubstream.stream.flush();
        } else {
            delayOrExecute(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.flush();
                }
            });
        }
    }

    public final boolean isReady() {
        for (Substream substream : this.state.drainedSubstreams) {
            if (substream.stream.isReady()) {
                return true;
            }
        }
        return false;
    }

    public final void setCompressor(final Compressor compressor) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setCompressor(compressor);
            }
        });
    }

    public final void setFullStreamDecompression(final boolean z) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setFullStreamDecompression(z);
            }
        });
    }

    public final void setMessageCompression(final boolean z) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setMessageCompression(z);
            }
        });
    }

    public final void halfClose() {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.halfClose();
            }
        });
    }

    public final void setAuthority(final String str) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setAuthority(str);
            }
        });
    }

    public final void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setDecompressorRegistry(decompressorRegistry);
            }
        });
    }

    public final void setMaxInboundMessageSize(final int i) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setMaxInboundMessageSize(i);
            }
        });
    }

    public final void setMaxOutboundMessageSize(final int i) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setMaxOutboundMessageSize(i);
            }
        });
    }

    public final void setDeadline(final Deadline deadline) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setDeadline(deadline);
            }
        });
    }

    public final Attributes getAttributes() {
        if (this.state.winningSubstream != null) {
            return this.state.winningSubstream.stream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        State state2;
        synchronized (this.lock) {
            insightBuilder.appendKeyValue("closed", this.closedSubstreamsInsight);
            state2 = this.state;
        }
        if (state2.winningSubstream != null) {
            InsightBuilder insightBuilder2 = new InsightBuilder();
            state2.winningSubstream.stream.appendTimeoutInsight(insightBuilder2);
            insightBuilder.appendKeyValue("committed", insightBuilder2);
            return;
        }
        InsightBuilder insightBuilder3 = new InsightBuilder();
        for (Substream substream : state2.drainedSubstreams) {
            InsightBuilder insightBuilder4 = new InsightBuilder();
            substream.stream.appendTimeoutInsight(insightBuilder4);
            insightBuilder3.append(insightBuilder4);
        }
        insightBuilder.appendKeyValue("open", insightBuilder3);
    }

    static void setRandom(Random random2) {
        random = random2;
    }

    /* access modifiers changed from: private */
    public boolean hasPotentialHedging(State state2) {
        return state2.winningSubstream == null && state2.hedgingAttemptCount < this.hedgingPolicy.maxAttempts && !state2.hedgingFrozen;
    }

    /* access modifiers changed from: private */
    public void freezeHedging() {
        Future<?> future;
        synchronized (this.lock) {
            FutureCanceller futureCanceller = this.scheduledHedging;
            future = null;
            if (futureCanceller != null) {
                Future<?> markCancelled = futureCanceller.markCancelled();
                this.scheduledHedging = null;
                future = markCancelled;
            }
            this.state = this.state.freezeHedging();
        }
        if (future != null) {
            future.cancel(false);
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$Sublistener */
    private final class Sublistener implements ClientStreamListener {
        final Substream substream;

        Sublistener(Substream substream2) {
            this.substream = substream2;
        }

        public void headersRead(Metadata metadata) {
            RetriableStream.this.commitAndRun(this.substream);
            if (RetriableStream.this.state.winningSubstream == this.substream) {
                RetriableStream.this.masterListener.headersRead(metadata);
                if (RetriableStream.this.throttle != null) {
                    RetriableStream.this.throttle.onSuccess();
                }
            }
        }

        public void closed(Status status, Metadata metadata) {
            closed(status, ClientStreamListener.RpcProgress.PROCESSED, metadata);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:75:0x019c, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void closed(p012io.grpc.Status r5, p012io.grpc.internal.ClientStreamListener.RpcProgress r6, p012io.grpc.Metadata r7) {
            /*
                r4 = this;
                io.grpc.internal.RetriableStream r0 = p012io.grpc.internal.RetriableStream.this
                java.lang.Object r0 = r0.lock
                monitor-enter(r0)
                io.grpc.internal.RetriableStream r1 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x01bf }
                io.grpc.internal.RetriableStream$State r2 = r1.state     // Catch:{ all -> 0x01bf }
                io.grpc.internal.RetriableStream$Substream r3 = r4.substream     // Catch:{ all -> 0x01bf }
                io.grpc.internal.RetriableStream$State r2 = r2.substreamClosed(r3)     // Catch:{ all -> 0x01bf }
                p012io.grpc.internal.RetriableStream.State unused = r1.state = r2     // Catch:{ all -> 0x01bf }
                io.grpc.internal.RetriableStream r1 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x01bf }
                io.grpc.internal.InsightBuilder r1 = r1.closedSubstreamsInsight     // Catch:{ all -> 0x01bf }
                io.grpc.Status$Code r2 = r5.getCode()     // Catch:{ all -> 0x01bf }
                r1.append(r2)     // Catch:{ all -> 0x01bf }
                monitor-exit(r0)     // Catch:{ all -> 0x01bf }
                io.grpc.internal.RetriableStream$Substream r0 = r4.substream
                boolean r0 = r0.bufferLimitExceeded
                if (r0 == 0) goto L_0x0047
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Substream r0 = r4.substream
                r6.commitAndRun(r0)
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r6 = r6.state
                io.grpc.internal.RetriableStream$Substream r6 = r6.winningSubstream
                io.grpc.internal.RetriableStream$Substream r0 = r4.substream
                if (r6 != r0) goto L_0x0046
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.ClientStreamListener r6 = r6.masterListener
                r6.closed(r5, r7)
            L_0x0046:
                return
            L_0x0047:
                io.grpc.internal.RetriableStream r0 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                if (r0 != 0) goto L_0x01a2
                io.grpc.internal.ClientStreamListener$RpcProgress r0 = p012io.grpc.internal.ClientStreamListener.RpcProgress.REFUSED
                r1 = 0
                r2 = 1
                if (r6 != r0) goto L_0x00e5
                io.grpc.internal.RetriableStream r0 = p012io.grpc.internal.RetriableStream.this
                java.util.concurrent.atomic.AtomicBoolean r0 = r0.noMoreTransparentRetry
                boolean r0 = r0.compareAndSet(r1, r2)
                if (r0 == 0) goto L_0x00e5
                io.grpc.internal.RetriableStream r5 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Substream r6 = r4.substream
                int r6 = r6.previousAttemptCount
                io.grpc.internal.RetriableStream$Substream r5 = r5.createSubstream(r6)
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                boolean r6 = r6.isHedging
                if (r6 == 0) goto L_0x00b2
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                java.lang.Object r6 = r6.lock
                monitor-enter(r6)
                io.grpc.internal.RetriableStream r7 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x00af }
                io.grpc.internal.RetriableStream$State r0 = r7.state     // Catch:{ all -> 0x00af }
                io.grpc.internal.RetriableStream$Substream r3 = r4.substream     // Catch:{ all -> 0x00af }
                io.grpc.internal.RetriableStream$State r0 = r0.replaceActiveHedge(r3, r5)     // Catch:{ all -> 0x00af }
                p012io.grpc.internal.RetriableStream.State unused = r7.state = r0     // Catch:{ all -> 0x00af }
                io.grpc.internal.RetriableStream r7 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x00af }
                io.grpc.internal.RetriableStream$State r0 = r7.state     // Catch:{ all -> 0x00af }
                boolean r7 = r7.hasPotentialHedging(r0)     // Catch:{ all -> 0x00af }
                if (r7 != 0) goto L_0x00a6
                io.grpc.internal.RetriableStream r7 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x00af }
                io.grpc.internal.RetriableStream$State r7 = r7.state     // Catch:{ all -> 0x00af }
                java.util.Collection<io.grpc.internal.RetriableStream$Substream> r7 = r7.activeHedges     // Catch:{ all -> 0x00af }
                int r7 = r7.size()     // Catch:{ all -> 0x00af }
                if (r7 != r2) goto L_0x00a6
                r1 = 1
            L_0x00a6:
                monitor-exit(r6)     // Catch:{ all -> 0x00af }
                if (r1 == 0) goto L_0x00d6
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                r6.commitAndRun(r5)
                goto L_0x00d6
            L_0x00af:
                r5 = move-exception
                monitor-exit(r6)     // Catch:{ all -> 0x00af }
                throw r5
            L_0x00b2:
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r6 = r6.retryPolicy
                if (r6 != 0) goto L_0x00c7
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy$Provider r7 = r6.retryPolicyProvider
                io.grpc.internal.RetryPolicy r7 = r7.get()
                p012io.grpc.internal.RetryPolicy unused = r6.retryPolicy = r7
            L_0x00c7:
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r6 = r6.retryPolicy
                int r6 = r6.maxAttempts
                if (r6 != r2) goto L_0x00d6
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                r6.commitAndRun(r5)
            L_0x00d6:
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                java.util.concurrent.Executor r6 = r6.callExecutor
                io.grpc.internal.RetriableStream$Sublistener$1 r7 = new io.grpc.internal.RetriableStream$Sublistener$1
                r7.<init>(r5)
                r6.execute(r7)
                return
            L_0x00e5:
                io.grpc.internal.ClientStreamListener$RpcProgress r0 = p012io.grpc.internal.ClientStreamListener.RpcProgress.DROPPED
                if (r6 != r0) goto L_0x00f7
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                boolean r6 = r6.isHedging
                if (r6 == 0) goto L_0x0161
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                r6.freezeHedging()
                goto L_0x0161
            L_0x00f7:
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                java.util.concurrent.atomic.AtomicBoolean r6 = r6.noMoreTransparentRetry
                r6.set(r2)
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r6 = r6.retryPolicy
                if (r6 != 0) goto L_0x0120
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy$Provider r0 = r6.retryPolicyProvider
                io.grpc.internal.RetryPolicy r0 = r0.get()
                p012io.grpc.internal.RetryPolicy unused = r6.retryPolicy = r0
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r0 = r6.retryPolicy
                long r0 = r0.initialBackoffNanos
                long unused = r6.nextBackoffIntervalNanos = r0
            L_0x0120:
                io.grpc.internal.RetriableStream$RetryPlan r6 = r4.makeRetryDecision(r5, r7)
                boolean r0 = r6.shouldRetry
                if (r0 == 0) goto L_0x0158
                io.grpc.internal.RetriableStream r5 = p012io.grpc.internal.RetriableStream.this
                java.lang.Object r0 = r5.lock
                monitor-enter(r0)
                io.grpc.internal.RetriableStream r5 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0155 }
                io.grpc.internal.RetriableStream$FutureCanceller r7 = new io.grpc.internal.RetriableStream$FutureCanceller     // Catch:{ all -> 0x0155 }
                java.lang.Object r1 = r5.lock     // Catch:{ all -> 0x0155 }
                r7.<init>(r1)     // Catch:{ all -> 0x0155 }
                p012io.grpc.internal.RetriableStream.FutureCanceller unused = r5.scheduledRetry = r7     // Catch:{ all -> 0x0155 }
                monitor-exit(r0)     // Catch:{ all -> 0x0155 }
                io.grpc.internal.RetriableStream r5 = p012io.grpc.internal.RetriableStream.this
                java.util.concurrent.ScheduledExecutorService r5 = r5.scheduledExecutorService
                io.grpc.internal.RetriableStream$Sublistener$2 r0 = new io.grpc.internal.RetriableStream$Sublistener$2
                r0.<init>()
                long r1 = r6.backoffNanos
                java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.NANOSECONDS
                java.util.concurrent.ScheduledFuture r5 = r5.schedule(r0, r1, r6)
                r7.setFuture(r5)
                return
            L_0x0155:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0155 }
                throw r5
            L_0x0158:
                boolean r1 = r6.isFatal
                io.grpc.internal.RetriableStream r0 = p012io.grpc.internal.RetriableStream.this
                java.lang.Integer r6 = r6.hedgingPushbackMillis
                r0.pushbackHedging(r6)
            L_0x0161:
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                boolean r6 = r6.isHedging
                if (r6 == 0) goto L_0x01a2
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                java.lang.Object r6 = r6.lock
                monitor-enter(r6)
                io.grpc.internal.RetriableStream r0 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x019f }
                io.grpc.internal.RetriableStream$State r2 = r0.state     // Catch:{ all -> 0x019f }
                io.grpc.internal.RetriableStream$Substream r3 = r4.substream     // Catch:{ all -> 0x019f }
                io.grpc.internal.RetriableStream$State r2 = r2.removeActiveHedge(r3)     // Catch:{ all -> 0x019f }
                p012io.grpc.internal.RetriableStream.State unused = r0.state = r2     // Catch:{ all -> 0x019f }
                if (r1 != 0) goto L_0x019d
                io.grpc.internal.RetriableStream r0 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x019f }
                io.grpc.internal.RetriableStream$State r1 = r0.state     // Catch:{ all -> 0x019f }
                boolean r0 = r0.hasPotentialHedging(r1)     // Catch:{ all -> 0x019f }
                if (r0 != 0) goto L_0x019b
                io.grpc.internal.RetriableStream r0 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x019f }
                io.grpc.internal.RetriableStream$State r0 = r0.state     // Catch:{ all -> 0x019f }
                java.util.Collection<io.grpc.internal.RetriableStream$Substream> r0 = r0.activeHedges     // Catch:{ all -> 0x019f }
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x019f }
                if (r0 != 0) goto L_0x019d
            L_0x019b:
                monitor-exit(r6)     // Catch:{ all -> 0x019f }
                return
            L_0x019d:
                monitor-exit(r6)     // Catch:{ all -> 0x019f }
                goto L_0x01a2
            L_0x019f:
                r5 = move-exception
                monitor-exit(r6)     // Catch:{ all -> 0x019f }
                throw r5
            L_0x01a2:
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Substream r0 = r4.substream
                r6.commitAndRun(r0)
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r6 = r6.state
                io.grpc.internal.RetriableStream$Substream r6 = r6.winningSubstream
                io.grpc.internal.RetriableStream$Substream r0 = r4.substream
                if (r6 != r0) goto L_0x01be
                io.grpc.internal.RetriableStream r6 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.ClientStreamListener r6 = r6.masterListener
                r6.closed(r5, r7)
            L_0x01be:
                return
            L_0x01bf:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x01bf }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.RetriableStream.Sublistener.closed(io.grpc.Status, io.grpc.internal.ClientStreamListener$RpcProgress, io.grpc.Metadata):void");
        }

        /* JADX WARNING: Removed duplicated region for block: B:34:0x00e7  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x00e9  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private p012io.grpc.internal.RetriableStream.RetryPlan makeRetryDecision(p012io.grpc.Status r13, p012io.grpc.Metadata r14) {
            /*
                r12 = this;
                io.grpc.internal.RetriableStream r0 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r0 = r0.retryPolicy
                java.util.Set<io.grpc.Status$Code> r0 = r0.retryableStatusCodes
                io.grpc.Status$Code r1 = r13.getCode()
                boolean r0 = r0.contains(r1)
                io.grpc.internal.RetriableStream r1 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.HedgingPolicy r1 = r1.hedgingPolicy
                java.util.Set<io.grpc.Status$Code> r1 = r1.nonFatalStatusCodes
                io.grpc.Status$Code r13 = r13.getCode()
                boolean r13 = r1.contains(r13)
                io.grpc.internal.RetriableStream r1 = p012io.grpc.internal.RetriableStream.this
                boolean r1 = r1.isHedging
                if (r1 == 0) goto L_0x0036
                if (r13 != 0) goto L_0x0036
                io.grpc.internal.RetriableStream$RetryPlan r13 = new io.grpc.internal.RetriableStream$RetryPlan
                r3 = 0
                r4 = 1
                r5 = 0
                r7 = 0
                r2 = r13
                r2.<init>(r3, r4, r5, r7)
                return r13
            L_0x0036:
                io.grpc.Metadata$Key<java.lang.String> r1 = p012io.grpc.internal.RetriableStream.GRPC_RETRY_PUSHBACK_MS
                java.lang.Object r14 = r14.get(r1)
                java.lang.String r14 = (java.lang.String) r14
                r1 = 0
                if (r14 == 0) goto L_0x004c
                java.lang.Integer r14 = java.lang.Integer.valueOf(r14)     // Catch:{ NumberFormatException -> 0x0046 }
                goto L_0x004d
            L_0x0046:
                r14 = -1
                java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
                goto L_0x004d
            L_0x004c:
                r14 = r1
            L_0x004d:
                io.grpc.internal.RetriableStream r2 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Throttle r2 = r2.throttle
                r3 = 0
                r4 = 1
                if (r2 == 0) goto L_0x006f
                if (r0 != 0) goto L_0x0063
                if (r13 != 0) goto L_0x0063
                if (r14 == 0) goto L_0x006f
                int r13 = r14.intValue()
                if (r13 >= 0) goto L_0x006f
            L_0x0063:
                io.grpc.internal.RetriableStream r13 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Throttle r13 = r13.throttle
                boolean r13 = r13.onQualifiedFailureThenCheckIsAboveThreshold()
                r13 = r13 ^ r4
                goto L_0x0070
            L_0x006f:
                r13 = 0
            L_0x0070:
                io.grpc.internal.RetriableStream r2 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r2 = r2.retryPolicy
                int r2 = r2.maxAttempts
                io.grpc.internal.RetriableStream$Substream r5 = r12.substream
                int r5 = r5.previousAttemptCount
                int r5 = r5 + r4
                if (r2 <= r5) goto L_0x00d8
                if (r13 != 0) goto L_0x00d8
                if (r14 != 0) goto L_0x00b9
                if (r0 == 0) goto L_0x00d8
                io.grpc.internal.RetriableStream r13 = p012io.grpc.internal.RetriableStream.this
                long r2 = r13.nextBackoffIntervalNanos
                double r2 = (double) r2
                java.util.Random r13 = p012io.grpc.internal.RetriableStream.random
                double r5 = r13.nextDouble()
                double r2 = r2 * r5
                long r2 = (long) r2
                io.grpc.internal.RetriableStream r13 = p012io.grpc.internal.RetriableStream.this
                long r5 = r13.nextBackoffIntervalNanos
                double r5 = (double) r5
                io.grpc.internal.RetriableStream r0 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r0 = r0.retryPolicy
                double r7 = r0.backoffMultiplier
                double r5 = r5 * r7
                long r5 = (long) r5
                io.grpc.internal.RetriableStream r0 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r0 = r0.retryPolicy
                long r7 = r0.maxBackoffNanos
                long r5 = java.lang.Math.min(r5, r7)
                long unused = r13.nextBackoffIntervalNanos = r5
                goto L_0x00d5
            L_0x00b9:
                int r13 = r14.intValue()
                if (r13 < 0) goto L_0x00d8
                java.util.concurrent.TimeUnit r13 = java.util.concurrent.TimeUnit.MILLISECONDS
                int r0 = r14.intValue()
                long r2 = (long) r0
                long r2 = r13.toNanos(r2)
                io.grpc.internal.RetriableStream r13 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r0 = r13.retryPolicy
                long r5 = r0.initialBackoffNanos
                long unused = r13.nextBackoffIntervalNanos = r5
            L_0x00d5:
                r9 = r2
                r7 = 1
                goto L_0x00dc
            L_0x00d8:
                r4 = 0
                r9 = r4
                r7 = 0
            L_0x00dc:
                io.grpc.internal.RetriableStream$RetryPlan r13 = new io.grpc.internal.RetriableStream$RetryPlan
                r8 = 0
                io.grpc.internal.RetriableStream r0 = p012io.grpc.internal.RetriableStream.this
                boolean r0 = r0.isHedging
                if (r0 == 0) goto L_0x00e9
                r11 = r14
                goto L_0x00ea
            L_0x00e9:
                r11 = r1
            L_0x00ea:
                r6 = r13
                r6.<init>(r7, r8, r9, r11)
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.RetriableStream.Sublistener.makeRetryDecision(io.grpc.Status, io.grpc.Metadata):io.grpc.internal.RetriableStream$RetryPlan");
        }

        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            State access$100 = RetriableStream.this.state;
            Preconditions.checkState(access$100.winningSubstream != null, "Headers should be received prior to messages.");
            if (access$100.winningSubstream == this.substream) {
                RetriableStream.this.masterListener.messagesAvailable(messageProducer);
            }
        }

        public void onReady() {
            if (RetriableStream.this.state.drainedSubstreams.contains(this.substream)) {
                RetriableStream.this.masterListener.onReady();
            }
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$State */
    private static final class State {
        final Collection<Substream> activeHedges;
        @Nullable
        final List<BufferEntry> buffer;
        final boolean cancelled;
        final Collection<Substream> drainedSubstreams;
        final int hedgingAttemptCount;
        final boolean hedgingFrozen;
        final boolean passThrough;
        @Nullable
        final Substream winningSubstream;

        State(@Nullable List<BufferEntry> list, Collection<Substream> collection, Collection<Substream> collection2, @Nullable Substream substream, boolean z, boolean z2, boolean z3, int i) {
            this.buffer = list;
            this.drainedSubstreams = (Collection) Preconditions.checkNotNull(collection, "drainedSubstreams");
            this.winningSubstream = substream;
            this.activeHedges = collection2;
            this.cancelled = z;
            this.passThrough = z2;
            this.hedgingFrozen = z3;
            this.hedgingAttemptCount = i;
            boolean z4 = false;
            Preconditions.checkState(!z2 || list == null, "passThrough should imply buffer is null");
            Preconditions.checkState(!z2 || substream != null, "passThrough should imply winningSubstream != null");
            Preconditions.checkState(!z2 || (collection.size() == 1 && collection.contains(substream)) || (collection.size() == 0 && substream.closed), "passThrough should imply winningSubstream is drained");
            Preconditions.checkState((!z || substream != null) ? true : z4, "cancelled should imply committed");
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State cancelled() {
            return new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, true, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State substreamDrained(Substream substream) {
            Collection unmodifiableCollection;
            boolean z = true;
            Preconditions.checkState(!this.passThrough, "Already passThrough");
            if (substream.closed) {
                unmodifiableCollection = this.drainedSubstreams;
            } else if (this.drainedSubstreams.isEmpty()) {
                unmodifiableCollection = Collections.singletonList(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.drainedSubstreams);
                arrayList.add(substream);
                unmodifiableCollection = Collections.unmodifiableCollection(arrayList);
            }
            Collection collection = unmodifiableCollection;
            Substream substream2 = this.winningSubstream;
            boolean z2 = substream2 != null;
            List<BufferEntry> list = this.buffer;
            if (z2) {
                if (substream2 != substream) {
                    z = false;
                }
                Preconditions.checkState(z, "Another RPC attempt has already committed");
                list = null;
            }
            return new State(list, collection, this.activeHedges, this.winningSubstream, this.cancelled, z2, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State substreamClosed(Substream substream) {
            substream.closed = true;
            if (!this.drainedSubstreams.contains(substream)) {
                return this;
            }
            ArrayList arrayList = new ArrayList(this.drainedSubstreams);
            arrayList.remove(substream);
            return new State(this.buffer, Collections.unmodifiableCollection(arrayList), this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State committed(Substream substream) {
            boolean z;
            Set set;
            List<BufferEntry> list;
            Preconditions.checkState(this.winningSubstream == null, "Already committed");
            List<BufferEntry> list2 = this.buffer;
            if (this.drainedSubstreams.contains(substream)) {
                list = null;
                set = Collections.singleton(substream);
                z = true;
            } else {
                list = list2;
                set = Collections.emptyList();
                z = false;
            }
            return new State(list, set, this.activeHedges, substream, this.cancelled, z, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State freezeHedging() {
            if (this.hedgingFrozen) {
                return this;
            }
            return new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, true, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State addActiveHedge(Substream substream) {
            Collection collection;
            Preconditions.checkState(!this.hedgingFrozen, "hedging frozen");
            Preconditions.checkState(this.winningSubstream == null, "already committed");
            if (this.activeHedges == null) {
                collection = Collections.singleton(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.activeHedges);
                arrayList.add(substream);
                collection = Collections.unmodifiableCollection(arrayList);
            }
            return new State(this.buffer, this.drainedSubstreams, collection, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount + 1);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State removeActiveHedge(Substream substream) {
            ArrayList arrayList = new ArrayList(this.activeHedges);
            arrayList.remove(substream);
            return new State(this.buffer, this.drainedSubstreams, Collections.unmodifiableCollection(arrayList), this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State replaceActiveHedge(Substream substream, Substream substream2) {
            ArrayList arrayList = new ArrayList(this.activeHedges);
            arrayList.remove(substream);
            arrayList.add(substream2);
            return new State(this.buffer, this.drainedSubstreams, Collections.unmodifiableCollection(arrayList), this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$Substream */
    private static final class Substream {
        boolean bufferLimitExceeded;
        boolean closed;
        final int previousAttemptCount;
        ClientStream stream;

        Substream(int i) {
            this.previousAttemptCount = i;
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$BufferSizeTracer */
    class BufferSizeTracer extends ClientStreamTracer {
        long bufferNeeded;
        private final Substream substream;

        BufferSizeTracer(Substream substream2) {
            this.substream = substream2;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:25:0x007e, code lost:
            if (r0 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0080, code lost:
            r0.run();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void outboundWireSize(long r8) {
            /*
                r7 = this;
                io.grpc.internal.RetriableStream r0 = p012io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                if (r0 == 0) goto L_0x000b
                return
            L_0x000b:
                r0 = 0
                io.grpc.internal.RetriableStream r1 = p012io.grpc.internal.RetriableStream.this
                java.lang.Object r1 = r1.lock
                monitor-enter(r1)
                io.grpc.internal.RetriableStream r2 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0086 }
                io.grpc.internal.RetriableStream$State r2 = r2.state     // Catch:{ all -> 0x0086 }
                io.grpc.internal.RetriableStream$Substream r2 = r2.winningSubstream     // Catch:{ all -> 0x0086 }
                if (r2 != 0) goto L_0x0084
                io.grpc.internal.RetriableStream$Substream r2 = r7.substream     // Catch:{ all -> 0x0086 }
                boolean r2 = r2.closed     // Catch:{ all -> 0x0086 }
                if (r2 == 0) goto L_0x0024
                goto L_0x0084
            L_0x0024:
                long r2 = r7.bufferNeeded     // Catch:{ all -> 0x0086 }
                long r2 = r2 + r8
                r7.bufferNeeded = r2     // Catch:{ all -> 0x0086 }
                io.grpc.internal.RetriableStream r8 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0086 }
                long r8 = r8.perRpcBufferUsed     // Catch:{ all -> 0x0086 }
                int r4 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
                if (r4 > 0) goto L_0x0035
                monitor-exit(r1)     // Catch:{ all -> 0x0086 }
                return
            L_0x0035:
                long r8 = r7.bufferNeeded     // Catch:{ all -> 0x0086 }
                io.grpc.internal.RetriableStream r2 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0086 }
                long r2 = r2.perRpcBufferLimit     // Catch:{ all -> 0x0086 }
                r4 = 1
                int r5 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
                if (r5 <= 0) goto L_0x0047
                io.grpc.internal.RetriableStream$Substream r8 = r7.substream     // Catch:{ all -> 0x0086 }
                r8.bufferLimitExceeded = r4     // Catch:{ all -> 0x0086 }
                goto L_0x006f
            L_0x0047:
                io.grpc.internal.RetriableStream r8 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0086 }
                io.grpc.internal.RetriableStream$ChannelBufferMeter r8 = r8.channelBufferUsed     // Catch:{ all -> 0x0086 }
                long r2 = r7.bufferNeeded     // Catch:{ all -> 0x0086 }
                io.grpc.internal.RetriableStream r9 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0086 }
                long r5 = r9.perRpcBufferUsed     // Catch:{ all -> 0x0086 }
                long r2 = r2 - r5
                long r8 = r8.addAndGet(r2)     // Catch:{ all -> 0x0086 }
                io.grpc.internal.RetriableStream r2 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0086 }
                long r5 = r7.bufferNeeded     // Catch:{ all -> 0x0086 }
                long unused = r2.perRpcBufferUsed = r5     // Catch:{ all -> 0x0086 }
                io.grpc.internal.RetriableStream r2 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0086 }
                long r2 = r2.channelBufferLimit     // Catch:{ all -> 0x0086 }
                int r5 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
                if (r5 <= 0) goto L_0x006f
                io.grpc.internal.RetriableStream$Substream r8 = r7.substream     // Catch:{ all -> 0x0086 }
                r8.bufferLimitExceeded = r4     // Catch:{ all -> 0x0086 }
            L_0x006f:
                io.grpc.internal.RetriableStream$Substream r8 = r7.substream     // Catch:{ all -> 0x0086 }
                boolean r8 = r8.bufferLimitExceeded     // Catch:{ all -> 0x0086 }
                if (r8 == 0) goto L_0x007d
                io.grpc.internal.RetriableStream r8 = p012io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0086 }
                io.grpc.internal.RetriableStream$Substream r9 = r7.substream     // Catch:{ all -> 0x0086 }
                java.lang.Runnable r0 = r8.commit(r9)     // Catch:{ all -> 0x0086 }
            L_0x007d:
                monitor-exit(r1)     // Catch:{ all -> 0x0086 }
                if (r0 == 0) goto L_0x0083
                r0.run()
            L_0x0083:
                return
            L_0x0084:
                monitor-exit(r1)     // Catch:{ all -> 0x0086 }
                return
            L_0x0086:
                r8 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0086 }
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.RetriableStream.BufferSizeTracer.outboundWireSize(long):void");
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$ChannelBufferMeter */
    static final class ChannelBufferMeter {
        private final AtomicLong bufferUsed = new AtomicLong();

        ChannelBufferMeter() {
        }

        /* access modifiers changed from: package-private */
        public long addAndGet(long j) {
            return this.bufferUsed.addAndGet(j);
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$Throttle */
    static final class Throttle {
        private static final int THREE_DECIMAL_PLACES_SCALE_UP = 1000;
        final int maxTokens;
        final int threshold;
        final AtomicInteger tokenCount;
        final int tokenRatio;

        Throttle(float f, float f2) {
            AtomicInteger atomicInteger = new AtomicInteger();
            this.tokenCount = atomicInteger;
            this.tokenRatio = (int) (f2 * 1000.0f);
            int i = (int) (f * 1000.0f);
            this.maxTokens = i;
            this.threshold = i / 2;
            atomicInteger.set(i);
        }

        /* access modifiers changed from: package-private */
        public boolean isAboveThreshold() {
            return this.tokenCount.get() > this.threshold;
        }

        /* access modifiers changed from: package-private */
        public boolean onQualifiedFailureThenCheckIsAboveThreshold() {
            int i;
            int i2;
            do {
                i = this.tokenCount.get();
                if (i == 0) {
                    return false;
                }
                i2 = i + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
            } while (!this.tokenCount.compareAndSet(i, Math.max(i2, 0)));
            if (i2 > this.threshold) {
                return true;
            }
            return false;
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        void onSuccess() {
            /*
                r4 = this;
            L_0x0000:
                java.util.concurrent.atomic.AtomicInteger r0 = r4.tokenCount
                int r0 = r0.get()
                int r1 = r4.maxTokens
                if (r0 != r1) goto L_0x000b
                goto L_0x001a
            L_0x000b:
                int r2 = r4.tokenRatio
                int r2 = r2 + r0
                java.util.concurrent.atomic.AtomicInteger r3 = r4.tokenCount
                int r1 = java.lang.Math.min(r2, r1)
                boolean r0 = r3.compareAndSet(r0, r1)
                if (r0 == 0) goto L_0x0000
            L_0x001a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.RetriableStream.Throttle.onSuccess():void");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Throttle)) {
                return false;
            }
            Throttle throttle = (Throttle) obj;
            if (this.maxTokens == throttle.maxTokens && this.tokenRatio == throttle.tokenRatio) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(Integer.valueOf(this.maxTokens), Integer.valueOf(this.tokenRatio));
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$RetryPlan */
    private static final class RetryPlan {
        final long backoffNanos;
        @Nullable
        final Integer hedgingPushbackMillis;
        final boolean isFatal;
        final boolean shouldRetry;

        RetryPlan(boolean z, boolean z2, long j, @Nullable Integer num) {
            this.shouldRetry = z;
            this.isFatal = z2;
            this.backoffNanos = j;
            this.hedgingPushbackMillis = num;
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$FutureCanceller */
    private static final class FutureCanceller {
        boolean cancelled;
        Future<?> future;
        final Object lock;

        FutureCanceller(Object obj) {
            this.lock = obj;
        }

        /* access modifiers changed from: package-private */
        public void setFuture(Future<?> future2) {
            synchronized (this.lock) {
                if (!this.cancelled) {
                    this.future = future2;
                }
            }
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public Future<?> markCancelled() {
            this.cancelled = true;
            return this.future;
        }

        /* access modifiers changed from: package-private */
        public boolean isCancelled() {
            return this.cancelled;
        }
    }
}

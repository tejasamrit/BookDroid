package com.google.firebase.firestore.remote;

import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Supplier;
import com.google.firestore.p009v1.FirestoreGrpc;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import p012io.grpc.CallCredentials;
import p012io.grpc.CallOptions;
import p012io.grpc.ClientCall;
import p012io.grpc.ConnectivityState;
import p012io.grpc.ManagedChannel;
import p012io.grpc.ManagedChannelBuilder;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.android.AndroidChannelBuilder;

public class GrpcCallProvider {
    private static final int CONNECTIVITY_ATTEMPT_TIMEOUT_MS = 15000;
    private static final String LOG_TAG = "GrpcCallProvider";
    private static Supplier<ManagedChannelBuilder<?>> overrideChannelBuilderSupplier;
    private final AsyncQueue asyncQueue;
    private CallOptions callOptions;
    private Task<ManagedChannel> channelTask;
    private AsyncQueue.DelayedTask connectivityAttemptTimer;
    private final Context context;
    private final DatabaseInfo databaseInfo;
    private final CallCredentials firestoreHeaders;

    public static void overrideChannelBuilder(Supplier<ManagedChannelBuilder<?>> supplier) {
        overrideChannelBuilderSupplier = supplier;
    }

    GrpcCallProvider(AsyncQueue asyncQueue2, Context context2, DatabaseInfo databaseInfo2, CallCredentials callCredentials) {
        this.asyncQueue = asyncQueue2;
        this.context = context2;
        this.databaseInfo = databaseInfo2;
        this.firestoreHeaders = callCredentials;
        initChannelTask();
    }

    private ManagedChannel initChannel(Context context2, DatabaseInfo databaseInfo2) {
        ManagedChannelBuilder<?> managedChannelBuilder;
        try {
            ProviderInstaller.installIfNeeded(context2);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IllegalStateException e) {
            Logger.warn(LOG_TAG, "Failed to update ssl context: %s", e);
        }
        Supplier<ManagedChannelBuilder<?>> supplier = overrideChannelBuilderSupplier;
        if (supplier != null) {
            managedChannelBuilder = supplier.get();
        } else {
            ManagedChannelBuilder<?> forTarget = ManagedChannelBuilder.forTarget(databaseInfo2.getHost());
            if (!databaseInfo2.isSslEnabled()) {
                forTarget.usePlaintext();
            }
            managedChannelBuilder = forTarget;
        }
        managedChannelBuilder.keepAliveTime(30, TimeUnit.SECONDS);
        return AndroidChannelBuilder.usingBuilder(managedChannelBuilder).context(context2).build();
    }

    /* access modifiers changed from: package-private */
    public <ReqT, RespT> Task<ClientCall<ReqT, RespT>> createClientCall(MethodDescriptor<ReqT, RespT> methodDescriptor) {
        return this.channelTask.continueWithTask(this.asyncQueue.getExecutor(), GrpcCallProvider$$Lambda$1.lambdaFactory$(this, methodDescriptor));
    }

    /* access modifiers changed from: package-private */
    public void shutdown() {
        try {
            ManagedChannel managedChannel = (ManagedChannel) Tasks.await(this.channelTask);
            managedChannel.shutdown();
            try {
                if (!managedChannel.awaitTermination(1, TimeUnit.SECONDS)) {
                    Logger.debug(FirestoreChannel.class.getSimpleName(), "Unable to gracefully shutdown the gRPC ManagedChannel. Will attempt an immediate shutdown.", new Object[0]);
                    managedChannel.shutdownNow();
                    if (!managedChannel.awaitTermination(60, TimeUnit.SECONDS)) {
                        Logger.warn(FirestoreChannel.class.getSimpleName(), "Unable to forcefully shutdown the gRPC ManagedChannel.", new Object[0]);
                    }
                }
            } catch (InterruptedException unused) {
                managedChannel.shutdownNow();
                Logger.warn(FirestoreChannel.class.getSimpleName(), "Interrupted while shutting down the gRPC Managed Channel", new Object[0]);
                Thread.currentThread().interrupt();
            }
        } catch (ExecutionException e) {
            Logger.warn(FirestoreChannel.class.getSimpleName(), "Channel is not initialized, shutdown will just do nothing. Channel initializing run into exception: %s", e);
        } catch (InterruptedException unused2) {
            Logger.warn(FirestoreChannel.class.getSimpleName(), "Interrupted while retrieving the gRPC Managed Channel", new Object[0]);
            Thread.currentThread().interrupt();
        }
    }

    /* access modifiers changed from: private */
    public void onConnectivityStateChange(ManagedChannel managedChannel) {
        ConnectivityState state = managedChannel.getState(true);
        Logger.debug(LOG_TAG, "Current gRPC connectivity state: " + state, new Object[0]);
        clearConnectivityAttemptTimer();
        if (state == ConnectivityState.CONNECTING) {
            Logger.debug(LOG_TAG, "Setting the connectivityAttemptTimer", new Object[0]);
            this.connectivityAttemptTimer = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.CONNECTIVITY_ATTEMPT_TIMER, 15000, GrpcCallProvider$$Lambda$2.lambdaFactory$(this, managedChannel));
        }
        managedChannel.notifyWhenStateChanged(state, GrpcCallProvider$$Lambda$3.lambdaFactory$(this, managedChannel));
    }

    static /* synthetic */ void lambda$onConnectivityStateChange$1(GrpcCallProvider grpcCallProvider, ManagedChannel managedChannel) {
        Logger.debug(LOG_TAG, "connectivityAttemptTimer elapsed. Resetting the channel.", new Object[0]);
        grpcCallProvider.clearConnectivityAttemptTimer();
        grpcCallProvider.resetChannel(managedChannel);
    }

    private void resetChannel(ManagedChannel managedChannel) {
        this.asyncQueue.enqueueAndForget(GrpcCallProvider$$Lambda$4.lambdaFactory$(this, managedChannel));
    }

    static /* synthetic */ void lambda$resetChannel$4(GrpcCallProvider grpcCallProvider, ManagedChannel managedChannel) {
        managedChannel.shutdownNow();
        grpcCallProvider.initChannelTask();
    }

    private void initChannelTask() {
        this.channelTask = Tasks.call(Executors.BACKGROUND_EXECUTOR, GrpcCallProvider$$Lambda$5.lambdaFactory$(this));
    }

    static /* synthetic */ ManagedChannel lambda$initChannelTask$6(GrpcCallProvider grpcCallProvider) throws Exception {
        ManagedChannel initChannel = grpcCallProvider.initChannel(grpcCallProvider.context, grpcCallProvider.databaseInfo);
        grpcCallProvider.asyncQueue.enqueueAndForget(GrpcCallProvider$$Lambda$6.lambdaFactory$(grpcCallProvider, initChannel));
        grpcCallProvider.callOptions = ((FirestoreGrpc.FirestoreStub) ((FirestoreGrpc.FirestoreStub) FirestoreGrpc.newStub(initChannel).withCallCredentials(grpcCallProvider.firestoreHeaders)).withExecutor(grpcCallProvider.asyncQueue.getExecutor())).getCallOptions();
        Logger.debug(LOG_TAG, "Channel successfully reset.", new Object[0]);
        return initChannel;
    }

    private void clearConnectivityAttemptTimer() {
        if (this.connectivityAttemptTimer != null) {
            Logger.debug(LOG_TAG, "Clearing the connectivityAttemptTimer", new Object[0]);
            this.connectivityAttemptTimer.cancel();
            this.connectivityAttemptTimer = null;
        }
    }
}

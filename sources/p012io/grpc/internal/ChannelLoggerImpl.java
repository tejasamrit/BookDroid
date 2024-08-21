package p012io.grpc.internal;

import com.google.common.base.Preconditions;
import java.text.MessageFormat;
import java.util.logging.Level;
import p012io.grpc.ChannelLogger;
import p012io.grpc.InternalChannelz;
import p012io.grpc.InternalLogId;

/* renamed from: io.grpc.internal.ChannelLoggerImpl */
final class ChannelLoggerImpl extends ChannelLogger {
    private final TimeProvider time;
    private final ChannelTracer tracer;

    ChannelLoggerImpl(ChannelTracer channelTracer, TimeProvider timeProvider) {
        this.tracer = (ChannelTracer) Preconditions.checkNotNull(channelTracer, "tracer");
        this.time = (TimeProvider) Preconditions.checkNotNull(timeProvider, "time");
    }

    public void log(ChannelLogger.ChannelLogLevel channelLogLevel, String str) {
        logOnly(this.tracer.getLogId(), channelLogLevel, str);
        if (isTraceable(channelLogLevel)) {
            trace(channelLogLevel, str);
        }
    }

    public void log(ChannelLogger.ChannelLogLevel channelLogLevel, String str, Object... objArr) {
        String str2;
        Level javaLogLevel = toJavaLogLevel(channelLogLevel);
        if (isTraceable(channelLogLevel) || ChannelTracer.logger.isLoggable(javaLogLevel)) {
            str2 = MessageFormat.format(str, objArr);
        } else {
            str2 = null;
        }
        log(channelLogLevel, str2);
    }

    static void logOnly(InternalLogId internalLogId, ChannelLogger.ChannelLogLevel channelLogLevel, String str) {
        Level javaLogLevel = toJavaLogLevel(channelLogLevel);
        if (ChannelTracer.logger.isLoggable(javaLogLevel)) {
            ChannelTracer.logOnly(internalLogId, javaLogLevel, str);
        }
    }

    static void logOnly(InternalLogId internalLogId, ChannelLogger.ChannelLogLevel channelLogLevel, String str, Object... objArr) {
        Level javaLogLevel = toJavaLogLevel(channelLogLevel);
        if (ChannelTracer.logger.isLoggable(javaLogLevel)) {
            ChannelTracer.logOnly(internalLogId, javaLogLevel, MessageFormat.format(str, objArr));
        }
    }

    private boolean isTraceable(ChannelLogger.ChannelLogLevel channelLogLevel) {
        return channelLogLevel != ChannelLogger.ChannelLogLevel.DEBUG && this.tracer.isTraceEnabled();
    }

    private void trace(ChannelLogger.ChannelLogLevel channelLogLevel, String str) {
        if (channelLogLevel != ChannelLogger.ChannelLogLevel.DEBUG) {
            this.tracer.traceOnly(new InternalChannelz.ChannelTrace.Event.Builder().setDescription(str).setSeverity(toTracerSeverity(channelLogLevel)).setTimestampNanos(this.time.currentTimeNanos()).build());
        }
    }

    /* renamed from: io.grpc.internal.ChannelLoggerImpl$1 */
    static /* synthetic */ class C23921 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                io.grpc.ChannelLogger$ChannelLogLevel[] r0 = p012io.grpc.ChannelLogger.ChannelLogLevel.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel = r0
                io.grpc.ChannelLogger$ChannelLogLevel r1 = p012io.grpc.ChannelLogger.ChannelLogLevel.ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel     // Catch:{ NoSuchFieldError -> 0x001d }
                io.grpc.ChannelLogger$ChannelLogLevel r1 = p012io.grpc.ChannelLogger.ChannelLogLevel.WARNING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.ChannelLoggerImpl.C23921.<clinit>():void");
        }
    }

    private static InternalChannelz.ChannelTrace.Event.Severity toTracerSeverity(ChannelLogger.ChannelLogLevel channelLogLevel) {
        int i = C23921.$SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel[channelLogLevel.ordinal()];
        if (i == 1) {
            return InternalChannelz.ChannelTrace.Event.Severity.CT_ERROR;
        }
        if (i != 2) {
            return InternalChannelz.ChannelTrace.Event.Severity.CT_INFO;
        }
        return InternalChannelz.ChannelTrace.Event.Severity.CT_WARNING;
    }

    private static Level toJavaLogLevel(ChannelLogger.ChannelLogLevel channelLogLevel) {
        int i = C23921.$SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel[channelLogLevel.ordinal()];
        if (i == 1) {
            return Level.FINE;
        }
        if (i != 2) {
            return Level.FINEST;
        }
        return Level.FINER;
    }
}

package com.google.android.datatransport.cct.internal;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.IOException;

public final class AutoBatchedLogRequestEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoBatchedLogRequestEncoder();

    private AutoBatchedLogRequestEncoder() {
    }

    public void configure(EncoderConfig<?> encoderConfig) {
        encoderConfig.registerEncoder((Class<U>) BatchedLogRequest.class, (ObjectEncoder<? super U>) BatchedLogRequestEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_BatchedLogRequest.class, (ObjectEncoder<? super U>) BatchedLogRequestEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) LogRequest.class, (ObjectEncoder<? super U>) LogRequestEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_LogRequest.class, (ObjectEncoder<? super U>) LogRequestEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) ClientInfo.class, (ObjectEncoder<? super U>) ClientInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_ClientInfo.class, (ObjectEncoder<? super U>) ClientInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AndroidClientInfo.class, (ObjectEncoder<? super U>) AndroidClientInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_AndroidClientInfo.class, (ObjectEncoder<? super U>) AndroidClientInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) LogEvent.class, (ObjectEncoder<? super U>) LogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_LogEvent.class, (ObjectEncoder<? super U>) LogEventEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) NetworkConnectionInfo.class, (ObjectEncoder<? super U>) NetworkConnectionInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_NetworkConnectionInfo.class, (ObjectEncoder<? super U>) NetworkConnectionInfoEncoder.INSTANCE);
    }

    private static final class BatchedLogRequestEncoder implements ObjectEncoder<BatchedLogRequest> {
        static final BatchedLogRequestEncoder INSTANCE = new BatchedLogRequestEncoder();
        private static final FieldDescriptor LOGREQUEST_DESCRIPTOR = FieldDescriptor.m244of("logRequest");

        private BatchedLogRequestEncoder() {
        }

        public void encode(BatchedLogRequest batchedLogRequest, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(LOGREQUEST_DESCRIPTOR, (Object) batchedLogRequest.getLogRequests());
        }
    }

    private static final class LogRequestEncoder implements ObjectEncoder<LogRequest> {
        private static final FieldDescriptor CLIENTINFO_DESCRIPTOR = FieldDescriptor.m244of("clientInfo");
        static final LogRequestEncoder INSTANCE = new LogRequestEncoder();
        private static final FieldDescriptor LOGEVENT_DESCRIPTOR = FieldDescriptor.m244of("logEvent");
        private static final FieldDescriptor LOGSOURCENAME_DESCRIPTOR = FieldDescriptor.m244of("logSourceName");
        private static final FieldDescriptor LOGSOURCE_DESCRIPTOR = FieldDescriptor.m244of("logSource");
        private static final FieldDescriptor QOSTIER_DESCRIPTOR = FieldDescriptor.m244of("qosTier");
        private static final FieldDescriptor REQUESTTIMEMS_DESCRIPTOR = FieldDescriptor.m244of("requestTimeMs");
        private static final FieldDescriptor REQUESTUPTIMEMS_DESCRIPTOR = FieldDescriptor.m244of("requestUptimeMs");

        private LogRequestEncoder() {
        }

        public void encode(LogRequest logRequest, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(REQUESTTIMEMS_DESCRIPTOR, logRequest.getRequestTimeMs());
            objectEncoderContext.add(REQUESTUPTIMEMS_DESCRIPTOR, logRequest.getRequestUptimeMs());
            objectEncoderContext.add(CLIENTINFO_DESCRIPTOR, (Object) logRequest.getClientInfo());
            objectEncoderContext.add(LOGSOURCE_DESCRIPTOR, (Object) logRequest.getLogSource());
            objectEncoderContext.add(LOGSOURCENAME_DESCRIPTOR, (Object) logRequest.getLogSourceName());
            objectEncoderContext.add(LOGEVENT_DESCRIPTOR, (Object) logRequest.getLogEvents());
            objectEncoderContext.add(QOSTIER_DESCRIPTOR, (Object) logRequest.getQosTier());
        }
    }

    private static final class ClientInfoEncoder implements ObjectEncoder<ClientInfo> {
        private static final FieldDescriptor ANDROIDCLIENTINFO_DESCRIPTOR = FieldDescriptor.m244of("androidClientInfo");
        private static final FieldDescriptor CLIENTTYPE_DESCRIPTOR = FieldDescriptor.m244of("clientType");
        static final ClientInfoEncoder INSTANCE = new ClientInfoEncoder();

        private ClientInfoEncoder() {
        }

        public void encode(ClientInfo clientInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(CLIENTTYPE_DESCRIPTOR, (Object) clientInfo.getClientType());
            objectEncoderContext.add(ANDROIDCLIENTINFO_DESCRIPTOR, (Object) clientInfo.getAndroidClientInfo());
        }
    }

    private static final class AndroidClientInfoEncoder implements ObjectEncoder<AndroidClientInfo> {
        private static final FieldDescriptor APPLICATIONBUILD_DESCRIPTOR = FieldDescriptor.m244of("applicationBuild");
        private static final FieldDescriptor COUNTRY_DESCRIPTOR = FieldDescriptor.m244of("country");
        private static final FieldDescriptor DEVICE_DESCRIPTOR = FieldDescriptor.m244of("device");
        private static final FieldDescriptor FINGERPRINT_DESCRIPTOR = FieldDescriptor.m244of("fingerprint");
        private static final FieldDescriptor HARDWARE_DESCRIPTOR = FieldDescriptor.m244of("hardware");
        static final AndroidClientInfoEncoder INSTANCE = new AndroidClientInfoEncoder();
        private static final FieldDescriptor LOCALE_DESCRIPTOR = FieldDescriptor.m244of("locale");
        private static final FieldDescriptor MANUFACTURER_DESCRIPTOR = FieldDescriptor.m244of("manufacturer");
        private static final FieldDescriptor MCCMNC_DESCRIPTOR = FieldDescriptor.m244of("mccMnc");
        private static final FieldDescriptor MODEL_DESCRIPTOR = FieldDescriptor.m244of("model");
        private static final FieldDescriptor OSBUILD_DESCRIPTOR = FieldDescriptor.m244of("osBuild");
        private static final FieldDescriptor PRODUCT_DESCRIPTOR = FieldDescriptor.m244of("product");
        private static final FieldDescriptor SDKVERSION_DESCRIPTOR = FieldDescriptor.m244of("sdkVersion");

        private AndroidClientInfoEncoder() {
        }

        public void encode(AndroidClientInfo androidClientInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(SDKVERSION_DESCRIPTOR, (Object) androidClientInfo.getSdkVersion());
            objectEncoderContext.add(MODEL_DESCRIPTOR, (Object) androidClientInfo.getModel());
            objectEncoderContext.add(HARDWARE_DESCRIPTOR, (Object) androidClientInfo.getHardware());
            objectEncoderContext.add(DEVICE_DESCRIPTOR, (Object) androidClientInfo.getDevice());
            objectEncoderContext.add(PRODUCT_DESCRIPTOR, (Object) androidClientInfo.getProduct());
            objectEncoderContext.add(OSBUILD_DESCRIPTOR, (Object) androidClientInfo.getOsBuild());
            objectEncoderContext.add(MANUFACTURER_DESCRIPTOR, (Object) androidClientInfo.getManufacturer());
            objectEncoderContext.add(FINGERPRINT_DESCRIPTOR, (Object) androidClientInfo.getFingerprint());
            objectEncoderContext.add(LOCALE_DESCRIPTOR, (Object) androidClientInfo.getLocale());
            objectEncoderContext.add(COUNTRY_DESCRIPTOR, (Object) androidClientInfo.getCountry());
            objectEncoderContext.add(MCCMNC_DESCRIPTOR, (Object) androidClientInfo.getMccMnc());
            objectEncoderContext.add(APPLICATIONBUILD_DESCRIPTOR, (Object) androidClientInfo.getApplicationBuild());
        }
    }

    private static final class LogEventEncoder implements ObjectEncoder<LogEvent> {
        private static final FieldDescriptor EVENTCODE_DESCRIPTOR = FieldDescriptor.m244of("eventCode");
        private static final FieldDescriptor EVENTTIMEMS_DESCRIPTOR = FieldDescriptor.m244of("eventTimeMs");
        private static final FieldDescriptor EVENTUPTIMEMS_DESCRIPTOR = FieldDescriptor.m244of("eventUptimeMs");
        static final LogEventEncoder INSTANCE = new LogEventEncoder();
        private static final FieldDescriptor NETWORKCONNECTIONINFO_DESCRIPTOR = FieldDescriptor.m244of("networkConnectionInfo");
        private static final FieldDescriptor SOURCEEXTENSIONJSONPROTO3_DESCRIPTOR = FieldDescriptor.m244of("sourceExtensionJsonProto3");
        private static final FieldDescriptor SOURCEEXTENSION_DESCRIPTOR = FieldDescriptor.m244of("sourceExtension");
        private static final FieldDescriptor TIMEZONEOFFSETSECONDS_DESCRIPTOR = FieldDescriptor.m244of("timezoneOffsetSeconds");

        private LogEventEncoder() {
        }

        public void encode(LogEvent logEvent, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(EVENTTIMEMS_DESCRIPTOR, logEvent.getEventTimeMs());
            objectEncoderContext.add(EVENTCODE_DESCRIPTOR, (Object) logEvent.getEventCode());
            objectEncoderContext.add(EVENTUPTIMEMS_DESCRIPTOR, logEvent.getEventUptimeMs());
            objectEncoderContext.add(SOURCEEXTENSION_DESCRIPTOR, (Object) logEvent.getSourceExtension());
            objectEncoderContext.add(SOURCEEXTENSIONJSONPROTO3_DESCRIPTOR, (Object) logEvent.getSourceExtensionJsonProto3());
            objectEncoderContext.add(TIMEZONEOFFSETSECONDS_DESCRIPTOR, logEvent.getTimezoneOffsetSeconds());
            objectEncoderContext.add(NETWORKCONNECTIONINFO_DESCRIPTOR, (Object) logEvent.getNetworkConnectionInfo());
        }
    }

    private static final class NetworkConnectionInfoEncoder implements ObjectEncoder<NetworkConnectionInfo> {
        static final NetworkConnectionInfoEncoder INSTANCE = new NetworkConnectionInfoEncoder();
        private static final FieldDescriptor MOBILESUBTYPE_DESCRIPTOR = FieldDescriptor.m244of("mobileSubtype");
        private static final FieldDescriptor NETWORKTYPE_DESCRIPTOR = FieldDescriptor.m244of("networkType");

        private NetworkConnectionInfoEncoder() {
        }

        public void encode(NetworkConnectionInfo networkConnectionInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(NETWORKTYPE_DESCRIPTOR, (Object) networkConnectionInfo.getNetworkType());
            objectEncoderContext.add(MOBILESUBTYPE_DESCRIPTOR, (Object) networkConnectionInfo.getMobileSubtype());
        }
    }
}

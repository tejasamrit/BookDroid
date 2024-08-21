package com.google.api;

import com.google.api.Authentication;
import com.google.api.Backend;
import com.google.api.Billing;
import com.google.api.Context;
import com.google.api.Control;
import com.google.api.Documentation;
import com.google.api.Endpoint;
import com.google.api.Http;
import com.google.api.LogDescriptor;
import com.google.api.Logging;
import com.google.api.MetricDescriptor;
import com.google.api.MonitoredResourceDescriptor;
import com.google.api.Monitoring;
import com.google.api.Quota;
import com.google.api.SourceInfo;
import com.google.api.SystemParameters;
import com.google.api.Usage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Api;
import com.google.protobuf.ApiOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Enum;
import com.google.protobuf.EnumOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Type;
import com.google.protobuf.TypeOrBuilder;
import com.google.protobuf.UInt32Value;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class Service extends GeneratedMessageLite<Service, Builder> implements ServiceOrBuilder {
    public static final int APIS_FIELD_NUMBER = 3;
    public static final int AUTHENTICATION_FIELD_NUMBER = 11;
    public static final int BACKEND_FIELD_NUMBER = 8;
    public static final int BILLING_FIELD_NUMBER = 26;
    public static final int CONFIG_VERSION_FIELD_NUMBER = 20;
    public static final int CONTEXT_FIELD_NUMBER = 12;
    public static final int CONTROL_FIELD_NUMBER = 21;
    /* access modifiers changed from: private */
    public static final Service DEFAULT_INSTANCE;
    public static final int DOCUMENTATION_FIELD_NUMBER = 6;
    public static final int ENDPOINTS_FIELD_NUMBER = 18;
    public static final int ENUMS_FIELD_NUMBER = 5;
    public static final int HTTP_FIELD_NUMBER = 9;
    public static final int ID_FIELD_NUMBER = 33;
    public static final int LOGGING_FIELD_NUMBER = 27;
    public static final int LOGS_FIELD_NUMBER = 23;
    public static final int METRICS_FIELD_NUMBER = 24;
    public static final int MONITORED_RESOURCES_FIELD_NUMBER = 25;
    public static final int MONITORING_FIELD_NUMBER = 28;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<Service> PARSER = null;
    public static final int PRODUCER_PROJECT_ID_FIELD_NUMBER = 22;
    public static final int QUOTA_FIELD_NUMBER = 10;
    public static final int SOURCE_INFO_FIELD_NUMBER = 37;
    public static final int SYSTEM_PARAMETERS_FIELD_NUMBER = 29;
    public static final int TITLE_FIELD_NUMBER = 2;
    public static final int TYPES_FIELD_NUMBER = 4;
    public static final int USAGE_FIELD_NUMBER = 15;
    private Internal.ProtobufList<Api> apis_ = emptyProtobufList();
    private Authentication authentication_;
    private Backend backend_;
    private Billing billing_;
    private UInt32Value configVersion_;
    private Context context_;
    private Control control_;
    private Documentation documentation_;
    private Internal.ProtobufList<Endpoint> endpoints_ = emptyProtobufList();
    private Internal.ProtobufList<Enum> enums_ = emptyProtobufList();
    private Http http_;
    private String id_ = "";
    private Logging logging_;
    private Internal.ProtobufList<LogDescriptor> logs_ = emptyProtobufList();
    private Internal.ProtobufList<MetricDescriptor> metrics_ = emptyProtobufList();
    private Internal.ProtobufList<MonitoredResourceDescriptor> monitoredResources_ = emptyProtobufList();
    private Monitoring monitoring_;
    private String name_ = "";
    private String producerProjectId_ = "";
    private Quota quota_;
    private SourceInfo sourceInfo_;
    private SystemParameters systemParameters_;
    private String title_ = "";
    private Internal.ProtobufList<Type> types_ = emptyProtobufList();
    private Usage usage_;

    private Service() {
    }

    public boolean hasConfigVersion() {
        return this.configVersion_ != null;
    }

    public UInt32Value getConfigVersion() {
        UInt32Value uInt32Value = this.configVersion_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    /* access modifiers changed from: private */
    public void setConfigVersion(UInt32Value uInt32Value) {
        uInt32Value.getClass();
        this.configVersion_ = uInt32Value;
    }

    /* access modifiers changed from: private */
    public void mergeConfigVersion(UInt32Value uInt32Value) {
        uInt32Value.getClass();
        UInt32Value uInt32Value2 = this.configVersion_;
        if (uInt32Value2 == null || uInt32Value2 == UInt32Value.getDefaultInstance()) {
            this.configVersion_ = uInt32Value;
        } else {
            this.configVersion_ = (UInt32Value) ((UInt32Value.Builder) UInt32Value.newBuilder(this.configVersion_).mergeFrom(uInt32Value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearConfigVersion() {
        this.configVersion_ = null;
    }

    public String getName() {
        return this.name_;
    }

    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.name_ = str;
    }

    /* access modifiers changed from: private */
    public void clearName() {
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.name_ = byteString.toStringUtf8();
    }

    public String getId() {
        return this.id_;
    }

    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    /* access modifiers changed from: private */
    public void setId(String str) {
        str.getClass();
        this.id_ = str;
    }

    /* access modifiers changed from: private */
    public void clearId() {
        this.id_ = getDefaultInstance().getId();
    }

    /* access modifiers changed from: private */
    public void setIdBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.id_ = byteString.toStringUtf8();
    }

    public String getTitle() {
        return this.title_;
    }

    public ByteString getTitleBytes() {
        return ByteString.copyFromUtf8(this.title_);
    }

    /* access modifiers changed from: private */
    public void setTitle(String str) {
        str.getClass();
        this.title_ = str;
    }

    /* access modifiers changed from: private */
    public void clearTitle() {
        this.title_ = getDefaultInstance().getTitle();
    }

    /* access modifiers changed from: private */
    public void setTitleBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.title_ = byteString.toStringUtf8();
    }

    public String getProducerProjectId() {
        return this.producerProjectId_;
    }

    public ByteString getProducerProjectIdBytes() {
        return ByteString.copyFromUtf8(this.producerProjectId_);
    }

    /* access modifiers changed from: private */
    public void setProducerProjectId(String str) {
        str.getClass();
        this.producerProjectId_ = str;
    }

    /* access modifiers changed from: private */
    public void clearProducerProjectId() {
        this.producerProjectId_ = getDefaultInstance().getProducerProjectId();
    }

    /* access modifiers changed from: private */
    public void setProducerProjectIdBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.producerProjectId_ = byteString.toStringUtf8();
    }

    public List<Api> getApisList() {
        return this.apis_;
    }

    public List<? extends ApiOrBuilder> getApisOrBuilderList() {
        return this.apis_;
    }

    public int getApisCount() {
        return this.apis_.size();
    }

    public Api getApis(int i) {
        return (Api) this.apis_.get(i);
    }

    public ApiOrBuilder getApisOrBuilder(int i) {
        return (ApiOrBuilder) this.apis_.get(i);
    }

    private void ensureApisIsMutable() {
        Internal.ProtobufList<Api> protobufList = this.apis_;
        if (!protobufList.isModifiable()) {
            this.apis_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setApis(int i, Api api) {
        api.getClass();
        ensureApisIsMutable();
        this.apis_.set(i, api);
    }

    /* access modifiers changed from: private */
    public void addApis(Api api) {
        api.getClass();
        ensureApisIsMutable();
        this.apis_.add(api);
    }

    /* access modifiers changed from: private */
    public void addApis(int i, Api api) {
        api.getClass();
        ensureApisIsMutable();
        this.apis_.add(i, api);
    }

    /* access modifiers changed from: private */
    public void addAllApis(Iterable<? extends Api> iterable) {
        ensureApisIsMutable();
        AbstractMessageLite.addAll(iterable, this.apis_);
    }

    /* access modifiers changed from: private */
    public void clearApis() {
        this.apis_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeApis(int i) {
        ensureApisIsMutable();
        this.apis_.remove(i);
    }

    public List<Type> getTypesList() {
        return this.types_;
    }

    public List<? extends TypeOrBuilder> getTypesOrBuilderList() {
        return this.types_;
    }

    public int getTypesCount() {
        return this.types_.size();
    }

    public Type getTypes(int i) {
        return (Type) this.types_.get(i);
    }

    public TypeOrBuilder getTypesOrBuilder(int i) {
        return (TypeOrBuilder) this.types_.get(i);
    }

    private void ensureTypesIsMutable() {
        Internal.ProtobufList<Type> protobufList = this.types_;
        if (!protobufList.isModifiable()) {
            this.types_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setTypes(int i, Type type) {
        type.getClass();
        ensureTypesIsMutable();
        this.types_.set(i, type);
    }

    /* access modifiers changed from: private */
    public void addTypes(Type type) {
        type.getClass();
        ensureTypesIsMutable();
        this.types_.add(type);
    }

    /* access modifiers changed from: private */
    public void addTypes(int i, Type type) {
        type.getClass();
        ensureTypesIsMutable();
        this.types_.add(i, type);
    }

    /* access modifiers changed from: private */
    public void addAllTypes(Iterable<? extends Type> iterable) {
        ensureTypesIsMutable();
        AbstractMessageLite.addAll(iterable, this.types_);
    }

    /* access modifiers changed from: private */
    public void clearTypes() {
        this.types_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeTypes(int i) {
        ensureTypesIsMutable();
        this.types_.remove(i);
    }

    public List<Enum> getEnumsList() {
        return this.enums_;
    }

    public List<? extends EnumOrBuilder> getEnumsOrBuilderList() {
        return this.enums_;
    }

    public int getEnumsCount() {
        return this.enums_.size();
    }

    public Enum getEnums(int i) {
        return (Enum) this.enums_.get(i);
    }

    public EnumOrBuilder getEnumsOrBuilder(int i) {
        return (EnumOrBuilder) this.enums_.get(i);
    }

    private void ensureEnumsIsMutable() {
        Internal.ProtobufList<Enum> protobufList = this.enums_;
        if (!protobufList.isModifiable()) {
            this.enums_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setEnums(int i, Enum enumR) {
        enumR.getClass();
        ensureEnumsIsMutable();
        this.enums_.set(i, enumR);
    }

    /* access modifiers changed from: private */
    public void addEnums(Enum enumR) {
        enumR.getClass();
        ensureEnumsIsMutable();
        this.enums_.add(enumR);
    }

    /* access modifiers changed from: private */
    public void addEnums(int i, Enum enumR) {
        enumR.getClass();
        ensureEnumsIsMutable();
        this.enums_.add(i, enumR);
    }

    /* access modifiers changed from: private */
    public void addAllEnums(Iterable<? extends Enum> iterable) {
        ensureEnumsIsMutable();
        AbstractMessageLite.addAll(iterable, this.enums_);
    }

    /* access modifiers changed from: private */
    public void clearEnums() {
        this.enums_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeEnums(int i) {
        ensureEnumsIsMutable();
        this.enums_.remove(i);
    }

    public boolean hasDocumentation() {
        return this.documentation_ != null;
    }

    public Documentation getDocumentation() {
        Documentation documentation = this.documentation_;
        return documentation == null ? Documentation.getDefaultInstance() : documentation;
    }

    /* access modifiers changed from: private */
    public void setDocumentation(Documentation documentation) {
        documentation.getClass();
        this.documentation_ = documentation;
    }

    /* access modifiers changed from: private */
    public void mergeDocumentation(Documentation documentation) {
        documentation.getClass();
        Documentation documentation2 = this.documentation_;
        if (documentation2 == null || documentation2 == Documentation.getDefaultInstance()) {
            this.documentation_ = documentation;
        } else {
            this.documentation_ = (Documentation) ((Documentation.Builder) Documentation.newBuilder(this.documentation_).mergeFrom(documentation)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearDocumentation() {
        this.documentation_ = null;
    }

    public boolean hasBackend() {
        return this.backend_ != null;
    }

    public Backend getBackend() {
        Backend backend = this.backend_;
        return backend == null ? Backend.getDefaultInstance() : backend;
    }

    /* access modifiers changed from: private */
    public void setBackend(Backend backend) {
        backend.getClass();
        this.backend_ = backend;
    }

    /* access modifiers changed from: private */
    public void mergeBackend(Backend backend) {
        backend.getClass();
        Backend backend2 = this.backend_;
        if (backend2 == null || backend2 == Backend.getDefaultInstance()) {
            this.backend_ = backend;
        } else {
            this.backend_ = (Backend) ((Backend.Builder) Backend.newBuilder(this.backend_).mergeFrom(backend)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearBackend() {
        this.backend_ = null;
    }

    public boolean hasHttp() {
        return this.http_ != null;
    }

    public Http getHttp() {
        Http http = this.http_;
        return http == null ? Http.getDefaultInstance() : http;
    }

    /* access modifiers changed from: private */
    public void setHttp(Http http) {
        http.getClass();
        this.http_ = http;
    }

    /* access modifiers changed from: private */
    public void mergeHttp(Http http) {
        http.getClass();
        Http http2 = this.http_;
        if (http2 == null || http2 == Http.getDefaultInstance()) {
            this.http_ = http;
        } else {
            this.http_ = (Http) ((Http.Builder) Http.newBuilder(this.http_).mergeFrom(http)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearHttp() {
        this.http_ = null;
    }

    public boolean hasQuota() {
        return this.quota_ != null;
    }

    public Quota getQuota() {
        Quota quota = this.quota_;
        return quota == null ? Quota.getDefaultInstance() : quota;
    }

    /* access modifiers changed from: private */
    public void setQuota(Quota quota) {
        quota.getClass();
        this.quota_ = quota;
    }

    /* access modifiers changed from: private */
    public void mergeQuota(Quota quota) {
        quota.getClass();
        Quota quota2 = this.quota_;
        if (quota2 == null || quota2 == Quota.getDefaultInstance()) {
            this.quota_ = quota;
        } else {
            this.quota_ = (Quota) ((Quota.Builder) Quota.newBuilder(this.quota_).mergeFrom(quota)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearQuota() {
        this.quota_ = null;
    }

    public boolean hasAuthentication() {
        return this.authentication_ != null;
    }

    public Authentication getAuthentication() {
        Authentication authentication = this.authentication_;
        return authentication == null ? Authentication.getDefaultInstance() : authentication;
    }

    /* access modifiers changed from: private */
    public void setAuthentication(Authentication authentication) {
        authentication.getClass();
        this.authentication_ = authentication;
    }

    /* access modifiers changed from: private */
    public void mergeAuthentication(Authentication authentication) {
        authentication.getClass();
        Authentication authentication2 = this.authentication_;
        if (authentication2 == null || authentication2 == Authentication.getDefaultInstance()) {
            this.authentication_ = authentication;
        } else {
            this.authentication_ = (Authentication) ((Authentication.Builder) Authentication.newBuilder(this.authentication_).mergeFrom(authentication)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearAuthentication() {
        this.authentication_ = null;
    }

    public boolean hasContext() {
        return this.context_ != null;
    }

    public Context getContext() {
        Context context = this.context_;
        return context == null ? Context.getDefaultInstance() : context;
    }

    /* access modifiers changed from: private */
    public void setContext(Context context) {
        context.getClass();
        this.context_ = context;
    }

    /* access modifiers changed from: private */
    public void mergeContext(Context context) {
        context.getClass();
        Context context2 = this.context_;
        if (context2 == null || context2 == Context.getDefaultInstance()) {
            this.context_ = context;
        } else {
            this.context_ = (Context) ((Context.Builder) Context.newBuilder(this.context_).mergeFrom(context)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearContext() {
        this.context_ = null;
    }

    public boolean hasUsage() {
        return this.usage_ != null;
    }

    public Usage getUsage() {
        Usage usage = this.usage_;
        return usage == null ? Usage.getDefaultInstance() : usage;
    }

    /* access modifiers changed from: private */
    public void setUsage(Usage usage) {
        usage.getClass();
        this.usage_ = usage;
    }

    /* access modifiers changed from: private */
    public void mergeUsage(Usage usage) {
        usage.getClass();
        Usage usage2 = this.usage_;
        if (usage2 == null || usage2 == Usage.getDefaultInstance()) {
            this.usage_ = usage;
        } else {
            this.usage_ = (Usage) ((Usage.Builder) Usage.newBuilder(this.usage_).mergeFrom(usage)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearUsage() {
        this.usage_ = null;
    }

    public List<Endpoint> getEndpointsList() {
        return this.endpoints_;
    }

    public List<? extends EndpointOrBuilder> getEndpointsOrBuilderList() {
        return this.endpoints_;
    }

    public int getEndpointsCount() {
        return this.endpoints_.size();
    }

    public Endpoint getEndpoints(int i) {
        return (Endpoint) this.endpoints_.get(i);
    }

    public EndpointOrBuilder getEndpointsOrBuilder(int i) {
        return (EndpointOrBuilder) this.endpoints_.get(i);
    }

    private void ensureEndpointsIsMutable() {
        Internal.ProtobufList<Endpoint> protobufList = this.endpoints_;
        if (!protobufList.isModifiable()) {
            this.endpoints_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setEndpoints(int i, Endpoint endpoint) {
        endpoint.getClass();
        ensureEndpointsIsMutable();
        this.endpoints_.set(i, endpoint);
    }

    /* access modifiers changed from: private */
    public void addEndpoints(Endpoint endpoint) {
        endpoint.getClass();
        ensureEndpointsIsMutable();
        this.endpoints_.add(endpoint);
    }

    /* access modifiers changed from: private */
    public void addEndpoints(int i, Endpoint endpoint) {
        endpoint.getClass();
        ensureEndpointsIsMutable();
        this.endpoints_.add(i, endpoint);
    }

    /* access modifiers changed from: private */
    public void addAllEndpoints(Iterable<? extends Endpoint> iterable) {
        ensureEndpointsIsMutable();
        AbstractMessageLite.addAll(iterable, this.endpoints_);
    }

    /* access modifiers changed from: private */
    public void clearEndpoints() {
        this.endpoints_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeEndpoints(int i) {
        ensureEndpointsIsMutable();
        this.endpoints_.remove(i);
    }

    public boolean hasControl() {
        return this.control_ != null;
    }

    public Control getControl() {
        Control control = this.control_;
        return control == null ? Control.getDefaultInstance() : control;
    }

    /* access modifiers changed from: private */
    public void setControl(Control control) {
        control.getClass();
        this.control_ = control;
    }

    /* access modifiers changed from: private */
    public void mergeControl(Control control) {
        control.getClass();
        Control control2 = this.control_;
        if (control2 == null || control2 == Control.getDefaultInstance()) {
            this.control_ = control;
        } else {
            this.control_ = (Control) ((Control.Builder) Control.newBuilder(this.control_).mergeFrom(control)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearControl() {
        this.control_ = null;
    }

    public List<LogDescriptor> getLogsList() {
        return this.logs_;
    }

    public List<? extends LogDescriptorOrBuilder> getLogsOrBuilderList() {
        return this.logs_;
    }

    public int getLogsCount() {
        return this.logs_.size();
    }

    public LogDescriptor getLogs(int i) {
        return (LogDescriptor) this.logs_.get(i);
    }

    public LogDescriptorOrBuilder getLogsOrBuilder(int i) {
        return (LogDescriptorOrBuilder) this.logs_.get(i);
    }

    private void ensureLogsIsMutable() {
        Internal.ProtobufList<LogDescriptor> protobufList = this.logs_;
        if (!protobufList.isModifiable()) {
            this.logs_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setLogs(int i, LogDescriptor logDescriptor) {
        logDescriptor.getClass();
        ensureLogsIsMutable();
        this.logs_.set(i, logDescriptor);
    }

    /* access modifiers changed from: private */
    public void addLogs(LogDescriptor logDescriptor) {
        logDescriptor.getClass();
        ensureLogsIsMutable();
        this.logs_.add(logDescriptor);
    }

    /* access modifiers changed from: private */
    public void addLogs(int i, LogDescriptor logDescriptor) {
        logDescriptor.getClass();
        ensureLogsIsMutable();
        this.logs_.add(i, logDescriptor);
    }

    /* access modifiers changed from: private */
    public void addAllLogs(Iterable<? extends LogDescriptor> iterable) {
        ensureLogsIsMutable();
        AbstractMessageLite.addAll(iterable, this.logs_);
    }

    /* access modifiers changed from: private */
    public void clearLogs() {
        this.logs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeLogs(int i) {
        ensureLogsIsMutable();
        this.logs_.remove(i);
    }

    public List<MetricDescriptor> getMetricsList() {
        return this.metrics_;
    }

    public List<? extends MetricDescriptorOrBuilder> getMetricsOrBuilderList() {
        return this.metrics_;
    }

    public int getMetricsCount() {
        return this.metrics_.size();
    }

    public MetricDescriptor getMetrics(int i) {
        return (MetricDescriptor) this.metrics_.get(i);
    }

    public MetricDescriptorOrBuilder getMetricsOrBuilder(int i) {
        return (MetricDescriptorOrBuilder) this.metrics_.get(i);
    }

    private void ensureMetricsIsMutable() {
        Internal.ProtobufList<MetricDescriptor> protobufList = this.metrics_;
        if (!protobufList.isModifiable()) {
            this.metrics_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setMetrics(int i, MetricDescriptor metricDescriptor) {
        metricDescriptor.getClass();
        ensureMetricsIsMutable();
        this.metrics_.set(i, metricDescriptor);
    }

    /* access modifiers changed from: private */
    public void addMetrics(MetricDescriptor metricDescriptor) {
        metricDescriptor.getClass();
        ensureMetricsIsMutable();
        this.metrics_.add(metricDescriptor);
    }

    /* access modifiers changed from: private */
    public void addMetrics(int i, MetricDescriptor metricDescriptor) {
        metricDescriptor.getClass();
        ensureMetricsIsMutable();
        this.metrics_.add(i, metricDescriptor);
    }

    /* access modifiers changed from: private */
    public void addAllMetrics(Iterable<? extends MetricDescriptor> iterable) {
        ensureMetricsIsMutable();
        AbstractMessageLite.addAll(iterable, this.metrics_);
    }

    /* access modifiers changed from: private */
    public void clearMetrics() {
        this.metrics_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeMetrics(int i) {
        ensureMetricsIsMutable();
        this.metrics_.remove(i);
    }

    public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
        return this.monitoredResources_;
    }

    public List<? extends MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesOrBuilderList() {
        return this.monitoredResources_;
    }

    public int getMonitoredResourcesCount() {
        return this.monitoredResources_.size();
    }

    public MonitoredResourceDescriptor getMonitoredResources(int i) {
        return (MonitoredResourceDescriptor) this.monitoredResources_.get(i);
    }

    public MonitoredResourceDescriptorOrBuilder getMonitoredResourcesOrBuilder(int i) {
        return (MonitoredResourceDescriptorOrBuilder) this.monitoredResources_.get(i);
    }

    private void ensureMonitoredResourcesIsMutable() {
        Internal.ProtobufList<MonitoredResourceDescriptor> protobufList = this.monitoredResources_;
        if (!protobufList.isModifiable()) {
            this.monitoredResources_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    /* access modifiers changed from: private */
    public void setMonitoredResources(int i, MonitoredResourceDescriptor monitoredResourceDescriptor) {
        monitoredResourceDescriptor.getClass();
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.set(i, monitoredResourceDescriptor);
    }

    /* access modifiers changed from: private */
    public void addMonitoredResources(MonitoredResourceDescriptor monitoredResourceDescriptor) {
        monitoredResourceDescriptor.getClass();
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.add(monitoredResourceDescriptor);
    }

    /* access modifiers changed from: private */
    public void addMonitoredResources(int i, MonitoredResourceDescriptor monitoredResourceDescriptor) {
        monitoredResourceDescriptor.getClass();
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.add(i, monitoredResourceDescriptor);
    }

    /* access modifiers changed from: private */
    public void addAllMonitoredResources(Iterable<? extends MonitoredResourceDescriptor> iterable) {
        ensureMonitoredResourcesIsMutable();
        AbstractMessageLite.addAll(iterable, this.monitoredResources_);
    }

    /* access modifiers changed from: private */
    public void clearMonitoredResources() {
        this.monitoredResources_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeMonitoredResources(int i) {
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.remove(i);
    }

    public boolean hasBilling() {
        return this.billing_ != null;
    }

    public Billing getBilling() {
        Billing billing = this.billing_;
        return billing == null ? Billing.getDefaultInstance() : billing;
    }

    /* access modifiers changed from: private */
    public void setBilling(Billing billing) {
        billing.getClass();
        this.billing_ = billing;
    }

    /* access modifiers changed from: private */
    public void mergeBilling(Billing billing) {
        billing.getClass();
        Billing billing2 = this.billing_;
        if (billing2 == null || billing2 == Billing.getDefaultInstance()) {
            this.billing_ = billing;
        } else {
            this.billing_ = (Billing) ((Billing.Builder) Billing.newBuilder(this.billing_).mergeFrom(billing)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearBilling() {
        this.billing_ = null;
    }

    public boolean hasLogging() {
        return this.logging_ != null;
    }

    public Logging getLogging() {
        Logging logging = this.logging_;
        return logging == null ? Logging.getDefaultInstance() : logging;
    }

    /* access modifiers changed from: private */
    public void setLogging(Logging logging) {
        logging.getClass();
        this.logging_ = logging;
    }

    /* access modifiers changed from: private */
    public void mergeLogging(Logging logging) {
        logging.getClass();
        Logging logging2 = this.logging_;
        if (logging2 == null || logging2 == Logging.getDefaultInstance()) {
            this.logging_ = logging;
        } else {
            this.logging_ = (Logging) ((Logging.Builder) Logging.newBuilder(this.logging_).mergeFrom(logging)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearLogging() {
        this.logging_ = null;
    }

    public boolean hasMonitoring() {
        return this.monitoring_ != null;
    }

    public Monitoring getMonitoring() {
        Monitoring monitoring = this.monitoring_;
        return monitoring == null ? Monitoring.getDefaultInstance() : monitoring;
    }

    /* access modifiers changed from: private */
    public void setMonitoring(Monitoring monitoring) {
        monitoring.getClass();
        this.monitoring_ = monitoring;
    }

    /* access modifiers changed from: private */
    public void mergeMonitoring(Monitoring monitoring) {
        monitoring.getClass();
        Monitoring monitoring2 = this.monitoring_;
        if (monitoring2 == null || monitoring2 == Monitoring.getDefaultInstance()) {
            this.monitoring_ = monitoring;
        } else {
            this.monitoring_ = (Monitoring) ((Monitoring.Builder) Monitoring.newBuilder(this.monitoring_).mergeFrom(monitoring)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearMonitoring() {
        this.monitoring_ = null;
    }

    public boolean hasSystemParameters() {
        return this.systemParameters_ != null;
    }

    public SystemParameters getSystemParameters() {
        SystemParameters systemParameters = this.systemParameters_;
        return systemParameters == null ? SystemParameters.getDefaultInstance() : systemParameters;
    }

    /* access modifiers changed from: private */
    public void setSystemParameters(SystemParameters systemParameters) {
        systemParameters.getClass();
        this.systemParameters_ = systemParameters;
    }

    /* access modifiers changed from: private */
    public void mergeSystemParameters(SystemParameters systemParameters) {
        systemParameters.getClass();
        SystemParameters systemParameters2 = this.systemParameters_;
        if (systemParameters2 == null || systemParameters2 == SystemParameters.getDefaultInstance()) {
            this.systemParameters_ = systemParameters;
        } else {
            this.systemParameters_ = (SystemParameters) ((SystemParameters.Builder) SystemParameters.newBuilder(this.systemParameters_).mergeFrom(systemParameters)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearSystemParameters() {
        this.systemParameters_ = null;
    }

    public boolean hasSourceInfo() {
        return this.sourceInfo_ != null;
    }

    public SourceInfo getSourceInfo() {
        SourceInfo sourceInfo = this.sourceInfo_;
        return sourceInfo == null ? SourceInfo.getDefaultInstance() : sourceInfo;
    }

    /* access modifiers changed from: private */
    public void setSourceInfo(SourceInfo sourceInfo) {
        sourceInfo.getClass();
        this.sourceInfo_ = sourceInfo;
    }

    /* access modifiers changed from: private */
    public void mergeSourceInfo(SourceInfo sourceInfo) {
        sourceInfo.getClass();
        SourceInfo sourceInfo2 = this.sourceInfo_;
        if (sourceInfo2 == null || sourceInfo2 == SourceInfo.getDefaultInstance()) {
            this.sourceInfo_ = sourceInfo;
        } else {
            this.sourceInfo_ = (SourceInfo) ((SourceInfo.Builder) SourceInfo.newBuilder(this.sourceInfo_).mergeFrom(sourceInfo)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearSourceInfo() {
        this.sourceInfo_ = null;
    }

    public static Service parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Service parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Service parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Service parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Service parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Service parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Service parseFrom(InputStream inputStream) throws IOException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Service parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Service parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Service) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Service parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Service) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Service parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Service parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Service service) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(service);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Service, Builder> implements ServiceOrBuilder {
        /* synthetic */ Builder(C11771 r1) {
            this();
        }

        private Builder() {
            super(Service.DEFAULT_INSTANCE);
        }

        public boolean hasConfigVersion() {
            return ((Service) this.instance).hasConfigVersion();
        }

        public UInt32Value getConfigVersion() {
            return ((Service) this.instance).getConfigVersion();
        }

        public Builder setConfigVersion(UInt32Value uInt32Value) {
            copyOnWrite();
            ((Service) this.instance).setConfigVersion(uInt32Value);
            return this;
        }

        public Builder setConfigVersion(UInt32Value.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setConfigVersion((UInt32Value) builder.build());
            return this;
        }

        public Builder mergeConfigVersion(UInt32Value uInt32Value) {
            copyOnWrite();
            ((Service) this.instance).mergeConfigVersion(uInt32Value);
            return this;
        }

        public Builder clearConfigVersion() {
            copyOnWrite();
            ((Service) this.instance).clearConfigVersion();
            return this;
        }

        public String getName() {
            return ((Service) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((Service) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Service) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Service) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Service) this.instance).setNameBytes(byteString);
            return this;
        }

        public String getId() {
            return ((Service) this.instance).getId();
        }

        public ByteString getIdBytes() {
            return ((Service) this.instance).getIdBytes();
        }

        public Builder setId(String str) {
            copyOnWrite();
            ((Service) this.instance).setId(str);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((Service) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString byteString) {
            copyOnWrite();
            ((Service) this.instance).setIdBytes(byteString);
            return this;
        }

        public String getTitle() {
            return ((Service) this.instance).getTitle();
        }

        public ByteString getTitleBytes() {
            return ((Service) this.instance).getTitleBytes();
        }

        public Builder setTitle(String str) {
            copyOnWrite();
            ((Service) this.instance).setTitle(str);
            return this;
        }

        public Builder clearTitle() {
            copyOnWrite();
            ((Service) this.instance).clearTitle();
            return this;
        }

        public Builder setTitleBytes(ByteString byteString) {
            copyOnWrite();
            ((Service) this.instance).setTitleBytes(byteString);
            return this;
        }

        public String getProducerProjectId() {
            return ((Service) this.instance).getProducerProjectId();
        }

        public ByteString getProducerProjectIdBytes() {
            return ((Service) this.instance).getProducerProjectIdBytes();
        }

        public Builder setProducerProjectId(String str) {
            copyOnWrite();
            ((Service) this.instance).setProducerProjectId(str);
            return this;
        }

        public Builder clearProducerProjectId() {
            copyOnWrite();
            ((Service) this.instance).clearProducerProjectId();
            return this;
        }

        public Builder setProducerProjectIdBytes(ByteString byteString) {
            copyOnWrite();
            ((Service) this.instance).setProducerProjectIdBytes(byteString);
            return this;
        }

        public List<Api> getApisList() {
            return Collections.unmodifiableList(((Service) this.instance).getApisList());
        }

        public int getApisCount() {
            return ((Service) this.instance).getApisCount();
        }

        public Api getApis(int i) {
            return ((Service) this.instance).getApis(i);
        }

        public Builder setApis(int i, Api api) {
            copyOnWrite();
            ((Service) this.instance).setApis(i, api);
            return this;
        }

        public Builder setApis(int i, Api.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setApis(i, (Api) builder.build());
            return this;
        }

        public Builder addApis(Api api) {
            copyOnWrite();
            ((Service) this.instance).addApis(api);
            return this;
        }

        public Builder addApis(int i, Api api) {
            copyOnWrite();
            ((Service) this.instance).addApis(i, api);
            return this;
        }

        public Builder addApis(Api.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addApis((Api) builder.build());
            return this;
        }

        public Builder addApis(int i, Api.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addApis(i, (Api) builder.build());
            return this;
        }

        public Builder addAllApis(Iterable<? extends Api> iterable) {
            copyOnWrite();
            ((Service) this.instance).addAllApis(iterable);
            return this;
        }

        public Builder clearApis() {
            copyOnWrite();
            ((Service) this.instance).clearApis();
            return this;
        }

        public Builder removeApis(int i) {
            copyOnWrite();
            ((Service) this.instance).removeApis(i);
            return this;
        }

        public List<Type> getTypesList() {
            return Collections.unmodifiableList(((Service) this.instance).getTypesList());
        }

        public int getTypesCount() {
            return ((Service) this.instance).getTypesCount();
        }

        public Type getTypes(int i) {
            return ((Service) this.instance).getTypes(i);
        }

        public Builder setTypes(int i, Type type) {
            copyOnWrite();
            ((Service) this.instance).setTypes(i, type);
            return this;
        }

        public Builder setTypes(int i, Type.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setTypes(i, (Type) builder.build());
            return this;
        }

        public Builder addTypes(Type type) {
            copyOnWrite();
            ((Service) this.instance).addTypes(type);
            return this;
        }

        public Builder addTypes(int i, Type type) {
            copyOnWrite();
            ((Service) this.instance).addTypes(i, type);
            return this;
        }

        public Builder addTypes(Type.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addTypes((Type) builder.build());
            return this;
        }

        public Builder addTypes(int i, Type.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addTypes(i, (Type) builder.build());
            return this;
        }

        public Builder addAllTypes(Iterable<? extends Type> iterable) {
            copyOnWrite();
            ((Service) this.instance).addAllTypes(iterable);
            return this;
        }

        public Builder clearTypes() {
            copyOnWrite();
            ((Service) this.instance).clearTypes();
            return this;
        }

        public Builder removeTypes(int i) {
            copyOnWrite();
            ((Service) this.instance).removeTypes(i);
            return this;
        }

        public List<Enum> getEnumsList() {
            return Collections.unmodifiableList(((Service) this.instance).getEnumsList());
        }

        public int getEnumsCount() {
            return ((Service) this.instance).getEnumsCount();
        }

        public Enum getEnums(int i) {
            return ((Service) this.instance).getEnums(i);
        }

        public Builder setEnums(int i, Enum enumR) {
            copyOnWrite();
            ((Service) this.instance).setEnums(i, enumR);
            return this;
        }

        public Builder setEnums(int i, Enum.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setEnums(i, (Enum) builder.build());
            return this;
        }

        public Builder addEnums(Enum enumR) {
            copyOnWrite();
            ((Service) this.instance).addEnums(enumR);
            return this;
        }

        public Builder addEnums(int i, Enum enumR) {
            copyOnWrite();
            ((Service) this.instance).addEnums(i, enumR);
            return this;
        }

        public Builder addEnums(Enum.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addEnums((Enum) builder.build());
            return this;
        }

        public Builder addEnums(int i, Enum.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addEnums(i, (Enum) builder.build());
            return this;
        }

        public Builder addAllEnums(Iterable<? extends Enum> iterable) {
            copyOnWrite();
            ((Service) this.instance).addAllEnums(iterable);
            return this;
        }

        public Builder clearEnums() {
            copyOnWrite();
            ((Service) this.instance).clearEnums();
            return this;
        }

        public Builder removeEnums(int i) {
            copyOnWrite();
            ((Service) this.instance).removeEnums(i);
            return this;
        }

        public boolean hasDocumentation() {
            return ((Service) this.instance).hasDocumentation();
        }

        public Documentation getDocumentation() {
            return ((Service) this.instance).getDocumentation();
        }

        public Builder setDocumentation(Documentation documentation) {
            copyOnWrite();
            ((Service) this.instance).setDocumentation(documentation);
            return this;
        }

        public Builder setDocumentation(Documentation.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setDocumentation((Documentation) builder.build());
            return this;
        }

        public Builder mergeDocumentation(Documentation documentation) {
            copyOnWrite();
            ((Service) this.instance).mergeDocumentation(documentation);
            return this;
        }

        public Builder clearDocumentation() {
            copyOnWrite();
            ((Service) this.instance).clearDocumentation();
            return this;
        }

        public boolean hasBackend() {
            return ((Service) this.instance).hasBackend();
        }

        public Backend getBackend() {
            return ((Service) this.instance).getBackend();
        }

        public Builder setBackend(Backend backend) {
            copyOnWrite();
            ((Service) this.instance).setBackend(backend);
            return this;
        }

        public Builder setBackend(Backend.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setBackend((Backend) builder.build());
            return this;
        }

        public Builder mergeBackend(Backend backend) {
            copyOnWrite();
            ((Service) this.instance).mergeBackend(backend);
            return this;
        }

        public Builder clearBackend() {
            copyOnWrite();
            ((Service) this.instance).clearBackend();
            return this;
        }

        public boolean hasHttp() {
            return ((Service) this.instance).hasHttp();
        }

        public Http getHttp() {
            return ((Service) this.instance).getHttp();
        }

        public Builder setHttp(Http http) {
            copyOnWrite();
            ((Service) this.instance).setHttp(http);
            return this;
        }

        public Builder setHttp(Http.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setHttp((Http) builder.build());
            return this;
        }

        public Builder mergeHttp(Http http) {
            copyOnWrite();
            ((Service) this.instance).mergeHttp(http);
            return this;
        }

        public Builder clearHttp() {
            copyOnWrite();
            ((Service) this.instance).clearHttp();
            return this;
        }

        public boolean hasQuota() {
            return ((Service) this.instance).hasQuota();
        }

        public Quota getQuota() {
            return ((Service) this.instance).getQuota();
        }

        public Builder setQuota(Quota quota) {
            copyOnWrite();
            ((Service) this.instance).setQuota(quota);
            return this;
        }

        public Builder setQuota(Quota.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setQuota((Quota) builder.build());
            return this;
        }

        public Builder mergeQuota(Quota quota) {
            copyOnWrite();
            ((Service) this.instance).mergeQuota(quota);
            return this;
        }

        public Builder clearQuota() {
            copyOnWrite();
            ((Service) this.instance).clearQuota();
            return this;
        }

        public boolean hasAuthentication() {
            return ((Service) this.instance).hasAuthentication();
        }

        public Authentication getAuthentication() {
            return ((Service) this.instance).getAuthentication();
        }

        public Builder setAuthentication(Authentication authentication) {
            copyOnWrite();
            ((Service) this.instance).setAuthentication(authentication);
            return this;
        }

        public Builder setAuthentication(Authentication.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setAuthentication((Authentication) builder.build());
            return this;
        }

        public Builder mergeAuthentication(Authentication authentication) {
            copyOnWrite();
            ((Service) this.instance).mergeAuthentication(authentication);
            return this;
        }

        public Builder clearAuthentication() {
            copyOnWrite();
            ((Service) this.instance).clearAuthentication();
            return this;
        }

        public boolean hasContext() {
            return ((Service) this.instance).hasContext();
        }

        public Context getContext() {
            return ((Service) this.instance).getContext();
        }

        public Builder setContext(Context context) {
            copyOnWrite();
            ((Service) this.instance).setContext(context);
            return this;
        }

        public Builder setContext(Context.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setContext((Context) builder.build());
            return this;
        }

        public Builder mergeContext(Context context) {
            copyOnWrite();
            ((Service) this.instance).mergeContext(context);
            return this;
        }

        public Builder clearContext() {
            copyOnWrite();
            ((Service) this.instance).clearContext();
            return this;
        }

        public boolean hasUsage() {
            return ((Service) this.instance).hasUsage();
        }

        public Usage getUsage() {
            return ((Service) this.instance).getUsage();
        }

        public Builder setUsage(Usage usage) {
            copyOnWrite();
            ((Service) this.instance).setUsage(usage);
            return this;
        }

        public Builder setUsage(Usage.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setUsage((Usage) builder.build());
            return this;
        }

        public Builder mergeUsage(Usage usage) {
            copyOnWrite();
            ((Service) this.instance).mergeUsage(usage);
            return this;
        }

        public Builder clearUsage() {
            copyOnWrite();
            ((Service) this.instance).clearUsage();
            return this;
        }

        public List<Endpoint> getEndpointsList() {
            return Collections.unmodifiableList(((Service) this.instance).getEndpointsList());
        }

        public int getEndpointsCount() {
            return ((Service) this.instance).getEndpointsCount();
        }

        public Endpoint getEndpoints(int i) {
            return ((Service) this.instance).getEndpoints(i);
        }

        public Builder setEndpoints(int i, Endpoint endpoint) {
            copyOnWrite();
            ((Service) this.instance).setEndpoints(i, endpoint);
            return this;
        }

        public Builder setEndpoints(int i, Endpoint.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setEndpoints(i, (Endpoint) builder.build());
            return this;
        }

        public Builder addEndpoints(Endpoint endpoint) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(endpoint);
            return this;
        }

        public Builder addEndpoints(int i, Endpoint endpoint) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(i, endpoint);
            return this;
        }

        public Builder addEndpoints(Endpoint.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints((Endpoint) builder.build());
            return this;
        }

        public Builder addEndpoints(int i, Endpoint.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(i, (Endpoint) builder.build());
            return this;
        }

        public Builder addAllEndpoints(Iterable<? extends Endpoint> iterable) {
            copyOnWrite();
            ((Service) this.instance).addAllEndpoints(iterable);
            return this;
        }

        public Builder clearEndpoints() {
            copyOnWrite();
            ((Service) this.instance).clearEndpoints();
            return this;
        }

        public Builder removeEndpoints(int i) {
            copyOnWrite();
            ((Service) this.instance).removeEndpoints(i);
            return this;
        }

        public boolean hasControl() {
            return ((Service) this.instance).hasControl();
        }

        public Control getControl() {
            return ((Service) this.instance).getControl();
        }

        public Builder setControl(Control control) {
            copyOnWrite();
            ((Service) this.instance).setControl(control);
            return this;
        }

        public Builder setControl(Control.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setControl((Control) builder.build());
            return this;
        }

        public Builder mergeControl(Control control) {
            copyOnWrite();
            ((Service) this.instance).mergeControl(control);
            return this;
        }

        public Builder clearControl() {
            copyOnWrite();
            ((Service) this.instance).clearControl();
            return this;
        }

        public List<LogDescriptor> getLogsList() {
            return Collections.unmodifiableList(((Service) this.instance).getLogsList());
        }

        public int getLogsCount() {
            return ((Service) this.instance).getLogsCount();
        }

        public LogDescriptor getLogs(int i) {
            return ((Service) this.instance).getLogs(i);
        }

        public Builder setLogs(int i, LogDescriptor logDescriptor) {
            copyOnWrite();
            ((Service) this.instance).setLogs(i, logDescriptor);
            return this;
        }

        public Builder setLogs(int i, LogDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setLogs(i, (LogDescriptor) builder.build());
            return this;
        }

        public Builder addLogs(LogDescriptor logDescriptor) {
            copyOnWrite();
            ((Service) this.instance).addLogs(logDescriptor);
            return this;
        }

        public Builder addLogs(int i, LogDescriptor logDescriptor) {
            copyOnWrite();
            ((Service) this.instance).addLogs(i, logDescriptor);
            return this;
        }

        public Builder addLogs(LogDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addLogs((LogDescriptor) builder.build());
            return this;
        }

        public Builder addLogs(int i, LogDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addLogs(i, (LogDescriptor) builder.build());
            return this;
        }

        public Builder addAllLogs(Iterable<? extends LogDescriptor> iterable) {
            copyOnWrite();
            ((Service) this.instance).addAllLogs(iterable);
            return this;
        }

        public Builder clearLogs() {
            copyOnWrite();
            ((Service) this.instance).clearLogs();
            return this;
        }

        public Builder removeLogs(int i) {
            copyOnWrite();
            ((Service) this.instance).removeLogs(i);
            return this;
        }

        public List<MetricDescriptor> getMetricsList() {
            return Collections.unmodifiableList(((Service) this.instance).getMetricsList());
        }

        public int getMetricsCount() {
            return ((Service) this.instance).getMetricsCount();
        }

        public MetricDescriptor getMetrics(int i) {
            return ((Service) this.instance).getMetrics(i);
        }

        public Builder setMetrics(int i, MetricDescriptor metricDescriptor) {
            copyOnWrite();
            ((Service) this.instance).setMetrics(i, metricDescriptor);
            return this;
        }

        public Builder setMetrics(int i, MetricDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setMetrics(i, (MetricDescriptor) builder.build());
            return this;
        }

        public Builder addMetrics(MetricDescriptor metricDescriptor) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(metricDescriptor);
            return this;
        }

        public Builder addMetrics(int i, MetricDescriptor metricDescriptor) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(i, metricDescriptor);
            return this;
        }

        public Builder addMetrics(MetricDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addMetrics((MetricDescriptor) builder.build());
            return this;
        }

        public Builder addMetrics(int i, MetricDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(i, (MetricDescriptor) builder.build());
            return this;
        }

        public Builder addAllMetrics(Iterable<? extends MetricDescriptor> iterable) {
            copyOnWrite();
            ((Service) this.instance).addAllMetrics(iterable);
            return this;
        }

        public Builder clearMetrics() {
            copyOnWrite();
            ((Service) this.instance).clearMetrics();
            return this;
        }

        public Builder removeMetrics(int i) {
            copyOnWrite();
            ((Service) this.instance).removeMetrics(i);
            return this;
        }

        public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
            return Collections.unmodifiableList(((Service) this.instance).getMonitoredResourcesList());
        }

        public int getMonitoredResourcesCount() {
            return ((Service) this.instance).getMonitoredResourcesCount();
        }

        public MonitoredResourceDescriptor getMonitoredResources(int i) {
            return ((Service) this.instance).getMonitoredResources(i);
        }

        public Builder setMonitoredResources(int i, MonitoredResourceDescriptor monitoredResourceDescriptor) {
            copyOnWrite();
            ((Service) this.instance).setMonitoredResources(i, monitoredResourceDescriptor);
            return this;
        }

        public Builder setMonitoredResources(int i, MonitoredResourceDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setMonitoredResources(i, (MonitoredResourceDescriptor) builder.build());
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor monitoredResourceDescriptor) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(monitoredResourceDescriptor);
            return this;
        }

        public Builder addMonitoredResources(int i, MonitoredResourceDescriptor monitoredResourceDescriptor) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(i, monitoredResourceDescriptor);
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources((MonitoredResourceDescriptor) builder.build());
            return this;
        }

        public Builder addMonitoredResources(int i, MonitoredResourceDescriptor.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(i, (MonitoredResourceDescriptor) builder.build());
            return this;
        }

        public Builder addAllMonitoredResources(Iterable<? extends MonitoredResourceDescriptor> iterable) {
            copyOnWrite();
            ((Service) this.instance).addAllMonitoredResources(iterable);
            return this;
        }

        public Builder clearMonitoredResources() {
            copyOnWrite();
            ((Service) this.instance).clearMonitoredResources();
            return this;
        }

        public Builder removeMonitoredResources(int i) {
            copyOnWrite();
            ((Service) this.instance).removeMonitoredResources(i);
            return this;
        }

        public boolean hasBilling() {
            return ((Service) this.instance).hasBilling();
        }

        public Billing getBilling() {
            return ((Service) this.instance).getBilling();
        }

        public Builder setBilling(Billing billing) {
            copyOnWrite();
            ((Service) this.instance).setBilling(billing);
            return this;
        }

        public Builder setBilling(Billing.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setBilling((Billing) builder.build());
            return this;
        }

        public Builder mergeBilling(Billing billing) {
            copyOnWrite();
            ((Service) this.instance).mergeBilling(billing);
            return this;
        }

        public Builder clearBilling() {
            copyOnWrite();
            ((Service) this.instance).clearBilling();
            return this;
        }

        public boolean hasLogging() {
            return ((Service) this.instance).hasLogging();
        }

        public Logging getLogging() {
            return ((Service) this.instance).getLogging();
        }

        public Builder setLogging(Logging logging) {
            copyOnWrite();
            ((Service) this.instance).setLogging(logging);
            return this;
        }

        public Builder setLogging(Logging.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setLogging((Logging) builder.build());
            return this;
        }

        public Builder mergeLogging(Logging logging) {
            copyOnWrite();
            ((Service) this.instance).mergeLogging(logging);
            return this;
        }

        public Builder clearLogging() {
            copyOnWrite();
            ((Service) this.instance).clearLogging();
            return this;
        }

        public boolean hasMonitoring() {
            return ((Service) this.instance).hasMonitoring();
        }

        public Monitoring getMonitoring() {
            return ((Service) this.instance).getMonitoring();
        }

        public Builder setMonitoring(Monitoring monitoring) {
            copyOnWrite();
            ((Service) this.instance).setMonitoring(monitoring);
            return this;
        }

        public Builder setMonitoring(Monitoring.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setMonitoring((Monitoring) builder.build());
            return this;
        }

        public Builder mergeMonitoring(Monitoring monitoring) {
            copyOnWrite();
            ((Service) this.instance).mergeMonitoring(monitoring);
            return this;
        }

        public Builder clearMonitoring() {
            copyOnWrite();
            ((Service) this.instance).clearMonitoring();
            return this;
        }

        public boolean hasSystemParameters() {
            return ((Service) this.instance).hasSystemParameters();
        }

        public SystemParameters getSystemParameters() {
            return ((Service) this.instance).getSystemParameters();
        }

        public Builder setSystemParameters(SystemParameters systemParameters) {
            copyOnWrite();
            ((Service) this.instance).setSystemParameters(systemParameters);
            return this;
        }

        public Builder setSystemParameters(SystemParameters.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setSystemParameters((SystemParameters) builder.build());
            return this;
        }

        public Builder mergeSystemParameters(SystemParameters systemParameters) {
            copyOnWrite();
            ((Service) this.instance).mergeSystemParameters(systemParameters);
            return this;
        }

        public Builder clearSystemParameters() {
            copyOnWrite();
            ((Service) this.instance).clearSystemParameters();
            return this;
        }

        public boolean hasSourceInfo() {
            return ((Service) this.instance).hasSourceInfo();
        }

        public SourceInfo getSourceInfo() {
            return ((Service) this.instance).getSourceInfo();
        }

        public Builder setSourceInfo(SourceInfo sourceInfo) {
            copyOnWrite();
            ((Service) this.instance).setSourceInfo(sourceInfo);
            return this;
        }

        public Builder setSourceInfo(SourceInfo.Builder builder) {
            copyOnWrite();
            ((Service) this.instance).setSourceInfo((SourceInfo) builder.build());
            return this;
        }

        public Builder mergeSourceInfo(SourceInfo sourceInfo) {
            copyOnWrite();
            ((Service) this.instance).mergeSourceInfo(sourceInfo);
            return this;
        }

        public Builder clearSourceInfo() {
            copyOnWrite();
            ((Service) this.instance).clearSourceInfo();
            return this;
        }
    }

    /* renamed from: com.google.api.Service$1 */
    static /* synthetic */ class C11771 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f212xa1df5c61;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f212xa1df5c61 = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f212xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f212xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f212xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f212xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f212xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f212xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Service.C11771.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (C11771.f212xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new Service();
            case 2:
                return new Builder((C11771) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0019\u0000\u0000\u0001%\u0019\u0000\u0007\u0000\u0001Ȉ\u0002Ȉ\u0003\u001b\u0004\u001b\u0005\u001b\u0006\t\b\t\t\t\n\t\u000b\t\f\t\u000f\t\u0012\u001b\u0014\t\u0015\t\u0016Ȉ\u0017\u001b\u0018\u001b\u0019\u001b\u001a\t\u001b\t\u001c\t\u001d\t!Ȉ%\t", new Object[]{"name_", "title_", "apis_", Api.class, "types_", Type.class, "enums_", Enum.class, "documentation_", "backend_", "http_", "quota_", "authentication_", "context_", "usage_", "endpoints_", Endpoint.class, "configVersion_", "control_", "producerProjectId_", "logs_", LogDescriptor.class, "metrics_", MetricDescriptor.class, "monitoredResources_", MonitoredResourceDescriptor.class, "billing_", "logging_", "monitoring_", "systemParameters_", "id_", "sourceInfo_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Service> parser = PARSER;
                if (parser == null) {
                    synchronized (Service.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        Service service = new Service();
        DEFAULT_INSTANCE = service;
        GeneratedMessageLite.registerDefaultInstance(Service.class, service);
    }

    public static Service getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Service> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}

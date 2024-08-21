package p012io.grpc.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import p012io.grpc.HandlerRegistry;
import p012io.grpc.ServerMethodDefinition;
import p012io.grpc.ServerServiceDefinition;

/* renamed from: io.grpc.internal.InternalHandlerRegistry */
final class InternalHandlerRegistry extends HandlerRegistry {
    private final Map<String, ServerMethodDefinition<?, ?>> methods;
    private final List<ServerServiceDefinition> services;

    private InternalHandlerRegistry(List<ServerServiceDefinition> list, Map<String, ServerMethodDefinition<?, ?>> map) {
        this.services = list;
        this.methods = map;
    }

    public List<ServerServiceDefinition> getServices() {
        return this.services;
    }

    @Nullable
    public ServerMethodDefinition<?, ?> lookupMethod(String str, @Nullable String str2) {
        return this.methods.get(str);
    }

    /* renamed from: io.grpc.internal.InternalHandlerRegistry$Builder */
    static final class Builder {
        private final HashMap<String, ServerServiceDefinition> services = new LinkedHashMap();

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public Builder addService(ServerServiceDefinition serverServiceDefinition) {
            this.services.put(serverServiceDefinition.getServiceDescriptor().getName(), serverServiceDefinition);
            return this;
        }

        /* access modifiers changed from: package-private */
        public InternalHandlerRegistry build() {
            HashMap hashMap = new HashMap();
            for (ServerServiceDefinition methods : this.services.values()) {
                for (ServerMethodDefinition next : methods.getMethods()) {
                    hashMap.put(next.getMethodDescriptor().getFullMethodName(), next);
                }
            }
            return new InternalHandlerRegistry(Collections.unmodifiableList(new ArrayList(this.services.values())), Collections.unmodifiableMap(hashMap));
        }
    }
}

package p012io.grpc.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;
import p012io.grpc.BindableService;
import p012io.grpc.HandlerRegistry;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.ServerMethodDefinition;
import p012io.grpc.ServerServiceDefinition;

/* renamed from: io.grpc.util.MutableHandlerRegistry */
public final class MutableHandlerRegistry extends HandlerRegistry {
    private final ConcurrentMap<String, ServerServiceDefinition> services = new ConcurrentHashMap();

    @Nullable
    public ServerServiceDefinition addService(ServerServiceDefinition serverServiceDefinition) {
        return (ServerServiceDefinition) this.services.put(serverServiceDefinition.getServiceDescriptor().getName(), serverServiceDefinition);
    }

    @Nullable
    public ServerServiceDefinition addService(BindableService bindableService) {
        return addService(bindableService.bindService());
    }

    public boolean removeService(ServerServiceDefinition serverServiceDefinition) {
        return this.services.remove(serverServiceDefinition.getServiceDescriptor().getName(), serverServiceDefinition);
    }

    public List<ServerServiceDefinition> getServices() {
        return Collections.unmodifiableList(new ArrayList(this.services.values()));
    }

    @Nullable
    public ServerMethodDefinition<?, ?> lookupMethod(String str, @Nullable String str2) {
        ServerServiceDefinition serverServiceDefinition;
        String extractFullServiceName = MethodDescriptor.extractFullServiceName(str);
        if (extractFullServiceName == null || (serverServiceDefinition = (ServerServiceDefinition) this.services.get(extractFullServiceName)) == null) {
            return null;
        }
        return serverServiceDefinition.getMethod(str);
    }
}

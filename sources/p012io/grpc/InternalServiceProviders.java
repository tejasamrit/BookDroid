package p012io.grpc;

import java.util.List;
import p012io.grpc.ServiceProviders;

/* renamed from: io.grpc.InternalServiceProviders */
public final class InternalServiceProviders {

    /* renamed from: io.grpc.InternalServiceProviders$PriorityAccessor */
    public interface PriorityAccessor<T> extends ServiceProviders.PriorityAccessor<T> {
    }

    private InternalServiceProviders() {
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Iterable<java.lang.Class<?>>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T load(java.lang.Class<T> r0, java.lang.Iterable<java.lang.Class<?>> r1, java.lang.ClassLoader r2, p012io.grpc.InternalServiceProviders.PriorityAccessor<T> r3) {
        /*
            java.lang.Object r0 = p012io.grpc.ServiceProviders.load(r0, r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.InternalServiceProviders.load(java.lang.Class, java.lang.Iterable, java.lang.ClassLoader, io.grpc.InternalServiceProviders$PriorityAccessor):java.lang.Object");
    }

    public static <T> List<T> loadAll(Class<T> cls, Iterable<Class<?>> iterable, ClassLoader classLoader, PriorityAccessor<T> priorityAccessor) {
        return ServiceProviders.loadAll(cls, iterable, classLoader, priorityAccessor);
    }

    public static <T> Iterable<T> getCandidatesViaServiceLoader(Class<T> cls, ClassLoader classLoader) {
        return ServiceProviders.getCandidatesViaServiceLoader(cls, classLoader);
    }

    public static <T> Iterable<T> getCandidatesViaHardCoded(Class<T> cls, Iterable<Class<?>> iterable) {
        return ServiceProviders.getCandidatesViaHardCoded(cls, iterable);
    }

    public static boolean isAndroid(ClassLoader classLoader) {
        return ServiceProviders.isAndroid(classLoader);
    }
}

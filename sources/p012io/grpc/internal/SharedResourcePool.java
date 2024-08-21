package p012io.grpc.internal;

import p012io.grpc.internal.SharedResourceHolder;

/* renamed from: io.grpc.internal.SharedResourcePool */
public final class SharedResourcePool<T> implements ObjectPool<T> {
    private final SharedResourceHolder.Resource<T> resource;

    private SharedResourcePool(SharedResourceHolder.Resource<T> resource2) {
        this.resource = resource2;
    }

    public static <T> SharedResourcePool<T> forResource(SharedResourceHolder.Resource<T> resource2) {
        return new SharedResourcePool<>(resource2);
    }

    public T getObject() {
        return SharedResourceHolder.get(this.resource);
    }

    public T returnObject(Object obj) {
        SharedResourceHolder.release(this.resource, obj);
        return null;
    }
}

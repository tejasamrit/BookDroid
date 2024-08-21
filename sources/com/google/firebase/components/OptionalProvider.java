package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

class OptionalProvider<T> implements Provider<T>, Deferred<T> {
    private static final Provider<Object> EMPTY_PROVIDER = OptionalProvider$$Lambda$5.lambdaFactory$();
    private static final Deferred.DeferredHandler<Object> NOOP_HANDLER = OptionalProvider$$Lambda$4.lambdaFactory$();
    private volatile Provider<T> delegate;
    private Deferred.DeferredHandler<T> handler;

    static /* synthetic */ void lambda$static$0(Provider provider) {
    }

    static /* synthetic */ Object lambda$static$1() {
        return null;
    }

    private OptionalProvider(Deferred.DeferredHandler<T> deferredHandler, Provider<T> provider) {
        this.handler = deferredHandler;
        this.delegate = provider;
    }

    static <T> OptionalProvider<T> empty() {
        return new OptionalProvider<>(NOOP_HANDLER, EMPTY_PROVIDER);
    }

    /* renamed from: of */
    static <T> OptionalProvider<T> m243of(Provider<T> provider) {
        return new OptionalProvider<>((Deferred.DeferredHandler) null, provider);
    }

    public T get() {
        return this.delegate.get();
    }

    /* access modifiers changed from: package-private */
    public void set(Provider<T> provider) {
        Deferred.DeferredHandler<T> deferredHandler;
        if (this.delegate == EMPTY_PROVIDER) {
            synchronized (this) {
                deferredHandler = this.handler;
                this.handler = null;
                this.delegate = provider;
            }
            deferredHandler.handle(provider);
            return;
        }
        throw new IllegalStateException("provide() can be called only once.");
    }

    public void whenAvailable(Deferred.DeferredHandler<T> deferredHandler) {
        Provider<T> provider;
        Provider<T> provider2 = this.delegate;
        Provider<Object> provider3 = EMPTY_PROVIDER;
        if (provider2 != provider3) {
            deferredHandler.handle(provider2);
            return;
        }
        Provider<T> provider4 = null;
        synchronized (this) {
            provider = this.delegate;
            if (provider != provider3) {
                provider4 = provider;
            } else {
                this.handler = OptionalProvider$$Lambda$1.lambdaFactory$(this.handler, deferredHandler);
            }
        }
        if (provider4 != null) {
            deferredHandler.handle(provider);
        }
    }

    static /* synthetic */ void lambda$whenAvailable$2(Deferred.DeferredHandler deferredHandler, Deferred.DeferredHandler deferredHandler2, Provider provider) {
        deferredHandler.handle(provider);
        deferredHandler2.handle(provider);
    }
}

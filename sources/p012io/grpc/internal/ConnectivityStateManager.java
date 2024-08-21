package p012io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import p012io.grpc.ConnectivityState;

/* renamed from: io.grpc.internal.ConnectivityStateManager */
final class ConnectivityStateManager {
    private ArrayList<C2401Listener> listeners = new ArrayList<>();
    private volatile ConnectivityState state = ConnectivityState.IDLE;

    ConnectivityStateManager() {
    }

    /* access modifiers changed from: package-private */
    public void notifyWhenStateChanged(Runnable runnable, Executor executor, ConnectivityState connectivityState) {
        Preconditions.checkNotNull(runnable, "callback");
        Preconditions.checkNotNull(executor, "executor");
        Preconditions.checkNotNull(connectivityState, Constants.ScionAnalytics.PARAM_SOURCE);
        C2401Listener listener = new C2401Listener(runnable, executor);
        if (this.state != connectivityState) {
            listener.runInExecutor();
        } else {
            this.listeners.add(listener);
        }
    }

    /* access modifiers changed from: package-private */
    public void gotoState(@Nonnull ConnectivityState connectivityState) {
        Preconditions.checkNotNull(connectivityState, "newState");
        if (this.state != connectivityState && this.state != ConnectivityState.SHUTDOWN) {
            this.state = connectivityState;
            if (!this.listeners.isEmpty()) {
                ArrayList<C2401Listener> arrayList = this.listeners;
                this.listeners = new ArrayList<>();
                Iterator<C2401Listener> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().runInExecutor();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ConnectivityState getState() {
        ConnectivityState connectivityState = this.state;
        if (connectivityState != null) {
            return connectivityState;
        }
        throw new UnsupportedOperationException("Channel state API is not implemented");
    }

    /* renamed from: io.grpc.internal.ConnectivityStateManager$Listener */
    private static final class C2401Listener {
        final Runnable callback;
        final Executor executor;

        C2401Listener(Runnable runnable, Executor executor2) {
            this.callback = runnable;
            this.executor = executor2;
        }

        /* access modifiers changed from: package-private */
        public void runInExecutor() {
            this.executor.execute(this.callback);
        }
    }
}

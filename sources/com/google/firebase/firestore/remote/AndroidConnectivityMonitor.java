package com.google.firebase.firestore.remote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.firebase.firestore.remote.ConnectivityMonitor;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import java.util.ArrayList;
import java.util.List;

public final class AndroidConnectivityMonitor implements ConnectivityMonitor {
    /* access modifiers changed from: private */
    public final List<Consumer<ConnectivityMonitor.NetworkStatus>> callbacks = new ArrayList();
    /* access modifiers changed from: private */
    public final ConnectivityManager connectivityManager;
    /* access modifiers changed from: private */
    public final Context context;
    private Runnable unregisterRunnable;

    public AndroidConnectivityMonitor(Context context2) {
        Assert.hardAssert(context2 != null, "Context must be non-null", new Object[0]);
        this.context = context2;
        this.connectivityManager = (ConnectivityManager) context2.getSystemService("connectivity");
        configureNetworkMonitoring();
    }

    public void addCallback(Consumer<ConnectivityMonitor.NetworkStatus> consumer) {
        synchronized (this.callbacks) {
            this.callbacks.add(consumer);
        }
    }

    public void shutdown() {
        Runnable runnable = this.unregisterRunnable;
        if (runnable != null) {
            runnable.run();
            this.unregisterRunnable = null;
        }
    }

    private void configureNetworkMonitoring() {
        if (Build.VERSION.SDK_INT < 24 || this.connectivityManager == null) {
            final NetworkReceiver networkReceiver = new NetworkReceiver();
            this.context.registerReceiver(networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.unregisterRunnable = new Runnable() {
                public void run() {
                    AndroidConnectivityMonitor.this.context.unregisterReceiver(networkReceiver);
                }
            };
            return;
        }
        final DefaultNetworkCallback defaultNetworkCallback = new DefaultNetworkCallback();
        this.connectivityManager.registerDefaultNetworkCallback(defaultNetworkCallback);
        this.unregisterRunnable = new Runnable() {
            public void run() {
                AndroidConnectivityMonitor.this.connectivityManager.unregisterNetworkCallback(defaultNetworkCallback);
            }
        };
    }

    private class DefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
        private DefaultNetworkCallback() {
        }

        public void onAvailable(Network network) {
            synchronized (AndroidConnectivityMonitor.this.callbacks) {
                for (Consumer accept : AndroidConnectivityMonitor.this.callbacks) {
                    accept.accept(ConnectivityMonitor.NetworkStatus.REACHABLE);
                }
            }
        }

        public void onLost(Network network) {
            synchronized (AndroidConnectivityMonitor.this.callbacks) {
                for (Consumer accept : AndroidConnectivityMonitor.this.callbacks) {
                    accept.accept(ConnectivityMonitor.NetworkStatus.UNREACHABLE);
                }
            }
        }
    }

    private class NetworkReceiver extends BroadcastReceiver {
        private boolean isConnected;

        private NetworkReceiver() {
            this.isConnected = false;
        }

        public void onReceive(Context context, Intent intent) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            boolean z = this.isConnected;
            boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
            this.isConnected = z2;
            if (z2 && !z) {
                synchronized (AndroidConnectivityMonitor.this.callbacks) {
                    for (Consumer accept : AndroidConnectivityMonitor.this.callbacks) {
                        accept.accept(ConnectivityMonitor.NetworkStatus.REACHABLE);
                    }
                }
            } else if (!z2 && z) {
                synchronized (AndroidConnectivityMonitor.this.callbacks) {
                    for (Consumer accept2 : AndroidConnectivityMonitor.this.callbacks) {
                        accept2.accept(ConnectivityMonitor.NetworkStatus.UNREACHABLE);
                    }
                }
            }
        }
    }
}

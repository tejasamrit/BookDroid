package com.google.android.gms.common.api.internal;

import android.app.Activity;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public abstract class ActivityLifecycleObserver {
    public abstract ActivityLifecycleObserver onStopCallOnce(Runnable runnable);

    /* renamed from: of */
    public static final ActivityLifecycleObserver m27of(Activity activity) {
        return new zaa(activity);
    }
}

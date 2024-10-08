package com.google.android.gms.common.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import com.google.firebase.firestore.util.ExponentialBackoff;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public final class zza {
    private static final IntentFilter zza = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static long zzb;
    private static float zzc = Float.NaN;

    public static int zza(Context context) {
        int i;
        boolean z;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, zza);
        int i2 = 0;
        if (registerReceiver == null) {
            i = 0;
        } else {
            i = registerReceiver.getIntExtra("plugged", 0);
        }
        int i3 = 3;
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            i3 = 7;
        }
        int i4 = (i & i3) != 0 ? 1 : 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        if (PlatformVersion.isAtLeastKitKatWatch()) {
            z = powerManager.isInteractive();
        } else {
            z = powerManager.isScreenOn();
        }
        if (z) {
            i2 = 2;
        }
        return i2 | i4;
    }

    public static synchronized float zzb(Context context) {
        synchronized (zza.class) {
            if (SystemClock.elapsedRealtime() - zzb >= ExponentialBackoff.DEFAULT_BACKOFF_MAX_DELAY_MS || Float.isNaN(zzc)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, zza);
                if (registerReceiver != null) {
                    zzc = ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                zzb = SystemClock.elapsedRealtime();
                float f = zzc;
                return f;
            }
            float f2 = zzc;
            return f2;
        }
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzx */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzx {
    private static final Logger zza = Logger.getLogger(zzx.class.getName());
    private static final zzw zzb = new zzw((zzv) null);

    private zzx() {
    }

    static boolean zza(@NullableDecl String str) {
        return str == null || str.isEmpty();
    }

    static zzs zzb(String str) {
        return new zzu(Pattern.compile("[.-]"));
    }
}

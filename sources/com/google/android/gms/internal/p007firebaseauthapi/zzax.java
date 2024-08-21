package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzax */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzax {
    private static final CopyOnWriteArrayList<zzaw> zza = new CopyOnWriteArrayList<>();

    public static zzaw zza(String str) throws GeneralSecurityException {
        Iterator<zzaw> it = zza.iterator();
        while (it.hasNext()) {
            zzaw next = it.next();
            if (next.zza(str)) {
                return next;
            }
        }
        String valueOf = String.valueOf(str);
        throw new GeneralSecurityException(valueOf.length() != 0 ? "No KMS client does support: ".concat(valueOf) : new String("No KMS client does support: "));
    }
}

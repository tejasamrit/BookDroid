package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaam */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzaam {
    zzaam() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzaal zzaal = (zzaal) obj;
        zzaak zzaak = (zzaak) obj2;
        if (zzaal.isEmpty()) {
            return 0;
        }
        Iterator it = zzaal.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public static final boolean zzb(Object obj) {
        return !((zzaal) obj).zze();
    }

    public static final Object zzc(Object obj, Object obj2) {
        zzaal zzaal = (zzaal) obj;
        zzaal zzaal2 = (zzaal) obj2;
        if (!zzaal2.isEmpty()) {
            if (!zzaal.zze()) {
                zzaal = zzaal.zzc();
            }
            zzaal.zzb(zzaal2);
        }
        return zzaal;
    }
}

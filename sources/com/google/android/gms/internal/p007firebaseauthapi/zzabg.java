package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabg */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzabg extends zzabn {
    zzabg(int i) {
        super(i, (zzabg) null);
    }

    public final void zza() {
        if (!zzb()) {
            for (int i = 0; i < zzc(); i++) {
                Map.Entry zzd = zzd(i);
                if (((zzzf) zzd.getKey()).zzb()) {
                    zzd.setValue(Collections.unmodifiableList((List) zzd.getValue()));
                }
            }
            for (Map.Entry entry : zze()) {
                if (((zzzf) entry.getKey()).zzb()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}

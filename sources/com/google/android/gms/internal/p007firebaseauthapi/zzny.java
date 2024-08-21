package com.google.android.gms.internal.p007firebaseauthapi;

import android.text.TextUtils;
import com.google.firebase.auth.zze;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzny */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzny implements zzup<zzvx> {
    final /* synthetic */ zzuo zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ Boolean zzd;
    final /* synthetic */ zze zze;
    final /* synthetic */ zztb zzf;
    final /* synthetic */ zzwg zzg;

    zzny(zzpj zzpj, zzuo zzuo, String str, String str2, Boolean bool, zze zze2, zztb zztb, zzwg zzwg) {
        this.zza = zzuo;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bool;
        this.zze = zze2;
        this.zzf = zztb;
        this.zzg = zzwg;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List<zzvz> zzb2 = ((zzvx) obj).zzb();
        if (zzb2 == null || zzb2.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        int i = 0;
        zzvz zzvz = zzb2.get(0);
        zzwo zzq = zzvz.zzq();
        List<zzwm> zza2 = zzq != null ? zzq.zza() : null;
        if (zza2 != null && !zza2.isEmpty()) {
            if (!TextUtils.isEmpty(this.zzb)) {
                while (true) {
                    if (i >= zza2.size()) {
                        break;
                    } else if (zza2.get(i).zzd().equals(this.zzb)) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            zza2.get(i).zzf(this.zzc);
        }
        zzvz.zzo(this.zzd.booleanValue());
        zzvz.zzs(this.zze);
        this.zzf.zzb(this.zzg, zzvz);
    }
}

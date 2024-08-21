package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzva */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzva extends zztb {
    final /* synthetic */ zzvd zza;
    private final String zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzva(zzvd zzvd, zztb zztb, String str) {
        super(zztb);
        this.zza = zzvd;
        this.zzb = str;
    }

    public final void zzh(String str) {
        zzvd.zza.mo13202d("onCodeSent", new Object[0]);
        zzvc zzvc = (zzvc) this.zza.zzd.get(this.zzb);
        if (zzvc != null) {
            for (zztb zzh : zzvc.zzb) {
                zzh.zzh(str);
            }
            zzvc.zzg = true;
            zzvc.zzd = str;
            if (zzvc.zza <= 0) {
                this.zza.zzg(this.zzb);
            } else if (!zzvc.zzc) {
                this.zza.zzo(this.zzb);
            } else if (!zzaf.zzb(zzvc.zze)) {
                zzvd.zzj(this.zza, this.zzb);
            }
        }
    }

    public final void zzk(Status status) {
        Logger zzh = zzvd.zza;
        String statusCodeString = CommonStatusCodes.getStatusCodeString(status.getStatusCode());
        String statusMessage = status.getStatusMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(statusCodeString).length() + 39 + String.valueOf(statusMessage).length());
        sb.append("SMS verification code request failed: ");
        sb.append(statusCodeString);
        sb.append(" ");
        sb.append(statusMessage);
        zzh.mo13204e(sb.toString(), new Object[0]);
        zzvc zzvc = (zzvc) this.zza.zzd.get(this.zzb);
        if (zzvc != null) {
            for (zztb zzk : zzvc.zzb) {
                zzk.zzk(status);
            }
            this.zza.zze(this.zzb);
        }
    }
}

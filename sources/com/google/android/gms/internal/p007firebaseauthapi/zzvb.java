package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvb */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzvb extends BroadcastReceiver {
    final /* synthetic */ zzvd zza;
    private final String zzb;

    public zzvb(zzvd zzvd, String str) {
        this.zza = zzvd;
        this.zzb = str;
    }

    public final void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            if (((Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).getStatusCode() == 0) {
                String str = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                zzvc zzvc = (zzvc) this.zza.zzd.get(this.zzb);
                if (zzvc == null) {
                    zzvd.zza.mo13204e("Verification code received with no active retrieval session.", new Object[0]);
                } else {
                    zzvc.zze = zzvd.zzf(str);
                    if (zzvc.zze == null) {
                        zzvd.zza.mo13204e("Unable to extract verification code.", new Object[0]);
                    } else if (!zzaf.zzb(zzvc.zzd)) {
                        zzvd.zzj(this.zza, this.zzb);
                    }
                }
            }
            context.getApplicationContext().unregisterReceiver(this);
        }
    }
}

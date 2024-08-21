package com.google.firebase.auth;

import android.util.Log;
import com.google.android.gms.internal.p007firebaseauthapi.zzta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzag;
import com.google.firebase.auth.internal.zze;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzp implements OnCompleteListener<zze> {
    final /* synthetic */ PhoneAuthOptions zza;
    final /* synthetic */ FirebaseAuth zzb;

    zzp(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions) {
        this.zzb = firebaseAuth;
        this.zza = phoneAuthOptions;
    }

    public final void onComplete(Task<zze> task) {
        String str;
        String str2;
        String str3;
        if (!task.isSuccessful()) {
            String valueOf = String.valueOf(task.getException().getMessage());
            if (valueOf.length() != 0) {
                str3 = "Error while validating application identity: ".concat(valueOf);
            } else {
                str3 = new String("Error while validating application identity: ");
            }
            Log.e("FirebaseAuth", str3);
            Log.e("FirebaseAuth", "Proceeding without any application identifier.");
            str2 = null;
            str = null;
        } else {
            String zza2 = task.getResult().zza();
            str = task.getResult().zzb();
            str2 = zza2;
        }
        long longValue = this.zza.zzc().longValue();
        PhoneAuthProvider.OnVerificationStateChangedCallbacks zzE = this.zzb.zzJ(this.zza.zzb(), this.zza.zzd());
        zzag zzag = (zzag) this.zza.zzg();
        if (zzag.zze()) {
            this.zzb.zze.zzM(zzag, this.zza.zzb(), this.zzb.zzi, longValue, this.zza.zzf() != null, this.zza.zzh(), str2, str, zzta.zza(), zzE, this.zza.zze(), this.zza.zzi());
        } else {
            this.zzb.zze.zzO(zzag, this.zza.zzj(), this.zzb.zzi, longValue, this.zza.zzf() != null, this.zza.zzh(), str2, str, zzta.zza(), zzE, this.zza.zze(), this.zza.zzi());
        }
    }
}

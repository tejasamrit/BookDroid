package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.auth.PhoneAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztb */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public class zztb {
    private final zztn zza;
    private final Logger zzb;

    public zztb(zztb zztb) {
        this(zztb.zza, zztb.zzb);
    }

    public final void zza(zzwg zzwg) {
        try {
            this.zza.zzd(zzwg);
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending token result.", e, new Object[0]);
        }
    }

    public final void zzb(zzwg zzwg, zzvz zzvz) {
        try {
            this.zza.zze(zzwg, zzvz);
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending get token and account info user response", e, new Object[0]);
        }
    }

    public final void zzc(zzvl zzvl) {
        try {
            this.zza.zzf(zzvl);
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending create auth uri response.", e, new Object[0]);
        }
    }

    public final void zzd(zzwr zzwr) {
        try {
            this.zza.zzg(zzwr);
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending password reset response.", e, new Object[0]);
        }
    }

    public final void zze() {
        try {
            this.zza.zzi();
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending delete account response.", e, new Object[0]);
        }
    }

    public final void zzf() {
        try {
            this.zza.zzj();
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending email verification response.", e, new Object[0]);
        }
    }

    public final void zzg(String str) {
        try {
            this.zza.zzk(str);
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending set account info response.", e, new Object[0]);
        }
    }

    public void zzh(String str) {
        try {
            this.zza.zzl(str);
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending send verification code response.", e, new Object[0]);
        }
    }

    public final void zzi(PhoneAuthCredential phoneAuthCredential) {
        try {
            this.zza.zzm(phoneAuthCredential);
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending verification completed response.", e, new Object[0]);
        }
    }

    public final void zzj(String str) {
        try {
            this.zza.zzn(str);
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending auto retrieval timeout response.", e, new Object[0]);
        }
    }

    public void zzk(Status status) {
        try {
            this.zza.zzh(status);
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending failure result.", e, new Object[0]);
        }
    }

    public final void zzl(Status status, PhoneAuthCredential phoneAuthCredential) {
        try {
            this.zza.zzo(status, phoneAuthCredential);
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending failure result.", e, new Object[0]);
        }
    }

    public final void zzm() {
        try {
            this.zza.zzp();
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when setting FirebaseUI Version", e, new Object[0]);
        }
    }

    public final void zzn(zzno zzno) {
        try {
            this.zza.zzq(zzno);
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending failure result with credential", e, new Object[0]);
        }
    }

    public final void zzo(zznq zznq) {
        try {
            this.zza.zzr(zznq);
        } catch (RemoteException e) {
            this.zzb.mo13203e("RemoteException when sending failure result for mfa", e, new Object[0]);
        }
    }

    public zztb(zztn zztn, Logger logger) {
        this.zza = (zztn) Preconditions.checkNotNull(zztn);
        this.zzb = (Logger) Preconditions.checkNotNull(logger);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.internal.zzao;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuk */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzuk extends zztm {
    final /* synthetic */ zzun zza;

    zzuk(zzun zzun) {
        this.zza = zzun;
    }

    private final void zzb(Status status, AuthCredential authCredential, String str, String str2) {
        zzun.zzn(this.zza, status);
        zzun zzun = this.zza;
        zzun.zzp = authCredential;
        zzun.zzq = str;
        zzun.zzr = str2;
        zzao zzao = zzun.zzg;
        if (zzao != null) {
            zzao.zzb(status);
        }
        this.zza.zzk(status);
    }

    private final void zzc(zzul zzul) {
        this.zza.zzi.execute(new zzuj(this, zzul));
    }

    public final void zzd(zzwg zzwg) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unexpected response type: ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzun zzun = this.zza;
        zzun.zzj = zzwg;
        zzun.zzl(zzun);
    }

    public final void zze(zzwg zzwg, zzvz zzvz) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 2;
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unexpected response type: ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzun zzun = this.zza;
        zzun.zzj = zzwg;
        zzun.zzk = zzvz;
        zzun.zzl(zzun);
    }

    public final void zzf(zzvl zzvl) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 3;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzun zzun = this.zza;
        zzun.zzl = zzvl;
        zzun.zzl(zzun);
    }

    public final void zzg(zzwr zzwr) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 4;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzun zzun = this.zza;
        zzun.zzm = zzwr;
        zzun.zzl(zzun);
    }

    public final void zzh(Status status) throws RemoteException {
        String statusMessage = status.getStatusMessage();
        if (statusMessage != null) {
            if (statusMessage.contains("MISSING_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17081);
            } else if (statusMessage.contains("MISSING_MFA_ENROLLMENT_ID")) {
                status = new Status(17082);
            } else if (statusMessage.contains("INVALID_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17083);
            } else if (statusMessage.contains("MFA_ENROLLMENT_NOT_FOUND")) {
                status = new Status(17084);
            } else if (statusMessage.contains("ADMIN_ONLY_OPERATION")) {
                status = new Status(17085);
            } else if (statusMessage.contains("UNVERIFIED_EMAIL")) {
                status = new Status(17086);
            } else if (statusMessage.contains("SECOND_FACTOR_EXISTS")) {
                status = new Status(17087);
            } else if (statusMessage.contains("SECOND_FACTOR_LIMIT_EXCEEDED")) {
                status = new Status(17088);
            } else if (statusMessage.contains("UNSUPPORTED_FIRST_FACTOR")) {
                status = new Status(17089);
            } else if (statusMessage.contains("EMAIL_CHANGE_NEEDS_VERIFICATION")) {
                status = new Status(17090);
            }
        }
        zzun zzun = this.zza;
        if (zzun.zzb == 8) {
            boolean unused = zzun.zza = true;
            zzc(new zzui(this, status));
            return;
        }
        zzun.zzn(zzun, status);
        this.zza.zzk(status);
    }

    public final void zzi() throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 5;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzun.zzl(this.zza);
    }

    public final void zzj() throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 6;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzun.zzl(this.zza);
    }

    public final void zzk(String str) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 7;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzun zzun = this.zza;
        zzun.zzn = str;
        zzun.zzl(zzun);
    }

    public final void zzl(String str) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 8;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        this.zza.zzo = str;
        zzc(new zzuf(this, str));
    }

    public final void zzm(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 8;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        boolean unused = this.zza.zza = true;
        zzc(new zzug(this, phoneAuthCredential));
    }

    public final void zzn(String str) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 8;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzun zzun = this.zza;
        zzun.zzo = str;
        boolean unused = zzun.zza = true;
        zzc(new zzuh(this, str));
    }

    public final void zzo(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 2;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzb(status, phoneAuthCredential, (String) null, (String) null);
    }

    public final void zzp() throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 9;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzun.zzl(this.zza);
    }

    public final void zzq(zzno zzno) {
        zzb(zzno.zza(), zzno.zzb(), zzno.zzc(), zzno.zzd());
    }

    public final void zzr(zznq zznq) {
        zzun zzun = this.zza;
        zzun.zzs = zznq;
        zzun.zzk(zzai.zza("REQUIRES_SECOND_FACTOR_AUTH"));
    }
}

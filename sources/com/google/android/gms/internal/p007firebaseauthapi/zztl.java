package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztl */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zztl extends zza implements zztn {
    zztl(IBinder iBinder) {
        super(iBinder, "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
    }

    public final void zzd(zzwg zzwg) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzwg);
        zzJ(1, zza);
    }

    public final void zze(zzwg zzwg, zzvz zzvz) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzwg);
        zzc.zzb(zza, zzvz);
        zzJ(2, zza);
    }

    public final void zzf(zzvl zzvl) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzvl);
        zzJ(3, zza);
    }

    public final void zzg(zzwr zzwr) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzwr);
        zzJ(4, zza);
    }

    public final void zzh(Status status) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, status);
        zzJ(5, zza);
    }

    public final void zzi() throws RemoteException {
        zzJ(6, zza());
    }

    public final void zzj() throws RemoteException {
        zzJ(7, zza());
    }

    public final void zzk(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzJ(8, zza);
    }

    public final void zzl(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzJ(9, zza);
    }

    public final void zzm(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, phoneAuthCredential);
        zzJ(10, zza);
    }

    public final void zzn(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzJ(11, zza);
    }

    public final void zzo(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, status);
        zzc.zzb(zza, phoneAuthCredential);
        zzJ(12, zza);
    }

    public final void zzp() throws RemoteException {
        zzJ(13, zza());
    }

    public final void zzq(zzno zzno) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzno);
        zzJ(14, zza);
    }

    public final void zzr(zznq zznq) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zznq);
        zzJ(15, zza);
    }
}

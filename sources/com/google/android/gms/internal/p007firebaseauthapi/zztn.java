package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztn */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public interface zztn extends IInterface {
    void zzd(zzwg zzwg) throws RemoteException;

    void zze(zzwg zzwg, zzvz zzvz) throws RemoteException;

    void zzf(zzvl zzvl) throws RemoteException;

    void zzg(zzwr zzwr) throws RemoteException;

    void zzh(Status status) throws RemoteException;

    void zzi() throws RemoteException;

    void zzj() throws RemoteException;

    void zzk(String str) throws RemoteException;

    void zzl(String str) throws RemoteException;

    void zzm(PhoneAuthCredential phoneAuthCredential) throws RemoteException;

    void zzn(String str) throws RemoteException;

    void zzo(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException;

    void zzp() throws RemoteException;

    void zzq(zzno zzno) throws RemoteException;

    void zzr(zznq zznq) throws RemoteException;
}

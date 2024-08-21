package com.google.android.gms.internal.p007firebaseauthapi;

import android.app.Activity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzao;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzun */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
abstract class zzun<ResultT, CallbackT> implements zzpn<zztc, ResultT> {
    /* access modifiers changed from: private */
    public boolean zza;
    protected final int zzb;
    final zzuk zzc = new zzuk(this);
    protected FirebaseApp zzd;
    protected FirebaseUser zze;
    protected CallbackT zzf;
    protected zzao zzg;
    protected final List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> zzh = new ArrayList();
    protected Executor zzi;
    protected zzwg zzj;
    protected zzvz zzk;
    protected zzvl zzl;
    protected zzwr zzm;
    protected String zzn;
    protected String zzo;
    protected AuthCredential zzp;
    protected String zzq;
    protected String zzr;
    protected zznq zzs;
    ResultT zzt;
    Status zzu;
    protected zzum zzv;

    public zzun(int i) {
        this.zzb = i;
    }

    static /* synthetic */ void zzl(zzun zzun) {
        zzun.zzc();
        Preconditions.checkState(zzun.zza, "no success or failure set on method implementation");
    }

    static /* synthetic */ void zzn(zzun zzun, Status status) {
        zzao zzao = zzun.zzg;
        if (zzao != null) {
            zzao.zzb(status);
        }
    }

    public abstract void zzc();

    public final zzun<ResultT, CallbackT> zze(FirebaseApp firebaseApp) {
        this.zzd = (FirebaseApp) Preconditions.checkNotNull(firebaseApp, "firebaseApp cannot be null");
        return this;
    }

    public final zzun<ResultT, CallbackT> zzf(FirebaseUser firebaseUser) {
        this.zze = (FirebaseUser) Preconditions.checkNotNull(firebaseUser, "firebaseUser cannot be null");
        return this;
    }

    public final zzun<ResultT, CallbackT> zzg(CallbackT callbackt) {
        this.zzf = Preconditions.checkNotNull(callbackt, "external callback cannot be null");
        return this;
    }

    public final zzun<ResultT, CallbackT> zzh(zzao zzao) {
        this.zzg = (zzao) Preconditions.checkNotNull(zzao, "external failure callback cannot be null");
        return this;
    }

    public final zzun<ResultT, CallbackT> zzi(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor, String str) {
        PhoneAuthProvider.OnVerificationStateChangedCallbacks zzc2 = zzux.zzc(str, onVerificationStateChangedCallbacks, this);
        synchronized (this.zzh) {
            this.zzh.add((PhoneAuthProvider.OnVerificationStateChangedCallbacks) Preconditions.checkNotNull(zzc2));
        }
        if (activity != null) {
            zzue.zza(activity, this.zzh);
        }
        this.zzi = (Executor) Preconditions.checkNotNull(executor);
        return this;
    }

    public final void zzj(ResultT resultt) {
        this.zza = true;
        this.zzt = resultt;
        this.zzv.zza(resultt, (Status) null);
    }

    public final void zzk(Status status) {
        this.zza = true;
        this.zzu = status;
        this.zzv.zza(null, status);
    }
}

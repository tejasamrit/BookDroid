package com.google.android.gms.internal.p007firebaseauthapi;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzag;
import com.google.firebase.auth.internal.zzan;
import com.google.firebase.auth.internal.zzba;
import com.google.firebase.auth.internal.zzbk;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzt;
import com.google.firebase.auth.internal.zzx;
import com.google.firebase.auth.internal.zzz;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsy */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzsy extends zzpo<zztv> {
    private final Context zza;
    private final zztv zzb;
    private final Future<zzpk<zztv>> zzc = zza();

    zzsy(Context context, zztv zztv) {
        this.zza = context;
        this.zzb = zztv;
    }

    static zzx zzS(FirebaseApp firebaseApp, zzvz zzvz) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(zzvz);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new zzt(zzvz, FirebaseAuthProvider.PROVIDER_ID));
        List<zzwm> zzp = zzvz.zzp();
        if (zzp != null && !zzp.isEmpty()) {
            for (int i = 0; i < zzp.size(); i++) {
                arrayList.add(new zzt(zzp.get(i)));
            }
        }
        zzx zzx = new zzx(firebaseApp, arrayList);
        zzx.zzm(new zzz(zzvz.zzh(), zzvz.zzg()));
        zzx.zzn(zzvz.zzi());
        zzx.zzp(zzvz.zzr());
        zzx.zzi(zzba.zzb(zzvz.zzt()));
        return zzx;
    }

    public final Task<Void> zzA(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, String str2) {
        actionCodeSettings.zze(1);
        zzrl zzrl = new zzrl(str, actionCodeSettings, str2, "sendPasswordResetEmail");
        zzrl.zze(firebaseApp);
        return zzc(zzrl);
    }

    public final Task<Void> zzB(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, String str2) {
        actionCodeSettings.zze(6);
        zzrl zzrl = new zzrl(str, actionCodeSettings, str2, "sendSignInLinkToEmail");
        zzrl.zze(firebaseApp);
        return zzc(zzrl);
    }

    public final Task<Void> zzC(FirebaseApp firebaseApp, ActionCodeSettings actionCodeSettings, String str) {
        zzrj zzrj = new zzrj(str, actionCodeSettings);
        zzrj.zze(firebaseApp);
        return zzc(zzrj);
    }

    public final Task<ActionCodeResult> zzD(FirebaseApp firebaseApp, String str, String str2) {
        zzpt zzpt = new zzpt(str, str2);
        zzpt.zze(firebaseApp);
        return zzc(zzpt);
    }

    public final Task<Void> zzE(FirebaseApp firebaseApp, String str, String str2) {
        zzpr zzpr = new zzpr(str, str2);
        zzpr.zze(firebaseApp);
        return zzc(zzpr);
    }

    public final Task<String> zzF(FirebaseApp firebaseApp, String str, String str2) {
        zzsv zzsv = new zzsv(str, str2);
        zzsv.zze(firebaseApp);
        return zzc(zzsv);
    }

    public final Task<Void> zzG(FirebaseApp firebaseApp, String str, String str2, String str3) {
        zzpv zzpv = new zzpv(str, str2, str3);
        zzpv.zze(firebaseApp);
        return zzc(zzpv);
    }

    public final Task<AuthResult> zzH(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, zzbk zzbk) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzbk);
        List<String> zza2 = firebaseUser.zza();
        if (zza2 != null && zza2.contains(authCredential.getProvider())) {
            return Tasks.forException(zzte.zza(new Status(FirebaseError.ERROR_PROVIDER_ALREADY_LINKED)));
        }
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            if (!emailAuthCredential.zzh()) {
                zzqj zzqj = new zzqj(emailAuthCredential);
                zzqj.zze(firebaseApp);
                zzqj.zzf(firebaseUser);
                zzqj.zzg(zzbk);
                zzqj.zzh(zzbk);
                return zzc(zzqj);
            }
            zzqp zzqp = new zzqp(emailAuthCredential);
            zzqp.zze(firebaseApp);
            zzqp.zzf(firebaseUser);
            zzqp.zzg(zzbk);
            zzqp.zzh(zzbk);
            return zzc(zzqp);
        } else if (authCredential instanceof PhoneAuthCredential) {
            zzux.zza();
            zzqn zzqn = new zzqn((PhoneAuthCredential) authCredential);
            zzqn.zze(firebaseApp);
            zzqn.zzf(firebaseUser);
            zzqn.zzg(zzbk);
            zzqn.zzh(zzbk);
            return zzc(zzqn);
        } else {
            Preconditions.checkNotNull(firebaseApp);
            Preconditions.checkNotNull(authCredential);
            Preconditions.checkNotNull(firebaseUser);
            Preconditions.checkNotNull(zzbk);
            zzql zzql = new zzql(authCredential);
            zzql.zze(firebaseApp);
            zzql.zzf(firebaseUser);
            zzql.zzg(zzbk);
            zzql.zzh(zzbk);
            return zzc(zzql);
        }
    }

    public final Task<AuthResult> zzI(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzbk);
        List<String> zza2 = firebaseUser.zza();
        if ((zza2 != null && !zza2.contains(str)) || firebaseUser.isAnonymous()) {
            return Tasks.forException(zzte.zza(new Status(FirebaseError.ERROR_NO_SUCH_PROVIDER, str)));
        }
        if (((str.hashCode() == 1216985755 && str.equals("password")) ? (char) 0 : 65535) != 0) {
            zzsj zzsj = new zzsj(str);
            zzsj.zze(firebaseApp);
            zzsj.zzf(firebaseUser);
            zzsj.zzg(zzbk);
            zzsj.zzh(zzbk);
            return zzc(zzsj);
        }
        zzsh zzsh = new zzsh();
        zzsh.zze(firebaseApp);
        zzsh.zzf(firebaseUser);
        zzsh.zzg(zzbk);
        zzsh.zzh(zzbk);
        return zzc(zzsh);
    }

    public final Task<Void> zzJ(FirebaseApp firebaseApp, FirebaseUser firebaseUser, zzbk zzbk) {
        zzrh zzrh = new zzrh();
        zzrh.zze(firebaseApp);
        zzrh.zzf(firebaseUser);
        zzrh.zzg(zzbk);
        zzrh.zzh(zzbk);
        return zzb(zzrh);
    }

    public final Task<Void> zzK(FirebaseUser firebaseUser, zzan zzan) {
        zzpz zzpz = new zzpz();
        zzpz.zzf(firebaseUser);
        zzpz.zzg(zzan);
        zzpz.zzh(zzan);
        return zzc(zzpz);
    }

    public final Task<Void> zzL(String str) {
        return zzc(new zzrn(str));
    }

    public final Task<Void> zzM(zzag zzag, String str, String str2, long j, boolean z, boolean z2, String str3, String str4, boolean z3, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, Activity activity) {
        zzsb zzsb = new zzsb(zzag, str, str2, j, z, z2, str3, str4, z3);
        String str5 = str;
        zzsb.zzi(onVerificationStateChangedCallbacks, activity, executor, str);
        return zzc(zzsb);
    }

    public final Task<Void> zzN(FirebaseApp firebaseApp, PhoneMultiFactorAssertion phoneMultiFactorAssertion, FirebaseUser firebaseUser, String str, zzg zzg) {
        zzux.zza();
        zzqd zzqd = new zzqd(phoneMultiFactorAssertion, firebaseUser.zzg(), str);
        zzqd.zze(firebaseApp);
        zzqd.zzg(zzg);
        return zzc(zzqd);
    }

    public final Task<Void> zzO(zzag zzag, PhoneMultiFactorInfo phoneMultiFactorInfo, String str, long j, boolean z, boolean z2, String str2, String str3, boolean z3, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, Activity activity) {
        zzsd zzsd = new zzsd(phoneMultiFactorInfo, zzag.zzd(), str, j, z, z2, str2, str3, z3);
        Activity activity2 = activity;
        zzsd.zzi(onVerificationStateChangedCallbacks, activity2, executor, phoneMultiFactorInfo.getUid());
        return zzc(zzsd);
    }

    public final Task<AuthResult> zzP(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneMultiFactorAssertion phoneMultiFactorAssertion, String str, zzg zzg) {
        zzux.zza();
        zzqf zzqf = new zzqf(phoneMultiFactorAssertion, str);
        zzqf.zze(firebaseApp);
        zzqf.zzg(zzg);
        if (firebaseUser != null) {
            zzqf.zzf(firebaseUser);
        }
        return zzc(zzqf);
    }

    public final Task<Void> zzQ(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        zzsf zzsf = new zzsf(firebaseUser.zzg(), str);
        zzsf.zze(firebaseApp);
        zzsf.zzf(firebaseUser);
        zzsf.zzg(zzbk);
        zzsf.zzh(zzbk);
        return zzc(zzsf);
    }

    public final Task<Void> zzR(String str, String str2, ActionCodeSettings actionCodeSettings) {
        actionCodeSettings.zze(7);
        return zzc(new zzst(str, str2, actionCodeSettings));
    }

    /* access modifiers changed from: package-private */
    public final Future<zzpk<zztv>> zza() {
        Future<zzpk<zztv>> future = this.zzc;
        if (future != null) {
            return future;
        }
        return zzh.zza().zza(2).submit(new zzsz(this.zzb, this.zza));
    }

    public final Task<GetTokenResult> zze(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        zzqh zzqh = new zzqh(str);
        zzqh.zze(firebaseApp);
        zzqh.zzf(firebaseUser);
        zzqh.zzg(zzbk);
        zzqh.zzh(zzbk);
        return zzb(zzqh);
    }

    public final Task<AuthResult> zzf(FirebaseApp firebaseApp, String str, String str2, zzg zzg) {
        zzrt zzrt = new zzrt(str, str2);
        zzrt.zze(firebaseApp);
        zzrt.zzg(zzg);
        return zzc(zzrt);
    }

    public final Task<AuthResult> zzg(FirebaseApp firebaseApp, AuthCredential authCredential, String str, zzg zzg) {
        zzrr zzrr = new zzrr(authCredential, str);
        zzrr.zze(firebaseApp);
        zzrr.zzg(zzg);
        return zzc(zzrr);
    }

    public final Task<Void> zzh(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzbk zzbk) {
        zzqr zzqr = new zzqr(authCredential, str);
        zzqr.zze(firebaseApp);
        zzqr.zzf(firebaseUser);
        zzqr.zzg(zzbk);
        zzqr.zzh(zzbk);
        return zzc(zzqr);
    }

    public final Task<AuthResult> zzi(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzbk zzbk) {
        zzqt zzqt = new zzqt(authCredential, str);
        zzqt.zze(firebaseApp);
        zzqt.zzf(firebaseUser);
        zzqt.zzg(zzbk);
        zzqt.zzh(zzbk);
        return zzc(zzqt);
    }

    public final Task<AuthResult> zzj(FirebaseApp firebaseApp, zzg zzg, String str) {
        zzrp zzrp = new zzrp(str);
        zzrp.zze(firebaseApp);
        zzrp.zzg(zzg);
        return zzc(zzrp);
    }

    public final void zzk(FirebaseApp firebaseApp, zzwt zzwt, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        zzsx zzsx = new zzsx(zzwt);
        zzsx.zze(firebaseApp);
        zzsx.zzi(onVerificationStateChangedCallbacks, activity, executor, zzwt.zzb());
        zzc(zzsx);
    }

    public final Task<Void> zzl(FirebaseApp firebaseApp, FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest, zzbk zzbk) {
        zzsr zzsr = new zzsr(userProfileChangeRequest);
        zzsr.zze(firebaseApp);
        zzsr.zzf(firebaseUser);
        zzsr.zzg(zzbk);
        zzsr.zzh(zzbk);
        return zzc(zzsr);
    }

    public final Task<Void> zzm(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        zzsl zzsl = new zzsl(str);
        zzsl.zze(firebaseApp);
        zzsl.zzf(firebaseUser);
        zzsl.zzg(zzbk);
        zzsl.zzh(zzbk);
        return zzc(zzsl);
    }

    public final Task<Void> zzn(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        zzsn zzsn = new zzsn(str);
        zzsn.zze(firebaseApp);
        zzsn.zzf(firebaseUser);
        zzsn.zzg(zzbk);
        zzsn.zzh(zzbk);
        return zzc(zzsn);
    }

    public final Task<Void> zzo(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, zzbk zzbk) {
        zzux.zza();
        zzsp zzsp = new zzsp(phoneAuthCredential);
        zzsp.zze(firebaseApp);
        zzsp.zzf(firebaseUser);
        zzsp.zzg(zzbk);
        zzsp.zzh(zzbk);
        return zzc(zzsp);
    }

    public final Task<AuthResult> zzp(FirebaseApp firebaseApp, String str, String str2, String str3, zzg zzg) {
        zzpx zzpx = new zzpx(str, str2, str3);
        zzpx.zze(firebaseApp);
        zzpx.zzg(zzg);
        return zzc(zzpx);
    }

    public final Task<AuthResult> zzq(FirebaseApp firebaseApp, String str, String str2, String str3, zzg zzg) {
        zzrv zzrv = new zzrv(str, str2, str3);
        zzrv.zze(firebaseApp);
        zzrv.zzg(zzg);
        return zzc(zzrv);
    }

    public final Task<AuthResult> zzr(FirebaseApp firebaseApp, EmailAuthCredential emailAuthCredential, zzg zzg) {
        zzrx zzrx = new zzrx(emailAuthCredential);
        zzrx.zze(firebaseApp);
        zzrx.zzg(zzg);
        return zzc(zzrx);
    }

    public final Task<Void> zzs(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, zzbk zzbk) {
        zzqz zzqz = new zzqz(str, str2, str3);
        zzqz.zze(firebaseApp);
        zzqz.zzf(firebaseUser);
        zzqz.zzg(zzbk);
        zzqz.zzh(zzbk);
        return zzc(zzqz);
    }

    public final Task<AuthResult> zzt(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, zzbk zzbk) {
        zzrb zzrb = new zzrb(str, str2, str3);
        zzrb.zze(firebaseApp);
        zzrb.zzf(firebaseUser);
        zzrb.zzg(zzbk);
        zzrb.zzh(zzbk);
        return zzc(zzrb);
    }

    public final Task<Void> zzu(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, zzbk zzbk) {
        zzqv zzqv = new zzqv(emailAuthCredential);
        zzqv.zze(firebaseApp);
        zzqv.zzf(firebaseUser);
        zzqv.zzg(zzbk);
        zzqv.zzh(zzbk);
        return zzc(zzqv);
    }

    public final Task<AuthResult> zzv(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, zzbk zzbk) {
        zzqx zzqx = new zzqx(emailAuthCredential);
        zzqx.zze(firebaseApp);
        zzqx.zzf(firebaseUser);
        zzqx.zzg(zzbk);
        zzqx.zzh(zzbk);
        return zzc(zzqx);
    }

    public final Task<AuthResult> zzw(FirebaseApp firebaseApp, PhoneAuthCredential phoneAuthCredential, String str, zzg zzg) {
        zzux.zza();
        zzrz zzrz = new zzrz(phoneAuthCredential, str);
        zzrz.zze(firebaseApp);
        zzrz.zzg(zzg);
        return zzc(zzrz);
    }

    public final Task<Void> zzx(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, String str, zzbk zzbk) {
        zzux.zza();
        zzrd zzrd = new zzrd(phoneAuthCredential, str);
        zzrd.zze(firebaseApp);
        zzrd.zzf(firebaseUser);
        zzrd.zzg(zzbk);
        zzrd.zzh(zzbk);
        return zzc(zzrd);
    }

    public final Task<AuthResult> zzy(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, String str, zzbk zzbk) {
        zzux.zza();
        zzrf zzrf = new zzrf(phoneAuthCredential, str);
        zzrf.zze(firebaseApp);
        zzrf.zzf(firebaseUser);
        zzrf.zzg(zzbk);
        zzrf.zzh(zzbk);
        return zzc(zzrf);
    }

    public final Task<SignInMethodQueryResult> zzz(FirebaseApp firebaseApp, String str, String str2) {
        zzqb zzqb = new zzqb(str, str2);
        zzqb.zze(firebaseApp);
        return zzb(zzqb);
    }
}

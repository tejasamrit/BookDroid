package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.auth.PhoneAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztf */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zztf extends zztp {
    private static final Logger zza = new Logger("FirebaseAuth", "FirebaseAuthFallback:");
    private final zzpj zzb;
    private final zzvd zzc;

    public zztf(Context context, String str) {
        Preconditions.checkNotNull(context);
        Context context2 = context;
        this.zzb = new zzpj(new zzub(context2, Preconditions.checkNotEmpty(str), zzua.zzb(), (zzuu) null, (zztr) null, (zzts) null));
        this.zzc = new zzvd(context);
    }

    private static boolean zzH(long j, boolean z) {
        if (j > 0 && z) {
            return true;
        }
        zza.mo13211w("App hash will not be appended to the request.", new Object[0]);
        return false;
    }

    public final void zzA(zzmw zzmw, zztn zztn) {
        Preconditions.checkNotNull(zzmw);
        Preconditions.checkNotNull(zzmw.zza());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzk(zzmw.zza(), new zztb(zztn, zza));
    }

    public final void zzB(zzna zzna, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zzna);
        Preconditions.checkNotNull(zztn);
        String zzb2 = zzna.zzb();
        zztb zztb = new zztb(zztn, zza);
        if (this.zzc.zza(zzb2)) {
            if (zzna.zze()) {
                this.zzc.zze(zzb2);
            } else {
                this.zzc.zzc(zztb, zzb2);
                return;
            }
        }
        long zzd = zzna.zzd();
        boolean zzh = zzna.zzh();
        zzxa zzb3 = zzxa.zzb(zzna.zza(), zzna.zzb(), zzna.zzc(), zzna.zzg(), zzna.zzf());
        if (zzH(zzd, zzh)) {
            zzb3.zzd(new zzvi(this.zzc.zzd()));
        }
        this.zzc.zzb(zzb2, zztb, zzd, zzh);
        this.zzb.zzy(zzb3, new zzva(this.zzc, zztb, zzb2));
    }

    public final void zzC(zzlq zzlq, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zzlq);
        Preconditions.checkNotNull(zztn);
        this.zzb.zzz((Context) null, zzvq.zzb(zzlq.zzb(), zzlq.zza().zzd(), zzlq.zza().getSmsCode(), zzlq.zzc()), zzlq.zzb(), new zztb(zztn, zza));
    }

    public final void zzD(zzne zzne, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zzne);
        Preconditions.checkNotNull(zztn);
        this.zzb.zzx(zzne.zza(), zzne.zzb(), new zztb(zztn, zza));
    }

    public final void zzE(zznc zznc, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zznc);
        Preconditions.checkNotNull(zztn);
        String phoneNumber = zznc.zza().getPhoneNumber();
        zztb zztb = new zztb(zztn, zza);
        if (this.zzc.zza(phoneNumber)) {
            if (zznc.zze()) {
                this.zzc.zze(phoneNumber);
            } else {
                this.zzc.zzc(zztb, phoneNumber);
                return;
            }
        }
        long zzd = zznc.zzd();
        boolean zzh = zznc.zzh();
        zzxc zzb2 = zzxc.zzb(zznc.zzb(), zznc.zza().getUid(), zznc.zza().getPhoneNumber(), zznc.zzc(), zznc.zzg(), zznc.zzf());
        if (zzH(zzd, zzh)) {
            zzb2.zzd(new zzvi(this.zzc.zzd()));
        }
        this.zzc.zzb(phoneNumber, zztb, zzd, zzh);
        this.zzb.zzB(zzb2, new zzva(this.zzc, zztb, phoneNumber));
    }

    public final void zzF(zzls zzls, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zzls);
        Preconditions.checkNotNull(zztn);
        this.zzb.zzA((Context) null, zzvs.zzb(zzls.zzb(), zzls.zza().zzd(), zzls.zza().getSmsCode()), new zztb(zztn, zza));
    }

    public final void zzG(zznm zznm, zztn zztn) {
        Preconditions.checkNotNull(zznm);
        this.zzb.zzC(zzwd.zzb(zznm.zzc(), zznm.zza(), zznm.zzb()), new zztb(zztn, zza));
    }

    public final void zzb(zzlu zzlu, zztn zztn) {
        Preconditions.checkNotNull(zzlu);
        Preconditions.checkNotNull(zztn);
        Preconditions.checkNotEmpty(zzlu.zza());
        this.zzb.zza(zzlu.zza(), new zztb(zztn, zza));
    }

    public final void zzc(zzms zzms, zztn zztn) {
        Preconditions.checkNotNull(zzms);
        Preconditions.checkNotEmpty(zzms.zza());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzb(new zzxj(zzms.zza(), zzms.zzb()), new zztb(zztn, zza));
    }

    public final void zzd(zzmq zzmq, zztn zztn) {
        Preconditions.checkNotNull(zzmq);
        Preconditions.checkNotNull(zzmq.zza());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzc((Context) null, zzmq.zza(), new zztb(zztn, zza));
    }

    public final void zze(zznk zznk, zztn zztn) {
        Preconditions.checkNotNull(zznk);
        Preconditions.checkNotEmpty(zznk.zzb());
        Preconditions.checkNotNull(zznk.zza());
        Preconditions.checkNotNull(zztn);
        this.zzb.zze(zznk.zzb(), zznk.zza(), new zztb(zztn, zza));
    }

    public final void zzf(zzle zzle, zztn zztn) {
        Preconditions.checkNotNull(zzle);
        Preconditions.checkNotEmpty(zzle.zza());
        Preconditions.checkNotEmpty(zzle.zzb());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzf(zzle.zza(), zzle.zzb(), new zztb(zztn, zza));
    }

    public final void zzg(zzlg zzlg, zztn zztn) {
        Preconditions.checkNotNull(zzlg);
        Preconditions.checkNotEmpty(zzlg.zza());
        Preconditions.checkNotEmpty(zzlg.zzb());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzg(zzlg.zza(), zzlg.zzb(), new zztb(zztn, zza));
    }

    public final void zzh(zzlm zzlm, zztn zztn) {
        Preconditions.checkNotNull(zzlm);
        Preconditions.checkNotEmpty(zzlm.zza());
        Preconditions.checkNotEmpty(zzlm.zzb());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzi(zzlm.zza(), zzlm.zzb(), zzlm.zzc(), new zztb(zztn, zza));
    }

    public final void zzi(zzmu zzmu, zztn zztn) {
        Preconditions.checkNotNull(zzmu);
        Preconditions.checkNotEmpty(zzmu.zza());
        Preconditions.checkNotEmpty(zzmu.zzb());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzj((Context) null, zzmu.zza(), zzmu.zzb(), zzmu.zzc(), new zztb(zztn, zza));
    }

    public final void zzj(zzlw zzlw, zztn zztn) {
        Preconditions.checkNotNull(zzlw);
        Preconditions.checkNotEmpty(zzlw.zza());
        this.zzb.zzl(zzlw.zza(), zzlw.zzb(), new zztb(zztn, zza));
    }

    public final void zzk(zzly zzly, zztn zztn) {
        Preconditions.checkNotNull(zzly);
        Preconditions.checkNotEmpty(zzly.zza());
        Preconditions.checkNotEmpty(zzly.zzb());
        Preconditions.checkNotEmpty(zzly.zzc());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzs(zzly.zza(), zzly.zzb(), zzly.zzc(), new zztb(zztn, zza));
    }

    public final void zzl(zzma zzma, zztn zztn) {
        Preconditions.checkNotNull(zzma);
        Preconditions.checkNotEmpty(zzma.zza());
        Preconditions.checkNotNull(zzma.zzb());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzu(zzma.zza(), zzma.zzb(), new zztb(zztn, zza));
    }

    public final void zzm(zzng zzng, zztn zztn) {
        Preconditions.checkNotNull(zzng);
        Preconditions.checkNotEmpty(zzng.zza());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzv(zzng.zza(), new zztb(zztn, zza));
    }

    public final void zzn(zzni zzni, zztn zztn) {
        Preconditions.checkNotNull(zzni);
        Preconditions.checkNotEmpty(zzni.zza());
        Preconditions.checkNotEmpty(zzni.zzb());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzw(zzni.zza(), zzni.zzb(), new zztb(zztn, zza));
    }

    public final void zzo(zzme zzme, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zzme);
        Preconditions.checkNotEmpty(zzme.zza());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzD(zzme.zza(), new zztb(zztn, zza));
    }

    public final void zzp(zzmo zzmo, zztn zztn) {
        Preconditions.checkNotNull(zzmo);
        Preconditions.checkNotNull(zztn);
        this.zzb.zzd(zzmo.zza(), new zztb(zztn, zza));
    }

    public final void zzq(zzlo zzlo, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zzlo);
        Preconditions.checkNotEmpty(zzlo.zza());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzE(zzlo.zza(), new zztb(zztn, zza));
    }

    public final void zzr(zzli zzli, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zzli);
        Preconditions.checkNotEmpty(zzli.zza());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzo(zzli.zza(), zzli.zzb(), new zztb(zztn, zza));
    }

    public final void zzs(zzlc zzlc, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zzlc);
        Preconditions.checkNotEmpty(zzlc.zza());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzh(zzlc.zza(), zzlc.zzb(), new zztb(zztn, zza));
    }

    public final void zzt(zzlk zzlk, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zzlk);
        Preconditions.checkNotEmpty(zzlk.zza());
        Preconditions.checkNotEmpty(zzlk.zzb());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzp(zzlk.zza(), zzlk.zzb(), zzlk.zzc(), new zztb(zztn, zza));
    }

    public final void zzu(zzmk zzmk, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zztn);
        Preconditions.checkNotNull(zzmk);
        zzwt zzwt = (zzwt) Preconditions.checkNotNull(zzmk.zza());
        String zzb2 = zzwt.zzb();
        zztb zztb = new zztb(zztn, zza);
        if (this.zzc.zza(zzb2)) {
            if (zzwt.zzd()) {
                this.zzc.zze(zzb2);
            } else {
                this.zzc.zzc(zztb, zzb2);
                return;
            }
        }
        long zzc2 = zzwt.zzc();
        boolean zzf = zzwt.zzf();
        if (zzH(zzc2, zzf)) {
            zzwt.zzg(new zzvi(this.zzc.zzd()));
        }
        this.zzc.zzb(zzb2, zztb, zzc2, zzf);
        this.zzb.zzq(zzwt, new zzva(this.zzc, zztb, zzb2));
    }

    public final void zzv(zzmy zzmy, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zztn);
        Preconditions.checkNotNull(zzmy);
        this.zzb.zzr((Context) null, zzut.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzmy.zza())), new zztb(zztn, zza));
    }

    public final void zzw(zzmc zzmc, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zztn);
        Preconditions.checkNotNull(zzmc);
        this.zzb.zzt((Context) null, Preconditions.checkNotEmpty(zzmc.zza()), zzut.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzmc.zzb())), new zztb(zztn, zza));
    }

    public final void zzx(zzmg zzmg, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zzmg);
        Preconditions.checkNotEmpty(zzmg.zza());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzn(zzmg.zza(), zzmg.zzb(), new zztb(zztn, zza));
    }

    public final void zzy(zzmm zzmm, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zzmm);
        Preconditions.checkNotNull(zztn);
        this.zzb.zzF(zzmm.zza(), new zztb(zztn, zza));
    }

    public final void zzz(zzmi zzmi, zztn zztn) throws RemoteException {
        Preconditions.checkNotNull(zzmi);
        Preconditions.checkNotEmpty(zzmi.zza());
        Preconditions.checkNotNull(zztn);
        this.zzb.zzm(zzmi.zza(), zzmi.zzb(), zzmi.zzc(), new zztb(zztn, zza));
    }
}

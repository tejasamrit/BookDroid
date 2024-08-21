package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpj */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzpj {
    /* access modifiers changed from: private */
    public final zzuq zza;

    public zzpj(zzuq zzuq) {
        this.zza = (zzuq) Preconditions.checkNotNull(zzuq);
    }

    static /* synthetic */ void zzH(zzpj zzpj, zzxi zzxi, zztb zztb, zzuo zzuo) {
        if (zzxi.zzk()) {
            zztb.zzn(new zzno(zzxi.zzb() ? new Status(FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL) : zzai.zza(zzxi.zzj()), zzxi.zzp(), zzxi.zzd(), zzxi.zzl()));
            return;
        }
        zzpj.zzO(new zzwg(zzxi.zzg(), zzxi.zzc(), Long.valueOf(zzxi.zzh()), "Bearer"), zzxi.zzf(), zzxi.zze(), Boolean.valueOf(zzxi.zzi()), zzxi.zzp(), zztb, zzuo);
    }

    static /* synthetic */ void zzI(zzpj zzpj, zztb zztb, zzwg zzwg, zzww zzww, zzuo zzuo) {
        Preconditions.checkNotNull(zztb);
        Preconditions.checkNotNull(zzwg);
        Preconditions.checkNotNull(zzww);
        Preconditions.checkNotNull(zzuo);
        zzpj.zza.zzh(new zzvw(zzwg.zze()), new zznw(zzpj, zzuo, zztb, zzwg, zzww));
    }

    static /* synthetic */ void zzK(zzpj zzpj, zztb zztb, zzwg zzwg, zzvz zzvz, zzww zzww, zzuo zzuo) {
        Preconditions.checkNotNull(zztb);
        Preconditions.checkNotNull(zzwg);
        Preconditions.checkNotNull(zzvz);
        Preconditions.checkNotNull(zzww);
        Preconditions.checkNotNull(zzuo);
        zzpj.zza.zzi(zzww, new zznx(zzpj, zzww, zzvz, zztb, zzwg, zzuo));
    }

    private final void zzM(String str, zzup<zzwg> zzup) {
        Preconditions.checkNotNull(zzup);
        Preconditions.checkNotEmpty(str);
        zzwg zzj = zzwg.zzj(str);
        if (zzj.zzb()) {
            zzup.zzb(zzj);
            return;
        }
        this.zza.zzb(new zzvv(zzj.zzd()), new zzpi(this, zzup));
    }

    /* access modifiers changed from: private */
    public final void zzN(zzvo zzvo, zztb zztb) {
        Preconditions.checkNotNull(zzvo);
        Preconditions.checkNotNull(zztb);
        this.zza.zzp(zzvo, new zznv(this, zztb));
    }

    /* access modifiers changed from: private */
    public final void zzO(zzwg zzwg, String str, String str2, Boolean bool, zze zze, zztb zztb, zzuo zzuo) {
        Preconditions.checkNotNull(zzwg);
        Preconditions.checkNotNull(zzuo);
        Preconditions.checkNotNull(zztb);
        this.zza.zzh(new zzvw(zzwg.zze()), new zzny(this, zzuo, str2, str, bool, zze, zztb, zzwg));
    }

    private final void zzP(zzwd zzwd, zztb zztb) {
        Preconditions.checkNotNull(zzwd);
        Preconditions.checkNotNull(zztb);
        this.zza.zzk(zzwd, new zzpb(this, zztb));
    }

    public final void zzA(Context context, zzvs zzvs, zztb zztb) {
        Preconditions.checkNotNull(zzvs);
        Preconditions.checkNotNull(zztb);
        this.zza.zzu((Context) null, zzvs, new zzou(this, zztb));
    }

    public final void zzB(zzxc zzxc, zztb zztb) {
        Preconditions.checkNotNull(zzxc);
        Preconditions.checkNotNull(zztb);
        this.zza.zzt(zzxc, new zzov(this, zztb));
    }

    public final void zzC(zzwd zzwd, zztb zztb) {
        zzP(zzwd, zztb);
    }

    public final void zzD(String str, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztb);
        zzM(str, new zzox(this, zztb));
    }

    public final void zzE(String str, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztb);
        zzM(str, new zzoz(this, zztb));
    }

    public final void zzF(String str, zztb zztb) {
        Preconditions.checkNotNull(zztb);
        this.zza.zzo(str, new zzpa(this, zztb));
    }

    public final void zza(String str, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztb);
        this.zza.zzb(new zzvv(str), new zzoc(this, zztb));
    }

    public final void zzb(zzxj zzxj, zztb zztb) {
        Preconditions.checkNotNull(zzxj);
        Preconditions.checkNotNull(zztb);
        this.zza.zzc(zzxj, new zzor(this, zztb));
    }

    public final void zzc(Context context, zzxg zzxg, zztb zztb) {
        Preconditions.checkNotNull(zzxg);
        Preconditions.checkNotNull(zztb);
        zzxg.zzf(true);
        this.zza.zzd((Context) null, zzxg, new zzpc(this, zztb));
    }

    public final void zzd(String str, zztb zztb) {
        Preconditions.checkNotNull(zztb);
        this.zza.zze(new zzwy(str), new zzpd(this, zztb));
    }

    public final void zze(String str, UserProfileChangeRequest userProfileChangeRequest, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zztb);
        zzM(str, new zzpe(this, userProfileChangeRequest, zztb));
    }

    public final void zzf(String str, String str2, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztb);
        zzM(str, new zzpf(this, str2, zztb));
    }

    public final void zzg(String str, String str2, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztb);
        zzM(str, new zzpg(this, str2, zztb));
    }

    public final void zzh(String str, String str2, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztb);
        zzww zzww = new zzww();
        zzww.zzm(str);
        zzww.zzn(str2);
        this.zza.zzi(zzww, new zzph(this, zztb));
    }

    public final void zzi(String str, String str2, String str3, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztb);
        this.zza.zze(new zzwy(str, str2, (String) null, str3), new zzns(this, zztb));
    }

    public final void zzj(Context context, String str, String str2, String str3, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztb);
        this.zza.zzf((Context) null, new zzxm(str, str2, str3), new zznt(this, zztb));
    }

    public final void zzk(EmailAuthCredential emailAuthCredential, zztb zztb) {
        Preconditions.checkNotNull(emailAuthCredential);
        Preconditions.checkNotNull(zztb);
        if (emailAuthCredential.zzf()) {
            zzM(emailAuthCredential.zze(), new zznu(this, emailAuthCredential, zztb));
        } else {
            zzN(new zzvo(emailAuthCredential, (String) null), zztb);
        }
    }

    public final void zzl(String str, String str2, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztb);
        this.zza.zzj(new zzvk(str, str2), new zznz(this, zztb));
    }

    public final void zzm(String str, ActionCodeSettings actionCodeSettings, String str2, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztb);
        zzwd zzwd = new zzwd(actionCodeSettings.zzf());
        zzwd.zzc(str);
        zzwd.zze(actionCodeSettings);
        zzwd.zzf(str2);
        this.zza.zzk(zzwd, new zzoa(this, zztb));
    }

    public final void zzn(String str, ActionCodeSettings actionCodeSettings, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztb);
        zzwd zzwd = new zzwd(4);
        zzwd.zzd(str);
        if (actionCodeSettings != null) {
            zzwd.zze(actionCodeSettings);
        }
        zzP(zzwd, zztb);
    }

    public final void zzo(String str, String str2, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztb);
        this.zza.zzg(new zzwq(str, (String) null, str2), new zzob(this, zztb));
    }

    public final void zzp(String str, String str2, String str3, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztb);
        this.zza.zzg(new zzwq(str, str2, str3), new zzod(this, zztb));
    }

    public final void zzq(zzwt zzwt, zztb zztb) {
        Preconditions.checkNotEmpty(zzwt.zzb());
        Preconditions.checkNotNull(zztb);
        this.zza.zzl(zzwt, new zzoe(this, zztb));
    }

    public final void zzr(Context context, zzxo zzxo, zztb zztb) {
        Preconditions.checkNotNull(zzxo);
        Preconditions.checkNotNull(zztb);
        this.zza.zzm((Context) null, zzxo, new zzof(this, zztb));
    }

    public final void zzs(String str, String str2, String str3, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zztb);
        zzM(str3, new zzog(this, str, str2, zztb));
    }

    public final void zzt(Context context, String str, zzxo zzxo, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxo);
        Preconditions.checkNotNull(zztb);
        zzM(str, new zzoi(this, zzxo, (Context) null, zztb));
    }

    public final void zzu(String str, zzxg zzxg, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxg);
        Preconditions.checkNotNull(zztb);
        zzM(str, new zzok(this, zzxg, zztb));
    }

    public final void zzv(String str, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztb);
        zzM(str, new zzol(this, zztb));
    }

    public final void zzw(String str, String str2, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztb);
        zzM(str2, new zzon(this, str, zztb));
    }

    public final void zzx(String str, String str2, zztb zztb) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztb);
        zzM(str, new zzop(this, str2, zztb));
    }

    public final void zzy(zzxa zzxa, zztb zztb) {
        Preconditions.checkNotNull(zzxa);
        Preconditions.checkNotNull(zztb);
        this.zza.zzq(zzxa, new zzoq(this, zztb));
    }

    public final void zzz(Context context, zzvq zzvq, String str, zztb zztb) {
        Preconditions.checkNotNull(zzvq);
        Preconditions.checkNotNull(zztb);
        zzM(str, new zzot(this, zzvq, (Context) null, zztb));
    }
}

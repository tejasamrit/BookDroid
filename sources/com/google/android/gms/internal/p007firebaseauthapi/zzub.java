package com.google.android.gms.internal.p007firebaseauthapi;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzub */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzub extends zzuq implements zzvg {
    zzuc zza;
    private zztr zzb;
    private zzts zzc;
    private zzuu zzd;
    private final zzua zze;
    private final Context zzf;
    private final String zzg;

    zzub(Context context, String str, zzua zzua, zzuu zzuu, zztr zztr, zzts zzts) {
        this.zzf = ((Context) Preconditions.checkNotNull(context)).getApplicationContext();
        this.zzg = Preconditions.checkNotEmpty(str);
        this.zze = (zzua) Preconditions.checkNotNull(zzua);
        zzv((zzuu) null, (zztr) null, (zzts) null);
        zzvh.zzc(str, this);
    }

    private final void zzv(zzuu zzuu, zztr zztr, zzts zzts) {
        this.zzd = null;
        this.zzb = null;
        this.zzc = null;
        String zza2 = zzve.zza("firebear.secureToken");
        if (TextUtils.isEmpty(zza2)) {
            zza2 = zzvh.zzd(this.zzg);
        } else {
            String valueOf = String.valueOf(zza2);
            Log.e("LocalClient", valueOf.length() != 0 ? "Found hermetic configuration for secureToken URL: ".concat(valueOf) : new String("Found hermetic configuration for secureToken URL: "));
        }
        if (this.zzd == null) {
            this.zzd = new zzuu(zza2, zzw());
        }
        String zza3 = zzve.zza("firebear.identityToolkit");
        if (TextUtils.isEmpty(zza3)) {
            zza3 = zzvh.zze(this.zzg);
        } else {
            String valueOf2 = String.valueOf(zza3);
            Log.e("LocalClient", valueOf2.length() != 0 ? "Found hermetic configuration for identityToolkit URL: ".concat(valueOf2) : new String("Found hermetic configuration for identityToolkit URL: "));
        }
        if (this.zzb == null) {
            this.zzb = new zztr(zza3, zzw());
        }
        String zza4 = zzve.zza("firebear.identityToolkitV2");
        if (TextUtils.isEmpty(zza4)) {
            zza4 = zzvh.zzf(this.zzg);
        } else {
            String valueOf3 = String.valueOf(zza4);
            Log.e("LocalClient", valueOf3.length() != 0 ? "Found hermetic configuration for identityToolkitV2 URL: ".concat(valueOf3) : new String("Found hermetic configuration for identityToolkitV2 URL: "));
        }
        if (this.zzc == null) {
            this.zzc = new zzts(zza4, zzw());
        }
    }

    private final zzuc zzw() {
        if (this.zza == null) {
            this.zza = new zzuc(this.zzf, this.zze.zza());
        }
        return this.zza;
    }

    public final void zza() {
        zzv((zzuu) null, (zztr) null, (zzts) null);
    }

    public final void zzb(zzvv zzvv, zzup<zzwg> zzup) {
        Preconditions.checkNotNull(zzvv);
        Preconditions.checkNotNull(zzup);
        zzuu zzuu = this.zzd;
        zzur.zza(zzuu.zza("/token", this.zzg), zzvv, zzup, zzwg.class, zzuu.zzb);
    }

    public final void zzc(zzxj zzxj, zzup<zzxk> zzup) {
        Preconditions.checkNotNull(zzxj);
        Preconditions.checkNotNull(zzup);
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/verifyCustomToken", this.zzg), zzxj, zzup, zzxk.class, zztr.zzb);
    }

    public final void zzd(Context context, zzxg zzxg, zzup<zzxi> zzup) {
        Preconditions.checkNotNull(zzxg);
        Preconditions.checkNotNull(zzup);
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/verifyAssertion", this.zzg), zzxg, zzup, zzxi.class, zztr.zzb);
    }

    public final void zze(zzwy zzwy, zzup<zzwz> zzup) {
        Preconditions.checkNotNull(zzwy);
        Preconditions.checkNotNull(zzup);
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/signupNewUser", this.zzg), zzwy, zzup, zzwz.class, zztr.zzb);
    }

    public final void zzf(Context context, zzxm zzxm, zzup<zzxn> zzup) {
        Preconditions.checkNotNull(zzxm);
        Preconditions.checkNotNull(zzup);
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/verifyPassword", this.zzg), zzxm, zzup, zzxn.class, zztr.zzb);
    }

    public final void zzg(zzwq zzwq, zzup<zzwr> zzup) {
        Preconditions.checkNotNull(zzwq);
        Preconditions.checkNotNull(zzup);
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/resetPassword", this.zzg), zzwq, zzup, zzwr.class, zztr.zzb);
    }

    public final void zzh(zzvw zzvw, zzup<zzvx> zzup) {
        Preconditions.checkNotNull(zzvw);
        Preconditions.checkNotNull(zzup);
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/getAccountInfo", this.zzg), zzvw, zzup, zzvx.class, zztr.zzb);
    }

    public final void zzi(zzww zzww, zzup<zzwx> zzup) {
        Preconditions.checkNotNull(zzww);
        Preconditions.checkNotNull(zzup);
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/setAccountInfo", this.zzg), zzww, zzup, zzwx.class, zztr.zzb);
    }

    public final void zzj(zzvk zzvk, zzup<zzvl> zzup) {
        Preconditions.checkNotNull(zzvk);
        Preconditions.checkNotNull(zzup);
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/createAuthUri", this.zzg), zzvk, zzup, zzvl.class, zztr.zzb);
    }

    public final void zzk(zzwd zzwd, zzup<zzwe> zzup) {
        Preconditions.checkNotNull(zzwd);
        Preconditions.checkNotNull(zzup);
        if (zzwd.zzg() != null) {
            zzw().zzc(zzwd.zzg().zzd());
        }
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/getOobConfirmationCode", this.zzg), zzwd, zzup, zzwe.class, zztr.zzb);
    }

    public final void zzl(zzwt zzwt, zzup<zzwv> zzup) {
        Preconditions.checkNotNull(zzwt);
        Preconditions.checkNotNull(zzup);
        if (!TextUtils.isEmpty(zzwt.zze())) {
            zzw().zzc(zzwt.zze());
        }
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/sendVerificationCode", this.zzg), zzwt, zzup, zzwv.class, zztr.zzb);
    }

    public final void zzm(Context context, zzxo zzxo, zzup<zzxp> zzup) {
        Preconditions.checkNotNull(zzxo);
        Preconditions.checkNotNull(zzup);
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/verifyPhoneNumber", this.zzg), zzxo, zzup, zzxp.class, zztr.zzb);
    }

    public final void zzn(zzvn zzvn, zzup<Void> zzup) {
        Preconditions.checkNotNull(zzvn);
        Preconditions.checkNotNull(zzup);
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/deleteAccount", this.zzg), zzvn, zzup, Void.class, zztr.zzb);
    }

    public final void zzo(String str, zzup<Void> zzup) {
        Preconditions.checkNotNull(zzup);
        zzw().zzb(str);
        ((zzpa) zzup).zza.zzm();
    }

    public final void zzp(zzvo zzvo, zzup<zzvp> zzup) {
        Preconditions.checkNotNull(zzvo);
        Preconditions.checkNotNull(zzup);
        zztr zztr = this.zzb;
        zzur.zza(zztr.zza("/emailLinkSignin", this.zzg), zzvo, zzup, zzvp.class, zztr.zzb);
    }

    public final void zzq(zzxa zzxa, zzup<zzxb> zzup) {
        Preconditions.checkNotNull(zzxa);
        Preconditions.checkNotNull(zzup);
        if (!TextUtils.isEmpty(zzxa.zzc())) {
            zzw().zzc(zzxa.zzc());
        }
        zzts zzts = this.zzc;
        zzur.zza(zzts.zza("/mfaEnrollment:start", this.zzg), zzxa, zzup, zzxb.class, zzts.zzb);
    }

    public final void zzr(Context context, zzvq zzvq, zzup<zzvr> zzup) {
        Preconditions.checkNotNull(zzvq);
        Preconditions.checkNotNull(zzup);
        zzts zzts = this.zzc;
        zzur.zza(zzts.zza("/mfaEnrollment:finalize", this.zzg), zzvq, zzup, zzvr.class, zzts.zzb);
    }

    public final void zzs(zzxq zzxq, zzup<zzxr> zzup) {
        Preconditions.checkNotNull(zzxq);
        Preconditions.checkNotNull(zzup);
        zzts zzts = this.zzc;
        zzur.zza(zzts.zza("/mfaEnrollment:withdraw", this.zzg), zzxq, zzup, zzxr.class, zzts.zzb);
    }

    public final void zzt(zzxc zzxc, zzup<zzxd> zzup) {
        Preconditions.checkNotNull(zzxc);
        Preconditions.checkNotNull(zzup);
        if (!TextUtils.isEmpty(zzxc.zzc())) {
            zzw().zzc(zzxc.zzc());
        }
        zzts zzts = this.zzc;
        zzur.zza(zzts.zza("/mfaSignIn:start", this.zzg), zzxc, zzup, zzxd.class, zzts.zzb);
    }

    public final void zzu(Context context, zzvs zzvs, zzup<zzvt> zzup) {
        Preconditions.checkNotNull(zzvs);
        Preconditions.checkNotNull(zzup);
        zzts zzts = this.zzc;
        zzur.zza(zzts.zza("/mfaSignIn:finalize", this.zzg), zzvs, zzup, zzvt.class, zzts.zzb);
    }
}

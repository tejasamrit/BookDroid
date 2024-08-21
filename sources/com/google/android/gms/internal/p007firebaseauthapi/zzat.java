package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzat */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzat {
    private final zzib zza;

    private zzat(zzib zzib) {
        this.zza = zzib;
    }

    static final zzat zza(zzib zzib) throws GeneralSecurityException {
        zzg(zzib);
        return new zzat(zzib);
    }

    public static void zzg(zzib zzib) throws GeneralSecurityException {
        if (zzib == null || zzib.zzc() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    public static final zzat zzi(zzdo zzdo, zzag zzag) throws GeneralSecurityException, IOException {
        zzgy zzb = zzdo.zzb();
        if (zzb == null || zzb.zza().zzc() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        try {
            zzib zze = zzib.zze(zzag.zzb(zzb.zza().zzp(), new byte[0]), zzzb.zza());
            zzg(zze);
            return new zzat(zze);
        } catch (zzzw unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    public final String toString() {
        return zzbl.zza(this.zza).toString();
    }

    /* access modifiers changed from: package-private */
    public final zzib zzb() {
        return this.zza;
    }

    public final zzig zzc() {
        return zzbl.zza(this.zza);
    }

    public final void zzd(zzav zzav, zzag zzag) throws GeneralSecurityException, IOException {
        zzib zzib = this.zza;
        byte[] zza2 = zzag.zza(zzib.zzI(), new byte[0]);
        try {
            if (zzib.zze(zzag.zzb(zza2, new byte[0]), zzzb.zza()).equals(zzib)) {
                zzgx zzc = zzgy.zzc();
                zzc.zza(zzym.zzm(zza2));
                zzc.zzb(zzbl.zza(zzib));
                zzav.zzc((zzgy) zzc.zzl());
                return;
            }
            throw new GeneralSecurityException("cannot encrypt keyset");
        } catch (zzzw unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zze(com.google.android.gms.internal.p007firebaseauthapi.zzav r5) throws java.security.GeneralSecurityException, java.io.IOException {
        /*
            r4 = this;
            com.google.android.gms.internal.firebase-auth-api.zzib r0 = r4.zza
            java.util.List r0 = r0.zzb()
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0060
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.firebase-auth-api.zzia r1 = (com.google.android.gms.internal.p007firebaseauthapi.zzia) r1
            com.google.android.gms.internal.firebase-auth-api.zzho r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzhn r2 = r2.zzc()
            com.google.android.gms.internal.firebase-auth-api.zzhn r3 = com.google.android.gms.internal.p007firebaseauthapi.zzhn.UNKNOWN_KEYMATERIAL
            if (r2 == r3) goto L_0x003b
            com.google.android.gms.internal.firebase-auth-api.zzho r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzhn r2 = r2.zzc()
            com.google.android.gms.internal.firebase-auth-api.zzhn r3 = com.google.android.gms.internal.p007firebaseauthapi.zzhn.SYMMETRIC
            if (r2 == r3) goto L_0x003b
            com.google.android.gms.internal.firebase-auth-api.zzho r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzhn r2 = r2.zzc()
            com.google.android.gms.internal.firebase-auth-api.zzhn r3 = com.google.android.gms.internal.p007firebaseauthapi.zzhn.ASYMMETRIC_PRIVATE
            if (r2 == r3) goto L_0x003b
            goto L_0x000a
        L_0x003b:
            java.security.GeneralSecurityException r5 = new java.security.GeneralSecurityException
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r2 = 0
            com.google.android.gms.internal.firebase-auth-api.zzho r3 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzhn r3 = r3.zzc()
            r0[r2] = r3
            r2 = 1
            com.google.android.gms.internal.firebase-auth-api.zzho r1 = r1.zzb()
            java.lang.String r1 = r1.zza()
            r0[r2] = r1
            java.lang.String r1 = "keyset contains key material of type %s for type url %s"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            r5.<init>(r0)
            throw r5
        L_0x0060:
            com.google.android.gms.internal.firebase-auth-api.zzib r0 = r4.zza
            r5.zzb(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzat.zze(com.google.android.gms.internal.firebase-auth-api.zzav):void");
    }

    public final <P> P zzh(Class<P> cls) throws GeneralSecurityException {
        Class<?> zzm = zzbk.zzm(cls);
        if (zzm == null) {
            String valueOf = String.valueOf(cls.getName());
            throw new GeneralSecurityException(valueOf.length() != 0 ? "No wrapper found for ".concat(valueOf) : new String("No wrapper found for "));
        }
        zzbl.zzb(this.zza);
        zzbc<P> zzb = zzbc.zzb(zzm);
        for (zzia next : this.zza.zzb()) {
            if (next.zzc() == zzhq.ENABLED) {
                zzba<P> zzd = zzb.zzd(zzbk.zzk(next.zzb(), zzm), next);
                if (next.zzd() == this.zza.zza()) {
                    zzb.zzc(zzd);
                }
            }
        }
        return zzbk.zzl(zzb, cls);
    }

    public final zzat zzf() throws GeneralSecurityException {
        if (this.zza != null) {
            zzhy zzf = zzib.zzf();
            for (zzia next : this.zza.zzb()) {
                zzho zzb = next.zzb();
                if (zzb.zzc() == zzhn.ASYMMETRIC_PRIVATE) {
                    zzho zzg = zzbk.zzg(zzb.zza(), zzb.zzb());
                    zzbk.zzj(zzg);
                    zzhz zzf2 = zzia.zzf();
                    zzf2.zzm(next);
                    zzf2.zza(zzg);
                    zzf.zze((zzia) zzf2.zzl());
                } else {
                    throw new GeneralSecurityException("The keyset contains a non-private key");
                }
            }
            zzf.zza(this.zza.zza());
            return new zzat((zzib) zzf.zzl());
        }
        throw new GeneralSecurityException("cleartext keyset is not available");
    }
}

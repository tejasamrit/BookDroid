package com.google.android.gms.internal.p007firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaaj */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzaaj implements zzabe {
    private static final zzaap zzb = new zzaah();
    private final zzaap zza;

    public zzaaj() {
        zzaap zzaap;
        zzaap[] zzaapArr = new zzaap[2];
        zzaapArr[0] = zzzj.zza();
        try {
            zzaap = (zzaap) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            zzaap = zzb;
        }
        zzaapArr[1] = zzaap;
        zzaai zzaai = new zzaai(zzaapArr);
        zzzu.zzb(zzaai, "messageInfoFactory");
        this.zza = zzaai;
    }

    private static boolean zzb(zzaao zzaao) {
        return zzaao.zzc() == 1;
    }

    public final <T> zzabd<T> zza(Class<T> cls) {
        zzabf.zza(cls);
        zzaao zzc = this.zza.zzc(cls);
        if (zzc.zza()) {
            if (zzzo.class.isAssignableFrom(cls)) {
                return zzaav.zzg(zzabf.zzC(), zzze.zza(), zzc.zzb());
            }
            return zzaav.zzg(zzabf.zzA(), zzze.zzb(), zzc.zzb());
        } else if (zzzo.class.isAssignableFrom(cls)) {
            if (zzb(zzc)) {
                return zzaau.zzl(cls, zzc, zzaax.zzb(), zzaaf.zze(), zzabf.zzC(), zzze.zza(), zzaan.zzb());
            }
            return zzaau.zzl(cls, zzc, zzaax.zzb(), zzaaf.zze(), zzabf.zzC(), (zzzc<?>) null, zzaan.zzb());
        } else if (zzb(zzc)) {
            return zzaau.zzl(cls, zzc, zzaax.zza(), zzaaf.zzd(), zzabf.zzA(), zzze.zzb(), zzaan.zza());
        } else {
            return zzaau.zzl(cls, zzc, zzaax.zza(), zzaaf.zzd(), zzabf.zzB(), (zzzc<?>) null, zzaan.zza());
        }
    }
}

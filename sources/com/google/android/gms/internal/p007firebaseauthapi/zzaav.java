package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaav */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzaav<T> implements zzabd<T> {
    private final zzaar zza;
    private final zzabr<?, ?> zzb;
    private final boolean zzc;
    private final zzzc<?> zzd;

    private zzaav(zzabr<?, ?> zzabr, zzzc<?> zzzc, zzaar zzaar) {
        this.zzb = zzabr;
        this.zzc = zzzc.zza(zzaar);
        this.zzd = zzzc;
        this.zza = zzaar;
    }

    static <T> zzaav<T> zzg(zzabr<?, ?> zzabr, zzzc<?> zzzc, zzaar zzaar) {
        return new zzaav<>(zzabr, zzzc, zzaar);
    }

    public final T zza() {
        return this.zza.zzH().zzn();
    }

    public final boolean zzb(T t, T t2) {
        if (!this.zzb.zzj(t).equals(this.zzb.zzj(t2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zzb(t);
        this.zzd.zzb(t2);
        throw null;
    }

    public final int zzc(T t) {
        int hashCode = this.zzb.zzj(t).hashCode();
        if (!this.zzc) {
            return hashCode;
        }
        this.zzd.zzb(t);
        throw null;
    }

    public final void zzd(T t, T t2) {
        zzabf.zzF(this.zzb, t, t2);
        if (this.zzc) {
            zzabf.zzE(this.zzd, t, t2);
        }
    }

    public final int zze(T t) {
        zzabr<?, ?> zzabr = this.zzb;
        int zzp = zzabr.zzp(zzabr.zzj(t));
        if (!this.zzc) {
            return zzp;
        }
        this.zzd.zzb(t);
        throw null;
    }

    public final void zzf(T t, zzabc zzabc, zzzb zzzb) throws IOException {
        boolean z;
        zzabr<?, ?> zzabr = this.zzb;
        zzzc<?> zzzc = this.zzd;
        Object zzk = zzabr.zzk(t);
        zzzg<?> zzc2 = zzzc.zzc(t);
        while (zzabc.zzb() != Integer.MAX_VALUE) {
            int zzc3 = zzabc.zzc();
            if (zzc3 != 11) {
                if ((zzc3 & 7) == 2) {
                    Object zzf = zzzc.zzf(zzzb, this.zza, zzc3 >>> 3);
                    if (zzf != null) {
                        zzzc.zzg(zzabc, zzf, zzzb, zzc2);
                    } else {
                        z = zzabr.zzn(zzk, zzabc);
                    }
                } else {
                    z = zzabc.zzd();
                }
                if (!z) {
                    zzabr.zzl(t, zzk);
                    return;
                }
            } else {
                int i = 0;
                Object obj = null;
                zzym zzym = null;
                while (true) {
                    try {
                        if (zzabc.zzb() == Integer.MAX_VALUE) {
                            break;
                        }
                        int zzc4 = zzabc.zzc();
                        if (zzc4 == 16) {
                            i = zzabc.zzr();
                            obj = zzzc.zzf(zzzb, this.zza, i);
                        } else if (zzc4 == 26) {
                            if (obj != null) {
                                zzzc.zzg(zzabc, obj, zzzb, zzc2);
                            } else {
                                zzym = zzabc.zzq();
                            }
                        } else if (!zzabc.zzd()) {
                            break;
                        }
                    } catch (Throwable th) {
                        zzabr.zzl(t, zzk);
                        throw th;
                    }
                }
                if (zzabc.zzc() != 12) {
                    throw zzzw.zzf();
                } else if (zzym != null) {
                    if (obj != null) {
                        zzzc.zzh(zzym, obj, zzzb, zzc2);
                    } else {
                        zzabr.zze(zzk, i, zzym);
                    }
                }
            }
        }
        zzabr.zzl(t, zzk);
    }

    public final void zzi(T t, byte[] bArr, int i, int i2, zzxx zzxx) throws IOException {
        zzzo zzzo = (zzzo) t;
        if (zzzo.zzc == zzabs.zza()) {
            zzzo.zzc = zzabs.zzb();
        }
        zzzl zzzl = (zzzl) t;
        throw null;
    }

    public final void zzj(T t) {
        this.zzb.zzm(t);
        this.zzd.zzd(t);
    }

    public final boolean zzk(T t) {
        this.zzd.zzb(t);
        throw null;
    }

    public final void zzn(T t, zzyx zzyx) throws IOException {
        this.zzd.zzb(t);
        throw null;
    }
}

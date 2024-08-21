package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyq */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzyq implements zzabc {
    private final zzyp zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzyq(zzyp zzyp) {
        zzzu.zzb(zzyp, "input");
        this.zza = zzyp;
        zzyp.zzb = this;
    }

    private final void zzP(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzzw.zzg();
        }
    }

    private final <T> T zzQ(zzabd<T> zzabd, zzzb zzzb) throws IOException {
        int zzh = ((zzyo) this.zza).zzh();
        zzyp zzyp = this.zza;
        if (zzyp.zza < 100) {
            int zzm = zzyp.zzm(zzh);
            T zza2 = zzabd.zza();
            this.zza.zza++;
            zzabd.zzf(zza2, this, zzzb);
            zzabd.zzj(zza2);
            this.zza.zzb(0);
            zzyp zzyp2 = this.zza;
            zzyp2.zza--;
            zzyp2.zzn(zzm);
            return zza2;
        }
        throw new zzzw("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final <T> T zzR(zzabd<T> zzabd, zzzb zzzb) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            T zza2 = zzabd.zza();
            zzabd.zzf(zza2, this, zzzb);
            zzabd.zzj(zza2);
            if (this.zzb == this.zzc) {
                return zza2;
            }
            throw zzzw.zzh();
        } finally {
            this.zzc = i;
        }
    }

    private final void zzS(int i) throws IOException {
        if (this.zza.zzp() != i) {
            throw zzzw.zzb();
        }
    }

    private static final void zzT(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzzw.zzh();
        }
    }

    private static final void zzU(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzzw.zzh();
        }
    }

    public static zzyq zza(zzyp zzyp) {
        zzyq zzyq = zzyp.zzb;
        return zzyq != null ? zzyq : new zzyq(zzyp);
    }

    public final void zzA(List<Long> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzaag) {
            zzaag zzaag = (zzaag) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzaag.zzf(((zzyo) this.zza).zzi());
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else if (i == 2) {
                int zzp = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    zzaag.zzf(((zzyo) this.zza).zzi());
                } while (this.zza.zzp() < zzp);
                zzS(zzp);
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(((zzyo) this.zza).zzi()));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i2 == 2) {
                int zzp2 = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    list.add(Long.valueOf(((zzyo) this.zza).zzi()));
                } while (this.zza.zzp() < zzp2);
                zzS(zzp2);
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final void zzB(List<Integer> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzzp) {
            zzzp zzzp = (zzzp) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzzp.zzf(((zzyo) this.zza).zzh());
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else if (i == 2) {
                int zzp = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    zzzp.zzf(((zzyo) this.zza).zzh());
                } while (this.zza.zzp() < zzp);
                zzS(zzp);
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(((zzyo) this.zza).zzh()));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i2 == 2) {
                int zzp2 = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    list.add(Integer.valueOf(((zzyo) this.zza).zzh()));
                } while (this.zza.zzp() < zzp2);
                zzS(zzp2);
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final void zzC(List<Long> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzaag) {
            zzaag zzaag = (zzaag) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzaag.zzf(((zzyo) this.zza).zzl());
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else if (i == 2) {
                int zzh = ((zzyo) this.zza).zzh();
                zzU(zzh);
                int zzp = this.zza.zzp() + zzh;
                do {
                    zzaag.zzf(((zzyo) this.zza).zzl());
                } while (this.zza.zzp() < zzp);
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(((zzyo) this.zza).zzl()));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i2 == 2) {
                int zzh2 = ((zzyo) this.zza).zzh();
                zzU(zzh2);
                int zzp2 = this.zza.zzp() + zzh2;
                do {
                    list.add(Long.valueOf(((zzyo) this.zza).zzl()));
                } while (this.zza.zzp() < zzp2);
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final void zzD(List<Integer> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzzp) {
            zzzp zzzp = (zzzp) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int zzh = ((zzyo) this.zza).zzh();
                zzT(zzh);
                int zzp = this.zza.zzp() + zzh;
                do {
                    zzzp.zzf(((zzyo) this.zza).zzk());
                } while (this.zza.zzp() < zzp);
            } else if (i == 5) {
                do {
                    zzzp.zzf(((zzyo) this.zza).zzk());
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzh2 = ((zzyo) this.zza).zzh();
                zzT(zzh2);
                int zzp2 = this.zza.zzp() + zzh2;
                do {
                    list.add(Integer.valueOf(((zzyo) this.zza).zzk()));
                } while (this.zza.zzp() < zzp2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(((zzyo) this.zza).zzk()));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final void zzE(List<Boolean> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzxz) {
            zzxz zzxz = (zzxz) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzxz.zzd(this.zza.zzd());
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else if (i == 2) {
                int zzp = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    zzxz.zzd(this.zza.zzd());
                } while (this.zza.zzp() < zzp);
                zzS(zzp);
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zza.zzd()));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i2 == 2) {
                int zzp2 = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    list.add(Boolean.valueOf(this.zza.zzd()));
                } while (this.zza.zzp() < zzp2);
                zzS(zzp2);
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final <T> void zzG(List<T> list, zzabd<T> zzabd, zzzb zzzb) throws IOException {
        int zza2;
        int i = this.zzb;
        if ((i & 7) == 2) {
            do {
                list.add(zzQ(zzabd, zzzb));
                if (!this.zza.zzo() && this.zzd == 0) {
                    zza2 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza2 == i);
            this.zzd = zza2;
            return;
        }
        throw zzzw.zzg();
    }

    public final <T> void zzH(List<T> list, zzabd<T> zzabd, zzzb zzzb) throws IOException {
        int zza2;
        int i = this.zzb;
        if ((i & 7) == 3) {
            do {
                list.add(zzR(zzabd, zzzb));
                if (!this.zza.zzo() && this.zzd == 0) {
                    zza2 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza2 == i);
            this.zzd = zza2;
            return;
        }
        throw zzzw.zzg();
    }

    public final void zzI(List<zzym> list) throws IOException {
        int zza2;
        if ((this.zzb & 7) == 2) {
            do {
                list.add(zzq());
                if (!this.zza.zzo()) {
                    zza2 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza2 == this.zzb);
            this.zzd = zza2;
            return;
        }
        throw zzzw.zzg();
    }

    public final void zzJ(List<Integer> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzzp) {
            zzzp zzzp = (zzzp) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzzp.zzf(((zzyo) this.zza).zzh());
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else if (i == 2) {
                int zzp = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    zzzp.zzf(((zzyo) this.zza).zzh());
                } while (this.zza.zzp() < zzp);
                zzS(zzp);
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(((zzyo) this.zza).zzh()));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i2 == 2) {
                int zzp2 = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    list.add(Integer.valueOf(((zzyo) this.zza).zzh()));
                } while (this.zza.zzp() < zzp2);
                zzS(zzp2);
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final void zzK(List<Integer> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzzp) {
            zzzp zzzp = (zzzp) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzzp.zzf(((zzyo) this.zza).zzh());
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else if (i == 2) {
                int zzp = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    zzzp.zzf(((zzyo) this.zza).zzh());
                } while (this.zza.zzp() < zzp);
                zzS(zzp);
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(((zzyo) this.zza).zzh()));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i2 == 2) {
                int zzp2 = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    list.add(Integer.valueOf(((zzyo) this.zza).zzh()));
                } while (this.zza.zzp() < zzp2);
                zzS(zzp2);
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final void zzL(List<Integer> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzzp) {
            zzzp zzzp = (zzzp) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int zzh = ((zzyo) this.zza).zzh();
                zzT(zzh);
                int zzp = this.zza.zzp() + zzh;
                do {
                    zzzp.zzf(((zzyo) this.zza).zzk());
                } while (this.zza.zzp() < zzp);
            } else if (i == 5) {
                do {
                    zzzp.zzf(((zzyo) this.zza).zzk());
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzh2 = ((zzyo) this.zza).zzh();
                zzT(zzh2);
                int zzp2 = this.zza.zzp() + zzh2;
                do {
                    list.add(Integer.valueOf(((zzyo) this.zza).zzk()));
                } while (this.zza.zzp() < zzp2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(((zzyo) this.zza).zzk()));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final void zzM(List<Long> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzaag) {
            zzaag zzaag = (zzaag) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzaag.zzf(((zzyo) this.zza).zzl());
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else if (i == 2) {
                int zzh = ((zzyo) this.zza).zzh();
                zzU(zzh);
                int zzp = this.zza.zzp() + zzh;
                do {
                    zzaag.zzf(((zzyo) this.zza).zzl());
                } while (this.zza.zzp() < zzp);
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(((zzyo) this.zza).zzl()));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i2 == 2) {
                int zzh2 = ((zzyo) this.zza).zzh();
                zzU(zzh2);
                int zzp2 = this.zza.zzp() + zzh2;
                do {
                    list.add(Long.valueOf(((zzyo) this.zza).zzl()));
                } while (this.zza.zzp() < zzp2);
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final void zzN(List<Integer> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzzp) {
            zzzp zzzp = (zzzp) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzzp.zzf(zzyo.zzt(((zzyo) this.zza).zzh()));
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else if (i == 2) {
                int zzp = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    zzzp.zzf(zzyo.zzt(((zzyo) this.zza).zzh()));
                } while (this.zza.zzp() < zzp);
                zzS(zzp);
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(zzyo.zzt(((zzyo) this.zza).zzh())));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i2 == 2) {
                int zzp2 = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    list.add(Integer.valueOf(zzyo.zzt(((zzyo) this.zza).zzh())));
                } while (this.zza.zzp() < zzp2);
                zzS(zzp2);
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final void zzO(List<Long> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzaag) {
            zzaag zzaag = (zzaag) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzaag.zzf(zzyo.zzu(((zzyo) this.zza).zzi()));
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else if (i == 2) {
                int zzp = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    zzaag.zzf(zzyo.zzu(((zzyo) this.zza).zzi()));
                } while (this.zza.zzp() < zzp);
                zzS(zzp);
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(zzyo.zzu(((zzyo) this.zza).zzi())));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i2 == 2) {
                int zzp2 = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    list.add(Long.valueOf(zzyo.zzu(((zzyo) this.zza).zzi())));
                } while (this.zza.zzp() < zzp2);
                zzS(zzp2);
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final int zzb() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            i = this.zza.zza();
            this.zzb = i;
        }
        if (i == 0 || i == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i >>> 3;
    }

    public final int zzc() {
        return this.zzb;
    }

    public final boolean zzd() throws IOException {
        int i;
        if (this.zza.zzo() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzc(i);
    }

    public final double zze() throws IOException {
        zzP(1);
        return Double.longBitsToDouble(((zzyo) this.zza).zzl());
    }

    public final float zzf() throws IOException {
        zzP(5);
        return Float.intBitsToFloat(((zzyo) this.zza).zzk());
    }

    public final long zzg() throws IOException {
        zzP(0);
        return ((zzyo) this.zza).zzi();
    }

    public final long zzh() throws IOException {
        zzP(0);
        return ((zzyo) this.zza).zzi();
    }

    public final int zzi() throws IOException {
        zzP(0);
        return ((zzyo) this.zza).zzh();
    }

    public final long zzj() throws IOException {
        zzP(1);
        return ((zzyo) this.zza).zzl();
    }

    public final int zzk() throws IOException {
        zzP(5);
        return ((zzyo) this.zza).zzk();
    }

    public final boolean zzl() throws IOException {
        zzP(0);
        return this.zza.zzd();
    }

    public final String zzm() throws IOException {
        zzP(2);
        return this.zza.zze();
    }

    public final String zzn() throws IOException {
        zzP(2);
        return this.zza.zzf();
    }

    public final <T> T zzo(zzabd<T> zzabd, zzzb zzzb) throws IOException {
        zzP(2);
        return zzQ(zzabd, zzzb);
    }

    public final <T> T zzp(zzabd<T> zzabd, zzzb zzzb) throws IOException {
        zzP(3);
        return zzR(zzabd, zzzb);
    }

    public final zzym zzq() throws IOException {
        zzP(2);
        return this.zza.zzg();
    }

    public final int zzr() throws IOException {
        zzP(0);
        return ((zzyo) this.zza).zzh();
    }

    public final int zzs() throws IOException {
        zzP(0);
        return ((zzyo) this.zza).zzh();
    }

    public final int zzt() throws IOException {
        zzP(5);
        return ((zzyo) this.zza).zzk();
    }

    public final long zzu() throws IOException {
        zzP(1);
        return ((zzyo) this.zza).zzl();
    }

    public final int zzv() throws IOException {
        zzP(0);
        return zzyo.zzt(((zzyo) this.zza).zzh());
    }

    public final long zzw() throws IOException {
        zzP(0);
        return zzyo.zzu(((zzyo) this.zza).zzi());
    }

    public final void zzx(List<Double> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzyy) {
            zzyy zzyy = (zzyy) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzyy.zzd(Double.longBitsToDouble(((zzyo) this.zza).zzl()));
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else if (i == 2) {
                int zzh = ((zzyo) this.zza).zzh();
                zzU(zzh);
                int zzp = this.zza.zzp() + zzh;
                do {
                    zzyy.zzd(Double.longBitsToDouble(((zzyo) this.zza).zzl()));
                } while (this.zza.zzp() < zzp);
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    list.add(Double.valueOf(Double.longBitsToDouble(((zzyo) this.zza).zzl())));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i2 == 2) {
                int zzh2 = ((zzyo) this.zza).zzh();
                zzU(zzh2);
                int zzp2 = this.zza.zzp() + zzh2;
                do {
                    list.add(Double.valueOf(Double.longBitsToDouble(((zzyo) this.zza).zzl())));
                } while (this.zza.zzp() < zzp2);
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final void zzy(List<Float> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzzi) {
            zzzi zzzi = (zzzi) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int zzh = ((zzyo) this.zza).zzh();
                zzT(zzh);
                int zzp = this.zza.zzp() + zzh;
                do {
                    zzzi.zzd(Float.intBitsToFloat(((zzyo) this.zza).zzk()));
                } while (this.zza.zzp() < zzp);
            } else if (i == 5) {
                do {
                    zzzi.zzd(Float.intBitsToFloat(((zzyo) this.zza).zzk()));
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzh2 = ((zzyo) this.zza).zzh();
                zzT(zzh2);
                int zzp2 = this.zza.zzp() + zzh2;
                do {
                    list.add(Float.valueOf(Float.intBitsToFloat(((zzyo) this.zza).zzk())));
                } while (this.zza.zzp() < zzp2);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(Float.intBitsToFloat(((zzyo) this.zza).zzk())));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final void zzz(List<Long> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzaag) {
            zzaag zzaag = (zzaag) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzaag.zzf(((zzyo) this.zza).zzi());
                    if (!this.zza.zzo()) {
                        zza3 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza3 == this.zzb);
                this.zzd = zza3;
            } else if (i == 2) {
                int zzp = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    zzaag.zzf(((zzyo) this.zza).zzi());
                } while (this.zza.zzp() < zzp);
                zzS(zzp);
            } else {
                throw zzzw.zzg();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(((zzyo) this.zza).zzi()));
                    if (!this.zza.zzo()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i2 == 2) {
                int zzp2 = this.zza.zzp() + ((zzyo) this.zza).zzh();
                do {
                    list.add(Long.valueOf(((zzyo) this.zza).zzi()));
                } while (this.zza.zzp() < zzp2);
                zzS(zzp2);
            } else {
                throw zzzw.zzg();
            }
        }
    }

    public final void zzF(List<String> list, boolean z) throws IOException {
        int zza2;
        int zza3;
        if ((this.zzb & 7) != 2) {
            throw zzzw.zzg();
        } else if ((list instanceof zzaab) && !z) {
            zzaab zzaab = (zzaab) list;
            do {
                zzaab.zzf(zzq());
                if (!this.zza.zzo()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
        } else {
            do {
                list.add(z ? zzn() : zzm());
                if (!this.zza.zzo()) {
                    zza2 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza2 == this.zzb);
            this.zzd = zza2;
        }
    }
}

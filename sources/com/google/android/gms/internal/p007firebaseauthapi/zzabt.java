package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabt */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzabt extends zzabr<zzabs, zzabs> {
    zzabt() {
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzabc zzabc) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzabs) obj).zzh(i << 3, Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zzc(Object obj, int i, int i2) {
        ((zzabs) obj).zzh((i << 3) | 5, Integer.valueOf(i2));
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zzd(Object obj, int i, long j) {
        ((zzabs) obj).zzh((i << 3) | 1, Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zze(Object obj, int i, zzym zzym) {
        ((zzabs) obj).zzh((i << 3) | 2, zzym);
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zzf(Object obj, int i, Object obj2) {
        ((zzabs) obj).zzh((i << 3) | 3, obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zzg() {
        return zzabs.zzb();
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zzh(Object obj) {
        zzabs zzabs = (zzabs) obj;
        zzabs.zzd();
        return zzabs;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zzi(Object obj, Object obj2) {
        ((zzzo) obj).zzc = (zzabs) obj2;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zzj(Object obj) {
        return ((zzzo) obj).zzc;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zzk(Object obj) {
        zzzo zzzo = (zzzo) obj;
        zzabs zzabs = zzzo.zzc;
        if (zzabs != zzabs.zza()) {
            return zzabs;
        }
        zzabs zzb = zzabs.zzb();
        zzzo.zzc = zzb;
        return zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zzl(Object obj, Object obj2) {
        ((zzzo) obj).zzc = (zzabs) obj2;
    }

    /* access modifiers changed from: package-private */
    public final void zzm(Object obj) {
        ((zzzo) obj).zzc.zzd();
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zzo(Object obj, Object obj2) {
        zzabs zzabs = (zzabs) obj2;
        return zzabs.equals(zzabs.zza()) ? obj : zzabs.zzc((zzabs) obj, zzabs);
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ int zzp(Object obj) {
        return ((zzabs) obj).zze();
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ int zzq(Object obj) {
        return ((zzabs) obj).zzf();
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zzr(Object obj, zzyx zzyx) throws IOException {
        ((zzabs) obj).zzi(zzyx);
    }
}

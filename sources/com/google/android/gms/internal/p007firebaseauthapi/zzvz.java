package com.google.android.gms.internal.p007firebaseauthapi;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.zze;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvz */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzvz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvz> CREATOR = new zzwa();
    private String zza;
    private String zzb;
    private boolean zzc;
    private String zzd;
    private String zze;
    private zzwo zzf;
    private String zzg;
    private String zzh;
    private long zzi;
    private long zzj;
    private boolean zzk;
    private zze zzl;
    private List<zzwk> zzm;

    public zzvz() {
        this.zzf = new zzwo();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzc);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzf, i, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzh, false);
        SafeParcelWriter.writeLong(parcel, 10, this.zzi);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzk);
        SafeParcelWriter.writeParcelable(parcel, 13, this.zzl, i, false);
        SafeParcelWriter.writeTypedList(parcel, 14, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zza() {
        return this.zzb;
    }

    public final boolean zzb() {
        return this.zzc;
    }

    public final String zzc() {
        return this.zza;
    }

    public final String zzd() {
        return this.zzd;
    }

    public final Uri zze() {
        if (!TextUtils.isEmpty(this.zze)) {
            return Uri.parse(this.zze);
        }
        return null;
    }

    public final String zzf() {
        return this.zzh;
    }

    public final long zzg() {
        return this.zzi;
    }

    public final long zzh() {
        return this.zzj;
    }

    public final boolean zzi() {
        return this.zzk;
    }

    public final zzvz zzj(String str) {
        this.zzb = str;
        return this;
    }

    public final zzvz zzk(String str) {
        this.zzd = str;
        return this;
    }

    public final zzvz zzl(String str) {
        this.zze = str;
        return this;
    }

    public final zzvz zzm(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzg = str;
        return this;
    }

    public final zzvz zzn(List<zzwm> list) {
        Preconditions.checkNotNull(list);
        zzwo zzwo = new zzwo();
        this.zzf = zzwo;
        zzwo.zza().addAll(list);
        return this;
    }

    public final zzvz zzo(boolean z) {
        this.zzk = z;
        return this;
    }

    public final List<zzwm> zzp() {
        return this.zzf.zza();
    }

    public final zzwo zzq() {
        return this.zzf;
    }

    public final zze zzr() {
        return this.zzl;
    }

    public final zzvz zzs(zze zze2) {
        this.zzl = zze2;
        return this;
    }

    public final List<zzwk> zzt() {
        return this.zzm;
    }

    public zzvz(String str, String str2, boolean z, String str3, String str4, zzwo zzwo, String str5, String str6, long j, long j2, boolean z2, zze zze2, List<zzwk> list) {
        zzwo zzwo2;
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = str3;
        this.zze = str4;
        if (zzwo == null) {
            zzwo2 = new zzwo();
        } else {
            zzwo2 = zzwo.zzb(zzwo);
        }
        this.zzf = zzwo2;
        this.zzg = str5;
        this.zzh = str6;
        this.zzi = j;
        this.zzj = j2;
        this.zzk = z2;
        this.zzl = zze2;
        this.zzm = list == null ? new ArrayList<>() : list;
    }
}

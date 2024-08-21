package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.p007firebaseauthapi.zzwg;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.zze;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzx extends FirebaseUser {
    public static final Parcelable.Creator<zzx> CREATOR = new zzy();
    private zzwg zza;
    private zzt zzb;
    private final String zzc;
    private String zzd;
    private List<zzt> zze;
    private List<String> zzf;
    private String zzg;
    private Boolean zzh;
    private zzz zzi;
    private boolean zzj;
    private zze zzk;
    private zzbb zzl;

    public zzx(FirebaseApp firebaseApp, List<? extends UserInfo> list) {
        Preconditions.checkNotNull(firebaseApp);
        this.zzc = firebaseApp.getName();
        this.zzd = "com.google.firebase.auth.internal.DefaultFirebaseUser";
        this.zzg = ExifInterface.GPS_MEASUREMENT_2D;
        zzb(list);
    }

    public static FirebaseUser zzs(FirebaseApp firebaseApp, FirebaseUser firebaseUser) {
        zzx zzx = new zzx(firebaseApp, firebaseUser.getProviderData());
        if (firebaseUser instanceof zzx) {
            zzx zzx2 = (zzx) firebaseUser;
            zzx.zzg = zzx2.zzg;
            zzx.zzd = zzx2.zzd;
            zzx.zzi = zzx2.zzi;
        } else {
            zzx.zzi = null;
        }
        if (firebaseUser.zze() != null) {
            zzx.zzf(firebaseUser.zze());
        }
        if (!firebaseUser.isAnonymous()) {
            zzx.zzj();
        }
        return zzx;
    }

    public final String getDisplayName() {
        return this.zzb.getDisplayName();
    }

    public final String getEmail() {
        return this.zzb.getEmail();
    }

    public final FirebaseUserMetadata getMetadata() {
        return this.zzi;
    }

    public final /* bridge */ /* synthetic */ MultiFactor getMultiFactor() {
        return new zzac(this);
    }

    public final String getPhoneNumber() {
        return this.zzb.getPhoneNumber();
    }

    public final Uri getPhotoUrl() {
        return this.zzb.getPhotoUrl();
    }

    public final List<? extends UserInfo> getProviderData() {
        return this.zze;
    }

    public final String getProviderId() {
        return this.zzb.getProviderId();
    }

    public final String getTenantId() {
        Map map;
        zzwg zzwg = this.zza;
        if (zzwg == null || zzwg.zze() == null || (map = (Map) zzay.zza(this.zza.zze()).getClaims().get(FirebaseAuthProvider.PROVIDER_ID)) == null) {
            return null;
        }
        return (String) map.get("tenant");
    }

    public final String getUid() {
        return this.zzb.getUid();
    }

    public final boolean isAnonymous() {
        Boolean bool = this.zzh;
        if (bool == null || bool.booleanValue()) {
            zzwg zzwg = this.zza;
            String signInProvider = zzwg != null ? zzay.zza(zzwg.zze()).getSignInProvider() : "";
            boolean z = false;
            if (this.zze.size() <= 1 && (signInProvider == null || !signInProvider.equals("custom"))) {
                z = true;
            }
            this.zzh = Boolean.valueOf(z);
        }
        return this.zzh.booleanValue();
    }

    public final boolean isEmailVerified() {
        return this.zzb.isEmailVerified();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zze, false);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.writeBooleanObject(parcel, 8, Boolean.valueOf(isAnonymous()), false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzi, i, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzk, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzl, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List<String> zza() {
        return this.zzf;
    }

    public final FirebaseUser zzb(List<? extends UserInfo> list) {
        Preconditions.checkNotNull(list);
        this.zze = new ArrayList(list.size());
        this.zzf = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            UserInfo userInfo = (UserInfo) list.get(i);
            if (userInfo.getProviderId().equals(FirebaseAuthProvider.PROVIDER_ID)) {
                this.zzb = (zzt) userInfo;
            } else {
                this.zzf.add(userInfo.getProviderId());
            }
            this.zze.add((zzt) userInfo);
        }
        if (this.zzb == null) {
            this.zzb = this.zze.get(0);
        }
        return this;
    }

    public final /* bridge */ /* synthetic */ FirebaseUser zzc() {
        zzj();
        return this;
    }

    public final FirebaseApp zzd() {
        return FirebaseApp.getInstance(this.zzc);
    }

    public final zzwg zze() {
        return this.zza;
    }

    public final void zzf(zzwg zzwg) {
        this.zza = (zzwg) Preconditions.checkNotNull(zzwg);
    }

    public final String zzg() {
        return this.zza.zzi();
    }

    public final String zzh() {
        return this.zza.zze();
    }

    public final void zzi(List<MultiFactorInfo> list) {
        Parcelable.Creator<zzbb> creator = zzbb.CREATOR;
        zzbb zzbb = null;
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (MultiFactorInfo next : list) {
                if (next instanceof PhoneMultiFactorInfo) {
                    arrayList.add((PhoneMultiFactorInfo) next);
                }
            }
            zzbb = new zzbb(arrayList);
        }
        this.zzl = zzbb;
    }

    public final zzx zzj() {
        this.zzh = false;
        return this;
    }

    public final zzx zzk(String str) {
        this.zzg = str;
        return this;
    }

    public final List<zzt> zzl() {
        return this.zze;
    }

    public final void zzm(zzz zzz) {
        this.zzi = zzz;
    }

    public final void zzn(boolean z) {
        this.zzj = z;
    }

    public final boolean zzo() {
        return this.zzj;
    }

    public final void zzp(zze zze2) {
        this.zzk = zze2;
    }

    public final zze zzq() {
        return this.zzk;
    }

    public final List<MultiFactorInfo> zzr() {
        zzbb zzbb = this.zzl;
        if (zzbb != null) {
            return zzbb.zza();
        }
        return new ArrayList();
    }

    zzx(zzwg zzwg, zzt zzt, String str, String str2, List<zzt> list, List<String> list2, String str3, Boolean bool, zzz zzz, boolean z, zze zze2, zzbb zzbb) {
        this.zza = zzwg;
        this.zzb = zzt;
        this.zzc = str;
        this.zzd = str2;
        this.zze = list;
        this.zzf = list2;
        this.zzg = str3;
        this.zzh = bool;
        this.zzi = zzz;
        this.zzj = z;
        this.zzk = zze2;
        this.zzl = zzbb;
    }
}

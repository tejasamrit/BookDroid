package com.google.firebase.auth.internal;

import android.text.TextUtils;
import com.google.android.gms.internal.p007firebaseauthapi.zzwk;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzba {
    public static MultiFactorInfo zza(zzwk zzwk) {
        if (zzwk != null && !TextUtils.isEmpty(zzwk.zza())) {
            return new PhoneMultiFactorInfo(zzwk.zzb(), zzwk.zzc(), zzwk.zzd(), zzwk.zza());
        }
        return null;
    }

    public static List<MultiFactorInfo> zzb(List<zzwk> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (zzwk zza : list) {
            MultiFactorInfo zza2 = zza(zza);
            if (zza2 != null) {
                arrayList.add(zza2);
            }
        }
        return arrayList;
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzae */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzae {
    /* access modifiers changed from: private */
    public final zzp zza;
    private final zzad zzb;

    private zzae(zzad zzad) {
        zzo zzo = zzo.zza;
        this.zzb = zzad;
        this.zza = zzo;
    }

    public static zzae zza(char c) {
        return new zzae(new zzz(new zzm('.')));
    }

    public static zzae zzb(String str) {
        zzs zzb2 = zzx.zzb("[.-]");
        if (!((zzt) zzb2.zza("")).zza.matches()) {
            return new zzae(new zzab(zzb2));
        }
        throw new IllegalArgumentException(zzaf.zzc("The pattern may not match the empty string: %s", zzb2));
    }

    public final List<String> zzc(CharSequence charSequence) {
        Objects.requireNonNull(charSequence);
        Iterator<String> zza2 = this.zzb.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (zza2.hasNext()) {
            arrayList.add(zza2.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzu */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzu extends zzs implements Serializable {
    private final Pattern zza;

    zzu(Pattern pattern) {
        Objects.requireNonNull(pattern);
        this.zza = pattern;
    }

    public final String toString() {
        return this.zza.toString();
    }

    public final zzr zza(CharSequence charSequence) {
        return new zzt(this.zza.matcher(charSequence));
    }
}

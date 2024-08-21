package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzah */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzah implements zzav {
    private final OutputStream zza;

    private zzah(OutputStream outputStream, boolean z) {
        this.zza = outputStream;
    }

    public static zzav zza(OutputStream outputStream) {
        return new zzah(outputStream, false);
    }

    public final void zzc(zzgy zzgy) throws IOException {
        throw null;
    }

    public final void zzb(zzib zzib) throws IOException {
        zzib.zzp(this.zza);
    }
}

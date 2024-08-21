package com.google.android.gms.internal.p007firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzw */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public class zzzw extends IOException {
    private zzaar zza = null;

    public zzzw(String str) {
        super(str);
    }

    static zzzw zzb() {
        return new zzzw("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzzw zzc() {
        return new zzzw("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzzw zzd() {
        return new zzzw("CodedInputStream encountered a malformed varint.");
    }

    static zzzw zze() {
        return new zzzw("Protocol message contained an invalid tag (zero).");
    }

    static zzzw zzf() {
        return new zzzw("Protocol message end-group tag did not match expected tag.");
    }

    static zzzv zzg() {
        return new zzzv("Protocol message tag had invalid wire type.");
    }

    static zzzw zzh() {
        return new zzzw("Failed to parse the message.");
    }

    static zzzw zzi() {
        return new zzzw("Protocol message had invalid UTF-8.");
    }

    public final zzzw zza(zzaar zzaar) {
        this.zza = zzaar;
        return this;
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.common.base.Ascii;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacd */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzacd {
    static /* synthetic */ boolean zza(byte b) {
        return b >= 0;
    }

    static /* synthetic */ boolean zzb(byte b) {
        return b < -32;
    }

    static /* synthetic */ void zzc(byte b, byte b2, char[] cArr, int i) throws zzzw {
        if (b < -62 || zzg(b2)) {
            throw zzzw.zzi();
        }
        cArr[i] = (char) (((b & Ascii.f236US) << 6) | (b2 & 63));
    }

    static /* synthetic */ boolean zzd(byte b) {
        return b < -16;
    }

    static /* synthetic */ void zze(byte b, byte b2, byte b3, char[] cArr, int i) throws zzzw {
        if (!zzg(b2)) {
            if (b == -32) {
                if (b2 >= -96) {
                    b = -32;
                }
            }
            if (b == -19) {
                if (b2 < -96) {
                    b = -19;
                }
            }
            if (!zzg(b3)) {
                cArr[i] = (char) (((b & Ascii.f233SI) << Ascii.f226FF) | ((b2 & 63) << 6) | (b3 & 63));
                return;
            }
        }
        throw zzzw.zzi();
    }

    static /* synthetic */ void zzf(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) throws zzzw {
        if (zzg(b2) || (((b << Ascii.f227FS) + (b2 + 112)) >> 30) != 0 || zzg(b3) || zzg(b4)) {
            throw zzzw.zzi();
        }
        byte b5 = ((b & 7) << Ascii.DC2) | ((b2 & 63) << Ascii.f226FF) | ((b3 & 63) << 6) | (b4 & 63);
        cArr[i] = (char) ((b5 >>> 10) + 55232);
        cArr[i + 1] = (char) ((b5 & 1023) + 56320);
    }

    private static boolean zzg(byte b) {
        return b > -65;
    }
}

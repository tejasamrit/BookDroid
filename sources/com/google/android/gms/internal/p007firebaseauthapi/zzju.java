package com.google.android.gms.internal.p007firebaseauthapi;

import java.security.interfaces.ECPrivateKey;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzju */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public final class zzju {
    private final ECPrivateKey zza;

    public zzju(ECPrivateKey eCPrivateKey) {
        this.zza = eCPrivateKey;
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01de A[SYNTHETIC, Splitter:B:71:0x01de] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zza(byte[] r10, java.lang.String r11, byte[] r12, byte[] r13, int r14, int r15) throws java.security.GeneralSecurityException {
        /*
            r9 = this;
            java.security.interfaces.ECPrivateKey r13 = r9.zza
            java.security.spec.ECParameterSpec r13 = r13.getParams()
            java.security.spec.EllipticCurve r0 = r13.getCurve()
            int r1 = com.google.android.gms.internal.p007firebaseauthapi.zzjw.zzc(r0)
            r2 = -1
            int r15 = r15 + r2
            java.lang.String r3 = "invalid point size"
            r4 = 2
            r5 = 0
            r6 = 1
            if (r15 == 0) goto L_0x0089
            if (r15 == r4) goto L_0x0062
            java.math.BigInteger r15 = com.google.android.gms.internal.p007firebaseauthapi.zzjw.zzb(r0)
            int r3 = r10.length
            int r1 = r1 + r6
            if (r3 != r1) goto L_0x005a
            byte r1 = r10[r5]
            if (r1 != r4) goto L_0x0027
            r1 = 0
            goto L_0x002b
        L_0x0027:
            r7 = 3
            if (r1 != r7) goto L_0x0052
            r1 = 1
        L_0x002b:
            java.math.BigInteger r7 = new java.math.BigInteger
            byte[] r3 = java.util.Arrays.copyOfRange(r10, r6, r3)
            r7.<init>(r6, r3)
            int r3 = r7.signum()
            if (r3 == r2) goto L_0x004a
            int r15 = r7.compareTo(r15)
            if (r15 >= 0) goto L_0x004a
            java.math.BigInteger r15 = com.google.android.gms.internal.p007firebaseauthapi.zzjw.zzd(r7, r1, r0)
            java.security.spec.ECPoint r0 = new java.security.spec.ECPoint
            r0.<init>(r7, r15)
            goto L_0x00b0
        L_0x004a:
            java.security.GeneralSecurityException r10 = new java.security.GeneralSecurityException
            java.lang.String r11 = "x is out of range"
            r10.<init>(r11)
            throw r10
        L_0x0052:
            java.security.GeneralSecurityException r10 = new java.security.GeneralSecurityException
            java.lang.String r11 = "invalid format"
            r10.<init>(r11)
            throw r10
        L_0x005a:
            java.security.GeneralSecurityException r10 = new java.security.GeneralSecurityException
            java.lang.String r11 = "compressed point has wrong length"
            r10.<init>(r11)
            throw r10
        L_0x0062:
            int r15 = r10.length
            int r7 = r1 + r1
            if (r15 != r7) goto L_0x0083
            java.math.BigInteger r3 = new java.math.BigInteger
            byte[] r7 = java.util.Arrays.copyOfRange(r10, r5, r1)
            r3.<init>(r6, r7)
            java.math.BigInteger r7 = new java.math.BigInteger
            byte[] r15 = java.util.Arrays.copyOfRange(r10, r1, r15)
            r7.<init>(r6, r15)
            java.security.spec.ECPoint r15 = new java.security.spec.ECPoint
            r15.<init>(r3, r7)
            com.google.android.gms.internal.p007firebaseauthapi.zzjw.zza(r15, r0)
        L_0x0081:
            r0 = r15
            goto L_0x00b0
        L_0x0083:
            java.security.GeneralSecurityException r10 = new java.security.GeneralSecurityException
            r10.<init>(r3)
            throw r10
        L_0x0089:
            int r15 = r10.length
            int r7 = r1 + r1
            int r7 = r7 + r6
            if (r15 != r7) goto L_0x01fb
            byte r3 = r10[r5]
            r7 = 4
            if (r3 != r7) goto L_0x01f3
            int r1 = r1 + r6
            java.math.BigInteger r3 = new java.math.BigInteger
            byte[] r7 = java.util.Arrays.copyOfRange(r10, r6, r1)
            r3.<init>(r6, r7)
            java.math.BigInteger r7 = new java.math.BigInteger
            byte[] r15 = java.util.Arrays.copyOfRange(r10, r1, r15)
            r7.<init>(r6, r15)
            java.security.spec.ECPoint r15 = new java.security.spec.ECPoint
            r15.<init>(r3, r7)
            com.google.android.gms.internal.p007firebaseauthapi.zzjw.zza(r15, r0)
            goto L_0x0081
        L_0x00b0:
            java.security.spec.ECPublicKeySpec r15 = new java.security.spec.ECPublicKeySpec
            r15.<init>(r0, r13)
            com.google.android.gms.internal.firebase-auth-api.zzjy<com.google.android.gms.internal.firebase-auth-api.zzkb, java.security.KeyFactory> r13 = com.google.android.gms.internal.p007firebaseauthapi.zzjy.zzg
            java.lang.String r0 = "EC"
            java.lang.Object r13 = r13.zza(r0)
            java.security.KeyFactory r13 = (java.security.KeyFactory) r13
            java.security.PublicKey r13 = r13.generatePublic(r15)
            java.security.interfaces.ECPublicKey r13 = (java.security.interfaces.ECPublicKey) r13
            java.security.interfaces.ECPrivateKey r15 = r9.zza
            java.security.spec.ECParameterSpec r1 = r13.getParams()     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            java.security.spec.ECParameterSpec r3 = r15.getParams()     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            java.security.spec.EllipticCurve r7 = r1.getCurve()     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            java.security.spec.EllipticCurve r8 = r3.getCurve()     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            boolean r7 = r7.equals(r8)     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            if (r7 == 0) goto L_0x01de
            java.security.spec.ECPoint r7 = r1.getGenerator()     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            java.security.spec.ECPoint r8 = r3.getGenerator()     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            boolean r7 = r7.equals(r8)     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            if (r7 == 0) goto L_0x01de
            java.math.BigInteger r7 = r1.getOrder()     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            java.math.BigInteger r8 = r3.getOrder()     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            boolean r7 = r7.equals(r8)     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            if (r7 == 0) goto L_0x01de
            int r1 = r1.getCofactor()     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            int r3 = r3.getCofactor()     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            if (r1 != r3) goto L_0x01de
            java.security.spec.ECPoint r13 = r13.getW()
            java.security.spec.ECParameterSpec r1 = r15.getParams()
            java.security.spec.EllipticCurve r1 = r1.getCurve()
            com.google.android.gms.internal.p007firebaseauthapi.zzjw.zza(r13, r1)
            java.security.spec.ECParameterSpec r1 = r15.getParams()
            java.security.spec.ECPublicKeySpec r3 = new java.security.spec.ECPublicKeySpec
            r3.<init>(r13, r1)
            com.google.android.gms.internal.firebase-auth-api.zzjy<com.google.android.gms.internal.firebase-auth-api.zzkb, java.security.KeyFactory> r13 = com.google.android.gms.internal.p007firebaseauthapi.zzjy.zzg
            java.lang.Object r13 = r13.zza(r0)
            java.security.KeyFactory r13 = (java.security.KeyFactory) r13
            java.security.PublicKey r13 = r13.generatePublic(r3)
            com.google.android.gms.internal.firebase-auth-api.zzjy<com.google.android.gms.internal.firebase-auth-api.zzka, javax.crypto.KeyAgreement> r0 = com.google.android.gms.internal.p007firebaseauthapi.zzjy.zze
            java.lang.String r1 = "ECDH"
            java.lang.Object r0 = r0.zza(r1)
            javax.crypto.KeyAgreement r0 = (javax.crypto.KeyAgreement) r0
            r0.init(r15)
            r0.doPhase(r13, r6)     // Catch:{ IllegalStateException -> 0x01d3 }
            byte[] r13 = r0.generateSecret()     // Catch:{ IllegalStateException -> 0x01d3 }
            java.security.spec.ECParameterSpec r15 = r15.getParams()     // Catch:{ IllegalStateException -> 0x01d3 }
            java.security.spec.EllipticCurve r15 = r15.getCurve()     // Catch:{ IllegalStateException -> 0x01d3 }
            java.math.BigInteger r0 = new java.math.BigInteger     // Catch:{ IllegalStateException -> 0x01d3 }
            r0.<init>(r6, r13)     // Catch:{ IllegalStateException -> 0x01d3 }
            int r1 = r0.signum()     // Catch:{ IllegalStateException -> 0x01d3 }
            if (r1 == r2) goto L_0x01cb
            java.math.BigInteger r1 = com.google.android.gms.internal.p007firebaseauthapi.zzjw.zzb(r15)     // Catch:{ IllegalStateException -> 0x01d3 }
            int r1 = r0.compareTo(r1)     // Catch:{ IllegalStateException -> 0x01d3 }
            if (r1 >= 0) goto L_0x01cb
            com.google.android.gms.internal.p007firebaseauthapi.zzjw.zzd(r0, r6, r15)     // Catch:{ IllegalStateException -> 0x01d3 }
            byte[][] r15 = new byte[r4][]
            r15[r5] = r10
            r15[r6] = r13
            byte[] r10 = com.google.android.gms.internal.p007firebaseauthapi.zzjm.zzb(r15)
            com.google.android.gms.internal.firebase-auth-api.zzjy<com.google.android.gms.internal.firebase-auth-api.zzkd, javax.crypto.Mac> r13 = com.google.android.gms.internal.p007firebaseauthapi.zzjy.zzb
            java.lang.Object r13 = r13.zza(r11)
            javax.crypto.Mac r13 = (javax.crypto.Mac) r13
            int r15 = r13.getMacLength()
            int r15 = r15 * 255
            if (r14 > r15) goto L_0x01c3
            if (r12 == 0) goto L_0x0184
            int r15 = r12.length
            if (r15 != 0) goto L_0x017b
            goto L_0x0184
        L_0x017b:
            javax.crypto.spec.SecretKeySpec r15 = new javax.crypto.spec.SecretKeySpec
            r15.<init>(r12, r11)
            r13.init(r15)
            goto L_0x0192
        L_0x0184:
            javax.crypto.spec.SecretKeySpec r12 = new javax.crypto.spec.SecretKeySpec
            int r15 = r13.getMacLength()
            byte[] r15 = new byte[r15]
            r12.<init>(r15, r11)
            r13.init(r12)
        L_0x0192:
            byte[] r10 = r13.doFinal(r10)
            byte[] r12 = new byte[r14]
            javax.crypto.spec.SecretKeySpec r15 = new javax.crypto.spec.SecretKeySpec
            r15.<init>(r10, r11)
            r13.init(r15)
            byte[] r10 = new byte[r5]
            r11 = 0
        L_0x01a3:
            r13.update(r10)
            r10 = 0
            r13.update(r10)
            byte r10 = (byte) r6
            r13.update(r10)
            byte[] r10 = r13.doFinal()
            int r15 = r10.length
            int r0 = r11 + r15
            if (r0 >= r14) goto L_0x01be
            java.lang.System.arraycopy(r10, r5, r12, r11, r15)
            int r6 = r6 + 1
            r11 = r0
            goto L_0x01a3
        L_0x01be:
            int r14 = r14 - r11
            java.lang.System.arraycopy(r10, r5, r12, r11, r14)
            return r12
        L_0x01c3:
            java.security.GeneralSecurityException r10 = new java.security.GeneralSecurityException
            java.lang.String r11 = "size too large"
            r10.<init>(r11)
            throw r10
        L_0x01cb:
            java.security.GeneralSecurityException r10 = new java.security.GeneralSecurityException     // Catch:{ IllegalStateException -> 0x01d3 }
            java.lang.String r11 = "shared secret is out of range"
            r10.<init>(r11)     // Catch:{ IllegalStateException -> 0x01d3 }
            throw r10     // Catch:{ IllegalStateException -> 0x01d3 }
        L_0x01d3:
            r10 = move-exception
            java.security.GeneralSecurityException r11 = new java.security.GeneralSecurityException
            java.lang.String r10 = r10.toString()
            r11.<init>(r10)
            throw r11
        L_0x01de:
            java.security.GeneralSecurityException r10 = new java.security.GeneralSecurityException     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            java.lang.String r11 = "invalid public key spec"
            r10.<init>(r11)     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
            throw r10     // Catch:{ IllegalArgumentException -> 0x01e8, NullPointerException -> 0x01e6 }
        L_0x01e6:
            r10 = move-exception
            goto L_0x01e9
        L_0x01e8:
            r10 = move-exception
        L_0x01e9:
            java.security.GeneralSecurityException r11 = new java.security.GeneralSecurityException
            java.lang.String r10 = r10.toString()
            r11.<init>(r10)
            throw r11
        L_0x01f3:
            java.security.GeneralSecurityException r10 = new java.security.GeneralSecurityException
            java.lang.String r11 = "invalid point format"
            r10.<init>(r11)
            throw r10
        L_0x01fb:
            java.security.GeneralSecurityException r10 = new java.security.GeneralSecurityException
            r10.<init>(r3)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzju.zza(byte[], java.lang.String, byte[], byte[], int, int):byte[]");
    }
}

package com.google.android.gms.internal.p007firebaseauthapi;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import sun.misc.Unsafe;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacc */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
final class zzacc {
    static final long zza = ((long) zzA(byte[].class));
    static final boolean zzb = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);
    private static final Unsafe zzc;
    private static final Class<?> zzd = zzxw.zzb();
    private static final boolean zze;
    private static final boolean zzf;
    private static final zzacb zzg;
    private static final boolean zzh;
    private static final boolean zzi;

    /* JADX WARNING: Removed duplicated region for block: B:37:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x015f A[SYNTHETIC, Splitter:B:39:0x015f] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0308  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x030a  */
    static {
        /*
            java.lang.String r0 = "copyMemory"
            java.lang.Class<double[]> r1 = double[].class
            java.lang.Class<float[]> r2 = float[].class
            java.lang.Class<long[]> r3 = long[].class
            java.lang.Class<int[]> r4 = int[].class
            java.lang.Class<boolean[]> r5 = boolean[].class
            java.lang.Class<com.google.android.gms.internal.firebase-auth-api.zzacc> r6 = com.google.android.gms.internal.p007firebaseauthapi.zzacc.class
            sun.misc.Unsafe r7 = zzr()
            zzc = r7
            java.lang.Class r8 = com.google.android.gms.internal.p007firebaseauthapi.zzxw.zzb()
            zzd = r8
            java.lang.Class r8 = java.lang.Long.TYPE
            boolean r8 = zzC(r8)
            zze = r8
            java.lang.Class r9 = java.lang.Integer.TYPE
            boolean r9 = zzC(r9)
            zzf = r9
            r10 = 0
            if (r7 != 0) goto L_0x002e
            goto L_0x0049
        L_0x002e:
            boolean r11 = com.google.android.gms.internal.p007firebaseauthapi.zzxw.zza()
            if (r11 == 0) goto L_0x0044
            if (r8 == 0) goto L_0x003c
            com.google.android.gms.internal.firebase-auth-api.zzabz r10 = new com.google.android.gms.internal.firebase-auth-api.zzabz
            r10.<init>(r7)
            goto L_0x0049
        L_0x003c:
            if (r9 == 0) goto L_0x0049
            com.google.android.gms.internal.firebase-auth-api.zzaby r10 = new com.google.android.gms.internal.firebase-auth-api.zzaby
            r10.<init>(r7)
            goto L_0x0049
        L_0x0044:
            com.google.android.gms.internal.firebase-auth-api.zzaca r10 = new com.google.android.gms.internal.firebase-auth-api.zzaca
            r10.<init>(r7)
        L_0x0049:
            zzg = r10
            java.lang.String r8 = "putLong"
            java.lang.String r9 = "putInt"
            java.lang.String r10 = "putByte"
            java.lang.String r11 = "getInt"
            java.lang.String r12 = "getByte"
            java.lang.String r13 = "objectFieldOffset"
            java.lang.String r14 = "com.google.protobuf.UnsafeUtil"
            java.lang.String r15 = "platform method missing - proto runtime falling back to safer methods: "
            r16 = r1
            java.lang.String r1 = "getLong"
            r17 = r2
            r2 = 1
            r18 = 0
            if (r7 != 0) goto L_0x006d
            r19 = r3
        L_0x0068:
            r21 = r4
        L_0x006a:
            r0 = 0
            goto L_0x0154
        L_0x006d:
            java.lang.Class r7 = r7.getClass()     // Catch:{ all -> 0x0121 }
            r19 = r3
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x011f }
            java.lang.Class<java.lang.reflect.Field> r20 = java.lang.reflect.Field.class
            r3[r18] = r20     // Catch:{ all -> 0x011f }
            r7.getMethod(r13, r3)     // Catch:{ all -> 0x011f }
            r3 = 2
            java.lang.Class[] r2 = new java.lang.Class[r3]     // Catch:{ all -> 0x011f }
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            r2[r18] = r3     // Catch:{ all -> 0x011f }
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ all -> 0x011f }
            r20 = 1
            r2[r20] = r3     // Catch:{ all -> 0x011f }
            r7.getMethod(r1, r2)     // Catch:{ all -> 0x011f }
            java.lang.reflect.Field r2 = zzD()     // Catch:{ all -> 0x011f }
            if (r2 != 0) goto L_0x0093
            goto L_0x0068
        L_0x0093:
            boolean r2 = com.google.android.gms.internal.p007firebaseauthapi.zzxw.zza()     // Catch:{ all -> 0x011f }
            if (r2 == 0) goto L_0x009e
            r21 = r4
        L_0x009b:
            r0 = 1
            goto L_0x0154
        L_0x009e:
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x011f }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x011f }
            r3[r18] = r2     // Catch:{ all -> 0x011f }
            r7.getMethod(r12, r3)     // Catch:{ all -> 0x011f }
            r2 = 2
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x011f }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x011f }
            r3[r18] = r2     // Catch:{ all -> 0x011f }
            java.lang.Class r2 = java.lang.Byte.TYPE     // Catch:{ all -> 0x011f }
            r21 = r4
            r4 = 1
            r3[r4] = r2     // Catch:{ all -> 0x011d }
            r7.getMethod(r10, r3)     // Catch:{ all -> 0x011d }
            java.lang.Class[] r2 = new java.lang.Class[r4]     // Catch:{ all -> 0x011d }
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ all -> 0x011d }
            r2[r18] = r3     // Catch:{ all -> 0x011d }
            r7.getMethod(r11, r2)     // Catch:{ all -> 0x011d }
            r2 = 2
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x011d }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x011d }
            r3[r18] = r2     // Catch:{ all -> 0x011d }
            java.lang.Class r2 = java.lang.Integer.TYPE     // Catch:{ all -> 0x011d }
            r4 = 1
            r3[r4] = r2     // Catch:{ all -> 0x011d }
            r7.getMethod(r9, r3)     // Catch:{ all -> 0x011d }
            java.lang.Class[] r2 = new java.lang.Class[r4]     // Catch:{ all -> 0x011d }
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ all -> 0x011d }
            r2[r18] = r3     // Catch:{ all -> 0x011d }
            r7.getMethod(r1, r2)     // Catch:{ all -> 0x011d }
            r2 = 2
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x011d }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x011d }
            r3[r18] = r2     // Catch:{ all -> 0x011d }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x011d }
            r4 = 1
            r3[r4] = r2     // Catch:{ all -> 0x011d }
            r7.getMethod(r8, r3)     // Catch:{ all -> 0x011d }
            r2 = 3
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x011d }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x011d }
            r3[r18] = r2     // Catch:{ all -> 0x011d }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x011d }
            r4 = 1
            r3[r4] = r2     // Catch:{ all -> 0x011d }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x011d }
            r4 = 2
            r3[r4] = r2     // Catch:{ all -> 0x011d }
            r7.getMethod(r0, r3)     // Catch:{ all -> 0x011d }
            r2 = 5
            java.lang.Class[] r2 = new java.lang.Class[r2]     // Catch:{ all -> 0x011d }
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            r2[r18] = r3     // Catch:{ all -> 0x011d }
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ all -> 0x011d }
            r4 = 1
            r2[r4] = r3     // Catch:{ all -> 0x011d }
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            r4 = 2
            r2[r4] = r3     // Catch:{ all -> 0x011d }
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ all -> 0x011d }
            r4 = 3
            r2[r4] = r3     // Catch:{ all -> 0x011d }
            r3 = 4
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ all -> 0x011d }
            r2[r3] = r4     // Catch:{ all -> 0x011d }
            r7.getMethod(r0, r2)     // Catch:{ all -> 0x011d }
            goto L_0x009b
        L_0x011d:
            r0 = move-exception
            goto L_0x0126
        L_0x011f:
            r0 = move-exception
            goto L_0x0124
        L_0x0121:
            r0 = move-exception
            r19 = r3
        L_0x0124:
            r21 = r4
        L_0x0126:
            java.lang.String r2 = r6.getName()
            java.util.logging.Logger r2 = java.util.logging.Logger.getLogger(r2)
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r4 = java.lang.String.valueOf(r0)
            int r4 = r4.length()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            int r4 = r4 + 71
            r7.<init>(r4)
            r7.append(r15)
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            java.lang.String r4 = "supportsUnsafeByteBufferOperations"
            r2.logp(r3, r14, r4, r0)
            goto L_0x006a
        L_0x0154:
            zzh = r0
            sun.misc.Unsafe r0 = zzc
            if (r0 != 0) goto L_0x015f
            r0 = 0
        L_0x015b:
            r20 = 1
            goto L_0x02c0
        L_0x015f:
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x0290 }
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.reflect.Field> r4 = java.lang.reflect.Field.class
            r3[r18] = r4     // Catch:{ all -> 0x0290 }
            r0.getMethod(r13, r3)     // Catch:{ all -> 0x0290 }
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Class> r4 = java.lang.Class.class
            r3[r18] = r4     // Catch:{ all -> 0x0290 }
            java.lang.String r4 = "arrayBaseOffset"
            r0.getMethod(r4, r3)     // Catch:{ all -> 0x0290 }
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Class> r2 = java.lang.Class.class
            r3[r18] = r2     // Catch:{ all -> 0x0290 }
            java.lang.String r2 = "arrayIndexScale"
            r0.getMethod(r2, r3)     // Catch:{ all -> 0x0290 }
            r2 = 2
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r3[r18] = r2     // Catch:{ all -> 0x0290 }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r4 = 1
            r3[r4] = r2     // Catch:{ all -> 0x0290 }
            r0.getMethod(r11, r3)     // Catch:{ all -> 0x0290 }
            r2 = 3
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r3[r18] = r2     // Catch:{ all -> 0x0290 }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r4 = 1
            r3[r4] = r2     // Catch:{ all -> 0x0290 }
            java.lang.Class r2 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0290 }
            r4 = 2
            r3[r4] = r2     // Catch:{ all -> 0x0290 }
            r0.getMethod(r9, r3)     // Catch:{ all -> 0x0290 }
            java.lang.Class[] r2 = new java.lang.Class[r4]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            r2[r18] = r3     // Catch:{ all -> 0x0290 }
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r4 = 1
            r2[r4] = r3     // Catch:{ all -> 0x0290 }
            r0.getMethod(r1, r2)     // Catch:{ all -> 0x0290 }
            r1 = 3
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r18] = r1     // Catch:{ all -> 0x0290 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x0290 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 2
            r2[r3] = r1     // Catch:{ all -> 0x0290 }
            r0.getMethod(r8, r2)     // Catch:{ all -> 0x0290 }
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r18] = r2     // Catch:{ all -> 0x0290 }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x0290 }
            java.lang.String r2 = "getObject"
            r0.getMethod(r2, r1)     // Catch:{ all -> 0x0290 }
            r1 = 3
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r18] = r1     // Catch:{ all -> 0x0290 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r3 = 2
            r2[r3] = r1     // Catch:{ all -> 0x0290 }
            java.lang.String r1 = "putObject"
            r0.getMethod(r1, r2)     // Catch:{ all -> 0x0290 }
            boolean r1 = com.google.android.gms.internal.p007firebaseauthapi.zzxw.zza()     // Catch:{ all -> 0x0290 }
            if (r1 == 0) goto L_0x01f7
            r0 = 1
            goto L_0x015b
        L_0x01f7:
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r18] = r2     // Catch:{ all -> 0x0290 }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x0290 }
            r0.getMethod(r12, r1)     // Catch:{ all -> 0x0290 }
            r1 = 3
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r18] = r1     // Catch:{ all -> 0x0290 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x0290 }
            java.lang.Class r1 = java.lang.Byte.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 2
            r2[r3] = r1     // Catch:{ all -> 0x0290 }
            r0.getMethod(r10, r2)     // Catch:{ all -> 0x0290 }
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r18] = r2     // Catch:{ all -> 0x0290 }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x0290 }
            java.lang.String r2 = "getBoolean"
            r0.getMethod(r2, r1)     // Catch:{ all -> 0x0290 }
            r1 = 3
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r18] = r1     // Catch:{ all -> 0x0290 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x0290 }
            java.lang.Class r1 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 2
            r2[r3] = r1     // Catch:{ all -> 0x0290 }
            java.lang.String r1 = "putBoolean"
            r0.getMethod(r1, r2)     // Catch:{ all -> 0x0290 }
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r18] = r2     // Catch:{ all -> 0x0290 }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x0290 }
            java.lang.String r2 = "getFloat"
            r0.getMethod(r2, r1)     // Catch:{ all -> 0x0290 }
            r1 = 3
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2[r18] = r1     // Catch:{ all -> 0x0290 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 1
            r2[r3] = r1     // Catch:{ all -> 0x0290 }
            java.lang.Class r1 = java.lang.Float.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 2
            r2[r3] = r1     // Catch:{ all -> 0x0290 }
            java.lang.String r1 = "putFloat"
            r0.getMethod(r1, r2)     // Catch:{ all -> 0x0290 }
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r18] = r2     // Catch:{ all -> 0x0290 }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x0290 }
            java.lang.String r2 = "getDouble"
            r0.getMethod(r2, r1)     // Catch:{ all -> 0x0290 }
            r1 = 3
            java.lang.Class[] r1 = new java.lang.Class[r1]     // Catch:{ all -> 0x0290 }
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r18] = r2     // Catch:{ all -> 0x0290 }
            java.lang.Class r2 = java.lang.Long.TYPE     // Catch:{ all -> 0x0290 }
            r20 = 1
            r1[r20] = r2     // Catch:{ all -> 0x028e }
            java.lang.Class r2 = java.lang.Double.TYPE     // Catch:{ all -> 0x028e }
            r3 = 2
            r1[r3] = r2     // Catch:{ all -> 0x028e }
            java.lang.String r2 = "putDouble"
            r0.getMethod(r2, r1)     // Catch:{ all -> 0x028e }
            r0 = 1
            goto L_0x02c0
        L_0x028e:
            r0 = move-exception
            goto L_0x0293
        L_0x0290:
            r0 = move-exception
            r20 = 1
        L_0x0293:
            java.lang.String r1 = r6.getName()
            java.util.logging.Logger r1 = java.util.logging.Logger.getLogger(r1)
            java.util.logging.Level r2 = java.util.logging.Level.WARNING
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r3 = java.lang.String.valueOf(r0)
            int r3 = r3.length()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r3 = r3 + 71
            r4.<init>(r3)
            r4.append(r15)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            java.lang.String r3 = "supportsUnsafeArrayOperations"
            r1.logp(r2, r14, r3, r0)
            r0 = 0
        L_0x02c0:
            zzi = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzA(r0)
            long r0 = (long) r0
            zza = r0
            zzA(r5)
            zzB(r5)
            zzA(r21)
            zzB(r21)
            zzA(r19)
            zzB(r19)
            zzA(r17)
            zzB(r17)
            zzA(r16)
            zzB(r16)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzA(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzB(r0)
            java.lang.reflect.Field r0 = zzD()
            if (r0 == 0) goto L_0x0300
            com.google.android.gms.internal.firebase-auth-api.zzacb r1 = zzg
            if (r1 == 0) goto L_0x0300
            r1.zzi(r0)
        L_0x0300:
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x030a
            r2 = 1
            goto L_0x030b
        L_0x030a:
            r2 = 0
        L_0x030b:
            zzb = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p007firebaseauthapi.zzacc.<clinit>():void");
    }

    private zzacc() {
    }

    private static int zzA(Class<?> cls) {
        if (zzi) {
            return zzg.zzp(cls);
        }
        return -1;
    }

    private static int zzB(Class<?> cls) {
        if (zzi) {
            return zzg.zzq(cls);
        }
        return -1;
    }

    private static boolean zzC(Class<?> cls) {
        Class<byte[]> cls2 = byte[].class;
        if (!zzxw.zza()) {
            return false;
        }
        try {
            Class<?> cls3 = zzd;
            cls3.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls3.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls3.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Field zzD() {
        Field zzE;
        if (zzxw.zza() && (zzE = zzE(Buffer.class, "effectiveDirectAddress")) != null) {
            return zzE;
        }
        Field zzE2 = zzE(Buffer.class, "address");
        if (zzE2 == null || zzE2.getType() != Long.TYPE) {
            return null;
        }
        return zzE2;
    }

    private static Field zzE(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static byte zzF(Object obj, long j) {
        return (byte) ((zzg.zzj(obj, -4 & j) >>> ((int) (((~j) & 3) << 3))) & 255);
    }

    /* access modifiers changed from: private */
    public static byte zzG(Object obj, long j) {
        return (byte) ((zzg.zzj(obj, -4 & j) >>> ((int) ((j & 3) << 3))) & 255);
    }

    /* access modifiers changed from: private */
    public static void zzH(Object obj, long j, byte b) {
        long j2 = -4 & j;
        zzacb zzacb = zzg;
        int zzj = zzacb.zzj(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zzacb.zzk(obj, j2, ((255 & b) << i) | (zzj & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static void zzI(Object obj, long j, byte b) {
        long j2 = -4 & j;
        zzacb zzacb = zzg;
        int i = (((int) j) & 3) << 3;
        zzacb.zzk(obj, j2, ((255 & b) << i) | (zzacb.zzj(obj, j2) & (~(255 << i))));
    }

    static boolean zza() {
        return zzi;
    }

    static boolean zzb() {
        return zzh;
    }

    static <T> T zzc(Class<T> cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static int zzd(Object obj, long j) {
        return zzg.zzj(obj, j);
    }

    static void zze(Object obj, long j, int i) {
        zzg.zzk(obj, j, i);
    }

    static long zzf(Object obj, long j) {
        return zzg.zzl(obj, j);
    }

    static void zzg(Object obj, long j, long j2) {
        zzg.zzm(obj, j, j2);
    }

    static boolean zzh(Object obj, long j) {
        return zzg.zzc(obj, j);
    }

    static void zzi(Object obj, long j, boolean z) {
        zzg.zzd(obj, j, z);
    }

    static float zzj(Object obj, long j) {
        return zzg.zze(obj, j);
    }

    static void zzk(Object obj, long j, float f) {
        zzg.zzf(obj, j, f);
    }

    static double zzl(Object obj, long j) {
        return zzg.zzg(obj, j);
    }

    static void zzm(Object obj, long j, double d) {
        zzg.zzh(obj, j, d);
    }

    static Object zzn(Object obj, long j) {
        return zzg.zzn(obj, j);
    }

    static void zzo(Object obj, long j, Object obj2) {
        zzg.zzo(obj, j, obj2);
    }

    static byte zzp(byte[] bArr, long j) {
        return zzg.zza(bArr, zza + j);
    }

    static void zzq(byte[] bArr, long j, byte b) {
        zzg.zzb(bArr, zza + j, b);
    }

    static Unsafe zzr() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzabx());
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* synthetic */ boolean zzw(Object obj, long j) {
        return zzF(obj, j) != 0;
    }

    static /* synthetic */ boolean zzx(Object obj, long j) {
        return zzG(obj, j) != 0;
    }
}

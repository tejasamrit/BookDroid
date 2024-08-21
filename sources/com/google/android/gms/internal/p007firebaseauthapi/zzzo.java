package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.internal.p007firebaseauthapi.zzzk;
import com.google.android.gms.internal.p007firebaseauthapi.zzzo;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzo */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public abstract class zzzo<MessageType extends zzzo<MessageType, BuilderType>, BuilderType extends zzzk<MessageType, BuilderType>> extends zzxu<MessageType, BuilderType> {
    private static final Map<Object, zzzo<?, ?>> zzb = new ConcurrentHashMap();
    protected zzabs zzc = zzabs.zza();
    protected int zzd = -1;

    static Object zzA(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static <E> zzzt<E> zzB() {
        return zzaba.zzd();
    }

    protected static <E> zzzt<E> zzC(zzzt<E> zzzt) {
        int size = zzzt.size();
        return zzzt.zze(size == 0 ? 10 : size + size);
    }

    static <T extends zzzo<T, ?>> T zzD(T t, byte[] bArr, int i, int i2, zzzb zzzb) throws zzzw {
        T t2 = (zzzo) t.zzj(4, (Object) null, (Object) null);
        try {
            zzabd<?> zzb2 = zzaaz.zza().zzb(t2.getClass());
            zzb2.zzi(t2, bArr, 0, i2, new zzxx(zzzb));
            zzb2.zzj(t2);
            if (t2.zza == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzzw) {
                throw ((zzzw) e.getCause());
            }
            zzzw zzzw = new zzzw(e.getMessage());
            zzzw.zza(t2);
            throw zzzw;
        } catch (IndexOutOfBoundsException unused) {
            zzzw zzb3 = zzzw.zzb();
            zzb3.zza(t2);
            throw zzb3;
        }
    }

    protected static <T extends zzzo<T, ?>> T zzE(T t, zzym zzym, zzzb zzzb) throws zzzw {
        T t2;
        try {
            zzyp zzk = zzym.zzk();
            t2 = (zzzo) t.zzj(4, (Object) null, (Object) null);
            zzabd<?> zzb2 = zzaaz.zza().zzb(t2.getClass());
            zzb2.zzf(t2, zzyq.zza(zzk), zzzb);
            zzb2.zzj(t2);
            zzk.zzb(0);
            zza(t2);
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzzw) {
                throw ((zzzw) e.getCause());
            }
            zzzw zzzw = new zzzw(e.getMessage());
            zzzw.zza(t2);
            throw zzzw;
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzzw) {
                throw ((zzzw) e2.getCause());
            }
            throw e2;
        } catch (zzzw e3) {
            e3.zza(t2);
            throw e3;
        } catch (zzzw e4) {
            throw e4;
        }
    }

    protected static <T extends zzzo<T, ?>> T zzF(T t, byte[] bArr, zzzb zzzb) throws zzzw {
        T zzD = zzD(t, bArr, 0, bArr.length, zzzb);
        zza(zzD);
        return zzD;
    }

    private static <T extends zzzo<T, ?>> T zza(T t) throws zzzw {
        if (t == null || t.zzt()) {
            return t;
        }
        zzzw zzzw = new zzzw(new zzabq(t).getMessage());
        zzzw.zza(t);
        throw zzzw;
    }

    static <T extends zzzo> T zzx(Class<T> cls) {
        Map<Object, zzzo<?, ?>> map = zzb;
        T t = (zzzo) map.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzzo) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzzo) ((zzzo) zzacc.zzc(cls)).zzj(6, (Object) null, (Object) null);
            if (t != null) {
                map.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends zzzo> void zzy(Class<T> cls, T t) {
        zzb.put(cls, t);
    }

    protected static Object zzz(zzaar zzaar, String str, Object[] objArr) {
        return new zzabb(zzaar, str, objArr);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzaaz.zza().zzb(getClass()).zzb(this, (zzzo) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzc2 = zzaaz.zza().zzb(getClass()).zzc(this);
        this.zza = zzc2;
        return zzc2;
    }

    public final String toString() {
        return zzaat.zza(this, super.toString());
    }

    public final /* bridge */ /* synthetic */ zzaaq zzG() {
        zzzk zzzk = (zzzk) zzj(5, (Object) null, (Object) null);
        zzzk.zzm(this);
        return zzzk;
    }

    public final /* bridge */ /* synthetic */ zzaaq zzH() {
        return (zzzk) zzj(5, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public abstract Object zzj(int i, Object obj, Object obj2);

    public final /* bridge */ /* synthetic */ zzaar zzo() {
        return (zzzo) zzj(6, (Object) null, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public final int zzq() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final void zzr(int i) {
        this.zzd = i;
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzzo<MessageType, BuilderType>, BuilderType extends zzzk<MessageType, BuilderType>> BuilderType zzs() {
        return (zzzk) zzj(5, (Object) null, (Object) null);
    }

    public final BuilderType zzu() {
        BuilderType buildertype = (zzzk) zzj(5, (Object) null, (Object) null);
        buildertype.zzm(this);
        return buildertype;
    }

    public final void zzv(zzyw zzyw) throws IOException {
        zzaaz.zza().zzb(getClass()).zzn(this, zzyx.zza(zzyw));
    }

    public final int zzw() {
        int i = this.zzd;
        if (i != -1) {
            return i;
        }
        int zze = zzaaz.zza().zzb(getClass()).zze(this);
        this.zzd = zze;
        return zze;
    }

    public final boolean zzt() {
        Boolean bool = Boolean.TRUE;
        byte byteValue = ((Byte) zzj(1, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zzaaz.zza().zzb(getClass()).zzk(this);
        zzj(2, true != zzk ? null : this, (Object) null);
        return zzk;
    }
}

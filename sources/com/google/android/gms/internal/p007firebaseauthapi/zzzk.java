package com.google.android.gms.internal.p007firebaseauthapi;

import com.google.android.gms.internal.p007firebaseauthapi.zzzk;
import com.google.android.gms.internal.p007firebaseauthapi.zzzo;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzk */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public class zzzk<MessageType extends zzzo<MessageType, BuilderType>, BuilderType extends zzzk<MessageType, BuilderType>> extends zzxt<MessageType, BuilderType> {
    protected MessageType zza;
    protected boolean zzb = false;
    private final MessageType zzc;

    protected zzzk(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (zzzo) messagetype.zzj(4, (Object) null, (Object) null);
    }

    private static final void zza(MessageType messagetype, MessageType messagetype2) {
        zzaaz.zza().zzb(messagetype.getClass()).zzd(messagetype, messagetype2);
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ zzxt zzg(zzxu zzxu) {
        zzm((zzzo) zzxu);
        return this;
    }

    /* access modifiers changed from: protected */
    public void zzi() {
        MessageType messagetype = (zzzo) this.zza.zzj(4, (Object) null, (Object) null);
        zza(messagetype, this.zza);
        this.zza = messagetype;
    }

    /* renamed from: zzj */
    public final BuilderType zzf() {
        BuilderType buildertype = (zzzk) this.zzc.zzj(5, (Object) null, (Object) null);
        buildertype.zzm(zzn());
        return buildertype;
    }

    /* renamed from: zzk */
    public MessageType zzn() {
        if (this.zzb) {
            return this.zza;
        }
        MessageType messagetype = this.zza;
        zzaaz.zza().zzb(messagetype.getClass()).zzj(messagetype);
        this.zzb = true;
        return this.zza;
    }

    public final MessageType zzl() {
        MessageType zzk = zzn();
        if (zzk.zzt()) {
            return zzk;
        }
        throw new zzabq(zzk);
    }

    public final BuilderType zzm(MessageType messagetype) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zza(this.zza, messagetype);
        return this;
    }

    public final /* bridge */ /* synthetic */ zzaar zzo() {
        return this.zzc;
    }
}

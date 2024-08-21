package com.google.android.gms.internal.p007firebaseauthapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztp */
/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public abstract class zztp extends zzb implements zztq {
    public zztp() {
        super("com.google.firebase.auth.api.internal.IFirebaseAuthService");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zztn zztn = null;
        switch (i) {
            case 101:
                zzlu zzlu = (zzlu) zzc.zza(parcel, zzlu.CREATOR);
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface instanceof zztn) {
                        zztn = (zztn) queryLocalInterface;
                    } else {
                        zztn = new zztl(readStrongBinder);
                    }
                }
                zzb(zzlu, zztn);
                break;
            case 102:
                zzms zzms = (zzms) zzc.zza(parcel, zzms.CREATOR);
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface2 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface2;
                    } else {
                        zztn = new zztl(readStrongBinder2);
                    }
                }
                zzc(zzms, zztn);
                break;
            case 103:
                zzmq zzmq = (zzmq) zzc.zza(parcel, zzmq.CREATOR);
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface3 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface3;
                    } else {
                        zztn = new zztl(readStrongBinder3);
                    }
                }
                zzd(zzmq, zztn);
                break;
            case 104:
                zznk zznk = (zznk) zzc.zza(parcel, zznk.CREATOR);
                IBinder readStrongBinder4 = parcel.readStrongBinder();
                if (readStrongBinder4 != null) {
                    IInterface queryLocalInterface4 = readStrongBinder4.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface4 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface4;
                    } else {
                        zztn = new zztl(readStrongBinder4);
                    }
                }
                zze(zznk, zztn);
                break;
            case 105:
                zzle zzle = (zzle) zzc.zza(parcel, zzle.CREATOR);
                IBinder readStrongBinder5 = parcel.readStrongBinder();
                if (readStrongBinder5 != null) {
                    IInterface queryLocalInterface5 = readStrongBinder5.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface5 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface5;
                    } else {
                        zztn = new zztl(readStrongBinder5);
                    }
                }
                zzf(zzle, zztn);
                break;
            case 106:
                zzlg zzlg = (zzlg) zzc.zza(parcel, zzlg.CREATOR);
                IBinder readStrongBinder6 = parcel.readStrongBinder();
                if (readStrongBinder6 != null) {
                    IInterface queryLocalInterface6 = readStrongBinder6.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface6 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface6;
                    } else {
                        zztn = new zztl(readStrongBinder6);
                    }
                }
                zzg(zzlg, zztn);
                break;
            case 107:
                zzlm zzlm = (zzlm) zzc.zza(parcel, zzlm.CREATOR);
                IBinder readStrongBinder7 = parcel.readStrongBinder();
                if (readStrongBinder7 != null) {
                    IInterface queryLocalInterface7 = readStrongBinder7.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface7 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface7;
                    } else {
                        zztn = new zztl(readStrongBinder7);
                    }
                }
                zzh(zzlm, zztn);
                break;
            case 108:
                zzmu zzmu = (zzmu) zzc.zza(parcel, zzmu.CREATOR);
                IBinder readStrongBinder8 = parcel.readStrongBinder();
                if (readStrongBinder8 != null) {
                    IInterface queryLocalInterface8 = readStrongBinder8.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface8 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface8;
                    } else {
                        zztn = new zztl(readStrongBinder8);
                    }
                }
                zzi(zzmu, zztn);
                break;
            case 109:
                zzlw zzlw = (zzlw) zzc.zza(parcel, zzlw.CREATOR);
                IBinder readStrongBinder9 = parcel.readStrongBinder();
                if (readStrongBinder9 != null) {
                    IInterface queryLocalInterface9 = readStrongBinder9.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface9 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface9;
                    } else {
                        zztn = new zztl(readStrongBinder9);
                    }
                }
                zzj(zzlw, zztn);
                break;
            case 111:
                zzly zzly = (zzly) zzc.zza(parcel, zzly.CREATOR);
                IBinder readStrongBinder10 = parcel.readStrongBinder();
                if (readStrongBinder10 != null) {
                    IInterface queryLocalInterface10 = readStrongBinder10.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface10 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface10;
                    } else {
                        zztn = new zztl(readStrongBinder10);
                    }
                }
                zzk(zzly, zztn);
                break;
            case 112:
                zzma zzma = (zzma) zzc.zza(parcel, zzma.CREATOR);
                IBinder readStrongBinder11 = parcel.readStrongBinder();
                if (readStrongBinder11 != null) {
                    IInterface queryLocalInterface11 = readStrongBinder11.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface11 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface11;
                    } else {
                        zztn = new zztl(readStrongBinder11);
                    }
                }
                zzl(zzma, zztn);
                break;
            case 113:
                zzng zzng = (zzng) zzc.zza(parcel, zzng.CREATOR);
                IBinder readStrongBinder12 = parcel.readStrongBinder();
                if (readStrongBinder12 != null) {
                    IInterface queryLocalInterface12 = readStrongBinder12.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface12 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface12;
                    } else {
                        zztn = new zztl(readStrongBinder12);
                    }
                }
                zzm(zzng, zztn);
                break;
            case 114:
                zzni zzni = (zzni) zzc.zza(parcel, zzni.CREATOR);
                IBinder readStrongBinder13 = parcel.readStrongBinder();
                if (readStrongBinder13 != null) {
                    IInterface queryLocalInterface13 = readStrongBinder13.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface13 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface13;
                    } else {
                        zztn = new zztl(readStrongBinder13);
                    }
                }
                zzn(zzni, zztn);
                break;
            case 115:
                zzme zzme = (zzme) zzc.zza(parcel, zzme.CREATOR);
                IBinder readStrongBinder14 = parcel.readStrongBinder();
                if (readStrongBinder14 != null) {
                    IInterface queryLocalInterface14 = readStrongBinder14.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface14 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface14;
                    } else {
                        zztn = new zztl(readStrongBinder14);
                    }
                }
                zzo(zzme, zztn);
                break;
            case 116:
                zzmo zzmo = (zzmo) zzc.zza(parcel, zzmo.CREATOR);
                IBinder readStrongBinder15 = parcel.readStrongBinder();
                if (readStrongBinder15 != null) {
                    IInterface queryLocalInterface15 = readStrongBinder15.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface15 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface15;
                    } else {
                        zztn = new zztl(readStrongBinder15);
                    }
                }
                zzp(zzmo, zztn);
                break;
            case 117:
                zzlo zzlo = (zzlo) zzc.zza(parcel, zzlo.CREATOR);
                IBinder readStrongBinder16 = parcel.readStrongBinder();
                if (readStrongBinder16 != null) {
                    IInterface queryLocalInterface16 = readStrongBinder16.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface16 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface16;
                    } else {
                        zztn = new zztl(readStrongBinder16);
                    }
                }
                zzq(zzlo, zztn);
                break;
            case 119:
                zzli zzli = (zzli) zzc.zza(parcel, zzli.CREATOR);
                IBinder readStrongBinder17 = parcel.readStrongBinder();
                if (readStrongBinder17 != null) {
                    IInterface queryLocalInterface17 = readStrongBinder17.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface17 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface17;
                    } else {
                        zztn = new zztl(readStrongBinder17);
                    }
                }
                zzr(zzli, zztn);
                break;
            case 120:
                zzlc zzlc = (zzlc) zzc.zza(parcel, zzlc.CREATOR);
                IBinder readStrongBinder18 = parcel.readStrongBinder();
                if (readStrongBinder18 != null) {
                    IInterface queryLocalInterface18 = readStrongBinder18.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface18 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface18;
                    } else {
                        zztn = new zztl(readStrongBinder18);
                    }
                }
                zzs(zzlc, zztn);
                break;
            case 121:
                zzlk zzlk = (zzlk) zzc.zza(parcel, zzlk.CREATOR);
                IBinder readStrongBinder19 = parcel.readStrongBinder();
                if (readStrongBinder19 != null) {
                    IInterface queryLocalInterface19 = readStrongBinder19.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface19 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface19;
                    } else {
                        zztn = new zztl(readStrongBinder19);
                    }
                }
                zzt(zzlk, zztn);
                break;
            case 122:
                zzmk zzmk = (zzmk) zzc.zza(parcel, zzmk.CREATOR);
                IBinder readStrongBinder20 = parcel.readStrongBinder();
                if (readStrongBinder20 != null) {
                    IInterface queryLocalInterface20 = readStrongBinder20.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface20 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface20;
                    } else {
                        zztn = new zztl(readStrongBinder20);
                    }
                }
                zzu(zzmk, zztn);
                break;
            case 123:
                zzmy zzmy = (zzmy) zzc.zza(parcel, zzmy.CREATOR);
                IBinder readStrongBinder21 = parcel.readStrongBinder();
                if (readStrongBinder21 != null) {
                    IInterface queryLocalInterface21 = readStrongBinder21.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface21 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface21;
                    } else {
                        zztn = new zztl(readStrongBinder21);
                    }
                }
                zzv(zzmy, zztn);
                break;
            case 124:
                zzmc zzmc = (zzmc) zzc.zza(parcel, zzmc.CREATOR);
                IBinder readStrongBinder22 = parcel.readStrongBinder();
                if (readStrongBinder22 != null) {
                    IInterface queryLocalInterface22 = readStrongBinder22.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface22 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface22;
                    } else {
                        zztn = new zztl(readStrongBinder22);
                    }
                }
                zzw(zzmc, zztn);
                break;
            case 126:
                zzmg zzmg = (zzmg) zzc.zza(parcel, zzmg.CREATOR);
                IBinder readStrongBinder23 = parcel.readStrongBinder();
                if (readStrongBinder23 != null) {
                    IInterface queryLocalInterface23 = readStrongBinder23.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface23 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface23;
                    } else {
                        zztn = new zztl(readStrongBinder23);
                    }
                }
                zzx(zzmg, zztn);
                break;
            case 127:
                zzmm zzmm = (zzmm) zzc.zza(parcel, zzmm.CREATOR);
                IBinder readStrongBinder24 = parcel.readStrongBinder();
                if (readStrongBinder24 != null) {
                    IInterface queryLocalInterface24 = readStrongBinder24.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface24 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface24;
                    } else {
                        zztn = new zztl(readStrongBinder24);
                    }
                }
                zzy(zzmm, zztn);
                break;
            case 128:
                zzmi zzmi = (zzmi) zzc.zza(parcel, zzmi.CREATOR);
                IBinder readStrongBinder25 = parcel.readStrongBinder();
                if (readStrongBinder25 != null) {
                    IInterface queryLocalInterface25 = readStrongBinder25.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface25 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface25;
                    } else {
                        zztn = new zztl(readStrongBinder25);
                    }
                }
                zzz(zzmi, zztn);
                break;
            case 129:
                zzmw zzmw = (zzmw) zzc.zza(parcel, zzmw.CREATOR);
                IBinder readStrongBinder26 = parcel.readStrongBinder();
                if (readStrongBinder26 != null) {
                    IInterface queryLocalInterface26 = readStrongBinder26.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface26 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface26;
                    } else {
                        zztn = new zztl(readStrongBinder26);
                    }
                }
                zzA(zzmw, zztn);
                break;
            case 130:
                zzna zzna = (zzna) zzc.zza(parcel, zzna.CREATOR);
                IBinder readStrongBinder27 = parcel.readStrongBinder();
                if (readStrongBinder27 != null) {
                    IInterface queryLocalInterface27 = readStrongBinder27.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface27 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface27;
                    } else {
                        zztn = new zztl(readStrongBinder27);
                    }
                }
                zzB(zzna, zztn);
                break;
            case 131:
                zzne zzne = (zzne) zzc.zza(parcel, zzne.CREATOR);
                IBinder readStrongBinder28 = parcel.readStrongBinder();
                if (readStrongBinder28 != null) {
                    IInterface queryLocalInterface28 = readStrongBinder28.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface28 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface28;
                    } else {
                        zztn = new zztl(readStrongBinder28);
                    }
                }
                zzD(zzne, zztn);
                break;
            case 132:
                zzlq zzlq = (zzlq) zzc.zza(parcel, zzlq.CREATOR);
                IBinder readStrongBinder29 = parcel.readStrongBinder();
                if (readStrongBinder29 != null) {
                    IInterface queryLocalInterface29 = readStrongBinder29.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface29 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface29;
                    } else {
                        zztn = new zztl(readStrongBinder29);
                    }
                }
                zzC(zzlq, zztn);
                break;
            case 133:
                zznc zznc = (zznc) zzc.zza(parcel, zznc.CREATOR);
                IBinder readStrongBinder30 = parcel.readStrongBinder();
                if (readStrongBinder30 != null) {
                    IInterface queryLocalInterface30 = readStrongBinder30.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface30 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface30;
                    } else {
                        zztn = new zztl(readStrongBinder30);
                    }
                }
                zzE(zznc, zztn);
                break;
            case 134:
                zzls zzls = (zzls) zzc.zza(parcel, zzls.CREATOR);
                IBinder readStrongBinder31 = parcel.readStrongBinder();
                if (readStrongBinder31 != null) {
                    IInterface queryLocalInterface31 = readStrongBinder31.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface31 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface31;
                    } else {
                        zztn = new zztl(readStrongBinder31);
                    }
                }
                zzF(zzls, zztn);
                break;
            case 135:
                zznm zznm = (zznm) zzc.zza(parcel, zznm.CREATOR);
                IBinder readStrongBinder32 = parcel.readStrongBinder();
                if (readStrongBinder32 != null) {
                    IInterface queryLocalInterface32 = readStrongBinder32.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface32 instanceof zztn) {
                        zztn = (zztn) queryLocalInterface32;
                    } else {
                        zztn = new zztl(readStrongBinder32);
                    }
                }
                zzG(zznm, zztn);
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}

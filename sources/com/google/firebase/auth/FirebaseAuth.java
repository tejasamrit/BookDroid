package com.google.firebase.auth;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p007firebaseauthapi.zzsy;
import com.google.android.gms.internal.p007firebaseauthapi.zzta;
import com.google.android.gms.internal.p007firebaseauthapi.zzte;
import com.google.android.gms.internal.p007firebaseauthapi.zztu;
import com.google.android.gms.internal.p007firebaseauthapi.zztw;
import com.google.android.gms.internal.p007firebaseauthapi.zzud;
import com.google.android.gms.internal.p007firebaseauthapi.zzux;
import com.google.android.gms.internal.p007firebaseauthapi.zzvh;
import com.google.android.gms.internal.p007firebaseauthapi.zzwg;
import com.google.android.gms.internal.p007firebaseauthapi.zzwt;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.auth.internal.zzag;
import com.google.firebase.auth.internal.zzay;
import com.google.firebase.auth.internal.zzbg;
import com.google.firebase.auth.internal.zzbi;
import com.google.firebase.auth.internal.zzbj;
import com.google.firebase.auth.internal.zzbk;
import com.google.firebase.auth.internal.zzbm;
import com.google.firebase.auth.internal.zzf;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzw;
import com.google.firebase.auth.internal.zzx;
import com.google.firebase.internal.InternalTokenResult;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
public abstract class FirebaseAuth implements InternalAuthProvider {
    private FirebaseApp zza;
    /* access modifiers changed from: private */
    public final List<IdTokenListener> zzb;
    /* access modifiers changed from: private */
    public final List<com.google.firebase.auth.internal.IdTokenListener> zzc;
    /* access modifiers changed from: private */
    public List<AuthStateListener> zzd;
    /* access modifiers changed from: private */
    public zzsy zze;
    /* access modifiers changed from: private */
    public FirebaseUser zzf;
    /* access modifiers changed from: private */
    public zzw zzg;
    private final Object zzh = new Object();
    /* access modifiers changed from: private */
    public String zzi;
    private final Object zzj = new Object();
    private String zzk;
    private final zzbg zzl;
    private final zzbm zzm;
    private final zzf zzn;
    private zzbi zzo;
    private zzbj zzp;

    /* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
    public interface AuthStateListener {
        void onAuthStateChanged(FirebaseAuth firebaseAuth);
    }

    /* compiled from: com.google.firebase:firebase-auth@@20.0.1 */
    public interface IdTokenListener {
        void onIdTokenChanged(FirebaseAuth firebaseAuth);
    }

    public FirebaseAuth(FirebaseApp firebaseApp) {
        zzwg zzd2;
        zzsy zza2 = zztw.zza(firebaseApp.getApplicationContext(), zztu.zza(Preconditions.checkNotEmpty(firebaseApp.getOptions().getApiKey())));
        zzbg zzbg = new zzbg(firebaseApp.getApplicationContext(), firebaseApp.getPersistenceKey());
        zzbm zza3 = zzbm.zza();
        zzf zza4 = zzf.zza();
        this.zza = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        this.zze = (zzsy) Preconditions.checkNotNull(zza2);
        zzbg zzbg2 = (zzbg) Preconditions.checkNotNull(zzbg);
        this.zzl = zzbg2;
        this.zzg = new zzw();
        zzbm zzbm = (zzbm) Preconditions.checkNotNull(zza3);
        this.zzm = zzbm;
        this.zzn = (zzf) Preconditions.checkNotNull(zza4);
        this.zzb = new CopyOnWriteArrayList();
        this.zzc = new CopyOnWriteArrayList();
        this.zzd = new CopyOnWriteArrayList();
        this.zzp = zzbj.zza();
        FirebaseUser zzb2 = zzbg2.zzb();
        this.zzf = zzb2;
        if (!(zzb2 == null || (zzd2 = zzbg2.zzd(zzb2)) == null)) {
            zza(this.zzf, zzd2, false, false);
        }
        zzbm.zzf(this);
    }

    public static FirebaseAuth getInstance() {
        return (FirebaseAuth) FirebaseApp.getInstance().get(FirebaseAuth.class);
    }

    /* access modifiers changed from: private */
    public final PhoneAuthProvider.OnVerificationStateChangedCallbacks zzJ(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
        return (!this.zzg.zzc() || !str.equals(this.zzg.zza())) ? onVerificationStateChangedCallbacks : new zzq(this, onVerificationStateChangedCallbacks);
    }

    private final boolean zzK(String str) {
        ActionCodeUrl parseLink = ActionCodeUrl.parseLink(str);
        return parseLink != null && !TextUtils.equals(this.zzk, parseLink.zza());
    }

    public void addAuthStateListener(AuthStateListener authStateListener) {
        this.zzd.add(authStateListener);
        this.zzp.execute(new zzk(this, authStateListener));
    }

    public void addIdTokenListener(IdTokenListener idTokenListener) {
        this.zzb.add(idTokenListener);
        this.zzp.execute(new zzj(this, idTokenListener));
    }

    public Task<Void> applyActionCode(String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzE(this.zza, str, this.zzk);
    }

    public Task<ActionCodeResult> checkActionCode(String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzD(this.zza, str, this.zzk);
    }

    public Task<Void> confirmPasswordReset(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zze.zzG(this.zza, str, str2, this.zzk);
    }

    public Task<AuthResult> createUserWithEmailAndPassword(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zze.zzp(this.zza, str, str2, this.zzk, new zzs(this));
    }

    public Task<SignInMethodQueryResult> fetchSignInMethodsForEmail(String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzz(this.zza, str, this.zzk);
    }

    public final Task<GetTokenResult> getAccessToken(boolean z) {
        return zzh(this.zzf, z);
    }

    public FirebaseApp getApp() {
        return this.zza;
    }

    public FirebaseUser getCurrentUser() {
        return this.zzf;
    }

    public FirebaseAuthSettings getFirebaseAuthSettings() {
        return this.zzg;
    }

    public String getLanguageCode() {
        String str;
        synchronized (this.zzh) {
            str = this.zzi;
        }
        return str;
    }

    public Task<AuthResult> getPendingAuthResult() {
        return this.zzm.zzd();
    }

    public String getTenantId() {
        String str;
        synchronized (this.zzj) {
            str = this.zzk;
        }
        return str;
    }

    public final String getUid() {
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser == null) {
            return null;
        }
        return firebaseUser.getUid();
    }

    public boolean isSignInWithEmailLink(String str) {
        return EmailAuthCredential.zzi(str);
    }

    public void removeAuthStateListener(AuthStateListener authStateListener) {
        this.zzd.remove(authStateListener);
    }

    public void removeIdTokenListener(IdTokenListener idTokenListener) {
        this.zzb.remove(idTokenListener);
    }

    public Task<Void> sendPasswordResetEmail(String str) {
        Preconditions.checkNotEmpty(str);
        return sendPasswordResetEmail(str, (ActionCodeSettings) null);
    }

    public Task<Void> sendSignInLinkToEmail(String str, ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(actionCodeSettings);
        if (actionCodeSettings.canHandleCodeInApp()) {
            String str2 = this.zzi;
            if (str2 != null) {
                actionCodeSettings.zzc(str2);
            }
            return this.zze.zzB(this.zza, str, actionCodeSettings, this.zzk);
        }
        throw new IllegalArgumentException("You must set canHandleCodeInApp in your ActionCodeSettings to true for Email-Link Sign-in.");
    }

    public Task<Void> setFirebaseUIVersion(String str) {
        return this.zze.zzL(str);
    }

    public void setLanguageCode(String str) {
        Preconditions.checkNotEmpty(str);
        synchronized (this.zzh) {
            this.zzi = str;
        }
    }

    public void setTenantId(String str) {
        Preconditions.checkNotEmpty(str);
        synchronized (this.zzj) {
            this.zzk = str;
        }
    }

    public Task<AuthResult> signInAnonymously() {
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser == null || !firebaseUser.isAnonymous()) {
            return this.zze.zzj(this.zza, new zzs(this), this.zzk);
        }
        zzx zzx = (zzx) this.zzf;
        zzx.zzn(false);
        return Tasks.forResult(new zzr(zzx));
    }

    public Task<AuthResult> signInWithCredential(AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        AuthCredential zza2 = authCredential.zza();
        if (zza2 instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zza2;
            if (!emailAuthCredential.zzh()) {
                return this.zze.zzq(this.zza, emailAuthCredential.zzb(), emailAuthCredential.zzc(), this.zzk, new zzs(this));
            }
            if (zzK(emailAuthCredential.zzd())) {
                return Tasks.forException(zzte.zza(new Status(17072)));
            }
            return this.zze.zzr(this.zza, emailAuthCredential, new zzs(this));
        } else if (!(zza2 instanceof PhoneAuthCredential)) {
            return this.zze.zzg(this.zza, zza2, this.zzk, new zzs(this));
        } else {
            return this.zze.zzw(this.zza, (PhoneAuthCredential) zza2, this.zzk, new zzs(this));
        }
    }

    public Task<AuthResult> signInWithCustomToken(String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzf(this.zza, str, this.zzk, new zzs(this));
    }

    public Task<AuthResult> signInWithEmailAndPassword(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zze.zzq(this.zza, str, str2, this.zzk, new zzs(this));
    }

    public Task<AuthResult> signInWithEmailLink(String str, String str2) {
        return signInWithCredential(EmailAuthProvider.getCredentialWithLink(str, str2));
    }

    public void signOut() {
        zzb();
        zzbi zzbi = this.zzo;
        if (zzbi != null) {
            zzbi.zzc();
        }
    }

    public Task<AuthResult> startActivityForSignInWithProvider(Activity activity, FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(activity);
        if (!zzta.zza()) {
            return Tasks.forException(zzte.zza(new Status(17063)));
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzm.zzi(activity, taskCompletionSource, this)) {
            return Tasks.forException(zzte.zza(new Status(17057)));
        }
        this.zzm.zzb(activity.getApplicationContext(), this);
        federatedAuthProvider.zza(activity);
        return taskCompletionSource.getTask();
    }

    public void useAppLanguage() {
        synchronized (this.zzh) {
            this.zzi = zzud.zza();
        }
    }

    public void useEmulator(String str, int i) {
        Preconditions.checkNotEmpty(str);
        boolean z = false;
        if (i >= 0 && i <= 65535) {
            z = true;
        }
        Preconditions.checkArgument(z, "Port number must be in the range 0-65535");
        zzvh.zza(this.zza, str, i);
    }

    public Task<String> verifyPasswordResetCode(String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzF(this.zza, str, this.zzk);
    }

    public final Task<Void> zzA(FirebaseUser firebaseUser, MultiFactorAssertion multiFactorAssertion, String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(multiFactorAssertion);
        if (!(multiFactorAssertion instanceof PhoneMultiFactorAssertion)) {
            return Tasks.forException(zzte.zza(new Status(FirebaseError.ERROR_INTERNAL_ERROR)));
        }
        return this.zze.zzN(this.zza, (PhoneMultiFactorAssertion) multiFactorAssertion, firebaseUser, str, new zzs(this));
    }

    /* access modifiers changed from: package-private */
    public final void zza(FirebaseUser firebaseUser, zzwg zzwg, boolean z, boolean z2) {
        boolean z3;
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzwg);
        boolean z4 = false;
        boolean z5 = true;
        boolean z6 = this.zzf != null && firebaseUser.getUid().equals(this.zzf.getUid());
        if (z6 || !z2) {
            FirebaseUser firebaseUser2 = this.zzf;
            if (firebaseUser2 == null) {
                z3 = true;
            } else {
                boolean z7 = !firebaseUser2.zze().zze().equals(zzwg.zze());
                if (!z6 || z7) {
                    z4 = true;
                }
                z3 = true ^ z6;
                z5 = z4;
            }
            Preconditions.checkNotNull(firebaseUser);
            FirebaseUser firebaseUser3 = this.zzf;
            if (firebaseUser3 == null) {
                this.zzf = firebaseUser;
            } else {
                firebaseUser3.zzb(firebaseUser.getProviderData());
                if (!firebaseUser.isAnonymous()) {
                    this.zzf.zzc();
                }
                this.zzf.zzi(firebaseUser.getMultiFactor().getEnrolledFactors());
            }
            if (z) {
                this.zzl.zza(this.zzf);
            }
            if (z5) {
                FirebaseUser firebaseUser4 = this.zzf;
                if (firebaseUser4 != null) {
                    firebaseUser4.zzf(zzwg);
                }
                zzf(this.zzf);
            }
            if (z3) {
                zzg(this.zzf);
            }
            if (z) {
                this.zzl.zzc(firebaseUser, zzwg);
            }
            zzd().zzb(this.zzf.zze());
        }
    }

    public final void zzb() {
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser != null) {
            zzbg zzbg = this.zzl;
            Preconditions.checkNotNull(firebaseUser);
            zzbg.zze(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}));
            this.zzf = null;
        }
        this.zzl.zze("com.google.firebase.auth.FIREBASE_USER");
        zzf((FirebaseUser) null);
        zzg((FirebaseUser) null);
    }

    public final synchronized void zzc(zzbi zzbi) {
        this.zzo = zzbi;
    }

    public final synchronized zzbi zzd() {
        if (this.zzo == null) {
            zzc(new zzbi(this.zza));
        }
        return this.zzo;
    }

    public final FirebaseApp zze() {
        return this.zza;
    }

    public final void zzf(FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(uid).length() + 45);
            sb.append("Notifying id token listeners about user ( ");
            sb.append(uid);
            sb.append(" ).");
            Log.d("FirebaseAuth", sb.toString());
        } else {
            Log.d("FirebaseAuth", "Notifying id token listeners about a sign-out event.");
        }
        this.zzp.execute(new zzl(this, new InternalTokenResult(firebaseUser != null ? firebaseUser.zzh() : null)));
    }

    public final void zzg(FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(uid).length() + 47);
            sb.append("Notifying auth state listeners about user ( ");
            sb.append(uid);
            sb.append(" ).");
            Log.d("FirebaseAuth", sb.toString());
        } else {
            Log.d("FirebaseAuth", "Notifying auth state listeners about a sign-out event.");
        }
        this.zzp.execute(new zzm(this));
    }

    public final Task<GetTokenResult> zzh(FirebaseUser firebaseUser, boolean z) {
        if (firebaseUser == null) {
            return Tasks.forException(zzte.zza(new Status(FirebaseError.ERROR_NO_SIGNED_IN_USER)));
        }
        zzwg zze2 = firebaseUser.zze();
        if (!zze2.zzb() || z) {
            return this.zze.zze(this.zza, firebaseUser, zze2.zzd(), new zzn(this));
        }
        return Tasks.forResult(zzay.zza(zze2.zze()));
    }

    public final Task<Void> zzi(FirebaseUser firebaseUser, AuthCredential authCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(authCredential);
        AuthCredential zza2 = authCredential.zza();
        if (zza2 instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zza2;
            if ("password".equals(emailAuthCredential.getSignInMethod())) {
                return this.zze.zzs(this.zza, firebaseUser, emailAuthCredential.zzb(), emailAuthCredential.zzc(), firebaseUser.getTenantId(), new zzt(this));
            } else if (zzK(emailAuthCredential.zzd())) {
                return Tasks.forException(zzte.zza(new Status(17072)));
            } else {
                return this.zze.zzu(this.zza, firebaseUser, emailAuthCredential, new zzt(this));
            }
        } else if (zza2 instanceof PhoneAuthCredential) {
            return this.zze.zzx(this.zza, firebaseUser, (PhoneAuthCredential) zza2, this.zzk, new zzt(this));
        } else {
            return this.zze.zzh(this.zza, firebaseUser, zza2, firebaseUser.getTenantId(), new zzt(this));
        }
    }

    public final Task<AuthResult> zzj(FirebaseUser firebaseUser, AuthCredential authCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(authCredential);
        AuthCredential zza2 = authCredential.zza();
        if (zza2 instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zza2;
            if ("password".equals(emailAuthCredential.getSignInMethod())) {
                return this.zze.zzt(this.zza, firebaseUser, emailAuthCredential.zzb(), emailAuthCredential.zzc(), firebaseUser.getTenantId(), new zzt(this));
            } else if (zzK(emailAuthCredential.zzd())) {
                return Tasks.forException(zzte.zza(new Status(17072)));
            } else {
                return this.zze.zzv(this.zza, firebaseUser, emailAuthCredential, new zzt(this));
            }
        } else if (zza2 instanceof PhoneAuthCredential) {
            return this.zze.zzy(this.zza, firebaseUser, (PhoneAuthCredential) zza2, this.zzk, new zzt(this));
        } else {
            return this.zze.zzi(this.zza, firebaseUser, zza2, firebaseUser.getTenantId(), new zzt(this));
        }
    }

    public final void zzk(String str, long j, TimeUnit timeUnit, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor, boolean z, String str2, String str3) {
        long j2 = j;
        long convert = TimeUnit.SECONDS.convert(j, timeUnit);
        if (convert < 0 || convert > 120) {
            throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
        }
        String str4 = str;
        this.zze.zzk(this.zza, new zzwt(str, convert, z, this.zzi, this.zzk, str2, zzta.zza(), str3), zzJ(str, onVerificationStateChangedCallbacks), activity, executor);
    }

    public final void zzl(PhoneAuthOptions phoneAuthOptions) {
        String str;
        if (phoneAuthOptions.zzk()) {
            FirebaseAuth zza2 = phoneAuthOptions.zza();
            zzag zzag = (zzag) phoneAuthOptions.zzg();
            if (phoneAuthOptions.zzf() != null) {
                if (zzag.zze()) {
                    str = phoneAuthOptions.zzb();
                } else {
                    str = phoneAuthOptions.zzj().getUid();
                }
                if (zzux.zzb(str, phoneAuthOptions.zzd(), phoneAuthOptions.zzi(), phoneAuthOptions.zze())) {
                    return;
                }
            }
            zza2.zzn.zzb(zza2, phoneAuthOptions.zzb(), phoneAuthOptions.zzi(), zzta.zza()).addOnCompleteListener(new zzp(zza2, phoneAuthOptions));
            return;
        }
        FirebaseAuth zza3 = phoneAuthOptions.zza();
        String zzb2 = phoneAuthOptions.zzb();
        long longValue = phoneAuthOptions.zzc().longValue();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        PhoneAuthProvider.OnVerificationStateChangedCallbacks zzd2 = phoneAuthOptions.zzd();
        Activity zzi2 = phoneAuthOptions.zzi();
        Executor zze2 = phoneAuthOptions.zze();
        boolean z = phoneAuthOptions.zzf() != null;
        if (z || !zzux.zzb(zzb2, zzd2, zzi2, zze2)) {
            zza3.zzn.zzb(zza3, zzb2, zzi2, zzta.zza()).addOnCompleteListener(new zzo(zza3, zzb2, longValue, timeUnit, zzd2, zzi2, zze2, z));
        }
    }

    public final Task<Void> zzm(FirebaseUser firebaseUser, zzbk zzbk) {
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zzJ(this.zza, firebaseUser, zzbk);
    }

    public final Task<AuthResult> zzn(FirebaseUser firebaseUser, AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zzH(this.zza, firebaseUser, authCredential.zza(), new zzt(this));
    }

    public final Task<AuthResult> zzo(FirebaseUser firebaseUser, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zzI(this.zza, firebaseUser, str, new zzt(this));
    }

    public final Task<Void> zzp(FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(userProfileChangeRequest);
        return this.zze.zzl(this.zza, firebaseUser, userProfileChangeRequest, new zzt(this));
    }

    public final Task<Void> zzq(FirebaseUser firebaseUser, String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zze.zzm(this.zza, firebaseUser, str, new zzt(this));
    }

    public final Task<Void> zzr(FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(phoneAuthCredential);
        return this.zze.zzo(this.zza, firebaseUser, phoneAuthCredential.clone(), new zzt(this));
    }

    public final Task<Void> zzs(FirebaseUser firebaseUser, String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zze.zzn(this.zza, firebaseUser, str, new zzt(this));
    }

    public final Task<Void> zzt(ActionCodeSettings actionCodeSettings, String str) {
        Preconditions.checkNotEmpty(str);
        if (this.zzi != null) {
            if (actionCodeSettings == null) {
                actionCodeSettings = ActionCodeSettings.zza();
            }
            actionCodeSettings.zzc(this.zzi);
        }
        return this.zze.zzC(this.zza, actionCodeSettings, str);
    }

    public final Task<Void> zzu(FirebaseUser firebaseUser, String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zze.zzQ(this.zza, firebaseUser, str, new zzt(this)).continueWithTask(new zzr(this));
    }

    public final Task<AuthResult> zzv(MultiFactorAssertion multiFactorAssertion, zzag zzag, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotNull(zzag);
        return this.zze.zzP(this.zza, firebaseUser, (PhoneMultiFactorAssertion) multiFactorAssertion, zzag.zzd(), new zzs(this));
    }

    public final Task<AuthResult> zzw(Activity activity, FederatedAuthProvider federatedAuthProvider, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(firebaseUser);
        if (!zzta.zza()) {
            return Tasks.forException(zzte.zza(new Status(17063)));
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzm.zzj(activity, taskCompletionSource, this, firebaseUser)) {
            return Tasks.forException(zzte.zza(new Status(17057)));
        }
        this.zzm.zzc(activity.getApplicationContext(), this, firebaseUser);
        federatedAuthProvider.zzb(activity);
        return taskCompletionSource.getTask();
    }

    public final Task<AuthResult> zzx(Activity activity, FederatedAuthProvider federatedAuthProvider, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(firebaseUser);
        if (!zzta.zza()) {
            return Tasks.forException(zzte.zza(new Status(17063)));
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzm.zzj(activity, taskCompletionSource, this, firebaseUser)) {
            return Tasks.forException(zzte.zza(new Status(17057)));
        }
        this.zzm.zzc(activity.getApplicationContext(), this, firebaseUser);
        federatedAuthProvider.zzc(activity);
        return taskCompletionSource.getTask();
    }

    public final Task<Void> zzy(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zzK(firebaseUser, new zzi(this, firebaseUser));
    }

    public final Task<Void> zzz(String str, String str2, ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        if (actionCodeSettings == null) {
            actionCodeSettings = ActionCodeSettings.zza();
        }
        String str3 = this.zzi;
        if (str3 != null) {
            actionCodeSettings.zzc(str3);
        }
        return this.zze.zzR(str, str2, actionCodeSettings);
    }

    public void removeIdTokenListener(com.google.firebase.auth.internal.IdTokenListener idTokenListener) {
        Preconditions.checkNotNull(idTokenListener);
        this.zzc.remove(idTokenListener);
        zzd().zza(this.zzc.size());
    }

    public Task<Void> updateCurrentUser(FirebaseUser firebaseUser) {
        String str;
        if (firebaseUser == null) {
            throw new IllegalArgumentException("Cannot update current user with null user!");
        } else if ((firebaseUser.getTenantId() != null && !firebaseUser.getTenantId().equals(this.zzk)) || ((str = this.zzk) != null && !str.equals(firebaseUser.getTenantId()))) {
            return Tasks.forException(zzte.zza(new Status(17072)));
        } else {
            String apiKey = firebaseUser.zzd().getOptions().getApiKey();
            String apiKey2 = this.zza.getOptions().getApiKey();
            if (!firebaseUser.zze().zzb() || !apiKey2.equals(apiKey)) {
                return zzm(firebaseUser, new zzu(this));
            }
            zza(zzx.zzs(this.zza, firebaseUser), firebaseUser.zze(), true, false);
            return Tasks.forResult(null);
        }
    }

    public static FirebaseAuth getInstance(FirebaseApp firebaseApp) {
        return (FirebaseAuth) firebaseApp.get(FirebaseAuth.class);
    }

    public void addIdTokenListener(com.google.firebase.auth.internal.IdTokenListener idTokenListener) {
        Preconditions.checkNotNull(idTokenListener);
        this.zzc.add(idTokenListener);
        zzd().zza(this.zzc.size());
    }

    public Task<Void> sendPasswordResetEmail(String str, ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        if (actionCodeSettings == null) {
            actionCodeSettings = ActionCodeSettings.zza();
        }
        String str2 = this.zzi;
        if (str2 != null) {
            actionCodeSettings.zzc(str2);
        }
        actionCodeSettings.zze(1);
        return this.zze.zzA(this.zza, str, actionCodeSettings, this.zzk);
    }
}

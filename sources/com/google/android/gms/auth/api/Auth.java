package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zzf;
import com.google.android.gms.auth.api.signin.internal.zzg;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.p006authapi.zzj;
import com.google.android.gms.internal.p006authapi.zzq;

/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class Auth {
    public static final Api<AuthCredentialsOptions> CREDENTIALS_API;
    public static final CredentialsApi CredentialsApi = new zzj();
    public static final Api<GoogleSignInOptions> GOOGLE_SIGN_IN_API;
    public static final GoogleSignInApi GoogleSignInApi = new zzg();
    @Deprecated
    public static final Api<AuthProxyOptions> PROXY_API = AuthProxy.API;
    @Deprecated
    public static final ProxyApi ProxyApi = AuthProxy.ProxyApi;
    public static final Api.ClientKey<zzq> zzg;
    public static final Api.ClientKey<zzf> zzh;
    private static final Api.AbstractClientBuilder<zzq, AuthCredentialsOptions> zzi;
    private static final Api.AbstractClientBuilder<zzf, GoogleSignInOptions> zzj;

    private Auth() {
    }

    @Deprecated
    /* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
    public static class AuthCredentialsOptions implements Api.ApiOptions.Optional {
        public static final AuthCredentialsOptions zzk = new Builder().zze();
        /* access modifiers changed from: private */
        public final String zzl;
        /* access modifiers changed from: private */
        public final boolean zzm;
        /* access modifiers changed from: private */
        public final String zzn;

        public AuthCredentialsOptions(Builder builder) {
            this.zzl = builder.zzl;
            this.zzm = builder.zzu.booleanValue();
            this.zzn = builder.zzn;
        }

        @Deprecated
        /* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
        public static class Builder {
            protected String zzl;
            protected String zzn;
            protected Boolean zzu = false;

            public Builder() {
            }

            public Builder(AuthCredentialsOptions authCredentialsOptions) {
                this.zzl = authCredentialsOptions.zzl;
                this.zzu = Boolean.valueOf(authCredentialsOptions.zzm);
                this.zzn = authCredentialsOptions.zzn;
            }

            public Builder forceEnableSaveDialog() {
                this.zzu = true;
                return this;
            }

            public Builder zzc(String str) {
                this.zzn = str;
                return this;
            }

            public AuthCredentialsOptions zze() {
                return new AuthCredentialsOptions(this);
            }
        }

        public final String zzd() {
            return this.zzl;
        }

        public final String getLogSessionId() {
            return this.zzn;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AuthCredentialsOptions)) {
                return false;
            }
            AuthCredentialsOptions authCredentialsOptions = (AuthCredentialsOptions) obj;
            return Objects.equal(this.zzl, authCredentialsOptions.zzl) && this.zzm == authCredentialsOptions.zzm && Objects.equal(this.zzn, authCredentialsOptions.zzn);
        }

        public int hashCode() {
            return Objects.hashCode(this.zzl, Boolean.valueOf(this.zzm), this.zzn);
        }

        public final Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putString("consumer_package", this.zzl);
            bundle.putBoolean("force_save_dialog", this.zzm);
            bundle.putString("log_session_id", this.zzn);
            return bundle;
        }
    }

    static {
        Api.ClientKey<zzq> clientKey = new Api.ClientKey<>();
        zzg = clientKey;
        Api.ClientKey<zzf> clientKey2 = new Api.ClientKey<>();
        zzh = clientKey2;
        zzc zzc = new zzc();
        zzi = zzc;
        zzd zzd = new zzd();
        zzj = zzd;
        CREDENTIALS_API = new Api<>("Auth.CREDENTIALS_API", zzc, clientKey);
        GOOGLE_SIGN_IN_API = new Api<>("Auth.GOOGLE_SIGN_IN_API", zzd, clientKey2);
    }
}

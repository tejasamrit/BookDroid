package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions.Optional, ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zad();
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    public static final Scope zaa = new Scope(Scopes.PROFILE);
    public static final Scope zab = new Scope("email");
    public static final Scope zac = new Scope(Scopes.OPEN_ID);
    public static final Scope zad;
    public static final Scope zae = new Scope(Scopes.GAMES);
    private static Comparator<Scope> zaq = new zac();
    private final int zaf;
    /* access modifiers changed from: private */
    public final ArrayList<Scope> zag;
    /* access modifiers changed from: private */
    public Account zah;
    /* access modifiers changed from: private */
    public boolean zai;
    /* access modifiers changed from: private */
    public final boolean zaj;
    /* access modifiers changed from: private */
    public final boolean zak;
    /* access modifiers changed from: private */
    public String zal;
    /* access modifiers changed from: private */
    public String zam;
    /* access modifiers changed from: private */
    public ArrayList<GoogleSignInOptionsExtensionParcelable> zan;
    /* access modifiers changed from: private */
    public String zao;
    private Map<Integer, GoogleSignInOptionsExtensionParcelable> zap;

    public static GoogleSignInOptions zaa(String str) throws JSONException {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString = jSONObject.has("accountName") ? jSONObject.optString("accountName") : null;
        Account account = !TextUtils.isEmpty(optString) ? new Account(optString, "com.google") : null;
        ArrayList arrayList = new ArrayList(hashSet);
        boolean z = jSONObject.getBoolean("idTokenRequested");
        boolean z2 = jSONObject.getBoolean("serverAuthRequested");
        boolean z3 = jSONObject.getBoolean("forceCodeForRefreshToken");
        String optString2 = jSONObject.has("serverClientId") ? jSONObject.optString("serverClientId") : null;
        if (jSONObject.has("hostedDomain")) {
            str2 = jSONObject.optString("hostedDomain");
        }
        return new GoogleSignInOptions(3, (ArrayList<Scope>) arrayList, account, z, z2, z3, optString2, str2, (Map<Integer, GoogleSignInOptionsExtensionParcelable>) new HashMap(), (String) null);
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static final class Builder {
        private Set<Scope> zaa = new HashSet();
        private boolean zab;
        private boolean zac;
        private boolean zad;
        private String zae;
        private Account zaf;
        private String zag;
        private Map<Integer, GoogleSignInOptionsExtensionParcelable> zah = new HashMap();
        private String zai;

        public Builder() {
        }

        public Builder(GoogleSignInOptions googleSignInOptions) {
            Preconditions.checkNotNull(googleSignInOptions);
            this.zaa = new HashSet(googleSignInOptions.zag);
            this.zab = googleSignInOptions.zaj;
            this.zac = googleSignInOptions.zak;
            this.zad = googleSignInOptions.zai;
            this.zae = googleSignInOptions.zal;
            this.zaf = googleSignInOptions.zah;
            this.zag = googleSignInOptions.zam;
            this.zah = GoogleSignInOptions.zab((List<GoogleSignInOptionsExtensionParcelable>) googleSignInOptions.zan);
            this.zai = googleSignInOptions.zao;
        }

        public final Builder requestId() {
            this.zaa.add(GoogleSignInOptions.zac);
            return this;
        }

        public final Builder requestEmail() {
            this.zaa.add(GoogleSignInOptions.zab);
            return this;
        }

        public final Builder requestProfile() {
            this.zaa.add(GoogleSignInOptions.zaa);
            return this;
        }

        public final Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.zaa.add(scope);
            this.zaa.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public final Builder requestIdToken(String str) {
            this.zad = true;
            this.zae = zaa(str);
            return this;
        }

        public final Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public final Builder requestServerAuthCode(String str, boolean z) {
            this.zab = true;
            this.zae = zaa(str);
            this.zac = z;
            return this;
        }

        public final Builder setAccountName(String str) {
            this.zaf = new Account(Preconditions.checkNotEmpty(str), "com.google");
            return this;
        }

        public final Builder setHostedDomain(String str) {
            this.zag = Preconditions.checkNotEmpty(str);
            return this;
        }

        public final Builder setLogSessionId(String str) {
            this.zai = str;
            return this;
        }

        public final Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (!this.zah.containsKey(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()))) {
                List<Scope> impliedScopes = googleSignInOptionsExtension.getImpliedScopes();
                if (impliedScopes != null) {
                    this.zaa.addAll(impliedScopes);
                }
                this.zah.put(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()), new GoogleSignInOptionsExtensionParcelable(googleSignInOptionsExtension));
                return this;
            }
            throw new IllegalStateException("Only one extension per type may be added");
        }

        public final GoogleSignInOptions build() {
            if (this.zaa.contains(GoogleSignInOptions.zae) && this.zaa.contains(GoogleSignInOptions.zad)) {
                this.zaa.remove(GoogleSignInOptions.zad);
            }
            if (this.zad && (this.zaf == null || !this.zaa.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.zaa), this.zaf, this.zad, this.zab, this.zac, this.zae, this.zag, this.zah, this.zai, (zac) null);
        }

        private final String zaa(String str) {
            Preconditions.checkNotEmpty(str);
            String str2 = this.zae;
            Preconditions.checkArgument(str2 == null || str2.equals(str), "two different server client ids provided");
            return str;
        }
    }

    GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, ArrayList<GoogleSignInOptionsExtensionParcelable> arrayList2, String str3) {
        this(i, arrayList, account, z, z2, z3, str, str2, zab((List<GoogleSignInOptionsExtensionParcelable>) arrayList2), str3);
    }

    private GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map<Integer, GoogleSignInOptionsExtensionParcelable> map, String str3) {
        this.zaf = i;
        this.zag = arrayList;
        this.zah = account;
        this.zai = z;
        this.zaj = z2;
        this.zak = z3;
        this.zal = str;
        this.zam = str2;
        this.zan = new ArrayList<>(map.values());
        this.zap = map;
        this.zao = str3;
    }

    public ArrayList<Scope> getScopes() {
        return new ArrayList<>(this.zag);
    }

    public Scope[] getScopeArray() {
        ArrayList<Scope> arrayList = this.zag;
        return (Scope[]) arrayList.toArray(new Scope[arrayList.size()]);
    }

    public Account getAccount() {
        return this.zah;
    }

    public boolean isIdTokenRequested() {
        return this.zai;
    }

    public boolean isServerAuthCodeRequested() {
        return this.zaj;
    }

    public boolean isForceCodeForRefreshToken() {
        return this.zak;
    }

    public String getServerClientId() {
        return this.zal;
    }

    public String getLogSessionId() {
        return this.zao;
    }

    public ArrayList<GoogleSignInOptionsExtensionParcelable> getExtensions() {
        return this.zan;
    }

    /* access modifiers changed from: private */
    public static Map<Integer, GoogleSignInOptionsExtensionParcelable> zab(List<GoogleSignInOptionsExtensionParcelable> list) {
        HashMap hashMap = new HashMap();
        if (list == null) {
            return hashMap;
        }
        for (GoogleSignInOptionsExtensionParcelable next : list) {
            hashMap.put(Integer.valueOf(next.getType()), next);
        }
        return hashMap;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaf);
        SafeParcelWriter.writeTypedList(parcel, 2, getScopes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getAccount(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, isIdTokenRequested());
        SafeParcelWriter.writeBoolean(parcel, 5, isServerAuthCodeRequested());
        SafeParcelWriter.writeBoolean(parcel, 6, isForceCodeForRefreshToken());
        SafeParcelWriter.writeString(parcel, 7, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zam, false);
        SafeParcelWriter.writeTypedList(parcel, 9, getExtensions(), false);
        SafeParcelWriter.writeString(parcel, 10, getLogSessionId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
        if (r1.equals(r4.getAccount()) != false) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0067, code lost:
        if (r3.zal.equals(r4.getServerClientId()) != false) goto L_0x0069;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r4 = (com.google.android.gms.auth.api.signin.GoogleSignInOptions) r4     // Catch:{ ClassCastException -> 0x008f }
            java.util.ArrayList<com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable> r1 = r3.zan     // Catch:{ ClassCastException -> 0x008f }
            int r1 = r1.size()     // Catch:{ ClassCastException -> 0x008f }
            if (r1 > 0) goto L_0x008f
            java.util.ArrayList<com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable> r1 = r4.zan     // Catch:{ ClassCastException -> 0x008f }
            int r1 = r1.size()     // Catch:{ ClassCastException -> 0x008f }
            if (r1 <= 0) goto L_0x0018
            goto L_0x008f
        L_0x0018:
            java.util.ArrayList<com.google.android.gms.common.api.Scope> r1 = r3.zag     // Catch:{ ClassCastException -> 0x008f }
            int r1 = r1.size()     // Catch:{ ClassCastException -> 0x008f }
            java.util.ArrayList r2 = r4.getScopes()     // Catch:{ ClassCastException -> 0x008f }
            int r2 = r2.size()     // Catch:{ ClassCastException -> 0x008f }
            if (r1 != r2) goto L_0x008f
            java.util.ArrayList<com.google.android.gms.common.api.Scope> r1 = r3.zag     // Catch:{ ClassCastException -> 0x008f }
            java.util.ArrayList r2 = r4.getScopes()     // Catch:{ ClassCastException -> 0x008f }
            boolean r1 = r1.containsAll(r2)     // Catch:{ ClassCastException -> 0x008f }
            if (r1 != 0) goto L_0x0035
            goto L_0x008f
        L_0x0035:
            android.accounts.Account r1 = r3.zah     // Catch:{ ClassCastException -> 0x008f }
            if (r1 != 0) goto L_0x0040
            android.accounts.Account r1 = r4.getAccount()     // Catch:{ ClassCastException -> 0x008f }
            if (r1 != 0) goto L_0x008f
            goto L_0x004a
        L_0x0040:
            android.accounts.Account r2 = r4.getAccount()     // Catch:{ ClassCastException -> 0x008f }
            boolean r1 = r1.equals(r2)     // Catch:{ ClassCastException -> 0x008f }
            if (r1 == 0) goto L_0x008f
        L_0x004a:
            java.lang.String r1 = r3.zal     // Catch:{ ClassCastException -> 0x008f }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ ClassCastException -> 0x008f }
            if (r1 == 0) goto L_0x005d
            java.lang.String r1 = r4.getServerClientId()     // Catch:{ ClassCastException -> 0x008f }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ ClassCastException -> 0x008f }
            if (r1 == 0) goto L_0x008f
            goto L_0x0069
        L_0x005d:
            java.lang.String r1 = r3.zal     // Catch:{ ClassCastException -> 0x008f }
            java.lang.String r2 = r4.getServerClientId()     // Catch:{ ClassCastException -> 0x008f }
            boolean r1 = r1.equals(r2)     // Catch:{ ClassCastException -> 0x008f }
            if (r1 == 0) goto L_0x008f
        L_0x0069:
            boolean r1 = r3.zak     // Catch:{ ClassCastException -> 0x008f }
            boolean r2 = r4.isForceCodeForRefreshToken()     // Catch:{ ClassCastException -> 0x008f }
            if (r1 != r2) goto L_0x008f
            boolean r1 = r3.zai     // Catch:{ ClassCastException -> 0x008f }
            boolean r2 = r4.isIdTokenRequested()     // Catch:{ ClassCastException -> 0x008f }
            if (r1 != r2) goto L_0x008f
            boolean r1 = r3.zaj     // Catch:{ ClassCastException -> 0x008f }
            boolean r2 = r4.isServerAuthCodeRequested()     // Catch:{ ClassCastException -> 0x008f }
            if (r1 != r2) goto L_0x008f
            java.lang.String r1 = r3.zao     // Catch:{ ClassCastException -> 0x008f }
            java.lang.String r4 = r4.getLogSessionId()     // Catch:{ ClassCastException -> 0x008f }
            boolean r4 = android.text.TextUtils.equals(r1, r4)     // Catch:{ ClassCastException -> 0x008f }
            if (r4 == 0) goto L_0x008f
            r4 = 1
            return r4
        L_0x008f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.GoogleSignInOptions.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = this.zag;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            arrayList.add(((Scope) obj).getScopeUri());
        }
        Collections.sort(arrayList);
        return new HashAccumulator().addObject(arrayList).addObject(this.zah).addObject(this.zal).zaa(this.zak).zaa(this.zai).zaa(this.zaj).addObject(this.zao).hash();
    }

    public final String zaa() {
        return zab().toString();
    }

    private final JSONObject zab() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zag, zaq);
            ArrayList arrayList = this.zag;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                jSONArray.put(((Scope) obj).getScopeUri());
            }
            jSONObject.put("scopes", jSONArray);
            Account account = this.zah;
            if (account != null) {
                jSONObject.put("accountName", account.name);
            }
            jSONObject.put("idTokenRequested", this.zai);
            jSONObject.put("forceCodeForRefreshToken", this.zak);
            jSONObject.put("serverAuthRequested", this.zaj);
            if (!TextUtils.isEmpty(this.zal)) {
                jSONObject.put("serverClientId", this.zal);
            }
            if (!TextUtils.isEmpty(this.zam)) {
                jSONObject.put("hostedDomain", this.zam);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /* synthetic */ GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, String str3, zac zac2) {
        this(3, (ArrayList<Scope>) arrayList, account, z, z2, z3, str, str2, (Map<Integer, GoogleSignInOptionsExtensionParcelable>) map, str3);
    }

    static {
        Scope scope = new Scope(Scopes.GAMES_LITE);
        zad = scope;
        DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(scope, new Scope[0]).build();
    }
}

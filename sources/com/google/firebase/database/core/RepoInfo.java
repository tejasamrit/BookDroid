package com.google.firebase.database.core;

import com.google.firebase.emulators.EmulatedServiceSettings;
import java.net.URI;

public final class RepoInfo {
    private static final String LAST_SESSION_ID_PARAM = "ls";
    private static final String VERSION_PARAM = "v";
    public String host;
    public String internalHost;
    public String namespace;
    public boolean secure;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("http");
        sb.append(this.secure ? "s" : "");
        sb.append("://");
        sb.append(this.host);
        return sb.toString();
    }

    public String toDebugString() {
        return "(host=" + this.host + ", secure=" + this.secure + ", ns=" + this.namespace + " internal=" + this.internalHost + ")";
    }

    public URI getConnectionURL(String str) {
        String str2 = (this.secure ? "wss" : "ws") + "://" + this.internalHost + "/.ws?ns=" + this.namespace + "&" + VERSION_PARAM + "=" + "5";
        if (str != null) {
            str2 = str2 + "&ls=" + str;
        }
        return URI.create(str2);
    }

    public void applyEmulatorSettings(EmulatedServiceSettings emulatedServiceSettings) {
        if (emulatedServiceSettings != null) {
            String str = emulatedServiceSettings.getHost() + ":" + emulatedServiceSettings.getPort();
            this.host = str;
            this.internalHost = str;
            this.secure = false;
        }
    }

    public boolean isCacheableHost() {
        return this.internalHost.startsWith("s-");
    }

    public boolean isSecure() {
        return this.secure;
    }

    public boolean isDemoHost() {
        return this.host.contains(".firebaseio-demo.com");
    }

    public boolean isCustomHost() {
        return !this.host.contains(".firebaseio.com") && !this.host.contains(".firebaseio-demo.com");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RepoInfo repoInfo = (RepoInfo) obj;
        if (this.secure == repoInfo.secure && this.host.equals(repoInfo.host)) {
            return this.namespace.equals(repoInfo.namespace);
        }
        return false;
    }

    public int hashCode() {
        return (((this.host.hashCode() * 31) + (this.secure ? 1 : 0)) * 31) + this.namespace.hashCode();
    }
}

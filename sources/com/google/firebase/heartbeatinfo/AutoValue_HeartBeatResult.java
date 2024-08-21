package com.google.firebase.heartbeatinfo;

import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import java.util.Objects;

final class AutoValue_HeartBeatResult extends HeartBeatResult {
    private final HeartBeatInfo.HeartBeat heartBeat;
    private final long millis;
    private final String sdkName;

    AutoValue_HeartBeatResult(String str, long j, HeartBeatInfo.HeartBeat heartBeat2) {
        Objects.requireNonNull(str, "Null sdkName");
        this.sdkName = str;
        this.millis = j;
        Objects.requireNonNull(heartBeat2, "Null heartBeat");
        this.heartBeat = heartBeat2;
    }

    public String getSdkName() {
        return this.sdkName;
    }

    public long getMillis() {
        return this.millis;
    }

    public HeartBeatInfo.HeartBeat getHeartBeat() {
        return this.heartBeat;
    }

    public String toString() {
        return "HeartBeatResult{sdkName=" + this.sdkName + ", millis=" + this.millis + ", heartBeat=" + this.heartBeat + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HeartBeatResult)) {
            return false;
        }
        HeartBeatResult heartBeatResult = (HeartBeatResult) obj;
        if (!this.sdkName.equals(heartBeatResult.getSdkName()) || this.millis != heartBeatResult.getMillis() || !this.heartBeat.equals(heartBeatResult.getHeartBeat())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.millis;
        return ((((this.sdkName.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.heartBeat.hashCode();
    }
}

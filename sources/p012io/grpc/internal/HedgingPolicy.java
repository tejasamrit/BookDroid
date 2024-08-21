package p012io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import java.util.Collections;
import java.util.Set;
import p012io.grpc.Status;

/* renamed from: io.grpc.internal.HedgingPolicy */
final class HedgingPolicy {
    static final HedgingPolicy DEFAULT = new HedgingPolicy(1, 0, Collections.emptySet());
    final long hedgingDelayNanos;
    final int maxAttempts;
    final Set<Status.Code> nonFatalStatusCodes;

    /* renamed from: io.grpc.internal.HedgingPolicy$Provider */
    interface Provider {
        HedgingPolicy get();
    }

    HedgingPolicy(int i, long j, Set<Status.Code> set) {
        this.maxAttempts = i;
        this.hedgingDelayNanos = j;
        this.nonFatalStatusCodes = ImmutableSet.copyOf(set);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HedgingPolicy hedgingPolicy = (HedgingPolicy) obj;
        if (this.maxAttempts == hedgingPolicy.maxAttempts && this.hedgingDelayNanos == hedgingPolicy.hedgingDelayNanos && Objects.equal(this.nonFatalStatusCodes, hedgingPolicy.nonFatalStatusCodes)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.maxAttempts), Long.valueOf(this.hedgingDelayNanos), this.nonFatalStatusCodes);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("maxAttempts", this.maxAttempts).add("hedgingDelayNanos", this.hedgingDelayNanos).add("nonFatalStatusCodes", (Object) this.nonFatalStatusCodes).toString();
    }
}

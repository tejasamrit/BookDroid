package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0001*\u001e\b\u0007\u0010\u0007\"\u00020\u00042\u00020\u0004B\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nB\u0002\b\u000b¨\u0006\f"}, mo37160d2 = {"convertDurationUnit", "", "value", "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "DurationUnit", "Lkotlin/SinceKotlin;", "version", "1.3", "Lkotlin/time/ExperimentalTime;", "kotlin-stdlib"}, mo37161k = 5, mo37162mv = {1, 4, 0}, mo37164xi = 1, mo37165xs = "kotlin/time/DurationUnitKt")
/* compiled from: DurationUnitJvm.kt */
class DurationUnitKt__DurationUnitJvmKt {
    public static /* synthetic */ void DurationUnit$annotations() {
    }

    public static final double convertDurationUnit(double d, TimeUnit timeUnit, TimeUnit timeUnit2) {
        Intrinsics.checkNotNullParameter(timeUnit, "sourceUnit");
        Intrinsics.checkNotNullParameter(timeUnit2, "targetUnit");
        long convert = timeUnit2.convert(1, timeUnit);
        if (convert > 0) {
            return d * ((double) convert);
        }
        return d / ((double) timeUnit.convert(1, timeUnit2));
    }
}

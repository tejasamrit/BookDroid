package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, mo37160d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"}, mo37161k = 3, mo37162mv = {1, 4, 0})
/* compiled from: Comparisons.kt */
public final class ComparisonsKt__ComparisonsKt$thenComparator$1<T> implements Comparator<T> {
    final /* synthetic */ Function2 $comparison;
    final /* synthetic */ Comparator $this_thenComparator;

    public ComparisonsKt__ComparisonsKt$thenComparator$1(Comparator comparator, Function2 function2) {
        this.$this_thenComparator = comparator;
        this.$comparison = function2;
    }

    public final int compare(T t, T t2) {
        int compare = this.$this_thenComparator.compare(t, t2);
        return compare != 0 ? compare : ((Number) this.$comparison.invoke(t, t2)).intValue();
    }
}

package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005¢\u0006\u0002\b\u0006"}, mo37160d2 = {"<anonymous>", "", "R", "T", "p1", "", "invoke"}, mo37161k = 3, mo37162mv = {1, 4, 0})
/* compiled from: _Sequences.kt */
final /* synthetic */ class SequencesKt___SequencesKt$flatMapIndexed$1 extends FunctionReferenceImpl implements Function1<Iterable<? extends R>, Iterator<? extends R>> {
    public static final SequencesKt___SequencesKt$flatMapIndexed$1 INSTANCE = new SequencesKt___SequencesKt$flatMapIndexed$1();

    SequencesKt___SequencesKt$flatMapIndexed$1() {
        super(1, Iterable.class, "iterator", "iterator()Ljava/util/Iterator;", 0);
    }

    public final Iterator<R> invoke(Iterable<? extends R> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "p1");
        return iterable.iterator();
    }
}

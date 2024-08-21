package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.sequences.Sequence;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0002¨\u0006\u0004¸\u0006\u0000"}, mo37160d2 = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: Sequences.kt */
public final class ArraysKt___ArraysKt$asSequence$$inlined$Sequence$1 implements Sequence<T> {
    final /* synthetic */ Object[] $this_asSequence$inlined;

    public ArraysKt___ArraysKt$asSequence$$inlined$Sequence$1(Object[] objArr) {
        this.$this_asSequence$inlined = objArr;
    }

    public Iterator<T> iterator() {
        return ArrayIteratorKt.iterator(this.$this_asSequence$inlined);
    }
}

package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.Lambda;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\f\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0005\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo37160d2 = {"<anonymous>", "", "", "invoke"}, mo37161k = 3, mo37162mv = {1, 4, 0})
/* compiled from: _Arrays.kt */
final class ArraysKt___ArraysKt$withIndex$2 extends Lambda implements Function0<Iterator<? extends Byte>> {
    final /* synthetic */ byte[] $this_withIndex;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArraysKt___ArraysKt$withIndex$2(byte[] bArr) {
        super(0);
        this.$this_withIndex = bArr;
    }

    public final Iterator<Byte> invoke() {
        return ArrayIteratorsKt.iterator(this.$this_withIndex);
    }
}

package androidx.core.util;

import android.util.SparseBooleanArray;
import kotlin.Metadata;
import kotlin.collections.BooleanIterator;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\t\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\tH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, mo37160d2 = {"androidx/core/util/SparseBooleanArrayKt$valueIterator$1", "Lkotlin/collections/BooleanIterator;", "index", "", "getIndex", "()I", "setIndex", "(I)V", "hasNext", "", "nextBoolean", "core-ktx_release"}, mo37161k = 1, mo37162mv = {1, 1, 16})
/* compiled from: SparseBooleanArray.kt */
public final class SparseBooleanArrayKt$valueIterator$1 extends BooleanIterator {
    final /* synthetic */ SparseBooleanArray $this_valueIterator;
    private int index;

    SparseBooleanArrayKt$valueIterator$1(SparseBooleanArray sparseBooleanArray) {
        this.$this_valueIterator = sparseBooleanArray;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public boolean hasNext() {
        return this.index < this.$this_valueIterator.size();
    }

    public boolean nextBoolean() {
        SparseBooleanArray sparseBooleanArray = this.$this_valueIterator;
        int i = this.index;
        this.index = i + 1;
        return sparseBooleanArray.valueAt(i);
    }
}

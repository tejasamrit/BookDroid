package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u0002H\u0002¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0002H&¨\u0006\u0007"}, mo37160d2 = {"Lkotlin/collections/IntIterator;", "", "", "()V", "next", "()Ljava/lang/Integer;", "nextInt", "kotlin-stdlib"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: Iterators.kt */
public abstract class IntIterator implements Iterator<Integer>, KMappedMarker {
    public abstract int nextInt();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final Integer next() {
        return Integer.valueOf(nextInt());
    }
}

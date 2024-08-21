package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0007\b'\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u0002H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\u0002H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\u0006ø\u0001\u0000\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\t"}, mo37160d2 = {"Lkotlin/collections/UByteIterator;", "", "Lkotlin/UByte;", "()V", "next", "next-w2LRezQ", "()B", "nextUByte", "nextUByte-w2LRezQ", "kotlin-stdlib"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: UIterators.kt */
public abstract class UByteIterator implements Iterator<UByte>, KMappedMarker {
    /* renamed from: nextUByte-w2LRezQ  reason: not valid java name */
    public abstract byte m727nextUBytew2LRezQ();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* bridge */ /* synthetic */ Object next() {
        return UByte.m311boximpl(m726nextw2LRezQ());
    }

    /* renamed from: next-w2LRezQ  reason: not valid java name */
    public final byte m726nextw2LRezQ() {
        return m727nextUBytew2LRezQ();
    }
}

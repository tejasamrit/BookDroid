package kotlin;

import kotlin.ranges.UIntRange;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 j2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001jB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u0000H\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0005J\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000fJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0016J\u0013\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\u0016\u0010#\u001a\u00020\u0000H\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010\u0005J\u0016\u0010%\u001a\u00020\u0000H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010\u0005J\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b(\u0010\u000fJ\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b)\u0010\u000bJ\u001b\u0010'\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u001dJ\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0016J\u001b\u0010,\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b-\u0010\u000bJ\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b/\u0010\u000fJ\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b0\u0010\u000bJ\u001b\u0010.\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u001dJ\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0016J\u001b\u00103\u001a\u0002042\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b5\u00106J\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b8\u0010\u000fJ\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b9\u0010\u000bJ\u001b\u00107\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u001dJ\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0016J\u001e\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u0003H\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b>\u0010\u000bJ\u001e\u0010?\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u0003H\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b@\u0010\u000bJ\u001b\u0010A\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u000fJ\u001b\u0010A\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u000bJ\u001b\u0010A\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\bD\u0010\u001dJ\u001b\u0010A\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\bE\u0010\u0016J\u0010\u0010F\u001a\u00020GH\b¢\u0006\u0004\bH\u0010IJ\u0010\u0010J\u001a\u00020KH\b¢\u0006\u0004\bL\u0010MJ\u0010\u0010N\u001a\u00020OH\b¢\u0006\u0004\bP\u0010QJ\u0010\u0010R\u001a\u00020\u0003H\b¢\u0006\u0004\bS\u0010\u0005J\u0010\u0010T\u001a\u00020UH\b¢\u0006\u0004\bV\u0010WJ\u0010\u0010X\u001a\u00020YH\b¢\u0006\u0004\bZ\u0010[J\u000f\u0010\\\u001a\u00020]H\u0016¢\u0006\u0004\b^\u0010_J\u0016\u0010`\u001a\u00020\rH\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\ba\u0010IJ\u0016\u0010b\u001a\u00020\u0000H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bc\u0010\u0005J\u0016\u0010d\u001a\u00020\u0011H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\be\u0010WJ\u0016\u0010f\u001a\u00020\u0014H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bg\u0010[J\u001b\u0010h\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\bi\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006k"}, mo37160d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "getData$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-pVg5ArA", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-pVg5ArA", "inv", "inv-pVg5ArA", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toDouble", "", "toDouble-impl", "(I)D", "toFloat", "", "toFloat-impl", "(I)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: UInt.kt */
public final class UInt implements Comparable<UInt> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UInt m379boximpl(int i) {
        return new UInt(i);
    }

    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private int m382compareToWZ4Q5Ns(int i) {
        return m383compareToWZ4Q5Ns(this.data, i);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static int m385constructorimpl(int i) {
        return i;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m391equalsimpl(int i, Object obj) {
        return (obj instanceof UInt) && i == ((UInt) obj).m428unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m392equalsimpl0(int i, int i2) {
        return i == i2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m393hashCodeimpl(int i) {
        return i;
    }

    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m416toByteimpl(int i) {
        return (byte) i;
    }

    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m419toIntimpl(int i) {
        return i;
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m420toLongimpl(int i) {
        return ((long) i) & 4294967295L;
    }

    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m421toShortimpl(int i) {
        return (short) i;
    }

    /* renamed from: toUInt-pVg5ArA  reason: not valid java name */
    private static final int m424toUIntpVg5ArA(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m391equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m393hashCodeimpl(this.data);
    }

    public String toString() {
        return m422toStringimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m428unboximpl() {
        return this.data;
    }

    private /* synthetic */ UInt(int i) {
        this.data = i;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m382compareToWZ4Q5Ns(((UInt) obj).m428unboximpl());
    }

    @Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"}, mo37160d2 = {"Lkotlin/UInt$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UInt;", "I", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, mo37161k = 1, mo37162mv = {1, 4, 0})
    /* compiled from: UInt.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private static final int m380compareTo7apg3OU(int i, byte b) {
        return UnsignedKt.uintCompare(i, m385constructorimpl(b & 255));
    }

    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static final int m384compareToxj2QHRw(int i, short s) {
        return UnsignedKt.uintCompare(i, m385constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static int m383compareToWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.uintCompare(i, i2);
    }

    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static final int m381compareToVKZWuLQ(int i, long j) {
        return UnsignedKt.ulongCompare(ULong.m455constructorimpl(((long) i) & 4294967295L), j);
    }

    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final int m401plus7apg3OU(int i, byte b) {
        return m385constructorimpl(i + m385constructorimpl(b & 255));
    }

    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final int m404plusxj2QHRw(int i, short s) {
        return m385constructorimpl(i + m385constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final int m403plusWZ4Q5Ns(int i, int i2) {
        return m385constructorimpl(i + i2);
    }

    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m402plusVKZWuLQ(int i, long j) {
        return ULong.m455constructorimpl(ULong.m455constructorimpl(((long) i) & 4294967295L) + j);
    }

    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final int m396minus7apg3OU(int i, byte b) {
        return m385constructorimpl(i - m385constructorimpl(b & 255));
    }

    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final int m399minusxj2QHRw(int i, short s) {
        return m385constructorimpl(i - m385constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final int m398minusWZ4Q5Ns(int i, int i2) {
        return m385constructorimpl(i - i2);
    }

    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m397minusVKZWuLQ(int i, long j) {
        return ULong.m455constructorimpl(ULong.m455constructorimpl(((long) i) & 4294967295L) - j);
    }

    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final int m412times7apg3OU(int i, byte b) {
        return m385constructorimpl(i * m385constructorimpl(b & 255));
    }

    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final int m415timesxj2QHRw(int i, short s) {
        return m385constructorimpl(i * m385constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final int m414timesWZ4Q5Ns(int i, int i2) {
        return m385constructorimpl(i * i2);
    }

    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m413timesVKZWuLQ(int i, long j) {
        return ULong.m455constructorimpl(ULong.m455constructorimpl(((long) i) & 4294967295L) * j);
    }

    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final int m387div7apg3OU(int i, byte b) {
        return UnsignedKt.m614uintDivideJ1ME1BU(i, m385constructorimpl(b & 255));
    }

    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final int m390divxj2QHRw(int i, short s) {
        return UnsignedKt.m614uintDivideJ1ME1BU(i, m385constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final int m389divWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m614uintDivideJ1ME1BU(i, i2);
    }

    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m388divVKZWuLQ(int i, long j) {
        return UnsignedKt.m616ulongDivideeb3DHEI(ULong.m455constructorimpl(((long) i) & 4294967295L), j);
    }

    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final int m406rem7apg3OU(int i, byte b) {
        return UnsignedKt.m615uintRemainderJ1ME1BU(i, m385constructorimpl(b & 255));
    }

    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final int m409remxj2QHRw(int i, short s) {
        return UnsignedKt.m615uintRemainderJ1ME1BU(i, m385constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final int m408remWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m615uintRemainderJ1ME1BU(i, i2);
    }

    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m407remVKZWuLQ(int i, long j) {
        return UnsignedKt.m617ulongRemaindereb3DHEI(ULong.m455constructorimpl(((long) i) & 4294967295L), j);
    }

    /* renamed from: inc-pVg5ArA  reason: not valid java name */
    private static final int m394incpVg5ArA(int i) {
        return m385constructorimpl(i + 1);
    }

    /* renamed from: dec-pVg5ArA  reason: not valid java name */
    private static final int m386decpVg5ArA(int i) {
        return m385constructorimpl(i - 1);
    }

    /* renamed from: rangeTo-WZ4Q5Ns  reason: not valid java name */
    private static final UIntRange m405rangeToWZ4Q5Ns(int i, int i2) {
        return new UIntRange(i, i2, (DefaultConstructorMarker) null);
    }

    /* renamed from: shl-pVg5ArA  reason: not valid java name */
    private static final int m410shlpVg5ArA(int i, int i2) {
        return m385constructorimpl(i << i2);
    }

    /* renamed from: shr-pVg5ArA  reason: not valid java name */
    private static final int m411shrpVg5ArA(int i, int i2) {
        return m385constructorimpl(i >>> i2);
    }

    /* renamed from: and-WZ4Q5Ns  reason: not valid java name */
    private static final int m378andWZ4Q5Ns(int i, int i2) {
        return m385constructorimpl(i & i2);
    }

    /* renamed from: or-WZ4Q5Ns  reason: not valid java name */
    private static final int m400orWZ4Q5Ns(int i, int i2) {
        return m385constructorimpl(i | i2);
    }

    /* renamed from: xor-WZ4Q5Ns  reason: not valid java name */
    private static final int m427xorWZ4Q5Ns(int i, int i2) {
        return m385constructorimpl(i ^ i2);
    }

    /* renamed from: inv-pVg5ArA  reason: not valid java name */
    private static final int m395invpVg5ArA(int i) {
        return m385constructorimpl(~i);
    }

    /* renamed from: toUByte-w2LRezQ  reason: not valid java name */
    private static final byte m423toUBytew2LRezQ(int i) {
        return UByte.m317constructorimpl((byte) i);
    }

    /* renamed from: toUShort-Mh2AYeg  reason: not valid java name */
    private static final short m426toUShortMh2AYeg(int i) {
        return UShort.m553constructorimpl((short) i);
    }

    /* renamed from: toULong-s-VKNKU  reason: not valid java name */
    private static final long m425toULongsVKNKU(int i) {
        return ULong.m455constructorimpl(((long) i) & 4294967295L);
    }

    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m418toFloatimpl(int i) {
        return (float) UnsignedKt.uintToDouble(i);
    }

    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m417toDoubleimpl(int i) {
        return UnsignedKt.uintToDouble(i);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m422toStringimpl(int i) {
        return String.valueOf(((long) i) & 4294967295L);
    }
}

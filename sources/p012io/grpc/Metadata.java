package p012io.grpc;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.p008io.BaseEncoding;
import com.google.common.p008io.ByteStreams;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: io.grpc.Metadata */
public final class Metadata {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final AsciiMarshaller<String> ASCII_STRING_MARSHALLER = new AsciiMarshaller<String>() {
        public String parseAsciiString(String str) {
            return str;
        }

        public String toAsciiString(String str) {
            return str;
        }
    };
    static final BaseEncoding BASE64_ENCODING_OMIT_PADDING = BaseEncoding.base64().omitPadding();
    public static final BinaryMarshaller<byte[]> BINARY_BYTE_MARSHALLER = new BinaryMarshaller<byte[]>() {
        public byte[] parseBytes(byte[] bArr) {
            return bArr;
        }

        public byte[] toBytes(byte[] bArr) {
            return bArr;
        }
    };
    public static final String BINARY_HEADER_SUFFIX = "-bin";
    private Object[] namesAndValues;
    /* access modifiers changed from: private */
    public int size;

    /* renamed from: io.grpc.Metadata$AsciiMarshaller */
    public interface AsciiMarshaller<T> {
        T parseAsciiString(String str);

        String toAsciiString(T t);
    }

    /* renamed from: io.grpc.Metadata$BinaryMarshaller */
    public interface BinaryMarshaller<T> {
        T parseBytes(byte[] bArr);

        byte[] toBytes(T t);
    }

    /* renamed from: io.grpc.Metadata$BinaryStreamMarshaller */
    public interface BinaryStreamMarshaller<T> {
        T parseStream(InputStream inputStream);

        InputStream toStream(T t);
    }

    /* renamed from: io.grpc.Metadata$TrustedAsciiMarshaller */
    interface TrustedAsciiMarshaller<T> {
        T parseAsciiString(byte[] bArr);

        byte[] toAsciiString(T t);
    }

    Metadata(byte[]... bArr) {
        this(bArr.length / 2, bArr);
    }

    Metadata(int i, byte[]... bArr) {
        this(i, (Object[]) bArr);
    }

    Metadata(int i, Object[] objArr) {
        this.size = i;
        this.namesAndValues = objArr;
    }

    /* access modifiers changed from: private */
    public byte[] name(int i) {
        return (byte[]) this.namesAndValues[i * 2];
    }

    private void name(int i, byte[] bArr) {
        this.namesAndValues[i * 2] = bArr;
    }

    private Object value(int i) {
        return this.namesAndValues[(i * 2) + 1];
    }

    private void value(int i, byte[] bArr) {
        this.namesAndValues[(i * 2) + 1] = bArr;
    }

    private void value(int i, Object obj) {
        if (this.namesAndValues instanceof byte[][]) {
            expand(cap());
        }
        this.namesAndValues[(i * 2) + 1] = obj;
    }

    private byte[] valueAsBytes(int i) {
        Object value = value(i);
        if (value instanceof byte[]) {
            return (byte[]) value;
        }
        return ((LazyValue) value).toBytes();
    }

    private Object valueAsBytesOrStream(int i) {
        Object value = value(i);
        if (value instanceof byte[]) {
            return value;
        }
        return ((LazyValue) value).toStream();
    }

    /* access modifiers changed from: private */
    public <T> T valueAsT(int i, Key<T> key) {
        Object value = value(i);
        if (value instanceof byte[]) {
            return key.parseBytes((byte[]) value);
        }
        return ((LazyValue) value).toObject(key);
    }

    private int cap() {
        Object[] objArr = this.namesAndValues;
        if (objArr != null) {
            return objArr.length;
        }
        return 0;
    }

    private int len() {
        return this.size * 2;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    public Metadata() {
    }

    /* access modifiers changed from: package-private */
    public int headerCount() {
        return this.size;
    }

    public boolean containsKey(Key<?> key) {
        for (int i = 0; i < this.size; i++) {
            if (bytesEqual(key.asciiName(), name(i))) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public <T> T get(Key<T> key) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (bytesEqual(key.asciiName(), name(i))) {
                return valueAsT(i, key);
            }
        }
        return null;
    }

    /* renamed from: io.grpc.Metadata$IterableAt */
    private final class IterableAt<T> implements Iterable<T> {
        /* access modifiers changed from: private */
        public final Key<T> key;
        /* access modifiers changed from: private */
        public int startIdx;

        private IterableAt(Key<T> key2, int i) {
            this.key = key2;
            this.startIdx = i;
        }

        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private boolean hasNext = true;
                private int idx;

                {
                    this.idx = IterableAt.this.startIdx;
                }

                public boolean hasNext() {
                    if (this.hasNext) {
                        return true;
                    }
                    while (this.idx < Metadata.this.size) {
                        if (Metadata.this.bytesEqual(IterableAt.this.key.asciiName(), Metadata.this.name(this.idx))) {
                            this.hasNext = true;
                            return true;
                        }
                        this.idx++;
                    }
                    return false;
                }

                public T next() {
                    if (hasNext()) {
                        this.hasNext = false;
                        Metadata metadata = Metadata.this;
                        int i = this.idx;
                        this.idx = i + 1;
                        return metadata.valueAsT(i, IterableAt.this.key);
                    }
                    throw new NoSuchElementException();
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    @Nullable
    public <T> Iterable<T> getAll(Key<T> key) {
        for (int i = 0; i < this.size; i++) {
            if (bytesEqual(key.asciiName(), name(i))) {
                return new IterableAt(key, i);
            }
        }
        return null;
    }

    public Set<String> keys() {
        if (isEmpty()) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.size);
        for (int i = 0; i < this.size; i++) {
            hashSet.add(new String(name(i), 0));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public <T> void put(Key<T> key, T t) {
        Preconditions.checkNotNull(key, "key");
        Preconditions.checkNotNull(t, "value");
        maybeExpand();
        name(this.size, key.asciiName());
        if (key.serializesToStreams()) {
            value(this.size, (Object) LazyValue.create(key, t));
        } else {
            value(this.size, key.toBytes(t));
        }
        this.size++;
    }

    private void maybeExpand() {
        if (len() == 0 || len() == cap()) {
            expand(Math.max(len() * 2, 8));
        }
    }

    private void expand(int i) {
        Object[] objArr = new Object[i];
        if (!isEmpty()) {
            System.arraycopy(this.namesAndValues, 0, objArr, 0, len());
        }
        this.namesAndValues = objArr;
    }

    public <T> boolean remove(Key<T> key, T t) {
        Preconditions.checkNotNull(key, "key");
        Preconditions.checkNotNull(t, "value");
        for (int i = 0; i < this.size; i++) {
            if (bytesEqual(key.asciiName(), name(i)) && t.equals(valueAsT(i, key))) {
                int i2 = i * 2;
                int i3 = (i + 1) * 2;
                Object[] objArr = this.namesAndValues;
                System.arraycopy(objArr, i3, objArr, i2, len() - i3);
                int i4 = this.size - 1;
                this.size = i4;
                name(i4, (byte[]) null);
                value(this.size, (byte[]) null);
                return true;
            }
        }
        return false;
    }

    public <T> Iterable<T> removeAll(Key<T> key) {
        if (isEmpty()) {
            return null;
        }
        ArrayList arrayList = null;
        int i = 0;
        for (int i2 = 0; i2 < this.size; i2++) {
            if (bytesEqual(key.asciiName(), name(i2))) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(valueAsT(i2, key));
            } else {
                name(i, name(i2));
                value(i, value(i2));
                i++;
            }
        }
        Arrays.fill(this.namesAndValues, i * 2, len(), (Object) null);
        this.size = i;
        return arrayList;
    }

    public <T> void discardAll(Key<T> key) {
        if (!isEmpty()) {
            int i = 0;
            for (int i2 = 0; i2 < this.size; i2++) {
                if (!bytesEqual(key.asciiName(), name(i2))) {
                    name(i, name(i2));
                    value(i, value(i2));
                    i++;
                }
            }
            Arrays.fill(this.namesAndValues, i * 2, len(), (Object) null);
            this.size = i;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public byte[][] serialize() {
        byte[][] bArr = new byte[len()][];
        Object[] objArr = this.namesAndValues;
        if (objArr instanceof byte[][]) {
            System.arraycopy(objArr, 0, bArr, 0, len());
        } else {
            for (int i = 0; i < this.size; i++) {
                int i2 = i * 2;
                bArr[i2] = name(i);
                bArr[i2 + 1] = valueAsBytes(i);
            }
        }
        return bArr;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Object[] serializePartial() {
        Object[] objArr = new Object[len()];
        for (int i = 0; i < this.size; i++) {
            int i2 = i * 2;
            objArr[i2] = name(i);
            objArr[i2 + 1] = valueAsBytesOrStream(i);
        }
        return objArr;
    }

    public void merge(Metadata metadata) {
        if (!metadata.isEmpty()) {
            int cap = cap() - len();
            if (isEmpty() || cap < metadata.len()) {
                expand(len() + metadata.len());
            }
            System.arraycopy(metadata.namesAndValues, 0, this.namesAndValues, len(), metadata.len());
            this.size += metadata.size;
        }
    }

    public void merge(Metadata metadata, Set<Key<?>> set) {
        Preconditions.checkNotNull(metadata, "other");
        HashMap hashMap = new HashMap(set.size());
        for (Key next : set) {
            hashMap.put(ByteBuffer.wrap(next.asciiName()), next);
        }
        for (int i = 0; i < metadata.size; i++) {
            if (hashMap.containsKey(ByteBuffer.wrap(metadata.name(i)))) {
                maybeExpand();
                name(this.size, metadata.name(i));
                value(this.size, metadata.value(i));
                this.size++;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Metadata(");
        for (int i = 0; i < this.size; i++) {
            if (i != 0) {
                sb.append(',');
            }
            String str = new String(name(i), Charsets.US_ASCII);
            sb.append(str);
            sb.append('=');
            if (str.endsWith(BINARY_HEADER_SUFFIX)) {
                sb.append(BASE64_ENCODING_OMIT_PADDING.encode(valueAsBytes(i)));
            } else {
                sb.append(new String(valueAsBytes(i), Charsets.US_ASCII));
            }
        }
        sb.append(')');
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public boolean bytesEqual(byte[] bArr, byte[] bArr2) {
        return Arrays.equals(bArr, bArr2);
    }

    /* renamed from: io.grpc.Metadata$Key */
    public static abstract class Key<T> {
        private static final BitSet VALID_T_CHARS = generateValidTChars();
        private final Object marshaller;
        private final String name;
        private final byte[] nameBytes;
        private final String originalName;

        /* access modifiers changed from: package-private */
        public abstract T parseBytes(byte[] bArr);

        /* access modifiers changed from: package-private */
        public boolean serializesToStreams() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public abstract byte[] toBytes(T t);

        /* renamed from: of */
        public static <T> Key<T> m263of(String str, BinaryMarshaller<T> binaryMarshaller) {
            return new BinaryKey(str, binaryMarshaller);
        }

        /* renamed from: of */
        public static <T> Key<T> m264of(String str, BinaryStreamMarshaller<T> binaryStreamMarshaller) {
            return new LazyStreamBinaryKey(str, binaryStreamMarshaller);
        }

        /* renamed from: of */
        public static <T> Key<T> m262of(String str, AsciiMarshaller<T> asciiMarshaller) {
            return m265of(str, false, asciiMarshaller);
        }

        /* renamed from: of */
        static <T> Key<T> m265of(String str, boolean z, AsciiMarshaller<T> asciiMarshaller) {
            return new AsciiKey(str, z, asciiMarshaller);
        }

        /* renamed from: of */
        static <T> Key<T> m266of(String str, boolean z, TrustedAsciiMarshaller<T> trustedAsciiMarshaller) {
            return new TrustedAsciiKey(str, z, trustedAsciiMarshaller);
        }

        private static BitSet generateValidTChars() {
            BitSet bitSet = new BitSet(127);
            bitSet.set(45);
            bitSet.set(95);
            bitSet.set(46);
            for (char c = '0'; c <= '9'; c = (char) (c + 1)) {
                bitSet.set(c);
            }
            for (char c2 = 'a'; c2 <= 'z'; c2 = (char) (c2 + 1)) {
                bitSet.set(c2);
            }
            return bitSet;
        }

        private static String validateName(String str, boolean z) {
            Preconditions.checkNotNull(str, "name");
            Preconditions.checkArgument(!str.isEmpty(), "token must have at least 1 tchar");
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (!z || charAt != ':' || i != 0) {
                    Preconditions.checkArgument(VALID_T_CHARS.get(charAt), "Invalid character '%s' in key name '%s'", charAt, (Object) str);
                }
            }
            return str;
        }

        private Key(String str, boolean z, Object obj) {
            String str2 = (String) Preconditions.checkNotNull(str, "name");
            this.originalName = str2;
            String validateName = validateName(str2.toLowerCase(Locale.ROOT), z);
            this.name = validateName;
            this.nameBytes = validateName.getBytes(Charsets.US_ASCII);
            this.marshaller = obj;
        }

        public final String originalName() {
            return this.originalName;
        }

        public final String name() {
            return this.name;
        }

        /* access modifiers changed from: package-private */
        public byte[] asciiName() {
            return this.nameBytes;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.name.equals(((Key) obj).name);
        }

        public final int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            return "Key{name='" + this.name + "'}";
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public final <M> M getMarshaller(Class<M> cls) {
            if (cls.isInstance(this.marshaller)) {
                return cls.cast(this.marshaller);
            }
            return null;
        }
    }

    /* renamed from: io.grpc.Metadata$BinaryKey */
    private static class BinaryKey<T> extends Key<T> {
        private final BinaryMarshaller<T> marshaller;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private BinaryKey(String str, BinaryMarshaller<T> binaryMarshaller) {
            super(str, false, binaryMarshaller);
            boolean z = false;
            Preconditions.checkArgument(str.endsWith(Metadata.BINARY_HEADER_SUFFIX), "Binary header is named %s. It must end with %s", (Object) str, (Object) Metadata.BINARY_HEADER_SUFFIX);
            Preconditions.checkArgument(str.length() > 4 ? true : z, "empty key name");
            this.marshaller = (BinaryMarshaller) Preconditions.checkNotNull(binaryMarshaller, "marshaller is null");
        }

        /* access modifiers changed from: package-private */
        public byte[] toBytes(T t) {
            return this.marshaller.toBytes(t);
        }

        /* access modifiers changed from: package-private */
        public T parseBytes(byte[] bArr) {
            return this.marshaller.parseBytes(bArr);
        }
    }

    /* renamed from: io.grpc.Metadata$LazyStreamBinaryKey */
    private static class LazyStreamBinaryKey<T> extends Key<T> {
        private final BinaryStreamMarshaller<T> marshaller;

        /* access modifiers changed from: package-private */
        public boolean serializesToStreams() {
            return true;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private LazyStreamBinaryKey(String str, BinaryStreamMarshaller<T> binaryStreamMarshaller) {
            super(str, false, binaryStreamMarshaller);
            boolean z = false;
            Preconditions.checkArgument(str.endsWith(Metadata.BINARY_HEADER_SUFFIX), "Binary header is named %s. It must end with %s", (Object) str, (Object) Metadata.BINARY_HEADER_SUFFIX);
            Preconditions.checkArgument(str.length() > 4 ? true : z, "empty key name");
            this.marshaller = (BinaryStreamMarshaller) Preconditions.checkNotNull(binaryStreamMarshaller, "marshaller is null");
        }

        /* access modifiers changed from: package-private */
        public byte[] toBytes(T t) {
            return Metadata.streamToBytes(this.marshaller.toStream(t));
        }

        /* access modifiers changed from: package-private */
        public T parseBytes(byte[] bArr) {
            return this.marshaller.parseStream(new ByteArrayInputStream(bArr));
        }
    }

    /* renamed from: io.grpc.Metadata$LazyValue */
    static final class LazyValue<T> {
        private final BinaryStreamMarshaller<T> marshaller;
        private volatile byte[] serialized;
        private final T value;

        static <T> LazyValue<T> create(Key<T> key, T t) {
            return new LazyValue<>((BinaryStreamMarshaller) Preconditions.checkNotNull(getBinaryStreamMarshaller(key)), t);
        }

        LazyValue(BinaryStreamMarshaller<T> binaryStreamMarshaller, T t) {
            this.marshaller = binaryStreamMarshaller;
            this.value = t;
        }

        /* access modifiers changed from: package-private */
        public InputStream toStream() {
            return this.marshaller.toStream(this.value);
        }

        /* access modifiers changed from: package-private */
        public byte[] toBytes() {
            if (this.serialized == null) {
                synchronized (this) {
                    if (this.serialized == null) {
                        this.serialized = Metadata.streamToBytes(toStream());
                    }
                }
            }
            return this.serialized;
        }

        /* access modifiers changed from: package-private */
        public <T2> T2 toObject(Key<T2> key) {
            BinaryStreamMarshaller<T2> binaryStreamMarshaller;
            if (!key.serializesToStreams() || (binaryStreamMarshaller = getBinaryStreamMarshaller(key)) == null) {
                return key.parseBytes(toBytes());
            }
            return binaryStreamMarshaller.parseStream(toStream());
        }

        @Nullable
        private static <T> BinaryStreamMarshaller<T> getBinaryStreamMarshaller(Key<T> key) {
            return (BinaryStreamMarshaller) key.getMarshaller(BinaryStreamMarshaller.class);
        }
    }

    /* renamed from: io.grpc.Metadata$AsciiKey */
    private static class AsciiKey<T> extends Key<T> {
        private final AsciiMarshaller<T> marshaller;

        private AsciiKey(String str, boolean z, AsciiMarshaller<T> asciiMarshaller) {
            super(str, z, asciiMarshaller);
            Preconditions.checkArgument(!str.endsWith(Metadata.BINARY_HEADER_SUFFIX), "ASCII header is named %s.  Only binary headers may end with %s", (Object) str, (Object) Metadata.BINARY_HEADER_SUFFIX);
            this.marshaller = (AsciiMarshaller) Preconditions.checkNotNull(asciiMarshaller, "marshaller");
        }

        /* access modifiers changed from: package-private */
        public byte[] toBytes(T t) {
            return this.marshaller.toAsciiString(t).getBytes(Charsets.US_ASCII);
        }

        /* access modifiers changed from: package-private */
        public T parseBytes(byte[] bArr) {
            return this.marshaller.parseAsciiString(new String(bArr, Charsets.US_ASCII));
        }
    }

    /* renamed from: io.grpc.Metadata$TrustedAsciiKey */
    private static final class TrustedAsciiKey<T> extends Key<T> {
        private final TrustedAsciiMarshaller<T> marshaller;

        private TrustedAsciiKey(String str, boolean z, TrustedAsciiMarshaller<T> trustedAsciiMarshaller) {
            super(str, z, trustedAsciiMarshaller);
            Preconditions.checkArgument(!str.endsWith(Metadata.BINARY_HEADER_SUFFIX), "ASCII header is named %s.  Only binary headers may end with %s", (Object) str, (Object) Metadata.BINARY_HEADER_SUFFIX);
            this.marshaller = (TrustedAsciiMarshaller) Preconditions.checkNotNull(trustedAsciiMarshaller, "marshaller");
        }

        /* access modifiers changed from: package-private */
        public byte[] toBytes(T t) {
            return this.marshaller.toAsciiString(t);
        }

        /* access modifiers changed from: package-private */
        public T parseBytes(byte[] bArr) {
            return this.marshaller.parseAsciiString(bArr);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] streamToBytes(InputStream inputStream) {
        try {
            return ByteStreams.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("failure reading serialized stream", e);
        }
    }
}

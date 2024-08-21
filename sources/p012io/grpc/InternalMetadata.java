package p012io.grpc;

import com.google.common.p008io.BaseEncoding;
import java.nio.charset.Charset;
import p012io.grpc.Metadata;

/* renamed from: io.grpc.InternalMetadata */
public final class InternalMetadata {
    public static final BaseEncoding BASE64_ENCODING_OMIT_PADDING = Metadata.BASE64_ENCODING_OMIT_PADDING;
    public static final Charset US_ASCII = Charset.forName("US-ASCII");

    /* renamed from: io.grpc.InternalMetadata$TrustedAsciiMarshaller */
    public interface TrustedAsciiMarshaller<T> extends Metadata.TrustedAsciiMarshaller<T> {
    }

    public static <T> Metadata.Key<T> keyOf(String str, TrustedAsciiMarshaller<T> trustedAsciiMarshaller) {
        boolean z = false;
        if (str != null && !str.isEmpty() && str.charAt(0) == ':') {
            z = true;
        }
        return Metadata.Key.m266of(str, z, trustedAsciiMarshaller);
    }

    public static <T> Metadata.Key<T> keyOf(String str, Metadata.AsciiMarshaller<T> asciiMarshaller) {
        boolean z = false;
        if (str != null && !str.isEmpty() && str.charAt(0) == ':') {
            z = true;
        }
        return Metadata.Key.m265of(str, z, asciiMarshaller);
    }

    public static Metadata newMetadata(byte[]... bArr) {
        return new Metadata(bArr);
    }

    public static Metadata newMetadata(int i, byte[]... bArr) {
        return new Metadata(i, bArr);
    }

    public static byte[][] serialize(Metadata metadata) {
        return metadata.serialize();
    }

    public static int headerCount(Metadata metadata) {
        return metadata.headerCount();
    }

    public static Object[] serializePartial(Metadata metadata) {
        return metadata.serializePartial();
    }

    public static <T> Object parsedValue(Metadata.BinaryStreamMarshaller<T> binaryStreamMarshaller, T t) {
        return new Metadata.LazyValue(binaryStreamMarshaller, t);
    }

    public static Metadata newMetadataWithParsedValues(int i, Object[] objArr) {
        return new Metadata(i, objArr);
    }
}

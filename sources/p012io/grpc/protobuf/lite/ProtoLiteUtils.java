package p012io.grpc.protobuf.lite;

import com.google.common.base.Preconditions;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import p012io.grpc.KnownLength;
import p012io.grpc.Metadata;
import p012io.grpc.MethodDescriptor;
import p012io.grpc.Status;

/* renamed from: io.grpc.protobuf.lite.ProtoLiteUtils */
public final class ProtoLiteUtils {
    private static final int BUF_SIZE = 8192;
    static final int DEFAULT_MAX_MESSAGE_SIZE = 4194304;
    static volatile ExtensionRegistryLite globalRegistry = ExtensionRegistryLite.getEmptyRegistry();

    public static void setExtensionRegistry(ExtensionRegistryLite extensionRegistryLite) {
        globalRegistry = (ExtensionRegistryLite) Preconditions.checkNotNull(extensionRegistryLite, "newRegistry");
    }

    public static <T extends MessageLite> MethodDescriptor.Marshaller<T> marshaller(T t) {
        return new MessageMarshaller(t);
    }

    public static <T extends MessageLite> Metadata.BinaryMarshaller<T> metadataMarshaller(T t) {
        return new MetadataMarshaller(t);
    }

    static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        Preconditions.checkNotNull(inputStream, "inputStream cannot be null!");
        Preconditions.checkNotNull(outputStream, "outputStream cannot be null!");
        byte[] bArr = new byte[8192];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    private ProtoLiteUtils() {
    }

    /* renamed from: io.grpc.protobuf.lite.ProtoLiteUtils$MessageMarshaller */
    private static final class MessageMarshaller<T extends MessageLite> implements MethodDescriptor.PrototypeMarshaller<T> {
        private static final ThreadLocal<Reference<byte[]>> bufs = new ThreadLocal<>();
        private final T defaultInstance;
        private final Parser<T> parser;

        MessageMarshaller(T t) {
            this.defaultInstance = t;
            this.parser = t.getParserForType();
        }

        public Class<T> getMessageClass() {
            return this.defaultInstance.getClass();
        }

        public T getMessagePrototype() {
            return this.defaultInstance;
        }

        public InputStream stream(T t) {
            return new ProtoInputStream(t, this.parser);
        }

        public T parse(InputStream inputStream) {
            byte[] bArr;
            if ((inputStream instanceof ProtoInputStream) && ((ProtoInputStream) inputStream).parser() == this.parser) {
                try {
                    return ((ProtoInputStream) inputStream).message();
                } catch (IllegalStateException unused) {
                }
            }
            CodedInputStream codedInputStream = null;
            try {
                if (inputStream instanceof KnownLength) {
                    int available = inputStream.available();
                    if (available > 0 && available <= 4194304) {
                        ThreadLocal<Reference<byte[]>> threadLocal = bufs;
                        Reference reference = threadLocal.get();
                        if (reference == null || (bArr = (byte[]) reference.get()) == null || bArr.length < available) {
                            bArr = new byte[available];
                            threadLocal.set(new WeakReference(bArr));
                        }
                        int i = available;
                        while (true) {
                            if (i <= 0) {
                                break;
                            }
                            int read = inputStream.read(bArr, available - i, i);
                            if (read == -1) {
                                break;
                            }
                            i -= read;
                        }
                        if (i == 0) {
                            codedInputStream = CodedInputStream.newInstance(bArr, 0, available);
                        } else {
                            throw new RuntimeException("size inaccurate: " + available + " != " + (available - i));
                        }
                    } else if (available == 0) {
                        return this.defaultInstance;
                    }
                }
                if (codedInputStream == null) {
                    codedInputStream = CodedInputStream.newInstance(inputStream);
                }
                codedInputStream.setSizeLimit(Integer.MAX_VALUE);
                try {
                    return parseFrom(codedInputStream);
                } catch (InvalidProtocolBufferException e) {
                    throw Status.INTERNAL.withDescription("Invalid protobuf byte sequence").withCause(e).asRuntimeException();
                }
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        private T parseFrom(CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
            T t = (MessageLite) this.parser.parseFrom(codedInputStream, ProtoLiteUtils.globalRegistry);
            try {
                codedInputStream.checkLastTagWas(0);
                return t;
            } catch (InvalidProtocolBufferException e) {
                e.setUnfinishedMessage(t);
                throw e;
            }
        }
    }

    /* renamed from: io.grpc.protobuf.lite.ProtoLiteUtils$MetadataMarshaller */
    private static final class MetadataMarshaller<T extends MessageLite> implements Metadata.BinaryMarshaller<T> {
        private final T defaultInstance;

        MetadataMarshaller(T t) {
            this.defaultInstance = t;
        }

        public byte[] toBytes(T t) {
            return t.toByteArray();
        }

        public T parseBytes(byte[] bArr) {
            try {
                return (MessageLite) this.defaultInstance.getParserForType().parseFrom(bArr, ProtoLiteUtils.globalRegistry);
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}

package p012io.grpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* renamed from: io.grpc.Codec */
public interface Codec extends Compressor, Decompressor {

    /* renamed from: io.grpc.Codec$Gzip */
    public static final class Gzip implements Codec {
        public String getMessageEncoding() {
            return "gzip";
        }

        public OutputStream compress(OutputStream outputStream) throws IOException {
            return new GZIPOutputStream(outputStream);
        }

        public InputStream decompress(InputStream inputStream) throws IOException {
            return new GZIPInputStream(inputStream);
        }
    }

    /* renamed from: io.grpc.Codec$Identity */
    public static final class Identity implements Codec {
        public static final Codec NONE = new Identity();

        public OutputStream compress(OutputStream outputStream) {
            return outputStream;
        }

        public InputStream decompress(InputStream inputStream) {
            return inputStream;
        }

        public String getMessageEncoding() {
            return "identity";
        }

        private Identity() {
        }
    }
}

package p012io.grpc.okhttp;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import okio.ByteString;
import p012io.grpc.InternalMetadata;
import p012io.grpc.Metadata;
import p012io.grpc.internal.GrpcUtil;
import p012io.grpc.internal.TransportFrameUtil;
import p012io.grpc.okhttp.internal.framed.Header;

/* renamed from: io.grpc.okhttp.Headers */
class Headers {
    public static final Header CONTENT_TYPE_HEADER = new Header(GrpcUtil.CONTENT_TYPE_KEY.name(), GrpcUtil.CONTENT_TYPE_GRPC);
    public static final Header HTTPS_SCHEME_HEADER = new Header(Header.TARGET_SCHEME, "https");
    public static final Header HTTP_SCHEME_HEADER = new Header(Header.TARGET_SCHEME, "http");
    public static final Header METHOD_GET_HEADER = new Header(Header.TARGET_METHOD, "GET");
    public static final Header METHOD_HEADER = new Header(Header.TARGET_METHOD, GrpcUtil.HTTP_METHOD);
    public static final Header TE_HEADER = new Header("te", GrpcUtil.TE_TRAILERS);

    Headers() {
    }

    public static List<Header> createRequestHeaders(Metadata metadata, String str, String str2, String str3, boolean z, boolean z2) {
        Preconditions.checkNotNull(metadata, "headers");
        Preconditions.checkNotNull(str, "defaultPath");
        Preconditions.checkNotNull(str2, "authority");
        metadata.discardAll(GrpcUtil.CONTENT_TYPE_KEY);
        metadata.discardAll(GrpcUtil.TE_HEADER);
        metadata.discardAll(GrpcUtil.USER_AGENT_KEY);
        ArrayList arrayList = new ArrayList(InternalMetadata.headerCount(metadata) + 7);
        if (z2) {
            arrayList.add(HTTP_SCHEME_HEADER);
        } else {
            arrayList.add(HTTPS_SCHEME_HEADER);
        }
        if (z) {
            arrayList.add(METHOD_GET_HEADER);
        } else {
            arrayList.add(METHOD_HEADER);
        }
        arrayList.add(new Header(Header.TARGET_AUTHORITY, str2));
        arrayList.add(new Header(Header.TARGET_PATH, str));
        arrayList.add(new Header(GrpcUtil.USER_AGENT_KEY.name(), str3));
        arrayList.add(CONTENT_TYPE_HEADER);
        arrayList.add(TE_HEADER);
        byte[][] http2Headers = TransportFrameUtil.toHttp2Headers(metadata);
        for (int i = 0; i < http2Headers.length; i += 2) {
            ByteString of = ByteString.m292of(http2Headers[i]);
            if (isApplicationHeader(of.utf8())) {
                arrayList.add(new Header(of, ByteString.m292of(http2Headers[i + 1])));
            }
        }
        return arrayList;
    }

    private static boolean isApplicationHeader(String str) {
        return !str.startsWith(":") && !GrpcUtil.CONTENT_TYPE_KEY.name().equalsIgnoreCase(str) && !GrpcUtil.USER_AGENT_KEY.name().equalsIgnoreCase(str);
    }
}

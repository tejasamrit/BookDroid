package androidx.core.text;

import android.text.TextUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\b¨\u0006\u0002"}, mo37160d2 = {"htmlEncode", "", "core-ktx_release"}, mo37161k = 2, mo37162mv = {1, 1, 16})
/* compiled from: String.kt */
public final class StringKt {
    public static final String htmlEncode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$htmlEncode");
        String htmlEncode = TextUtils.htmlEncode(str);
        Intrinsics.checkExpressionValueIsNotNull(htmlEncode, "TextUtils.htmlEncode(this)");
        return htmlEncode;
    }
}

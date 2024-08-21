package com.github.pgreze.reactions;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0015\u0010\u0003\u001a\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, mo37160d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "p1", "", "Lkotlin/ParameterName;", "name", "index", "invoke"}, mo37161k = 3, mo37162mv = {1, 4, 0})
/* compiled from: model.kt */
final /* synthetic */ class ReactionsConfigBuilder$withReactionTexts$2$1 extends FunctionReferenceImpl implements Function1<Integer, String> {
    ReactionsConfigBuilder$withReactionTexts$2$1(String[] strArr) {
        super(1, strArr, Object[].class, "get", "get(I)Ljava/lang/Object;", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final String invoke(int i) {
        return ((String[]) this.receiver)[i];
    }
}

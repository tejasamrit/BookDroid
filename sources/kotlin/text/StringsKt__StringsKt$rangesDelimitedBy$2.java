package kotlin.text;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\r\n\u0002\b\u0002\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0005"}, mo37160d2 = {"<anonymous>", "Lkotlin/Pair;", "", "", "currentIndex", "invoke"}, mo37161k = 3, mo37162mv = {1, 4, 0})
/* compiled from: Strings.kt */
final class StringsKt__StringsKt$rangesDelimitedBy$2 extends Lambda implements Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>> {
    final /* synthetic */ char[] $delimiters;
    final /* synthetic */ boolean $ignoreCase;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StringsKt__StringsKt$rangesDelimitedBy$2(char[] cArr, boolean z) {
        super(2);
        this.$delimiters = cArr;
        this.$ignoreCase = z;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((CharSequence) obj, ((Number) obj2).intValue());
    }

    public final Pair<Integer, Integer> invoke(CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "$receiver");
        int indexOfAny = StringsKt.indexOfAny(charSequence, this.$delimiters, i, this.$ignoreCase);
        if (indexOfAny < 0) {
            return null;
        }
        return TuplesKt.m278to(Integer.valueOf(indexOfAny), 1);
    }
}

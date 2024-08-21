package p012io.perfmark;

import javax.annotation.Nullable;

/* renamed from: io.perfmark.Tag */
public final class Tag {
    final long tagId;
    @Nullable
    final String tagName;

    Tag(@Nullable String str, long j) {
        this.tagName = str;
        this.tagId = j;
    }
}

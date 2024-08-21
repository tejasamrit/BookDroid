package p012io.perfmark;

/* renamed from: io.perfmark.PerfMark */
public final class PerfMark {
    private static final Impl impl;

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0016  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    static {
        /*
            java.util.logging.Level r0 = java.util.logging.Level.WARNING
            r1 = 0
            java.lang.String r2 = "io.perfmark.impl.SecretPerfMarkImpl$PerfMarkImpl"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException -> 0x000e, all -> 0x000b }
            r3 = r1
            goto L_0x0014
        L_0x000b:
            r2 = move-exception
            r3 = r2
            goto L_0x0013
        L_0x000e:
            r0 = move-exception
            java.util.logging.Level r2 = java.util.logging.Level.FINE
            r3 = r0
            r0 = r2
        L_0x0013:
            r2 = r1
        L_0x0014:
            if (r2 == 0) goto L_0x0038
            java.lang.Class<io.perfmark.Impl> r4 = p012io.perfmark.Impl.class
            java.lang.Class r2 = r2.asSubclass(r4)     // Catch:{ all -> 0x0036 }
            r4 = 1
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch:{ all -> 0x0036 }
            java.lang.Class<io.perfmark.Tag> r6 = p012io.perfmark.Tag.class
            r7 = 0
            r5[r7] = r6     // Catch:{ all -> 0x0036 }
            java.lang.reflect.Constructor r2 = r2.getConstructor(r5)     // Catch:{ all -> 0x0036 }
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0036 }
            io.perfmark.Tag r5 = p012io.perfmark.Impl.NO_TAG     // Catch:{ all -> 0x0036 }
            r4[r7] = r5     // Catch:{ all -> 0x0036 }
            java.lang.Object r2 = r2.newInstance(r4)     // Catch:{ all -> 0x0036 }
            io.perfmark.Impl r2 = (p012io.perfmark.Impl) r2     // Catch:{ all -> 0x0036 }
            r1 = r2
            goto L_0x0038
        L_0x0036:
            r2 = move-exception
            r3 = r2
        L_0x0038:
            if (r1 == 0) goto L_0x003d
            impl = r1
            goto L_0x0046
        L_0x003d:
            io.perfmark.Impl r1 = new io.perfmark.Impl
            io.perfmark.Tag r2 = p012io.perfmark.Impl.NO_TAG
            r1.<init>(r2)
            impl = r1
        L_0x0046:
            if (r3 == 0) goto L_0x0057
            java.lang.Class<io.perfmark.PerfMark> r1 = p012io.perfmark.PerfMark.class
            java.lang.String r1 = r1.getName()
            java.util.logging.Logger r1 = java.util.logging.Logger.getLogger(r1)
            java.lang.String r2 = "Error during PerfMark.<clinit>"
            r1.log(r0, r2, r3)
        L_0x0057:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p012io.perfmark.PerfMark.<clinit>():void");
    }

    public static void setEnabled(boolean z) {
        impl.setEnabled(z);
    }

    public static void startTask(String str, Tag tag) {
        impl.startTask(str, tag);
    }

    public static void startTask(String str) {
        impl.startTask(str);
    }

    public static void event(String str, Tag tag) {
        impl.event(str, tag);
    }

    public static void event(String str) {
        impl.event(str);
    }

    public static void stopTask(String str, Tag tag) {
        impl.stopTask(str, tag);
    }

    public static void stopTask(String str) {
        impl.stopTask(str);
    }

    public static Tag createTag() {
        return Impl.NO_TAG;
    }

    public static Tag createTag(long j) {
        return impl.createTag("", j);
    }

    public static Tag createTag(String str) {
        return impl.createTag(str, Long.MIN_VALUE);
    }

    public static Tag createTag(String str, long j) {
        return impl.createTag(str, j);
    }

    @Deprecated
    public static Link link() {
        return Impl.NO_LINK;
    }

    public static Link linkOut() {
        return impl.linkOut();
    }

    public static void linkIn(Link link) {
        impl.linkIn(link);
    }

    public static void attachTag(Tag tag) {
        impl.attachTag(tag);
    }

    private PerfMark() {
    }
}

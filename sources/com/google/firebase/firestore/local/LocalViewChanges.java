package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.DocumentKey;
import java.util.ArrayList;

public final class LocalViewChanges {
    private final ImmutableSortedSet<DocumentKey> added;
    private final boolean fromCache;
    private final ImmutableSortedSet<DocumentKey> removed;
    private final int targetId;

    public static LocalViewChanges fromViewSnapshot(int i, ViewSnapshot viewSnapshot) {
        ImmutableSortedSet immutableSortedSet = new ImmutableSortedSet(new ArrayList(), DocumentKey.comparator());
        ImmutableSortedSet immutableSortedSet2 = new ImmutableSortedSet(new ArrayList(), DocumentKey.comparator());
        for (DocumentViewChange next : viewSnapshot.getChanges()) {
            int i2 = C18921.f332x33862af7[next.getType().ordinal()];
            if (i2 == 1) {
                immutableSortedSet = immutableSortedSet.insert(next.getDocument().getKey());
            } else if (i2 == 2) {
                immutableSortedSet2 = immutableSortedSet2.insert(next.getDocument().getKey());
            }
        }
        return new LocalViewChanges(i, viewSnapshot.isFromCache(), immutableSortedSet, immutableSortedSet2);
    }

    /* renamed from: com.google.firebase.firestore.local.LocalViewChanges$1 */
    static /* synthetic */ class C18921 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type */
        static final /* synthetic */ int[] f332x33862af7;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.google.firebase.firestore.core.DocumentViewChange$Type[] r0 = com.google.firebase.firestore.core.DocumentViewChange.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f332x33862af7 = r0
                com.google.firebase.firestore.core.DocumentViewChange$Type r1 = com.google.firebase.firestore.core.DocumentViewChange.Type.ADDED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f332x33862af7     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.core.DocumentViewChange$Type r1 = com.google.firebase.firestore.core.DocumentViewChange.Type.REMOVED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.local.LocalViewChanges.C18921.<clinit>():void");
        }
    }

    public LocalViewChanges(int i, boolean z, ImmutableSortedSet<DocumentKey> immutableSortedSet, ImmutableSortedSet<DocumentKey> immutableSortedSet2) {
        this.targetId = i;
        this.fromCache = z;
        this.added = immutableSortedSet;
        this.removed = immutableSortedSet2;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public boolean isFromCache() {
        return this.fromCache;
    }

    public ImmutableSortedSet<DocumentKey> getAdded() {
        return this.added;
    }

    public ImmutableSortedSet<DocumentKey> getRemoved() {
        return this.removed;
    }
}

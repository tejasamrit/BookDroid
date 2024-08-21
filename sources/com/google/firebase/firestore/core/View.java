package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.LimboDocumentChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class View {
    private boolean current;
    private DocumentSet documentSet;
    private ImmutableSortedSet<DocumentKey> limboDocuments;
    private ImmutableSortedSet<DocumentKey> mutatedKeys;
    private final Query query;
    private ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
    private ImmutableSortedSet<DocumentKey> syncedDocuments;

    public static class DocumentChanges {
        final DocumentViewChangeSet changeSet;
        final DocumentSet documentSet;
        final ImmutableSortedSet<DocumentKey> mutatedKeys;
        /* access modifiers changed from: private */
        public final boolean needsRefill;

        /* synthetic */ DocumentChanges(DocumentSet documentSet2, DocumentViewChangeSet documentViewChangeSet, ImmutableSortedSet immutableSortedSet, boolean z, C18881 r5) {
            this(documentSet2, documentViewChangeSet, immutableSortedSet, z);
        }

        private DocumentChanges(DocumentSet documentSet2, DocumentViewChangeSet documentViewChangeSet, ImmutableSortedSet<DocumentKey> immutableSortedSet, boolean z) {
            this.documentSet = documentSet2;
            this.changeSet = documentViewChangeSet;
            this.mutatedKeys = immutableSortedSet;
            this.needsRefill = z;
        }

        public boolean needsRefill() {
            return this.needsRefill;
        }
    }

    public View(Query query2, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        this.query = query2;
        this.documentSet = DocumentSet.emptySet(query2.comparator());
        this.syncedDocuments = immutableSortedSet;
        this.limboDocuments = DocumentKey.emptyKeySet();
        this.mutatedKeys = DocumentKey.emptyKeySet();
    }

    public ViewSnapshot.SyncState getSyncState() {
        return this.syncState;
    }

    public <D extends MaybeDocument> DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, D> immutableSortedMap) {
        return computeDocChanges(immutableSortedMap, (DocumentChanges) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0107, code lost:
        if (r0.query.comparator().compare(r12, r4) > 0) goto L_0x0146;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0115, code lost:
        if (r0.query.comparator().compare(r12, r7) < 0) goto L_0x0146;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0144, code lost:
        if (r7 == null) goto L_0x0133;
     */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0172 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x014b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <D extends com.google.firebase.firestore.model.MaybeDocument> com.google.firebase.firestore.core.View.DocumentChanges computeDocChanges(com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.firestore.model.DocumentKey, D> r19, com.google.firebase.firestore.core.View.DocumentChanges r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r20
            if (r1 == 0) goto L_0x0009
            com.google.firebase.firestore.core.DocumentViewChangeSet r2 = r1.changeSet
            goto L_0x000e
        L_0x0009:
            com.google.firebase.firestore.core.DocumentViewChangeSet r2 = new com.google.firebase.firestore.core.DocumentViewChangeSet
            r2.<init>()
        L_0x000e:
            r5 = r2
            if (r1 == 0) goto L_0x0014
            com.google.firebase.firestore.model.DocumentSet r2 = r1.documentSet
            goto L_0x0016
        L_0x0014:
            com.google.firebase.firestore.model.DocumentSet r2 = r0.documentSet
        L_0x0016:
            if (r1 == 0) goto L_0x001b
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.model.DocumentKey> r3 = r1.mutatedKeys
            goto L_0x001d
        L_0x001b:
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.model.DocumentKey> r3 = r0.mutatedKeys
        L_0x001d:
            com.google.firebase.firestore.core.Query r4 = r0.query
            boolean r4 = r4.hasLimitToFirst()
            if (r4 == 0) goto L_0x0039
            int r4 = r2.size()
            long r7 = (long) r4
            com.google.firebase.firestore.core.Query r4 = r0.query
            long r9 = r4.getLimitToFirst()
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 != 0) goto L_0x0039
            com.google.firebase.firestore.model.Document r4 = r2.getLastDocument()
            goto L_0x003a
        L_0x0039:
            r4 = 0
        L_0x003a:
            com.google.firebase.firestore.core.Query r7 = r0.query
            boolean r7 = r7.hasLimitToLast()
            if (r7 == 0) goto L_0x0056
            int r7 = r2.size()
            long r7 = (long) r7
            com.google.firebase.firestore.core.Query r9 = r0.query
            long r9 = r9.getLimitToLast()
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x0056
            com.google.firebase.firestore.model.Document r7 = r2.getFirstDocument()
            goto L_0x0057
        L_0x0056:
            r7 = 0
        L_0x0057:
            java.util.Iterator r8 = r19.iterator()
            r9 = 0
            r11 = r2
            r10 = 0
        L_0x005e:
            boolean r12 = r8.hasNext()
            if (r12 == 0) goto L_0x0177
            java.lang.Object r12 = r8.next()
            java.util.Map$Entry r12 = (java.util.Map.Entry) r12
            java.lang.Object r14 = r12.getKey()
            com.google.firebase.firestore.model.DocumentKey r14 = (com.google.firebase.firestore.model.DocumentKey) r14
            com.google.firebase.firestore.model.Document r15 = r2.getDocument(r14)
            java.lang.Object r12 = r12.getValue()
            com.google.firebase.firestore.model.MaybeDocument r12 = (com.google.firebase.firestore.model.MaybeDocument) r12
            boolean r6 = r12 instanceof com.google.firebase.firestore.model.Document
            if (r6 == 0) goto L_0x0081
            com.google.firebase.firestore.model.Document r12 = (com.google.firebase.firestore.model.Document) r12
            goto L_0x0082
        L_0x0081:
            r12 = 0
        L_0x0082:
            if (r12 == 0) goto L_0x00a8
            com.google.firebase.firestore.model.DocumentKey r6 = r12.getKey()
            boolean r6 = r14.equals(r6)
            r13 = 2
            java.lang.Object[] r13 = new java.lang.Object[r13]
            r13[r9] = r14
            com.google.firebase.firestore.model.DocumentKey r16 = r12.getKey()
            r17 = 1
            r13[r17] = r16
            java.lang.String r9 = "Mismatching key in doc change %s != %s"
            com.google.firebase.firestore.util.Assert.hardAssert(r6, r9, r13)
            com.google.firebase.firestore.core.Query r6 = r0.query
            boolean r6 = r6.matches(r12)
            if (r6 != 0) goto L_0x00aa
            r12 = 0
            goto L_0x00aa
        L_0x00a8:
            r17 = 1
        L_0x00aa:
            if (r15 == 0) goto L_0x00ba
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.model.DocumentKey> r6 = r0.mutatedKeys
            com.google.firebase.firestore.model.DocumentKey r9 = r15.getKey()
            boolean r6 = r6.contains(r9)
            if (r6 == 0) goto L_0x00ba
            r6 = 1
            goto L_0x00bb
        L_0x00ba:
            r6 = 0
        L_0x00bb:
            if (r12 == 0) goto L_0x00d7
            boolean r9 = r12.hasLocalMutations()
            if (r9 != 0) goto L_0x00d5
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.model.DocumentKey> r9 = r0.mutatedKeys
            com.google.firebase.firestore.model.DocumentKey r13 = r12.getKey()
            boolean r9 = r9.contains(r13)
            if (r9 == 0) goto L_0x00d7
            boolean r9 = r12.hasCommittedMutations()
            if (r9 == 0) goto L_0x00d7
        L_0x00d5:
            r9 = 1
            goto L_0x00d8
        L_0x00d7:
            r9 = 0
        L_0x00d8:
            if (r15 == 0) goto L_0x0124
            if (r12 == 0) goto L_0x0124
            com.google.firebase.firestore.model.ObjectValue r13 = r15.getData()
            r16 = r2
            com.google.firebase.firestore.model.ObjectValue r2 = r12.getData()
            boolean r2 = r13.equals(r2)
            if (r2 != 0) goto L_0x0118
            boolean r2 = r0.shouldWaitForSyncedDocument(r15, r12)
            if (r2 != 0) goto L_0x0148
            com.google.firebase.firestore.core.DocumentViewChange$Type r2 = com.google.firebase.firestore.core.DocumentViewChange.Type.MODIFIED
            com.google.firebase.firestore.core.DocumentViewChange r2 = com.google.firebase.firestore.core.DocumentViewChange.create(r2, r12)
            r5.addChange(r2)
            if (r4 == 0) goto L_0x0109
            com.google.firebase.firestore.core.Query r2 = r0.query
            java.util.Comparator r2 = r2.comparator()
            int r2 = r2.compare(r12, r4)
            if (r2 > 0) goto L_0x0146
        L_0x0109:
            if (r7 == 0) goto L_0x0133
            com.google.firebase.firestore.core.Query r2 = r0.query
            java.util.Comparator r2 = r2.comparator()
            int r2 = r2.compare(r12, r7)
            if (r2 >= 0) goto L_0x0133
            goto L_0x0146
        L_0x0118:
            if (r6 == r9) goto L_0x0148
            com.google.firebase.firestore.core.DocumentViewChange$Type r2 = com.google.firebase.firestore.core.DocumentViewChange.Type.METADATA
            com.google.firebase.firestore.core.DocumentViewChange r2 = com.google.firebase.firestore.core.DocumentViewChange.create(r2, r12)
            r5.addChange(r2)
            goto L_0x0133
        L_0x0124:
            r16 = r2
            if (r15 != 0) goto L_0x0135
            if (r12 == 0) goto L_0x0135
            com.google.firebase.firestore.core.DocumentViewChange$Type r2 = com.google.firebase.firestore.core.DocumentViewChange.Type.ADDED
            com.google.firebase.firestore.core.DocumentViewChange r2 = com.google.firebase.firestore.core.DocumentViewChange.create(r2, r12)
            r5.addChange(r2)
        L_0x0133:
            r13 = 1
            goto L_0x0149
        L_0x0135:
            if (r15 == 0) goto L_0x0148
            if (r12 != 0) goto L_0x0148
            com.google.firebase.firestore.core.DocumentViewChange$Type r2 = com.google.firebase.firestore.core.DocumentViewChange.Type.REMOVED
            com.google.firebase.firestore.core.DocumentViewChange r2 = com.google.firebase.firestore.core.DocumentViewChange.create(r2, r15)
            r5.addChange(r2)
            if (r4 != 0) goto L_0x0146
            if (r7 == 0) goto L_0x0133
        L_0x0146:
            r10 = 1
            goto L_0x0133
        L_0x0148:
            r13 = 0
        L_0x0149:
            if (r13 == 0) goto L_0x0172
            if (r12 == 0) goto L_0x0169
            com.google.firebase.firestore.model.DocumentSet r11 = r11.add(r12)
            boolean r2 = r12.hasLocalMutations()
            if (r2 == 0) goto L_0x0160
            com.google.firebase.firestore.model.DocumentKey r2 = r12.getKey()
            com.google.firebase.database.collection.ImmutableSortedSet r2 = r3.insert(r2)
            goto L_0x0171
        L_0x0160:
            com.google.firebase.firestore.model.DocumentKey r2 = r12.getKey()
            com.google.firebase.database.collection.ImmutableSortedSet r2 = r3.remove(r2)
            goto L_0x0171
        L_0x0169:
            com.google.firebase.firestore.model.DocumentSet r11 = r11.remove(r14)
            com.google.firebase.database.collection.ImmutableSortedSet r2 = r3.remove(r14)
        L_0x0171:
            r3 = r2
        L_0x0172:
            r2 = r16
            r9 = 0
            goto L_0x005e
        L_0x0177:
            r17 = 1
            com.google.firebase.firestore.core.Query r2 = r0.query
            boolean r2 = r2.hasLimitToFirst()
            if (r2 != 0) goto L_0x018d
            com.google.firebase.firestore.core.Query r2 = r0.query
            boolean r2 = r2.hasLimitToLast()
            if (r2 == 0) goto L_0x018a
            goto L_0x018d
        L_0x018a:
            r6 = r3
            r4 = r11
            goto L_0x01db
        L_0x018d:
            com.google.firebase.firestore.core.Query r2 = r0.query
            boolean r2 = r2.hasLimitToFirst()
            if (r2 == 0) goto L_0x019c
            com.google.firebase.firestore.core.Query r2 = r0.query
            long r6 = r2.getLimitToFirst()
            goto L_0x01a2
        L_0x019c:
            com.google.firebase.firestore.core.Query r2 = r0.query
            long r6 = r2.getLimitToLast()
        L_0x01a2:
            int r2 = r11.size()
            long r8 = (long) r2
        L_0x01a7:
            long r8 = r8 - r6
            r6 = 0
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x018a
            com.google.firebase.firestore.core.Query r2 = r0.query
            boolean r2 = r2.hasLimitToFirst()
            if (r2 == 0) goto L_0x01bb
            com.google.firebase.firestore.model.Document r2 = r11.getLastDocument()
            goto L_0x01bf
        L_0x01bb:
            com.google.firebase.firestore.model.Document r2 = r11.getFirstDocument()
        L_0x01bf:
            com.google.firebase.firestore.model.DocumentKey r4 = r2.getKey()
            com.google.firebase.firestore.model.DocumentSet r11 = r11.remove(r4)
            com.google.firebase.firestore.model.DocumentKey r4 = r2.getKey()
            com.google.firebase.database.collection.ImmutableSortedSet r3 = r3.remove(r4)
            com.google.firebase.firestore.core.DocumentViewChange$Type r4 = com.google.firebase.firestore.core.DocumentViewChange.Type.REMOVED
            com.google.firebase.firestore.core.DocumentViewChange r2 = com.google.firebase.firestore.core.DocumentViewChange.create(r4, r2)
            r5.addChange(r2)
            r6 = 1
            goto L_0x01a7
        L_0x01db:
            if (r10 == 0) goto L_0x01e3
            if (r1 != 0) goto L_0x01e0
            goto L_0x01e3
        L_0x01e0:
            r1 = 0
            r13 = 0
            goto L_0x01e5
        L_0x01e3:
            r1 = 0
            r13 = 1
        L_0x01e5:
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "View was refilled using docs that themselves needed refilling."
            com.google.firebase.firestore.util.Assert.hardAssert(r13, r2, r1)
            com.google.firebase.firestore.core.View$DocumentChanges r1 = new com.google.firebase.firestore.core.View$DocumentChanges
            r8 = 0
            r3 = r1
            r7 = r10
            r3.<init>(r4, r5, r6, r7, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.View.computeDocChanges(com.google.firebase.database.collection.ImmutableSortedMap, com.google.firebase.firestore.core.View$DocumentChanges):com.google.firebase.firestore.core.View$DocumentChanges");
    }

    private boolean shouldWaitForSyncedDocument(Document document, Document document2) {
        return document.hasLocalMutations() && document2.hasCommittedMutations() && !document2.hasLocalMutations();
    }

    public ViewChange applyChanges(DocumentChanges documentChanges) {
        return applyChanges(documentChanges, (TargetChange) null);
    }

    public ViewChange applyChanges(DocumentChanges documentChanges, TargetChange targetChange) {
        DocumentChanges documentChanges2 = documentChanges;
        Assert.hardAssert(!documentChanges.needsRefill, "Cannot apply changes that need a refill", new Object[0]);
        DocumentSet documentSet2 = this.documentSet;
        this.documentSet = documentChanges2.documentSet;
        this.mutatedKeys = documentChanges2.mutatedKeys;
        List<DocumentViewChange> changes = documentChanges2.changeSet.getChanges();
        Collections.sort(changes, View$$Lambda$1.lambdaFactory$(this));
        applyTargetChange(targetChange);
        List<LimboDocumentChange> updateLimboDocuments = updateLimboDocuments();
        ViewSnapshot.SyncState syncState2 = this.limboDocuments.size() == 0 && this.current ? ViewSnapshot.SyncState.SYNCED : ViewSnapshot.SyncState.LOCAL;
        boolean z = syncState2 != this.syncState;
        this.syncState = syncState2;
        ViewSnapshot viewSnapshot = null;
        if (changes.size() != 0 || z) {
            viewSnapshot = new ViewSnapshot(this.query, documentChanges2.documentSet, documentSet2, changes, syncState2 == ViewSnapshot.SyncState.LOCAL, documentChanges2.mutatedKeys, z, false);
        }
        return new ViewChange(viewSnapshot, updateLimboDocuments);
    }

    static /* synthetic */ int lambda$applyChanges$0(View view, DocumentViewChange documentViewChange, DocumentViewChange documentViewChange2) {
        int compareIntegers = Util.compareIntegers(changeTypeOrder(documentViewChange), changeTypeOrder(documentViewChange2));
        documentViewChange.getType().compareTo(documentViewChange2.getType());
        if (compareIntegers != 0) {
            return compareIntegers;
        }
        return view.query.comparator().compare(documentViewChange.getDocument(), documentViewChange2.getDocument());
    }

    public ViewChange applyOnlineStateChange(OnlineState onlineState) {
        if (!this.current || onlineState != OnlineState.OFFLINE) {
            return new ViewChange((ViewSnapshot) null, Collections.emptyList());
        }
        this.current = false;
        return applyChanges(new DocumentChanges(this.documentSet, new DocumentViewChangeSet(), this.mutatedKeys, false, (C18881) null));
    }

    private void applyTargetChange(TargetChange targetChange) {
        if (targetChange != null) {
            Iterator<DocumentKey> it = targetChange.getAddedDocuments().iterator();
            while (it.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.insert(it.next());
            }
            Iterator<DocumentKey> it2 = targetChange.getModifiedDocuments().iterator();
            while (it2.hasNext()) {
                DocumentKey next = it2.next();
                Assert.hardAssert(this.syncedDocuments.contains(next), "Modified document %s not found in view.", next);
            }
            Iterator<DocumentKey> it3 = targetChange.getRemovedDocuments().iterator();
            while (it3.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.remove(it3.next());
            }
            this.current = targetChange.isCurrent();
        }
    }

    private List<LimboDocumentChange> updateLimboDocuments() {
        if (!this.current) {
            return Collections.emptyList();
        }
        ImmutableSortedSet<DocumentKey> immutableSortedSet = this.limboDocuments;
        this.limboDocuments = DocumentKey.emptyKeySet();
        Iterator<Document> it = this.documentSet.iterator();
        while (it.hasNext()) {
            Document next = it.next();
            if (shouldBeLimboDoc(next.getKey())) {
                this.limboDocuments = this.limboDocuments.insert(next.getKey());
            }
        }
        ArrayList arrayList = new ArrayList(immutableSortedSet.size() + this.limboDocuments.size());
        Iterator<DocumentKey> it2 = immutableSortedSet.iterator();
        while (it2.hasNext()) {
            DocumentKey next2 = it2.next();
            if (!this.limboDocuments.contains(next2)) {
                arrayList.add(new LimboDocumentChange(LimboDocumentChange.Type.REMOVED, next2));
            }
        }
        Iterator<DocumentKey> it3 = this.limboDocuments.iterator();
        while (it3.hasNext()) {
            DocumentKey next3 = it3.next();
            if (!immutableSortedSet.contains(next3)) {
                arrayList.add(new LimboDocumentChange(LimboDocumentChange.Type.ADDED, next3));
            }
        }
        return arrayList;
    }

    private boolean shouldBeLimboDoc(DocumentKey documentKey) {
        Document document;
        if (!this.syncedDocuments.contains(documentKey) && (document = this.documentSet.getDocument(documentKey)) != null && !document.hasLocalMutations()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<DocumentKey> getLimboDocuments() {
        return this.limboDocuments;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<DocumentKey> getSyncedDocuments() {
        return this.syncedDocuments;
    }

    /* renamed from: com.google.firebase.firestore.core.View$1 */
    static /* synthetic */ class C18881 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type */
        static final /* synthetic */ int[] f329x33862af7;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.google.firebase.firestore.core.DocumentViewChange$Type[] r0 = com.google.firebase.firestore.core.DocumentViewChange.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f329x33862af7 = r0
                com.google.firebase.firestore.core.DocumentViewChange$Type r1 = com.google.firebase.firestore.core.DocumentViewChange.Type.ADDED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f329x33862af7     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.core.DocumentViewChange$Type r1 = com.google.firebase.firestore.core.DocumentViewChange.Type.MODIFIED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f329x33862af7     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.firestore.core.DocumentViewChange$Type r1 = com.google.firebase.firestore.core.DocumentViewChange.Type.METADATA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f329x33862af7     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.firestore.core.DocumentViewChange$Type r1 = com.google.firebase.firestore.core.DocumentViewChange.Type.REMOVED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.View.C18881.<clinit>():void");
        }
    }

    private static int changeTypeOrder(DocumentViewChange documentViewChange) {
        int i = C18881.f329x33862af7[documentViewChange.getType().ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (!(i == 2 || i == 3)) {
                if (i == 4) {
                    return 0;
                }
                throw new IllegalArgumentException("Unknown change type: " + documentViewChange.getType());
            }
        }
        return i2;
    }
}

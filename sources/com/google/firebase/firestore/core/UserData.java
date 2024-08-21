package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.FieldTransform;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.model.mutation.SetMutation;
import com.google.firebase.firestore.model.mutation.TransformMutation;
import com.google.firebase.firestore.model.mutation.TransformOperation;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UserData {

    public enum Source {
        Set,
        MergeSet,
        Update,
        Argument,
        ArrayArgument
    }

    private UserData() {
    }

    public static class ParseAccumulator {
        /* access modifiers changed from: private */
        public final Source dataSource;
        private final Set<FieldPath> fieldMask = new HashSet();
        private final ArrayList<FieldTransform> fieldTransforms = new ArrayList<>();

        public ParseAccumulator(Source source) {
            this.dataSource = source;
        }

        public Source getDataSource() {
            return this.dataSource;
        }

        public List<FieldTransform> getFieldTransforms() {
            return this.fieldTransforms;
        }

        public ParseContext rootContext() {
            return new ParseContext(this, FieldPath.EMPTY_PATH, false, (C18871) null);
        }

        public boolean contains(FieldPath fieldPath) {
            for (FieldPath isPrefixOf : this.fieldMask) {
                if (fieldPath.isPrefixOf(isPrefixOf)) {
                    return true;
                }
            }
            Iterator<FieldTransform> it = this.fieldTransforms.iterator();
            while (it.hasNext()) {
                if (fieldPath.isPrefixOf(it.next().getFieldPath())) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void addToFieldMask(FieldPath fieldPath) {
            this.fieldMask.add(fieldPath);
        }

        /* access modifiers changed from: package-private */
        public void addToFieldTransforms(FieldPath fieldPath, TransformOperation transformOperation) {
            this.fieldTransforms.add(new FieldTransform(fieldPath, transformOperation));
        }

        public ParsedSetData toMergeData(ObjectValue objectValue) {
            return new ParsedSetData(objectValue, FieldMask.fromSet(this.fieldMask), Collections.unmodifiableList(this.fieldTransforms));
        }

        public ParsedSetData toMergeData(ObjectValue objectValue, FieldMask fieldMask2) {
            ArrayList arrayList = new ArrayList();
            Iterator<FieldTransform> it = this.fieldTransforms.iterator();
            while (it.hasNext()) {
                FieldTransform next = it.next();
                if (fieldMask2.covers(next.getFieldPath())) {
                    arrayList.add(next);
                }
            }
            return new ParsedSetData(objectValue, fieldMask2, Collections.unmodifiableList(arrayList));
        }

        public ParsedSetData toSetData(ObjectValue objectValue) {
            return new ParsedSetData(objectValue, (FieldMask) null, Collections.unmodifiableList(this.fieldTransforms));
        }

        public ParsedUpdateData toUpdateData(ObjectValue objectValue) {
            return new ParsedUpdateData(objectValue, FieldMask.fromSet(this.fieldMask), Collections.unmodifiableList(this.fieldTransforms));
        }
    }

    public static class ParseContext {
        private static final String RESERVED_FIELD_DESIGNATOR = "__";
        private final ParseAccumulator accumulator;
        private final boolean arrayElement;
        private final FieldPath path;

        /* synthetic */ ParseContext(ParseAccumulator parseAccumulator, FieldPath fieldPath, boolean z, C18871 r4) {
            this(parseAccumulator, fieldPath, z);
        }

        private ParseContext(ParseAccumulator parseAccumulator, FieldPath fieldPath, boolean z) {
            this.accumulator = parseAccumulator;
            this.path = fieldPath;
            this.arrayElement = z;
        }

        public boolean isArrayElement() {
            return this.arrayElement;
        }

        public Source getDataSource() {
            return this.accumulator.dataSource;
        }

        public FieldPath getPath() {
            return this.path;
        }

        public boolean isWrite() {
            int i = C18871.$SwitchMap$com$google$firebase$firestore$core$UserData$Source[this.accumulator.dataSource.ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                return true;
            }
            if (i == 4 || i == 5) {
                return false;
            }
            throw Assert.fail("Unexpected case for UserDataSource: %s", this.accumulator.dataSource.name());
        }

        public ParseContext childContext(String str) {
            FieldPath fieldPath = this.path;
            ParseContext parseContext = new ParseContext(this.accumulator, fieldPath == null ? null : (FieldPath) fieldPath.append(str), false);
            parseContext.validatePathSegment(str);
            return parseContext;
        }

        public ParseContext childContext(FieldPath fieldPath) {
            FieldPath fieldPath2 = this.path;
            ParseContext parseContext = new ParseContext(this.accumulator, fieldPath2 == null ? null : (FieldPath) fieldPath2.append(fieldPath), false);
            parseContext.validatePath();
            return parseContext;
        }

        public ParseContext childContext(int i) {
            return new ParseContext(this.accumulator, (FieldPath) null, true);
        }

        public void addToFieldMask(FieldPath fieldPath) {
            this.accumulator.addToFieldMask(fieldPath);
        }

        public void addToFieldTransforms(FieldPath fieldPath, TransformOperation transformOperation) {
            this.accumulator.addToFieldTransforms(fieldPath, transformOperation);
        }

        public RuntimeException createError(String str) {
            String str2;
            FieldPath fieldPath = this.path;
            if (fieldPath == null || fieldPath.isEmpty()) {
                str2 = "";
            } else {
                str2 = " (found in field " + this.path.toString() + ")";
            }
            return new IllegalArgumentException("Invalid data. " + str + str2);
        }

        private void validatePath() {
            if (this.path != null) {
                for (int i = 0; i < this.path.length(); i++) {
                    validatePathSegment(this.path.getSegment(i));
                }
            }
        }

        private void validatePathSegment(String str) {
            if (str.isEmpty()) {
                throw createError("Document fields must not be empty");
            } else if (isWrite() && str.startsWith(RESERVED_FIELD_DESIGNATOR) && str.endsWith(RESERVED_FIELD_DESIGNATOR)) {
                throw createError("Document fields cannot begin and end with \"__\"");
            }
        }
    }

    /* renamed from: com.google.firebase.firestore.core.UserData$1 */
    static /* synthetic */ class C18871 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$UserData$Source;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.firebase.firestore.core.UserData$Source[] r0 = com.google.firebase.firestore.core.UserData.Source.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$firestore$core$UserData$Source = r0
                com.google.firebase.firestore.core.UserData$Source r1 = com.google.firebase.firestore.core.UserData.Source.Set     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$UserData$Source     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.core.UserData$Source r1 = com.google.firebase.firestore.core.UserData.Source.MergeSet     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$UserData$Source     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.firestore.core.UserData$Source r1 = com.google.firebase.firestore.core.UserData.Source.Update     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$UserData$Source     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.firestore.core.UserData$Source r1 = com.google.firebase.firestore.core.UserData.Source.Argument     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$UserData$Source     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firebase.firestore.core.UserData$Source r1 = com.google.firebase.firestore.core.UserData.Source.ArrayArgument     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.UserData.C18871.<clinit>():void");
        }
    }

    public static class ParsedSetData {
        private final ObjectValue data;
        private final FieldMask fieldMask;
        private final List<FieldTransform> fieldTransforms;

        ParsedSetData(ObjectValue objectValue, FieldMask fieldMask2, List<FieldTransform> list) {
            this.data = objectValue;
            this.fieldMask = fieldMask2;
            this.fieldTransforms = list;
        }

        public List<Mutation> toMutationList(DocumentKey documentKey, Precondition precondition) {
            ArrayList arrayList = new ArrayList();
            FieldMask fieldMask2 = this.fieldMask;
            if (fieldMask2 != null) {
                arrayList.add(new PatchMutation(documentKey, this.data, fieldMask2, precondition));
            } else {
                arrayList.add(new SetMutation(documentKey, this.data, precondition));
            }
            if (!this.fieldTransforms.isEmpty()) {
                arrayList.add(new TransformMutation(documentKey, this.fieldTransforms));
            }
            return arrayList;
        }
    }

    public static class ParsedUpdateData {
        private final ObjectValue data;
        private final FieldMask fieldMask;
        private final List<FieldTransform> fieldTransforms;

        ParsedUpdateData(ObjectValue objectValue, FieldMask fieldMask2, List<FieldTransform> list) {
            this.data = objectValue;
            this.fieldMask = fieldMask2;
            this.fieldTransforms = list;
        }

        public List<FieldTransform> getFieldTransforms() {
            return this.fieldTransforms;
        }

        public List<Mutation> toMutationList(DocumentKey documentKey, Precondition precondition) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PatchMutation(documentKey, this.data, this.fieldMask, precondition));
            if (!this.fieldTransforms.isEmpty()) {
                arrayList.add(new TransformMutation(documentKey, this.fieldTransforms));
            }
            return arrayList;
        }
    }
}

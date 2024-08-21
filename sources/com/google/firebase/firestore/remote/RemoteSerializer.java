package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MaybeDocument;
import com.google.firebase.firestore.model.NoDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.model.mutation.ArrayTransformOperation;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.FieldTransform;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.model.mutation.NumericIncrementTransformOperation;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.model.mutation.ServerTimestampOperation;
import com.google.firebase.firestore.model.mutation.SetMutation;
import com.google.firebase.firestore.model.mutation.TransformMutation;
import com.google.firebase.firestore.model.mutation.TransformOperation;
import com.google.firebase.firestore.model.mutation.VerifyMutation;
import com.google.firebase.firestore.remote.WatchChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p009v1.ArrayValue;
import com.google.firestore.p009v1.BatchGetDocumentsResponse;
import com.google.firestore.p009v1.Cursor;
import com.google.firestore.p009v1.Document;
import com.google.firestore.p009v1.DocumentChange;
import com.google.firestore.p009v1.DocumentDelete;
import com.google.firestore.p009v1.DocumentMask;
import com.google.firestore.p009v1.DocumentRemove;
import com.google.firestore.p009v1.DocumentTransform;
import com.google.firestore.p009v1.ExistenceFilter;
import com.google.firestore.p009v1.ListenResponse;
import com.google.firestore.p009v1.Precondition;
import com.google.firestore.p009v1.StructuredQuery;
import com.google.firestore.p009v1.Target;
import com.google.firestore.p009v1.TargetChange;
import com.google.firestore.p009v1.Write;
import com.google.firestore.p009v1.WriteResult;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import p012io.grpc.Status;

public final class RemoteSerializer {
    private final DatabaseId databaseId;
    private final String databaseName;

    public RemoteSerializer(DatabaseId databaseId2) {
        this.databaseId = databaseId2;
        this.databaseName = encodedDatabaseId(databaseId2).canonicalString();
    }

    public Timestamp encodeTimestamp(com.google.firebase.Timestamp timestamp) {
        Timestamp.Builder newBuilder = Timestamp.newBuilder();
        newBuilder.setSeconds(timestamp.getSeconds());
        newBuilder.setNanos(timestamp.getNanoseconds());
        return (Timestamp) newBuilder.build();
    }

    public com.google.firebase.Timestamp decodeTimestamp(Timestamp timestamp) {
        return new com.google.firebase.Timestamp(timestamp.getSeconds(), timestamp.getNanos());
    }

    public Timestamp encodeVersion(SnapshotVersion snapshotVersion) {
        return encodeTimestamp(snapshotVersion.getTimestamp());
    }

    public SnapshotVersion decodeVersion(Timestamp timestamp) {
        if (timestamp.getSeconds() == 0 && timestamp.getNanos() == 0) {
            return SnapshotVersion.NONE;
        }
        return new SnapshotVersion(decodeTimestamp(timestamp));
    }

    public String encodeKey(DocumentKey documentKey) {
        return encodeResourceName(this.databaseId, documentKey.getPath());
    }

    public DocumentKey decodeKey(String str) {
        ResourcePath decodeResourceName = decodeResourceName(str);
        Assert.hardAssert(decodeResourceName.getSegment(1).equals(this.databaseId.getProjectId()), "Tried to deserialize key from different project.", new Object[0]);
        Assert.hardAssert(decodeResourceName.getSegment(3).equals(this.databaseId.getDatabaseId()), "Tried to deserialize key from different database.", new Object[0]);
        return DocumentKey.fromPath(extractLocalPathFromResourceName(decodeResourceName));
    }

    private String encodeQueryPath(ResourcePath resourcePath) {
        return encodeResourceName(this.databaseId, resourcePath);
    }

    private ResourcePath decodeQueryPath(String str) {
        ResourcePath decodeResourceName = decodeResourceName(str);
        if (decodeResourceName.length() == 4) {
            return ResourcePath.EMPTY;
        }
        return extractLocalPathFromResourceName(decodeResourceName);
    }

    private String encodeResourceName(DatabaseId databaseId2, ResourcePath resourcePath) {
        return ((ResourcePath) ((ResourcePath) encodedDatabaseId(databaseId2).append("documents")).append(resourcePath)).canonicalString();
    }

    private ResourcePath decodeResourceName(String str) {
        ResourcePath fromString = ResourcePath.fromString(str);
        Assert.hardAssert(isValidResourceName(fromString), "Tried to deserialize invalid key %s", fromString);
        return fromString;
    }

    private static ResourcePath encodedDatabaseId(DatabaseId databaseId2) {
        return ResourcePath.fromSegments(Arrays.asList(new String[]{"projects", databaseId2.getProjectId(), "databases", databaseId2.getDatabaseId()}));
    }

    private static ResourcePath extractLocalPathFromResourceName(ResourcePath resourcePath) {
        Assert.hardAssert(resourcePath.length() > 4 && resourcePath.getSegment(4).equals("documents"), "Tried to deserialize invalid key %s", resourcePath);
        return (ResourcePath) resourcePath.popFirst(5);
    }

    private static boolean isValidResourceName(ResourcePath resourcePath) {
        if (resourcePath.length() < 4 || !resourcePath.getSegment(0).equals("projects") || !resourcePath.getSegment(2).equals("databases")) {
            return false;
        }
        return true;
    }

    public String databaseName() {
        return this.databaseName;
    }

    public Document encodeDocument(DocumentKey documentKey, ObjectValue objectValue) {
        Document.Builder newBuilder = Document.newBuilder();
        newBuilder.setName(encodeKey(documentKey));
        newBuilder.putAllFields(objectValue.getFieldsMap());
        return (Document) newBuilder.build();
    }

    public MaybeDocument decodeMaybeDocument(BatchGetDocumentsResponse batchGetDocumentsResponse) {
        if (batchGetDocumentsResponse.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.FOUND)) {
            return decodeFoundDocument(batchGetDocumentsResponse);
        }
        if (batchGetDocumentsResponse.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.MISSING)) {
            return decodeMissingDocument(batchGetDocumentsResponse);
        }
        throw new IllegalArgumentException("Unknown result case: " + batchGetDocumentsResponse.getResultCase());
    }

    private com.google.firebase.firestore.model.Document decodeFoundDocument(BatchGetDocumentsResponse batchGetDocumentsResponse) {
        Assert.hardAssert(batchGetDocumentsResponse.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.FOUND), "Tried to deserialize a found document from a missing document.", new Object[0]);
        DocumentKey decodeKey = decodeKey(batchGetDocumentsResponse.getFound().getName());
        ObjectValue fromMap = ObjectValue.fromMap(batchGetDocumentsResponse.getFound().getFieldsMap());
        SnapshotVersion decodeVersion = decodeVersion(batchGetDocumentsResponse.getFound().getUpdateTime());
        Assert.hardAssert(!decodeVersion.equals(SnapshotVersion.NONE), "Got a document response with no snapshot version", new Object[0]);
        return new com.google.firebase.firestore.model.Document(decodeKey, decodeVersion, fromMap, Document.DocumentState.SYNCED);
    }

    private NoDocument decodeMissingDocument(BatchGetDocumentsResponse batchGetDocumentsResponse) {
        Assert.hardAssert(batchGetDocumentsResponse.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.MISSING), "Tried to deserialize a missing document from a found document.", new Object[0]);
        DocumentKey decodeKey = decodeKey(batchGetDocumentsResponse.getMissing());
        SnapshotVersion decodeVersion = decodeVersion(batchGetDocumentsResponse.getReadTime());
        Assert.hardAssert(!decodeVersion.equals(SnapshotVersion.NONE), "Got a no document response with no snapshot version", new Object[0]);
        return new NoDocument(decodeKey, decodeVersion, false);
    }

    public Write encodeMutation(Mutation mutation) {
        Write.Builder newBuilder = Write.newBuilder();
        if (mutation instanceof SetMutation) {
            newBuilder.setUpdate(encodeDocument(mutation.getKey(), ((SetMutation) mutation).getValue()));
        } else if (mutation instanceof PatchMutation) {
            PatchMutation patchMutation = (PatchMutation) mutation;
            newBuilder.setUpdate(encodeDocument(mutation.getKey(), patchMutation.getValue()));
            newBuilder.setUpdateMask(encodeDocumentMask(patchMutation.getMask()));
        } else if (mutation instanceof TransformMutation) {
            TransformMutation transformMutation = (TransformMutation) mutation;
            DocumentTransform.Builder newBuilder2 = DocumentTransform.newBuilder();
            newBuilder2.setDocument(encodeKey(transformMutation.getKey()));
            for (FieldTransform encodeFieldTransform : transformMutation.getFieldTransforms()) {
                newBuilder2.addFieldTransforms(encodeFieldTransform(encodeFieldTransform));
            }
            newBuilder.setTransform(newBuilder2);
        } else if (mutation instanceof DeleteMutation) {
            newBuilder.setDelete(encodeKey(mutation.getKey()));
        } else if (mutation instanceof VerifyMutation) {
            newBuilder.setVerify(encodeKey(mutation.getKey()));
        } else {
            throw Assert.fail("unknown mutation type %s", mutation.getClass());
        }
        if (!mutation.getPrecondition().isNone()) {
            newBuilder.setCurrentDocument(encodePrecondition(mutation.getPrecondition()));
        }
        return (Write) newBuilder.build();
    }

    public Mutation decodeMutation(Write write) {
        Precondition precondition;
        if (write.hasCurrentDocument()) {
            precondition = decodePrecondition(write.getCurrentDocument());
        } else {
            precondition = Precondition.NONE;
        }
        int i = C19121.$SwitchMap$com$google$firestore$v1$Write$OperationCase[write.getOperationCase().ordinal()];
        boolean z = true;
        if (i != 1) {
            if (i == 2) {
                return new DeleteMutation(decodeKey(write.getDelete()), precondition);
            }
            if (i == 3) {
                ArrayList arrayList = new ArrayList();
                for (DocumentTransform.FieldTransform decodeFieldTransform : write.getTransform().getFieldTransformsList()) {
                    arrayList.add(decodeFieldTransform(decodeFieldTransform));
                }
                Boolean exists = precondition.getExists();
                if (exists == null || !exists.booleanValue()) {
                    z = false;
                }
                Assert.hardAssert(z, "Transforms only support precondition \"exists == true\"", new Object[0]);
                return new TransformMutation(decodeKey(write.getTransform().getDocument()), arrayList);
            } else if (i == 4) {
                return new VerifyMutation(decodeKey(write.getVerify()), precondition);
            } else {
                throw Assert.fail("Unknown mutation operation: %d", write.getOperationCase());
            }
        } else if (write.hasUpdateMask()) {
            return new PatchMutation(decodeKey(write.getUpdate().getName()), ObjectValue.fromMap(write.getUpdate().getFieldsMap()), decodeDocumentMask(write.getUpdateMask()), precondition);
        } else {
            return new SetMutation(decodeKey(write.getUpdate().getName()), ObjectValue.fromMap(write.getUpdate().getFieldsMap()), precondition);
        }
    }

    private com.google.firestore.p009v1.Precondition encodePrecondition(Precondition precondition) {
        Assert.hardAssert(!precondition.isNone(), "Can't serialize an empty precondition", new Object[0]);
        Precondition.Builder newBuilder = com.google.firestore.p009v1.Precondition.newBuilder();
        if (precondition.getUpdateTime() != null) {
            return (com.google.firestore.p009v1.Precondition) newBuilder.setUpdateTime(encodeVersion(precondition.getUpdateTime())).build();
        }
        if (precondition.getExists() != null) {
            return (com.google.firestore.p009v1.Precondition) newBuilder.setExists(precondition.getExists().booleanValue()).build();
        }
        throw Assert.fail("Unknown Precondition", new Object[0]);
    }

    private com.google.firebase.firestore.model.mutation.Precondition decodePrecondition(com.google.firestore.p009v1.Precondition precondition) {
        int i = C19121.f352x8f18ca41[precondition.getConditionTypeCase().ordinal()];
        if (i == 1) {
            return com.google.firebase.firestore.model.mutation.Precondition.updateTime(decodeVersion(precondition.getUpdateTime()));
        }
        if (i == 2) {
            return com.google.firebase.firestore.model.mutation.Precondition.exists(precondition.getExists());
        }
        if (i == 3) {
            return com.google.firebase.firestore.model.mutation.Precondition.NONE;
        }
        throw Assert.fail("Unknown precondition", new Object[0]);
    }

    private DocumentMask encodeDocumentMask(FieldMask fieldMask) {
        DocumentMask.Builder newBuilder = DocumentMask.newBuilder();
        for (FieldPath canonicalString : fieldMask.getMask()) {
            newBuilder.addFieldPaths(canonicalString.canonicalString());
        }
        return (DocumentMask) newBuilder.build();
    }

    private FieldMask decodeDocumentMask(DocumentMask documentMask) {
        int fieldPathsCount = documentMask.getFieldPathsCount();
        HashSet hashSet = new HashSet(fieldPathsCount);
        for (int i = 0; i < fieldPathsCount; i++) {
            hashSet.add(FieldPath.fromServerFormat(documentMask.getFieldPaths(i)));
        }
        return FieldMask.fromSet(hashSet);
    }

    private DocumentTransform.FieldTransform encodeFieldTransform(FieldTransform fieldTransform) {
        TransformOperation operation = fieldTransform.getOperation();
        if (operation instanceof ServerTimestampOperation) {
            return (DocumentTransform.FieldTransform) DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setSetToServerValue(DocumentTransform.FieldTransform.ServerValue.REQUEST_TIME).build();
        }
        if (operation instanceof ArrayTransformOperation.Union) {
            return (DocumentTransform.FieldTransform) DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setAppendMissingElements(ArrayValue.newBuilder().addAllValues(((ArrayTransformOperation.Union) operation).getElements())).build();
        }
        if (operation instanceof ArrayTransformOperation.Remove) {
            return (DocumentTransform.FieldTransform) DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setRemoveAllFromArray(ArrayValue.newBuilder().addAllValues(((ArrayTransformOperation.Remove) operation).getElements())).build();
        }
        if (operation instanceof NumericIncrementTransformOperation) {
            return (DocumentTransform.FieldTransform) DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setIncrement(((NumericIncrementTransformOperation) operation).getOperand()).build();
        }
        throw Assert.fail("Unknown transform: %s", operation);
    }

    private FieldTransform decodeFieldTransform(DocumentTransform.FieldTransform fieldTransform) {
        int i = C19121.f350xdd498c9f[fieldTransform.getTransformTypeCase().ordinal()];
        if (i == 1) {
            Assert.hardAssert(fieldTransform.getSetToServerValue() == DocumentTransform.FieldTransform.ServerValue.REQUEST_TIME, "Unknown transform setToServerValue: %s", fieldTransform.getSetToServerValue());
            return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), ServerTimestampOperation.getInstance());
        } else if (i == 2) {
            return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new ArrayTransformOperation.Union(fieldTransform.getAppendMissingElements().getValuesList()));
        } else {
            if (i == 3) {
                return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new ArrayTransformOperation.Remove(fieldTransform.getRemoveAllFromArray().getValuesList()));
            }
            if (i == 4) {
                return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new NumericIncrementTransformOperation(fieldTransform.getIncrement()));
            }
            throw Assert.fail("Unknown FieldTransform proto: %s", fieldTransform);
        }
    }

    public MutationResult decodeMutationResult(WriteResult writeResult, SnapshotVersion snapshotVersion) {
        SnapshotVersion decodeVersion = decodeVersion(writeResult.getUpdateTime());
        if (!SnapshotVersion.NONE.equals(decodeVersion)) {
            snapshotVersion = decodeVersion;
        }
        ArrayList arrayList = null;
        int transformResultsCount = writeResult.getTransformResultsCount();
        if (transformResultsCount > 0) {
            arrayList = new ArrayList(transformResultsCount);
            for (int i = 0; i < transformResultsCount; i++) {
                arrayList.add(writeResult.getTransformResults(i));
            }
        }
        return new MutationResult(snapshotVersion, arrayList);
    }

    public Map<String, String> encodeListenRequestLabels(TargetData targetData) {
        String encodeLabel = encodeLabel(targetData.getPurpose());
        if (encodeLabel == null) {
            return null;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("goog-listen-tags", encodeLabel);
        return hashMap;
    }

    private String encodeLabel(QueryPurpose queryPurpose) {
        int i = C19121.$SwitchMap$com$google$firebase$firestore$local$QueryPurpose[queryPurpose.ordinal()];
        if (i == 1) {
            return null;
        }
        if (i == 2) {
            return "existence-filter-mismatch";
        }
        if (i == 3) {
            return "limbo-document";
        }
        throw Assert.fail("Unrecognized query purpose: %s", queryPurpose);
    }

    public Target encodeTarget(TargetData targetData) {
        Target.Builder newBuilder = Target.newBuilder();
        com.google.firebase.firestore.core.Target target = targetData.getTarget();
        if (target.isDocumentQuery()) {
            newBuilder.setDocuments(encodeDocumentsTarget(target));
        } else {
            newBuilder.setQuery(encodeQueryTarget(target));
        }
        newBuilder.setTargetId(targetData.getTargetId());
        newBuilder.setResumeToken(targetData.getResumeToken());
        return (Target) newBuilder.build();
    }

    public Target.DocumentsTarget encodeDocumentsTarget(com.google.firebase.firestore.core.Target target) {
        Target.DocumentsTarget.Builder newBuilder = Target.DocumentsTarget.newBuilder();
        newBuilder.addDocuments(encodeQueryPath(target.getPath()));
        return (Target.DocumentsTarget) newBuilder.build();
    }

    public com.google.firebase.firestore.core.Target decodeDocumentsTarget(Target.DocumentsTarget documentsTarget) {
        int documentsCount = documentsTarget.getDocumentsCount();
        Assert.hardAssert(documentsCount == 1, "DocumentsTarget contained other than 1 document %d", Integer.valueOf(documentsCount));
        return Query.atPath(decodeQueryPath(documentsTarget.getDocuments(0))).toTarget();
    }

    public Target.QueryTarget encodeQueryTarget(com.google.firebase.firestore.core.Target target) {
        Target.QueryTarget.Builder newBuilder = Target.QueryTarget.newBuilder();
        StructuredQuery.Builder newBuilder2 = StructuredQuery.newBuilder();
        ResourcePath path = target.getPath();
        boolean z = true;
        if (target.getCollectionGroup() != null) {
            Assert.hardAssert(path.length() % 2 == 0, "Collection Group queries should be within a document path or root.", new Object[0]);
            newBuilder.setParent(encodeQueryPath(path));
            StructuredQuery.CollectionSelector.Builder newBuilder3 = StructuredQuery.CollectionSelector.newBuilder();
            newBuilder3.setCollectionId(target.getCollectionGroup());
            newBuilder3.setAllDescendants(true);
            newBuilder2.addFrom(newBuilder3);
        } else {
            if (path.length() % 2 == 0) {
                z = false;
            }
            Assert.hardAssert(z, "Document queries with filters are not supported.", new Object[0]);
            newBuilder.setParent(encodeQueryPath((ResourcePath) path.popLast()));
            StructuredQuery.CollectionSelector.Builder newBuilder4 = StructuredQuery.CollectionSelector.newBuilder();
            newBuilder4.setCollectionId(path.getLastSegment());
            newBuilder2.addFrom(newBuilder4);
        }
        if (target.getFilters().size() > 0) {
            newBuilder2.setWhere(encodeFilters(target.getFilters()));
        }
        for (OrderBy encodeOrderBy : target.getOrderBy()) {
            newBuilder2.addOrderBy(encodeOrderBy(encodeOrderBy));
        }
        if (target.hasLimit()) {
            newBuilder2.setLimit(Int32Value.newBuilder().setValue((int) target.getLimit()));
        }
        if (target.getStartAt() != null) {
            newBuilder2.setStartAt(encodeBound(target.getStartAt()));
        }
        if (target.getEndAt() != null) {
            newBuilder2.setEndAt(encodeBound(target.getEndAt()));
        }
        newBuilder.setStructuredQuery(newBuilder2);
        return (Target.QueryTarget) newBuilder.build();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.firestore.core.Target decodeQueryTarget(com.google.firestore.p009v1.Target.QueryTarget r15) {
        /*
            r14 = this;
            java.lang.String r0 = r15.getParent()
            com.google.firebase.firestore.model.ResourcePath r0 = r14.decodeQueryPath(r0)
            com.google.firestore.v1.StructuredQuery r15 = r15.getStructuredQuery()
            int r1 = r15.getFromCount()
            r2 = 0
            r3 = 0
            if (r1 <= 0) goto L_0x003b
            r4 = 1
            if (r1 != r4) goto L_0x0018
            goto L_0x0019
        L_0x0018:
            r4 = 0
        L_0x0019:
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.String r5 = "StructuredQuery.from with more than one collection is not supported."
            com.google.firebase.firestore.util.Assert.hardAssert(r4, r5, r1)
            com.google.firestore.v1.StructuredQuery$CollectionSelector r1 = r15.getFrom(r3)
            boolean r4 = r1.getAllDescendants()
            if (r4 == 0) goto L_0x0031
            java.lang.String r1 = r1.getCollectionId()
            r5 = r0
            r6 = r1
            goto L_0x003d
        L_0x0031:
            java.lang.String r1 = r1.getCollectionId()
            com.google.firebase.firestore.model.BasePath r0 = r0.append((java.lang.String) r1)
            com.google.firebase.firestore.model.ResourcePath r0 = (com.google.firebase.firestore.model.ResourcePath) r0
        L_0x003b:
            r5 = r0
            r6 = r2
        L_0x003d:
            boolean r0 = r15.hasWhere()
            if (r0 == 0) goto L_0x004c
            com.google.firestore.v1.StructuredQuery$Filter r0 = r15.getWhere()
            java.util.List r0 = r14.decodeFilters(r0)
            goto L_0x0050
        L_0x004c:
            java.util.List r0 = java.util.Collections.emptyList()
        L_0x0050:
            r7 = r0
            int r0 = r15.getOrderByCount()
            if (r0 <= 0) goto L_0x006e
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r0)
        L_0x005c:
            if (r3 >= r0) goto L_0x006c
            com.google.firestore.v1.StructuredQuery$Order r4 = r15.getOrderBy(r3)
            com.google.firebase.firestore.core.OrderBy r4 = r14.decodeOrderBy(r4)
            r1.add(r4)
            int r3 = r3 + 1
            goto L_0x005c
        L_0x006c:
            r8 = r1
            goto L_0x0073
        L_0x006e:
            java.util.List r0 = java.util.Collections.emptyList()
            r8 = r0
        L_0x0073:
            r0 = -1
            boolean r3 = r15.hasLimit()
            if (r3 == 0) goto L_0x0084
            com.google.protobuf.Int32Value r0 = r15.getLimit()
            int r0 = r0.getValue()
            long r0 = (long) r0
        L_0x0084:
            r9 = r0
            boolean r0 = r15.hasStartAt()
            if (r0 == 0) goto L_0x0095
            com.google.firestore.v1.Cursor r0 = r15.getStartAt()
            com.google.firebase.firestore.core.Bound r0 = r14.decodeBound(r0)
            r12 = r0
            goto L_0x0096
        L_0x0095:
            r12 = r2
        L_0x0096:
            boolean r0 = r15.hasEndAt()
            if (r0 == 0) goto L_0x00a4
            com.google.firestore.v1.Cursor r15 = r15.getEndAt()
            com.google.firebase.firestore.core.Bound r2 = r14.decodeBound(r15)
        L_0x00a4:
            r13 = r2
            com.google.firebase.firestore.core.Query r15 = new com.google.firebase.firestore.core.Query
            com.google.firebase.firestore.core.Query$LimitType r11 = com.google.firebase.firestore.core.Query.LimitType.LIMIT_TO_FIRST
            r4 = r15
            r4.<init>(r5, r6, r7, r8, r9, r11, r12, r13)
            com.google.firebase.firestore.core.Target r15 = r15.toTarget()
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.remote.RemoteSerializer.decodeQueryTarget(com.google.firestore.v1.Target$QueryTarget):com.google.firebase.firestore.core.Target");
    }

    private StructuredQuery.Filter encodeFilters(List<Filter> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Filter next : list) {
            if (next instanceof FieldFilter) {
                arrayList.add(encodeUnaryOrFieldFilter((FieldFilter) next));
            }
        }
        if (list.size() == 1) {
            return (StructuredQuery.Filter) arrayList.get(0);
        }
        StructuredQuery.CompositeFilter.Builder newBuilder = StructuredQuery.CompositeFilter.newBuilder();
        newBuilder.setOp(StructuredQuery.CompositeFilter.Operator.AND);
        newBuilder.addAllFilters(arrayList);
        return (StructuredQuery.Filter) StructuredQuery.Filter.newBuilder().setCompositeFilter(newBuilder).build();
    }

    private List<Filter> decodeFilters(StructuredQuery.Filter filter) {
        List<StructuredQuery.Filter> list;
        if (filter.getFilterTypeCase() == StructuredQuery.Filter.FilterTypeCase.COMPOSITE_FILTER) {
            Assert.hardAssert(filter.getCompositeFilter().getOp() == StructuredQuery.CompositeFilter.Operator.AND, "Only AND-type composite filters are supported, got %d", filter.getCompositeFilter().getOp());
            list = filter.getCompositeFilter().getFiltersList();
        } else {
            list = Collections.singletonList(filter);
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (StructuredQuery.Filter next : list) {
            int i = C19121.f354x9d2ee979[next.getFilterTypeCase().ordinal()];
            if (i == 1) {
                throw Assert.fail("Nested composite filters are not supported.", new Object[0]);
            } else if (i == 2) {
                arrayList.add(decodeFieldFilter(next.getFieldFilter()));
            } else if (i == 3) {
                arrayList.add(decodeUnaryFilter(next.getUnaryFilter()));
            } else {
                throw Assert.fail("Unrecognized Filter.filterType %d", next.getFilterTypeCase());
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public StructuredQuery.Filter encodeUnaryOrFieldFilter(FieldFilter fieldFilter) {
        StructuredQuery.UnaryFilter.Operator operator;
        StructuredQuery.UnaryFilter.Operator operator2;
        if (fieldFilter.getOperator() == Filter.Operator.EQUAL || fieldFilter.getOperator() == Filter.Operator.NOT_EQUAL) {
            StructuredQuery.UnaryFilter.Builder newBuilder = StructuredQuery.UnaryFilter.newBuilder();
            newBuilder.setField(encodeFieldPath(fieldFilter.getField()));
            if (Values.isNanValue(fieldFilter.getValue())) {
                if (fieldFilter.getOperator() == Filter.Operator.EQUAL) {
                    operator2 = StructuredQuery.UnaryFilter.Operator.IS_NAN;
                } else {
                    operator2 = StructuredQuery.UnaryFilter.Operator.IS_NOT_NAN;
                }
                newBuilder.setOp(operator2);
                return (StructuredQuery.Filter) StructuredQuery.Filter.newBuilder().setUnaryFilter(newBuilder).build();
            } else if (Values.isNullValue(fieldFilter.getValue())) {
                if (fieldFilter.getOperator() == Filter.Operator.EQUAL) {
                    operator = StructuredQuery.UnaryFilter.Operator.IS_NULL;
                } else {
                    operator = StructuredQuery.UnaryFilter.Operator.IS_NOT_NULL;
                }
                newBuilder.setOp(operator);
                return (StructuredQuery.Filter) StructuredQuery.Filter.newBuilder().setUnaryFilter(newBuilder).build();
            }
        }
        StructuredQuery.FieldFilter.Builder newBuilder2 = StructuredQuery.FieldFilter.newBuilder();
        newBuilder2.setField(encodeFieldPath(fieldFilter.getField()));
        newBuilder2.setOp(encodeFieldFilterOperator(fieldFilter.getOperator()));
        newBuilder2.setValue(fieldFilter.getValue());
        return (StructuredQuery.Filter) StructuredQuery.Filter.newBuilder().setFieldFilter(newBuilder2).build();
    }

    /* access modifiers changed from: package-private */
    public FieldFilter decodeFieldFilter(StructuredQuery.FieldFilter fieldFilter) {
        return FieldFilter.create(FieldPath.fromServerFormat(fieldFilter.getField().getFieldPath()), decodeFieldFilterOperator(fieldFilter.getOp()), fieldFilter.getValue());
    }

    private Filter decodeUnaryFilter(StructuredQuery.UnaryFilter unaryFilter) {
        FieldPath fromServerFormat = FieldPath.fromServerFormat(unaryFilter.getField().getFieldPath());
        int i = C19121.f355xf473b456[unaryFilter.getOp().ordinal()];
        if (i == 1) {
            return FieldFilter.create(fromServerFormat, Filter.Operator.EQUAL, Values.NAN_VALUE);
        }
        if (i == 2) {
            return FieldFilter.create(fromServerFormat, Filter.Operator.EQUAL, Values.NULL_VALUE);
        }
        if (i == 3) {
            return FieldFilter.create(fromServerFormat, Filter.Operator.NOT_EQUAL, Values.NAN_VALUE);
        }
        if (i == 4) {
            return FieldFilter.create(fromServerFormat, Filter.Operator.NOT_EQUAL, Values.NULL_VALUE);
        }
        throw Assert.fail("Unrecognized UnaryFilter.operator %d", unaryFilter.getOp());
    }

    private StructuredQuery.FieldReference encodeFieldPath(FieldPath fieldPath) {
        return (StructuredQuery.FieldReference) StructuredQuery.FieldReference.newBuilder().setFieldPath(fieldPath.canonicalString()).build();
    }

    private StructuredQuery.FieldFilter.Operator encodeFieldFilterOperator(Filter.Operator operator) {
        switch (C19121.$SwitchMap$com$google$firebase$firestore$core$Filter$Operator[operator.ordinal()]) {
            case 1:
                return StructuredQuery.FieldFilter.Operator.LESS_THAN;
            case 2:
                return StructuredQuery.FieldFilter.Operator.LESS_THAN_OR_EQUAL;
            case 3:
                return StructuredQuery.FieldFilter.Operator.EQUAL;
            case 4:
                return StructuredQuery.FieldFilter.Operator.NOT_EQUAL;
            case 5:
                return StructuredQuery.FieldFilter.Operator.GREATER_THAN;
            case 6:
                return StructuredQuery.FieldFilter.Operator.GREATER_THAN_OR_EQUAL;
            case 7:
                return StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS;
            case 8:
                return StructuredQuery.FieldFilter.Operator.IN;
            case 9:
                return StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS_ANY;
            case 10:
                return StructuredQuery.FieldFilter.Operator.NOT_IN;
            default:
                throw Assert.fail("Unknown operator %d", operator);
        }
    }

    private Filter.Operator decodeFieldFilterOperator(StructuredQuery.FieldFilter.Operator operator) {
        switch (C19121.f353xaf95d2b[operator.ordinal()]) {
            case 1:
                return Filter.Operator.LESS_THAN;
            case 2:
                return Filter.Operator.LESS_THAN_OR_EQUAL;
            case 3:
                return Filter.Operator.EQUAL;
            case 4:
                return Filter.Operator.NOT_EQUAL;
            case 5:
                return Filter.Operator.GREATER_THAN_OR_EQUAL;
            case 6:
                return Filter.Operator.GREATER_THAN;
            case 7:
                return Filter.Operator.ARRAY_CONTAINS;
            case 8:
                return Filter.Operator.IN;
            case 9:
                return Filter.Operator.ARRAY_CONTAINS_ANY;
            case 10:
                return Filter.Operator.NOT_IN;
            default:
                throw Assert.fail("Unhandled FieldFilter.operator %d", operator);
        }
    }

    private StructuredQuery.Order encodeOrderBy(OrderBy orderBy) {
        StructuredQuery.Order.Builder newBuilder = StructuredQuery.Order.newBuilder();
        if (orderBy.getDirection().equals(OrderBy.Direction.ASCENDING)) {
            newBuilder.setDirection(StructuredQuery.Direction.ASCENDING);
        } else {
            newBuilder.setDirection(StructuredQuery.Direction.DESCENDING);
        }
        newBuilder.setField(encodeFieldPath(orderBy.getField()));
        return (StructuredQuery.Order) newBuilder.build();
    }

    private OrderBy decodeOrderBy(StructuredQuery.Order order) {
        OrderBy.Direction direction;
        FieldPath fromServerFormat = FieldPath.fromServerFormat(order.getField().getFieldPath());
        int i = C19121.$SwitchMap$com$google$firestore$v1$StructuredQuery$Direction[order.getDirection().ordinal()];
        if (i == 1) {
            direction = OrderBy.Direction.ASCENDING;
        } else if (i == 2) {
            direction = OrderBy.Direction.DESCENDING;
        } else {
            throw Assert.fail("Unrecognized direction %d", order.getDirection());
        }
        return OrderBy.getInstance(direction, fromServerFormat);
    }

    private Cursor encodeBound(Bound bound) {
        Cursor.Builder newBuilder = Cursor.newBuilder();
        newBuilder.addAllValues(bound.getPosition());
        newBuilder.setBefore(bound.isBefore());
        return (Cursor) newBuilder.build();
    }

    private Bound decodeBound(Cursor cursor) {
        return new Bound(cursor.getValuesList(), cursor.getBefore());
    }

    /* renamed from: com.google.firebase.firestore.remote.RemoteSerializer$1 */
    static /* synthetic */ class C19121 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$Filter$Operator;
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$local$QueryPurpose;

        /* renamed from: $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase */
        static final /* synthetic */ int[] f350xdd498c9f;

        /* renamed from: $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase */
        static final /* synthetic */ int[] f351x1837d9f;

        /* renamed from: $SwitchMap$com$google$firestore$v1$Precondition$ConditionTypeCase */
        static final /* synthetic */ int[] f352x8f18ca41;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction;

        /* renamed from: $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator */
        static final /* synthetic */ int[] f353xaf95d2b;

        /* renamed from: $SwitchMap$com$google$firestore$v1$StructuredQuery$Filter$FilterTypeCase */
        static final /* synthetic */ int[] f354x9d2ee979;

        /* renamed from: $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator */
        static final /* synthetic */ int[] f355xf473b456;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$Write$OperationCase;

        /* JADX WARNING: Can't wrap try/catch for region: R(113:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|(2:41|42)|43|45|46|47|48|49|50|51|52|53|54|(2:55|56)|57|(2:59|60)|61|(2:63|64)|65|(2:67|68)|69|(2:71|72)|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|107|108|109|110|(2:111|112)|113|115|116|117|118|(2:119|120)|121|123|124|125|126|127|128|129|130|131|133|134|135|136|(2:137|138)|139|141|142|143|144|145|146|(3:147|148|150)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(114:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|(2:41|42)|43|45|46|47|48|49|50|51|52|53|54|(2:55|56)|57|(2:59|60)|61|(2:63|64)|65|(2:67|68)|69|(2:71|72)|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|(2:111|112)|113|115|116|117|118|(2:119|120)|121|123|124|125|126|127|128|129|130|131|133|134|135|136|(2:137|138)|139|141|142|143|144|145|146|(3:147|148|150)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(118:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|(2:41|42)|43|45|46|47|48|49|50|51|52|53|54|(2:55|56)|57|(2:59|60)|61|63|64|65|(2:67|68)|69|(2:71|72)|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|(2:111|112)|113|115|116|117|118|(2:119|120)|121|123|124|125|126|127|128|129|130|131|133|134|135|136|(2:137|138)|139|141|142|143|144|145|146|147|148|150) */
        /* JADX WARNING: Can't wrap try/catch for region: R(119:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|(2:41|42)|43|45|46|47|48|49|50|51|52|53|54|(2:55|56)|57|(2:59|60)|61|63|64|65|(2:67|68)|69|(2:71|72)|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|(2:111|112)|113|115|116|117|118|119|120|121|123|124|125|126|127|128|129|130|131|133|134|135|136|(2:137|138)|139|141|142|143|144|145|146|147|148|150) */
        /* JADX WARNING: Can't wrap try/catch for region: R(120:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|(2:41|42)|43|45|46|47|48|49|50|51|52|53|54|(2:55|56)|57|59|60|61|63|64|65|(2:67|68)|69|(2:71|72)|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|(2:111|112)|113|115|116|117|118|119|120|121|123|124|125|126|127|128|129|130|131|133|134|135|136|(2:137|138)|139|141|142|143|144|145|146|147|148|150) */
        /* JADX WARNING: Can't wrap try/catch for region: R(121:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|(2:41|42)|43|45|46|47|48|49|50|51|52|53|54|(2:55|56)|57|59|60|61|63|64|65|(2:67|68)|69|(2:71|72)|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|(2:111|112)|113|115|116|117|118|119|120|121|123|124|125|126|127|128|129|130|131|133|134|135|136|(2:137|138)|139|141|142|143|144|145|146|147|148|150) */
        /* JADX WARNING: Can't wrap try/catch for region: R(122:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|(2:41|42)|43|45|46|47|48|49|50|51|52|53|54|55|56|57|59|60|61|63|64|65|(2:67|68)|69|(2:71|72)|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|(2:111|112)|113|115|116|117|118|119|120|121|123|124|125|126|127|128|129|130|131|133|134|135|136|(2:137|138)|139|141|142|143|144|145|146|147|148|150) */
        /* JADX WARNING: Can't wrap try/catch for region: R(124:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|41|42|43|45|46|47|48|49|50|51|52|53|54|55|56|57|59|60|61|63|64|65|(2:67|68)|69|(2:71|72)|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|(2:111|112)|113|115|116|117|118|119|120|121|123|124|125|126|127|128|129|130|131|133|134|135|136|(2:137|138)|139|141|142|143|144|145|146|147|148|150) */
        /* JADX WARNING: Can't wrap try/catch for region: R(125:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|41|42|43|45|46|47|48|49|50|51|52|53|54|55|56|57|59|60|61|63|64|65|(2:67|68)|69|(2:71|72)|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|(2:111|112)|113|115|116|117|118|119|120|121|123|124|125|126|127|128|129|130|131|133|134|135|136|(2:137|138)|139|141|142|143|144|145|146|147|148|150) */
        /* JADX WARNING: Can't wrap try/catch for region: R(126:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|41|42|43|45|46|47|48|49|50|51|52|53|54|55|56|57|59|60|61|63|64|65|(2:67|68)|69|71|72|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|(2:111|112)|113|115|116|117|118|119|120|121|123|124|125|126|127|128|129|130|131|133|134|135|136|(2:137|138)|139|141|142|143|144|145|146|147|148|150) */
        /* JADX WARNING: Can't wrap try/catch for region: R(127:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|41|42|43|45|46|47|48|49|50|51|52|53|54|55|56|57|59|60|61|63|64|65|(2:67|68)|69|71|72|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|111|112|113|115|116|117|118|119|120|121|123|124|125|126|127|128|129|130|131|133|134|135|136|(2:137|138)|139|141|142|143|144|145|146|147|148|150) */
        /* JADX WARNING: Can't wrap try/catch for region: R(128:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|41|42|43|45|46|47|48|49|50|51|52|53|54|55|56|57|59|60|61|63|64|65|(2:67|68)|69|71|72|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|111|112|113|115|116|117|118|119|120|121|123|124|125|126|127|128|129|130|131|133|134|135|136|(2:137|138)|139|141|142|143|144|145|146|147|148|150) */
        /* JADX WARNING: Can't wrap try/catch for region: R(129:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|41|42|43|45|46|47|48|49|50|51|52|53|54|55|56|57|59|60|61|63|64|65|(2:67|68)|69|71|72|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|111|112|113|115|116|117|118|119|120|121|123|124|125|126|127|128|129|130|131|133|134|135|136|137|138|139|141|142|143|144|145|146|147|148|150) */
        /* JADX WARNING: Can't wrap try/catch for region: R(131:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|37|39|40|41|42|43|45|46|47|48|49|50|51|52|53|54|55|56|57|59|60|61|63|64|65|67|68|69|71|72|73|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|107|108|109|110|111|112|113|115|116|117|118|119|120|121|123|124|125|126|127|128|129|130|131|133|134|135|136|137|138|139|141|142|143|144|145|146|147|148|150) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:101:0x019f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:103:0x01a9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:109:0x01c4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:111:0x01ce */
        /* JADX WARNING: Missing exception handler attribute for start block: B:117:0x01e9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:119:0x01f3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:125:0x020e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:127:0x0218 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:129:0x0222 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:135:0x023d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:137:0x0247 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:143:0x0262 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:145:0x026c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:147:0x0276 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0082 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x009d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00b8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00c2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00cc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00d6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00e0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x012a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:79:0x0134 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:81:0x013e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:83:0x0148 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:85:0x0152 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:87:0x015c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:89:0x0166 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x0170 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:93:0x017a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:99:0x0195 */
        static {
            /*
                com.google.firestore.v1.ListenResponse$ResponseTypeCase[] r0 = com.google.firestore.p009v1.ListenResponse.ResponseTypeCase.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f351x1837d9f = r0
                r1 = 1
                com.google.firestore.v1.ListenResponse$ResponseTypeCase r2 = com.google.firestore.p009v1.ListenResponse.ResponseTypeCase.TARGET_CHANGE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = f351x1837d9f     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firestore.v1.ListenResponse$ResponseTypeCase r3 = com.google.firestore.p009v1.ListenResponse.ResponseTypeCase.DOCUMENT_CHANGE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = f351x1837d9f     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firestore.v1.ListenResponse$ResponseTypeCase r4 = com.google.firestore.p009v1.ListenResponse.ResponseTypeCase.DOCUMENT_DELETE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = f351x1837d9f     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firestore.v1.ListenResponse$ResponseTypeCase r5 = com.google.firestore.p009v1.ListenResponse.ResponseTypeCase.DOCUMENT_REMOVE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = f351x1837d9f     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firestore.v1.ListenResponse$ResponseTypeCase r6 = com.google.firestore.p009v1.ListenResponse.ResponseTypeCase.FILTER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                r5 = 6
                int[] r6 = f351x1837d9f     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.firestore.v1.ListenResponse$ResponseTypeCase r7 = com.google.firestore.p009v1.ListenResponse.ResponseTypeCase.RESPONSETYPE_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                com.google.firestore.v1.TargetChange$TargetChangeType[] r6 = com.google.firestore.p009v1.TargetChange.TargetChangeType.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType = r6
                com.google.firestore.v1.TargetChange$TargetChangeType r7 = com.google.firestore.p009v1.TargetChange.TargetChangeType.NO_CHANGE     // Catch:{ NoSuchFieldError -> 0x005a }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r6 = $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.google.firestore.v1.TargetChange$TargetChangeType r7 = com.google.firestore.p009v1.TargetChange.TargetChangeType.ADD     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r6[r7] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                int[] r6 = $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType     // Catch:{ NoSuchFieldError -> 0x006e }
                com.google.firestore.v1.TargetChange$TargetChangeType r7 = com.google.firestore.p009v1.TargetChange.TargetChangeType.REMOVE     // Catch:{ NoSuchFieldError -> 0x006e }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r6[r7] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r6 = $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.google.firestore.v1.TargetChange$TargetChangeType r7 = com.google.firestore.p009v1.TargetChange.TargetChangeType.CURRENT     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r6[r7] = r3     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r6 = $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType     // Catch:{ NoSuchFieldError -> 0x0082 }
                com.google.firestore.v1.TargetChange$TargetChangeType r7 = com.google.firestore.p009v1.TargetChange.TargetChangeType.RESET     // Catch:{ NoSuchFieldError -> 0x0082 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
                r6[r7] = r4     // Catch:{ NoSuchFieldError -> 0x0082 }
            L_0x0082:
                int[] r6 = $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType     // Catch:{ NoSuchFieldError -> 0x008c }
                com.google.firestore.v1.TargetChange$TargetChangeType r7 = com.google.firestore.p009v1.TargetChange.TargetChangeType.UNRECOGNIZED     // Catch:{ NoSuchFieldError -> 0x008c }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x008c }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x008c }
            L_0x008c:
                com.google.firestore.v1.StructuredQuery$Direction[] r6 = com.google.firestore.p009v1.StructuredQuery.Direction.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction = r6
                com.google.firestore.v1.StructuredQuery$Direction r7 = com.google.firestore.p009v1.StructuredQuery.Direction.ASCENDING     // Catch:{ NoSuchFieldError -> 0x009d }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x009d }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x009d }
            L_0x009d:
                int[] r6 = $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction     // Catch:{ NoSuchFieldError -> 0x00a7 }
                com.google.firestore.v1.StructuredQuery$Direction r7 = com.google.firestore.p009v1.StructuredQuery.Direction.DESCENDING     // Catch:{ NoSuchFieldError -> 0x00a7 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a7 }
                r6[r7] = r0     // Catch:{ NoSuchFieldError -> 0x00a7 }
            L_0x00a7:
                com.google.firestore.v1.StructuredQuery$FieldFilter$Operator[] r6 = com.google.firestore.p009v1.StructuredQuery.FieldFilter.Operator.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                f353xaf95d2b = r6
                com.google.firestore.v1.StructuredQuery$FieldFilter$Operator r7 = com.google.firestore.p009v1.StructuredQuery.FieldFilter.Operator.LESS_THAN     // Catch:{ NoSuchFieldError -> 0x00b8 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b8 }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x00b8 }
            L_0x00b8:
                int[] r6 = f353xaf95d2b     // Catch:{ NoSuchFieldError -> 0x00c2 }
                com.google.firestore.v1.StructuredQuery$FieldFilter$Operator r7 = com.google.firestore.p009v1.StructuredQuery.FieldFilter.Operator.LESS_THAN_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x00c2 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c2 }
                r6[r7] = r0     // Catch:{ NoSuchFieldError -> 0x00c2 }
            L_0x00c2:
                int[] r6 = f353xaf95d2b     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.google.firestore.v1.StructuredQuery$FieldFilter$Operator r7 = com.google.firestore.p009v1.StructuredQuery.FieldFilter.Operator.EQUAL     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r6[r7] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                int[] r6 = f353xaf95d2b     // Catch:{ NoSuchFieldError -> 0x00d6 }
                com.google.firestore.v1.StructuredQuery$FieldFilter$Operator r7 = com.google.firestore.p009v1.StructuredQuery.FieldFilter.Operator.NOT_EQUAL     // Catch:{ NoSuchFieldError -> 0x00d6 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d6 }
                r6[r7] = r3     // Catch:{ NoSuchFieldError -> 0x00d6 }
            L_0x00d6:
                int[] r6 = f353xaf95d2b     // Catch:{ NoSuchFieldError -> 0x00e0 }
                com.google.firestore.v1.StructuredQuery$FieldFilter$Operator r7 = com.google.firestore.p009v1.StructuredQuery.FieldFilter.Operator.GREATER_THAN_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x00e0 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e0 }
                r6[r7] = r4     // Catch:{ NoSuchFieldError -> 0x00e0 }
            L_0x00e0:
                int[] r6 = f353xaf95d2b     // Catch:{ NoSuchFieldError -> 0x00ea }
                com.google.firestore.v1.StructuredQuery$FieldFilter$Operator r7 = com.google.firestore.p009v1.StructuredQuery.FieldFilter.Operator.GREATER_THAN     // Catch:{ NoSuchFieldError -> 0x00ea }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ea }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x00ea }
            L_0x00ea:
                r6 = 7
                int[] r7 = f353xaf95d2b     // Catch:{ NoSuchFieldError -> 0x00f5 }
                com.google.firestore.v1.StructuredQuery$FieldFilter$Operator r8 = com.google.firestore.p009v1.StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS     // Catch:{ NoSuchFieldError -> 0x00f5 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f5 }
                r7[r8] = r6     // Catch:{ NoSuchFieldError -> 0x00f5 }
            L_0x00f5:
                r7 = 8
                int[] r8 = f353xaf95d2b     // Catch:{ NoSuchFieldError -> 0x0101 }
                com.google.firestore.v1.StructuredQuery$FieldFilter$Operator r9 = com.google.firestore.p009v1.StructuredQuery.FieldFilter.Operator.IN     // Catch:{ NoSuchFieldError -> 0x0101 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0101 }
                r8[r9] = r7     // Catch:{ NoSuchFieldError -> 0x0101 }
            L_0x0101:
                r8 = 9
                int[] r9 = f353xaf95d2b     // Catch:{ NoSuchFieldError -> 0x010d }
                com.google.firestore.v1.StructuredQuery$FieldFilter$Operator r10 = com.google.firestore.p009v1.StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS_ANY     // Catch:{ NoSuchFieldError -> 0x010d }
                int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x010d }
                r9[r10] = r8     // Catch:{ NoSuchFieldError -> 0x010d }
            L_0x010d:
                r9 = 10
                int[] r10 = f353xaf95d2b     // Catch:{ NoSuchFieldError -> 0x0119 }
                com.google.firestore.v1.StructuredQuery$FieldFilter$Operator r11 = com.google.firestore.p009v1.StructuredQuery.FieldFilter.Operator.NOT_IN     // Catch:{ NoSuchFieldError -> 0x0119 }
                int r11 = r11.ordinal()     // Catch:{ NoSuchFieldError -> 0x0119 }
                r10[r11] = r9     // Catch:{ NoSuchFieldError -> 0x0119 }
            L_0x0119:
                com.google.firebase.firestore.core.Filter$Operator[] r10 = com.google.firebase.firestore.core.Filter.Operator.values()
                int r10 = r10.length
                int[] r10 = new int[r10]
                $SwitchMap$com$google$firebase$firestore$core$Filter$Operator = r10
                com.google.firebase.firestore.core.Filter$Operator r11 = com.google.firebase.firestore.core.Filter.Operator.LESS_THAN     // Catch:{ NoSuchFieldError -> 0x012a }
                int r11 = r11.ordinal()     // Catch:{ NoSuchFieldError -> 0x012a }
                r10[r11] = r1     // Catch:{ NoSuchFieldError -> 0x012a }
            L_0x012a:
                int[] r10 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0134 }
                com.google.firebase.firestore.core.Filter$Operator r11 = com.google.firebase.firestore.core.Filter.Operator.LESS_THAN_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x0134 }
                int r11 = r11.ordinal()     // Catch:{ NoSuchFieldError -> 0x0134 }
                r10[r11] = r0     // Catch:{ NoSuchFieldError -> 0x0134 }
            L_0x0134:
                int[] r10 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x013e }
                com.google.firebase.firestore.core.Filter$Operator r11 = com.google.firebase.firestore.core.Filter.Operator.EQUAL     // Catch:{ NoSuchFieldError -> 0x013e }
                int r11 = r11.ordinal()     // Catch:{ NoSuchFieldError -> 0x013e }
                r10[r11] = r2     // Catch:{ NoSuchFieldError -> 0x013e }
            L_0x013e:
                int[] r10 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0148 }
                com.google.firebase.firestore.core.Filter$Operator r11 = com.google.firebase.firestore.core.Filter.Operator.NOT_EQUAL     // Catch:{ NoSuchFieldError -> 0x0148 }
                int r11 = r11.ordinal()     // Catch:{ NoSuchFieldError -> 0x0148 }
                r10[r11] = r3     // Catch:{ NoSuchFieldError -> 0x0148 }
            L_0x0148:
                int[] r10 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0152 }
                com.google.firebase.firestore.core.Filter$Operator r11 = com.google.firebase.firestore.core.Filter.Operator.GREATER_THAN     // Catch:{ NoSuchFieldError -> 0x0152 }
                int r11 = r11.ordinal()     // Catch:{ NoSuchFieldError -> 0x0152 }
                r10[r11] = r4     // Catch:{ NoSuchFieldError -> 0x0152 }
            L_0x0152:
                int[] r4 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x015c }
                com.google.firebase.firestore.core.Filter$Operator r10 = com.google.firebase.firestore.core.Filter.Operator.GREATER_THAN_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x015c }
                int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x015c }
                r4[r10] = r5     // Catch:{ NoSuchFieldError -> 0x015c }
            L_0x015c:
                int[] r4 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0166 }
                com.google.firebase.firestore.core.Filter$Operator r5 = com.google.firebase.firestore.core.Filter.Operator.ARRAY_CONTAINS     // Catch:{ NoSuchFieldError -> 0x0166 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0166 }
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x0166 }
            L_0x0166:
                int[] r4 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0170 }
                com.google.firebase.firestore.core.Filter$Operator r5 = com.google.firebase.firestore.core.Filter.Operator.IN     // Catch:{ NoSuchFieldError -> 0x0170 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0170 }
                r4[r5] = r7     // Catch:{ NoSuchFieldError -> 0x0170 }
            L_0x0170:
                int[] r4 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x017a }
                com.google.firebase.firestore.core.Filter$Operator r5 = com.google.firebase.firestore.core.Filter.Operator.ARRAY_CONTAINS_ANY     // Catch:{ NoSuchFieldError -> 0x017a }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x017a }
                r4[r5] = r8     // Catch:{ NoSuchFieldError -> 0x017a }
            L_0x017a:
                int[] r4 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0184 }
                com.google.firebase.firestore.core.Filter$Operator r5 = com.google.firebase.firestore.core.Filter.Operator.NOT_IN     // Catch:{ NoSuchFieldError -> 0x0184 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0184 }
                r4[r5] = r9     // Catch:{ NoSuchFieldError -> 0x0184 }
            L_0x0184:
                com.google.firestore.v1.StructuredQuery$UnaryFilter$Operator[] r4 = com.google.firestore.p009v1.StructuredQuery.UnaryFilter.Operator.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                f355xf473b456 = r4
                com.google.firestore.v1.StructuredQuery$UnaryFilter$Operator r5 = com.google.firestore.p009v1.StructuredQuery.UnaryFilter.Operator.IS_NAN     // Catch:{ NoSuchFieldError -> 0x0195 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0195 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0195 }
            L_0x0195:
                int[] r4 = f355xf473b456     // Catch:{ NoSuchFieldError -> 0x019f }
                com.google.firestore.v1.StructuredQuery$UnaryFilter$Operator r5 = com.google.firestore.p009v1.StructuredQuery.UnaryFilter.Operator.IS_NULL     // Catch:{ NoSuchFieldError -> 0x019f }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x019f }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x019f }
            L_0x019f:
                int[] r4 = f355xf473b456     // Catch:{ NoSuchFieldError -> 0x01a9 }
                com.google.firestore.v1.StructuredQuery$UnaryFilter$Operator r5 = com.google.firestore.p009v1.StructuredQuery.UnaryFilter.Operator.IS_NOT_NAN     // Catch:{ NoSuchFieldError -> 0x01a9 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x01a9 }
                r4[r5] = r2     // Catch:{ NoSuchFieldError -> 0x01a9 }
            L_0x01a9:
                int[] r4 = f355xf473b456     // Catch:{ NoSuchFieldError -> 0x01b3 }
                com.google.firestore.v1.StructuredQuery$UnaryFilter$Operator r5 = com.google.firestore.p009v1.StructuredQuery.UnaryFilter.Operator.IS_NOT_NULL     // Catch:{ NoSuchFieldError -> 0x01b3 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x01b3 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x01b3 }
            L_0x01b3:
                com.google.firestore.v1.StructuredQuery$Filter$FilterTypeCase[] r4 = com.google.firestore.p009v1.StructuredQuery.Filter.FilterTypeCase.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                f354x9d2ee979 = r4
                com.google.firestore.v1.StructuredQuery$Filter$FilterTypeCase r5 = com.google.firestore.p009v1.StructuredQuery.Filter.FilterTypeCase.COMPOSITE_FILTER     // Catch:{ NoSuchFieldError -> 0x01c4 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x01c4 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x01c4 }
            L_0x01c4:
                int[] r4 = f354x9d2ee979     // Catch:{ NoSuchFieldError -> 0x01ce }
                com.google.firestore.v1.StructuredQuery$Filter$FilterTypeCase r5 = com.google.firestore.p009v1.StructuredQuery.Filter.FilterTypeCase.FIELD_FILTER     // Catch:{ NoSuchFieldError -> 0x01ce }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x01ce }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x01ce }
            L_0x01ce:
                int[] r4 = f354x9d2ee979     // Catch:{ NoSuchFieldError -> 0x01d8 }
                com.google.firestore.v1.StructuredQuery$Filter$FilterTypeCase r5 = com.google.firestore.p009v1.StructuredQuery.Filter.FilterTypeCase.UNARY_FILTER     // Catch:{ NoSuchFieldError -> 0x01d8 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x01d8 }
                r4[r5] = r2     // Catch:{ NoSuchFieldError -> 0x01d8 }
            L_0x01d8:
                com.google.firebase.firestore.local.QueryPurpose[] r4 = com.google.firebase.firestore.local.QueryPurpose.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$google$firebase$firestore$local$QueryPurpose = r4
                com.google.firebase.firestore.local.QueryPurpose r5 = com.google.firebase.firestore.local.QueryPurpose.LISTEN     // Catch:{ NoSuchFieldError -> 0x01e9 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x01e9 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x01e9 }
            L_0x01e9:
                int[] r4 = $SwitchMap$com$google$firebase$firestore$local$QueryPurpose     // Catch:{ NoSuchFieldError -> 0x01f3 }
                com.google.firebase.firestore.local.QueryPurpose r5 = com.google.firebase.firestore.local.QueryPurpose.EXISTENCE_FILTER_MISMATCH     // Catch:{ NoSuchFieldError -> 0x01f3 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x01f3 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x01f3 }
            L_0x01f3:
                int[] r4 = $SwitchMap$com$google$firebase$firestore$local$QueryPurpose     // Catch:{ NoSuchFieldError -> 0x01fd }
                com.google.firebase.firestore.local.QueryPurpose r5 = com.google.firebase.firestore.local.QueryPurpose.LIMBO_RESOLUTION     // Catch:{ NoSuchFieldError -> 0x01fd }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x01fd }
                r4[r5] = r2     // Catch:{ NoSuchFieldError -> 0x01fd }
            L_0x01fd:
                com.google.firestore.v1.DocumentTransform$FieldTransform$TransformTypeCase[] r4 = com.google.firestore.p009v1.DocumentTransform.FieldTransform.TransformTypeCase.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                f350xdd498c9f = r4
                com.google.firestore.v1.DocumentTransform$FieldTransform$TransformTypeCase r5 = com.google.firestore.p009v1.DocumentTransform.FieldTransform.TransformTypeCase.SET_TO_SERVER_VALUE     // Catch:{ NoSuchFieldError -> 0x020e }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x020e }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x020e }
            L_0x020e:
                int[] r4 = f350xdd498c9f     // Catch:{ NoSuchFieldError -> 0x0218 }
                com.google.firestore.v1.DocumentTransform$FieldTransform$TransformTypeCase r5 = com.google.firestore.p009v1.DocumentTransform.FieldTransform.TransformTypeCase.APPEND_MISSING_ELEMENTS     // Catch:{ NoSuchFieldError -> 0x0218 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0218 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0218 }
            L_0x0218:
                int[] r4 = f350xdd498c9f     // Catch:{ NoSuchFieldError -> 0x0222 }
                com.google.firestore.v1.DocumentTransform$FieldTransform$TransformTypeCase r5 = com.google.firestore.p009v1.DocumentTransform.FieldTransform.TransformTypeCase.REMOVE_ALL_FROM_ARRAY     // Catch:{ NoSuchFieldError -> 0x0222 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0222 }
                r4[r5] = r2     // Catch:{ NoSuchFieldError -> 0x0222 }
            L_0x0222:
                int[] r4 = f350xdd498c9f     // Catch:{ NoSuchFieldError -> 0x022c }
                com.google.firestore.v1.DocumentTransform$FieldTransform$TransformTypeCase r5 = com.google.firestore.p009v1.DocumentTransform.FieldTransform.TransformTypeCase.INCREMENT     // Catch:{ NoSuchFieldError -> 0x022c }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x022c }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x022c }
            L_0x022c:
                com.google.firestore.v1.Precondition$ConditionTypeCase[] r4 = com.google.firestore.p009v1.Precondition.ConditionTypeCase.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                f352x8f18ca41 = r4
                com.google.firestore.v1.Precondition$ConditionTypeCase r5 = com.google.firestore.p009v1.Precondition.ConditionTypeCase.UPDATE_TIME     // Catch:{ NoSuchFieldError -> 0x023d }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x023d }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x023d }
            L_0x023d:
                int[] r4 = f352x8f18ca41     // Catch:{ NoSuchFieldError -> 0x0247 }
                com.google.firestore.v1.Precondition$ConditionTypeCase r5 = com.google.firestore.p009v1.Precondition.ConditionTypeCase.EXISTS     // Catch:{ NoSuchFieldError -> 0x0247 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0247 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0247 }
            L_0x0247:
                int[] r4 = f352x8f18ca41     // Catch:{ NoSuchFieldError -> 0x0251 }
                com.google.firestore.v1.Precondition$ConditionTypeCase r5 = com.google.firestore.p009v1.Precondition.ConditionTypeCase.CONDITIONTYPE_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0251 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0251 }
                r4[r5] = r2     // Catch:{ NoSuchFieldError -> 0x0251 }
            L_0x0251:
                com.google.firestore.v1.Write$OperationCase[] r4 = com.google.firestore.p009v1.Write.OperationCase.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$google$firestore$v1$Write$OperationCase = r4
                com.google.firestore.v1.Write$OperationCase r5 = com.google.firestore.p009v1.Write.OperationCase.UPDATE     // Catch:{ NoSuchFieldError -> 0x0262 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0262 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0262 }
            L_0x0262:
                int[] r1 = $SwitchMap$com$google$firestore$v1$Write$OperationCase     // Catch:{ NoSuchFieldError -> 0x026c }
                com.google.firestore.v1.Write$OperationCase r4 = com.google.firestore.p009v1.Write.OperationCase.DELETE     // Catch:{ NoSuchFieldError -> 0x026c }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x026c }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x026c }
            L_0x026c:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Write$OperationCase     // Catch:{ NoSuchFieldError -> 0x0276 }
                com.google.firestore.v1.Write$OperationCase r1 = com.google.firestore.p009v1.Write.OperationCase.TRANSFORM     // Catch:{ NoSuchFieldError -> 0x0276 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0276 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0276 }
            L_0x0276:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Write$OperationCase     // Catch:{ NoSuchFieldError -> 0x0280 }
                com.google.firestore.v1.Write$OperationCase r1 = com.google.firestore.p009v1.Write.OperationCase.VERIFY     // Catch:{ NoSuchFieldError -> 0x0280 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0280 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0280 }
            L_0x0280:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.remote.RemoteSerializer.C19121.<clinit>():void");
        }
    }

    public WatchChange decodeWatchChange(ListenResponse listenResponse) {
        WatchChange.WatchTargetChangeType watchTargetChangeType;
        WatchChange watchTargetChange;
        int i = C19121.f351x1837d9f[listenResponse.getResponseTypeCase().ordinal()];
        Status status = null;
        if (i == 1) {
            TargetChange targetChange = listenResponse.getTargetChange();
            int i2 = C19121.$SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[targetChange.getTargetChangeType().ordinal()];
            if (i2 == 1) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.NoChange;
            } else if (i2 == 2) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.Added;
            } else if (i2 == 3) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.Removed;
                status = fromStatus(targetChange.getCause());
            } else if (i2 == 4) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.Current;
            } else if (i2 == 5) {
                watchTargetChangeType = WatchChange.WatchTargetChangeType.Reset;
            } else {
                throw new IllegalArgumentException("Unknown target change type");
            }
            watchTargetChange = new WatchChange.WatchTargetChange(watchTargetChangeType, targetChange.getTargetIdsList(), targetChange.getResumeToken(), status);
        } else if (i == 2) {
            DocumentChange documentChange = listenResponse.getDocumentChange();
            List<Integer> targetIdsList = documentChange.getTargetIdsList();
            List<Integer> removedTargetIdsList = documentChange.getRemovedTargetIdsList();
            DocumentKey decodeKey = decodeKey(documentChange.getDocument().getName());
            SnapshotVersion decodeVersion = decodeVersion(documentChange.getDocument().getUpdateTime());
            Assert.hardAssert(!decodeVersion.equals(SnapshotVersion.NONE), "Got a document change without an update time", new Object[0]);
            com.google.firebase.firestore.model.Document document = new com.google.firebase.firestore.model.Document(decodeKey, decodeVersion, ObjectValue.fromMap(documentChange.getDocument().getFieldsMap()), Document.DocumentState.SYNCED);
            return new WatchChange.DocumentChange(targetIdsList, removedTargetIdsList, document.getKey(), document);
        } else if (i == 3) {
            DocumentDelete documentDelete = listenResponse.getDocumentDelete();
            List<Integer> removedTargetIdsList2 = documentDelete.getRemovedTargetIdsList();
            NoDocument noDocument = new NoDocument(decodeKey(documentDelete.getDocument()), decodeVersion(documentDelete.getReadTime()), false);
            return new WatchChange.DocumentChange(Collections.emptyList(), removedTargetIdsList2, noDocument.getKey(), noDocument);
        } else if (i == 4) {
            DocumentRemove documentRemove = listenResponse.getDocumentRemove();
            watchTargetChange = new WatchChange.DocumentChange(Collections.emptyList(), documentRemove.getRemovedTargetIdsList(), decodeKey(documentRemove.getDocument()), (MaybeDocument) null);
        } else if (i == 5) {
            ExistenceFilter filter = listenResponse.getFilter();
            return new WatchChange.ExistenceFilterWatchChange(filter.getTargetId(), new ExistenceFilter(filter.getCount()));
        } else {
            throw new IllegalArgumentException("Unknown change type set");
        }
        return watchTargetChange;
    }

    public SnapshotVersion decodeVersionFromListenResponse(ListenResponse listenResponse) {
        if (listenResponse.getResponseTypeCase() != ListenResponse.ResponseTypeCase.TARGET_CHANGE) {
            return SnapshotVersion.NONE;
        }
        if (listenResponse.getTargetChange().getTargetIdsCount() != 0) {
            return SnapshotVersion.NONE;
        }
        return decodeVersion(listenResponse.getTargetChange().getReadTime());
    }

    private Status fromStatus(com.google.rpc.Status status) {
        return Status.fromCodeValue(status.getCode()).withDescription(status.getMessage());
    }
}

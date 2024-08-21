package com.google.firebase.database.core.operation;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.snapshot.ChildKey;

public abstract class Operation {
    protected final Path path;
    protected final OperationSource source;
    protected final OperationType type;

    public enum OperationType {
        Overwrite,
        Merge,
        AckUserWrite,
        ListenComplete
    }

    public abstract Operation operationForChild(ChildKey childKey);

    protected Operation(OperationType operationType, OperationSource operationSource, Path path2) {
        this.type = operationType;
        this.source = operationSource;
        this.path = path2;
    }

    public Path getPath() {
        return this.path;
    }

    public OperationSource getSource() {
        return this.source;
    }

    public OperationType getType() {
        return this.type;
    }
}

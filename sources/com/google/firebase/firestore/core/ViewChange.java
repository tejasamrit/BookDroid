package com.google.firebase.firestore.core;

import java.util.List;

public class ViewChange {
    private final List<LimboDocumentChange> limboChanges;
    private final ViewSnapshot snapshot;

    public ViewChange(ViewSnapshot viewSnapshot, List<LimboDocumentChange> list) {
        this.snapshot = viewSnapshot;
        this.limboChanges = list;
    }

    public ViewSnapshot getSnapshot() {
        return this.snapshot;
    }

    public List<LimboDocumentChange> getLimboChanges() {
        return this.limboChanges;
    }
}

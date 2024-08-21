package com.google.firebase.database.core.utilities.tuple;

import com.google.firebase.database.core.Path;

public class PathAndId {

    /* renamed from: id */
    private long f316id;
    private Path path;

    public PathAndId(Path path2, long j) {
        this.path = path2;
        this.f316id = j;
    }

    public Path getPath() {
        return this.path;
    }

    public long getId() {
        return this.f316id;
    }
}

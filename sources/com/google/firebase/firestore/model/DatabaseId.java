package com.google.firebase.firestore.model;

import com.google.firebase.firestore.util.Assert;

public final class DatabaseId implements Comparable<DatabaseId> {
    public static final String DEFAULT_DATABASE_ID = "(default)";
    private final String databaseId;
    private final String projectId;

    public static DatabaseId forProject(String str) {
        return forDatabase(str, DEFAULT_DATABASE_ID);
    }

    public static DatabaseId forDatabase(String str, String str2) {
        return new DatabaseId(str, str2);
    }

    private DatabaseId(String str, String str2) {
        this.projectId = str;
        this.databaseId = str2;
    }

    public static DatabaseId fromName(String str) {
        ResourcePath fromString = ResourcePath.fromString(str);
        Assert.hardAssert(fromString.length() >= 3 && fromString.getSegment(0).equals("projects") && fromString.getSegment(2).equals("databases"), "Tried to parse an invalid resource name: %s", fromString);
        return new DatabaseId(fromString.getSegment(1), fromString.getSegment(3));
    }

    public String getProjectId() {
        return this.projectId;
    }

    public String getDatabaseId() {
        return this.databaseId;
    }

    public String toString() {
        return "DatabaseId(" + this.projectId + ", " + this.databaseId + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DatabaseId databaseId2 = (DatabaseId) obj;
        if (!this.projectId.equals(databaseId2.projectId) || !this.databaseId.equals(databaseId2.databaseId)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.projectId.hashCode() * 31) + this.databaseId.hashCode();
    }

    public int compareTo(DatabaseId databaseId2) {
        int compareTo = this.projectId.compareTo(databaseId2.projectId);
        return compareTo != 0 ? compareTo : this.databaseId.compareTo(databaseId2.databaseId);
    }
}

package com.google.firebase.database.core;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.snapshot.ChildKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Path implements Iterable<ChildKey>, Comparable<Path> {
    private static final Path EMPTY_PATH = new Path("");
    /* access modifiers changed from: private */
    public final int end;
    /* access modifiers changed from: private */
    public final ChildKey[] pieces;
    /* access modifiers changed from: private */
    public final int start;

    public static Path getRelative(Path path, Path path2) {
        ChildKey front = path.getFront();
        ChildKey front2 = path2.getFront();
        if (front == null) {
            return path2;
        }
        if (front.equals(front2)) {
            return getRelative(path.popFront(), path2.popFront());
        }
        throw new DatabaseException("INTERNAL ERROR: " + path2 + " is not contained in " + path);
    }

    public static Path getEmptyPath() {
        return EMPTY_PATH;
    }

    public Path(ChildKey... childKeyArr) {
        this.pieces = (ChildKey[]) Arrays.copyOf(childKeyArr, childKeyArr.length);
        this.start = 0;
        this.end = childKeyArr.length;
        int length = childKeyArr.length;
        for (int i = 0; i < length; i++) {
            Utilities.hardAssert(childKeyArr[i] != null, "Can't construct a path with a null value!");
        }
    }

    public Path(List<String> list) {
        this.pieces = new ChildKey[list.size()];
        int i = 0;
        for (String fromString : list) {
            this.pieces[i] = ChildKey.fromString(fromString);
            i++;
        }
        this.start = 0;
        this.end = list.size();
    }

    public Path(String str) {
        String[] split = str.split("/", -1);
        int i = 0;
        for (String length : split) {
            if (length.length() > 0) {
                i++;
            }
        }
        this.pieces = new ChildKey[i];
        int i2 = 0;
        for (String str2 : split) {
            if (str2.length() > 0) {
                this.pieces[i2] = ChildKey.fromString(str2);
                i2++;
            }
        }
        this.start = 0;
        this.end = this.pieces.length;
    }

    private Path(ChildKey[] childKeyArr, int i, int i2) {
        this.pieces = childKeyArr;
        this.start = i;
        this.end = i2;
    }

    public Path child(Path path) {
        int size = size() + path.size();
        ChildKey[] childKeyArr = new ChildKey[size];
        System.arraycopy(this.pieces, this.start, childKeyArr, 0, size());
        System.arraycopy(path.pieces, path.start, childKeyArr, size(), path.size());
        return new Path(childKeyArr, 0, size);
    }

    public Path child(ChildKey childKey) {
        int size = size();
        int i = size + 1;
        ChildKey[] childKeyArr = new ChildKey[i];
        System.arraycopy(this.pieces, this.start, childKeyArr, 0, size);
        childKeyArr[size] = childKey;
        return new Path(childKeyArr, 0, i);
    }

    public String toString() {
        if (isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = this.start; i < this.end; i++) {
            sb.append("/");
            sb.append(this.pieces[i].asString());
        }
        return sb.toString();
    }

    public String wireFormat() {
        if (isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = this.start; i < this.end; i++) {
            if (i > this.start) {
                sb.append("/");
            }
            sb.append(this.pieces[i].asString());
        }
        return sb.toString();
    }

    public List<String> asList() {
        ArrayList arrayList = new ArrayList(size());
        Iterator<ChildKey> it = iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().asString());
        }
        return arrayList;
    }

    public ChildKey getFront() {
        if (isEmpty()) {
            return null;
        }
        return this.pieces[this.start];
    }

    public Path popFront() {
        int i = this.start;
        if (!isEmpty()) {
            i++;
        }
        return new Path(this.pieces, i, this.end);
    }

    public Path getParent() {
        if (isEmpty()) {
            return null;
        }
        return new Path(this.pieces, this.start, this.end - 1);
    }

    public ChildKey getBack() {
        if (!isEmpty()) {
            return this.pieces[this.end - 1];
        }
        return null;
    }

    public boolean isEmpty() {
        return this.start >= this.end;
    }

    public int size() {
        return this.end - this.start;
    }

    public Iterator<ChildKey> iterator() {
        return new Iterator<ChildKey>() {
            int offset;

            {
                this.offset = Path.this.start;
            }

            public boolean hasNext() {
                return this.offset < Path.this.end;
            }

            public ChildKey next() {
                if (hasNext()) {
                    ChildKey[] access$200 = Path.this.pieces;
                    int i = this.offset;
                    ChildKey childKey = access$200[i];
                    this.offset = i + 1;
                    return childKey;
                }
                throw new NoSuchElementException("No more elements.");
            }

            public void remove() {
                throw new UnsupportedOperationException("Can't remove component from immutable Path!");
            }
        };
    }

    public boolean contains(Path path) {
        if (size() > path.size()) {
            return false;
        }
        int i = this.start;
        int i2 = path.start;
        while (i < this.end) {
            if (!this.pieces[i].equals(path.pieces[i2])) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Path)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Path path = (Path) obj;
        if (size() != path.size()) {
            return false;
        }
        int i = this.start;
        int i2 = path.start;
        while (i < this.end && i2 < path.end) {
            if (!this.pieces[i].equals(path.pieces[i2])) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = this.start; i2 < this.end; i2++) {
            i = (i * 37) + this.pieces[i2].hashCode();
        }
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0026 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int compareTo(com.google.firebase.database.core.Path r5) {
        /*
            r4 = this;
            int r0 = r4.start
            int r1 = r5.start
        L_0x0004:
            int r2 = r4.end
            if (r0 >= r2) goto L_0x0020
            int r3 = r5.end
            if (r1 >= r3) goto L_0x0020
            com.google.firebase.database.snapshot.ChildKey[] r2 = r4.pieces
            r2 = r2[r0]
            com.google.firebase.database.snapshot.ChildKey[] r3 = r5.pieces
            r3 = r3[r1]
            int r2 = r2.compareTo((com.google.firebase.database.snapshot.ChildKey) r3)
            if (r2 == 0) goto L_0x001b
            return r2
        L_0x001b:
            int r0 = r0 + 1
            int r1 = r1 + 1
            goto L_0x0004
        L_0x0020:
            if (r0 != r2) goto L_0x0028
            int r5 = r5.end
            if (r1 != r5) goto L_0x0028
            r5 = 0
            return r5
        L_0x0028:
            if (r0 != r2) goto L_0x002c
            r5 = -1
            return r5
        L_0x002c:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.core.Path.compareTo(com.google.firebase.database.core.Path):int");
    }
}

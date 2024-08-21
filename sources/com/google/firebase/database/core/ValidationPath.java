package com.google.firebase.database.core;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.snapshot.ChildKey;
import com.iceteck.silicompressorr.FileUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ValidationPath {
    public static final int MAX_PATH_DEPTH = 32;
    public static final int MAX_PATH_LENGTH_BYTES = 768;
    private int byteLength;
    private final List<String> parts = new ArrayList();

    private ValidationPath(Path path) throws DatabaseException {
        this.byteLength = 0;
        Iterator<ChildKey> it = path.iterator();
        while (it.hasNext()) {
            this.parts.add(it.next().asString());
        }
        this.byteLength = Math.max(1, this.parts.size());
        for (int i = 0; i < this.parts.size(); i++) {
            this.byteLength += utf8Bytes(this.parts.get(i));
        }
        checkValid();
    }

    public static void validateWithObject(Path path, Object obj) throws DatabaseException {
        new ValidationPath(path).withObject(obj);
    }

    private void withObject(Object obj) throws DatabaseException {
        if (obj instanceof Map) {
            Map map = (Map) obj;
            for (String str : map.keySet()) {
                if (!str.startsWith(FileUtils.HIDDEN_PREFIX)) {
                    push(str);
                    withObject(map.get(str));
                    pop();
                }
            }
        } else if (obj instanceof List) {
            List list = (List) obj;
            for (int i = 0; i < list.size(); i++) {
                push(Integer.toString(i));
                withObject(list.get(i));
                pop();
            }
        }
    }

    private void push(String str) throws DatabaseException {
        if (this.parts.size() > 0) {
            this.byteLength++;
        }
        this.parts.add(str);
        this.byteLength += utf8Bytes(str);
        checkValid();
    }

    private String pop() {
        List<String> list = this.parts;
        String remove = list.remove(list.size() - 1);
        this.byteLength -= utf8Bytes(remove);
        if (this.parts.size() > 0) {
            this.byteLength--;
        }
        return remove;
    }

    private void checkValid() throws DatabaseException {
        if (this.byteLength > 768) {
            throw new DatabaseException("Data has a key path longer than 768 bytes (" + this.byteLength + ").");
        } else if (this.parts.size() > 32) {
            throw new DatabaseException("Path specified exceeds the maximum depth that can be written (32) or object contains a cycle " + toErrorString());
        }
    }

    private String toErrorString() {
        if (this.parts.size() == 0) {
            return "";
        }
        return "in path '" + joinStringList("/", this.parts) + "'";
    }

    private static String joinStringList(String str, List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(str);
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    private static int utf8Bytes(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt <= 127) {
                i2++;
            } else if (charAt <= 2047) {
                i2 += 2;
            } else if (Character.isHighSurrogate(charAt)) {
                i2 += 4;
                i++;
            } else {
                i2 += 3;
            }
            i++;
        }
        return i2;
    }
}

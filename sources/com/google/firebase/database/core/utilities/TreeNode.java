package com.google.firebase.database.core.utilities;

import com.google.firebase.database.snapshot.ChildKey;
import java.util.HashMap;
import java.util.Map;

public class TreeNode<T> {
    public Map<ChildKey, TreeNode<T>> children = new HashMap();
    public T value;

    /* access modifiers changed from: package-private */
    public String toString(String str) {
        String str2 = str + "<value>: " + this.value + "\n";
        if (this.children.isEmpty()) {
            return str2 + str + "<empty>";
        }
        for (Map.Entry next : this.children.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(str);
            sb.append(next.getKey());
            sb.append(":\n");
            sb.append(((TreeNode) next.getValue()).toString(str + "\t"));
            sb.append("\n");
            str2 = sb.toString();
        }
        return str2;
    }
}

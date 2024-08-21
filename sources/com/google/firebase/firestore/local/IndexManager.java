package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.ResourcePath;
import java.util.List;

public interface IndexManager {
    void addToCollectionParentIndex(ResourcePath resourcePath);

    List<ResourcePath> getCollectionParents(String str);
}

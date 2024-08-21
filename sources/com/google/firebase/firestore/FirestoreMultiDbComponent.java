package com.google.firebase.firestore;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class FirestoreMultiDbComponent implements FirebaseAppLifecycleListener, FirebaseFirestore.InstanceRegistry {
    private final FirebaseApp app;
    private final InternalAuthProvider authProvider;
    private final Context context;
    private final Map<String, FirebaseFirestore> instances = new HashMap();
    private final GrpcMetadataProvider metadataProvider;

    FirestoreMultiDbComponent(Context context2, FirebaseApp firebaseApp, InternalAuthProvider internalAuthProvider, GrpcMetadataProvider grpcMetadataProvider) {
        this.context = context2;
        this.app = firebaseApp;
        this.authProvider = internalAuthProvider;
        this.metadataProvider = grpcMetadataProvider;
        firebaseApp.addLifecycleEventListener(this);
    }

    /* access modifiers changed from: package-private */
    public synchronized FirebaseFirestore get(String str) {
        FirebaseFirestore firebaseFirestore;
        firebaseFirestore = this.instances.get(str);
        if (firebaseFirestore == null) {
            firebaseFirestore = FirebaseFirestore.newInstance(this.context, this.app, this.authProvider, str, this, this.metadataProvider);
            this.instances.put(str, firebaseFirestore);
        }
        return firebaseFirestore;
    }

    public synchronized void remove(String str) {
        this.instances.remove(str);
    }

    public synchronized void onDeleted(String str, FirebaseOptions firebaseOptions) {
        Iterator it = new ArrayList(this.instances.entrySet()).iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            ((FirebaseFirestore) entry.getValue()).terminate();
            Assert.hardAssert(!this.instances.containsKey(entry.getKey()), "terminate() should have removed its entry from `instances` for key: %s", entry.getKey());
        }
    }
}

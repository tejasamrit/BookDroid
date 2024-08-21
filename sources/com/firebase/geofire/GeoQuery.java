package com.firebase.geofire;

import com.firebase.geofire.core.GeoHash;
import com.firebase.geofire.core.GeoHashQuery;
import com.firebase.geofire.util.GeoUtils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GeoQuery {
    private static final int KILOMETER_TO_METER = 1000;
    private GeoLocation center;
    private final ChildEventListener childEventLister = new ChildEventListener() {
        public void onChildAdded(DataSnapshot dataSnapshot, String str) {
            synchronized (GeoQuery.this) {
                GeoQuery.this.childAdded(dataSnapshot);
            }
        }

        public void onChildChanged(DataSnapshot dataSnapshot, String str) {
            synchronized (GeoQuery.this) {
                GeoQuery.this.childChanged(dataSnapshot);
            }
        }

        public void onChildRemoved(DataSnapshot dataSnapshot) {
            synchronized (GeoQuery.this) {
                GeoQuery.this.childRemoved(dataSnapshot);
            }
        }

        public synchronized void onChildMoved(DataSnapshot dataSnapshot, String str) {
        }

        public synchronized void onCancelled(DatabaseError databaseError) {
        }
    };
    /* access modifiers changed from: private */
    public final Set<GeoQueryDataEventListener> eventListeners = new HashSet();
    private final Map<GeoHashQuery, Query> firebaseQueries = new HashMap();
    /* access modifiers changed from: private */
    public final GeoFire geoFire;
    /* access modifiers changed from: private */
    public final Map<String, LocationInfo> locationInfos = new HashMap();
    /* access modifiers changed from: private */
    public final Set<GeoHashQuery> outstandingQueries = new HashSet();
    private Set<GeoHashQuery> queries;
    private double radius;

    private static class LocationInfo {
        final DataSnapshot dataSnapshot;
        final GeoHash geoHash;
        final boolean inGeoQuery;
        final GeoLocation location;

        public LocationInfo(GeoLocation geoLocation, boolean z, DataSnapshot dataSnapshot2) {
            this.location = geoLocation;
            this.inGeoQuery = z;
            this.geoHash = new GeoHash(geoLocation);
            this.dataSnapshot = dataSnapshot2;
        }
    }

    GeoQuery(GeoFire geoFire2, GeoLocation geoLocation, double d) {
        this.geoFire = geoFire2;
        this.center = geoLocation;
        this.radius = d * 1000.0d;
    }

    private boolean locationIsInQuery(GeoLocation geoLocation) {
        return GeoUtils.distance(geoLocation, this.center) <= this.radius;
    }

    private void updateLocationInfo(final DataSnapshot dataSnapshot, final GeoLocation geoLocation) {
        String key = dataSnapshot.getKey();
        LocationInfo locationInfo = this.locationInfos.get(key);
        boolean z = true;
        boolean z2 = locationInfo == null;
        boolean z3 = locationInfo != null && !locationInfo.location.equals(geoLocation);
        if (locationInfo == null || !locationInfo.inGeoQuery) {
            z = false;
        }
        boolean locationIsInQuery = locationIsInQuery(geoLocation);
        if ((z2 || !z) && locationIsInQuery) {
            for (final GeoQueryDataEventListener next : this.eventListeners) {
                this.geoFire.raiseEvent(new Runnable() {
                    public void run() {
                        next.onDataEntered(dataSnapshot, geoLocation);
                    }
                });
            }
        } else if (!z2 && locationIsInQuery) {
            for (final GeoQueryDataEventListener next2 : this.eventListeners) {
                final boolean z4 = z3;
                final DataSnapshot dataSnapshot2 = dataSnapshot;
                final GeoLocation geoLocation2 = geoLocation;
                this.geoFire.raiseEvent(new Runnable() {
                    public void run() {
                        if (z4) {
                            next2.onDataMoved(dataSnapshot2, geoLocation2);
                        }
                        next2.onDataChanged(dataSnapshot2, geoLocation2);
                    }
                });
            }
        } else if (z && !locationIsInQuery) {
            for (final GeoQueryDataEventListener next3 : this.eventListeners) {
                this.geoFire.raiseEvent(new Runnable() {
                    public void run() {
                        next3.onDataExited(dataSnapshot);
                    }
                });
            }
        }
        this.locationInfos.put(key, new LocationInfo(geoLocation, locationIsInQuery(geoLocation), dataSnapshot));
    }

    /* access modifiers changed from: private */
    public boolean geoHashQueriesContainGeoHash(GeoHash geoHash) {
        Set<GeoHashQuery> set = this.queries;
        if (set == null) {
            return false;
        }
        for (GeoHashQuery containsGeoHash : set) {
            if (containsGeoHash.containsGeoHash(geoHash)) {
                return true;
            }
        }
        return false;
    }

    private void reset() {
        for (Map.Entry<GeoHashQuery, Query> value : this.firebaseQueries.entrySet()) {
            ((Query) value.getValue()).removeEventListener(this.childEventLister);
        }
        this.outstandingQueries.clear();
        this.firebaseQueries.clear();
        this.queries = null;
        this.locationInfos.clear();
    }

    private boolean hasListeners() {
        return !this.eventListeners.isEmpty();
    }

    private boolean canFireReady() {
        return this.outstandingQueries.isEmpty();
    }

    /* access modifiers changed from: private */
    public void checkAndFireReady() {
        if (canFireReady()) {
            for (final GeoQueryDataEventListener next : this.eventListeners) {
                this.geoFire.raiseEvent(new Runnable() {
                    public void run() {
                        next.onGeoQueryReady();
                    }
                });
            }
        }
    }

    private void addValueToReadyListener(Query query, final GeoHashQuery geoHashQuery) {
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                synchronized (GeoQuery.this) {
                    GeoQuery.this.outstandingQueries.remove(geoHashQuery);
                    GeoQuery.this.checkAndFireReady();
                }
            }

            public void onCancelled(final DatabaseError databaseError) {
                synchronized (GeoQuery.this) {
                    for (final GeoQueryDataEventListener geoQueryDataEventListener : GeoQuery.this.eventListeners) {
                        GeoQuery.this.geoFire.raiseEvent(new Runnable() {
                            public void run() {
                                geoQueryDataEventListener.onGeoQueryError(databaseError);
                            }
                        });
                    }
                }
            }
        });
    }

    private void setupQueries() {
        Set<GeoHashQuery> set = this.queries;
        if (set == null) {
            set = new HashSet<>();
        }
        Set<GeoHashQuery> queriesAtLocation = GeoHashQuery.queriesAtLocation(this.center, this.radius);
        this.queries = queriesAtLocation;
        for (GeoHashQuery geoHashQuery : set) {
            if (!queriesAtLocation.contains(geoHashQuery)) {
                this.firebaseQueries.get(geoHashQuery).removeEventListener(this.childEventLister);
                this.firebaseQueries.remove(geoHashQuery);
                this.outstandingQueries.remove(geoHashQuery);
            }
        }
        for (GeoHashQuery next : queriesAtLocation) {
            if (!set.contains(next)) {
                this.outstandingQueries.add(next);
                Query endAt = this.geoFire.getDatabaseReference().orderByChild("g").startAt(next.getStartValue()).endAt(next.getEndValue());
                endAt.addChildEventListener(this.childEventLister);
                addValueToReadyListener(endAt, next);
                this.firebaseQueries.put(next, endAt);
            }
        }
        for (Map.Entry<String, LocationInfo> value : this.locationInfos.entrySet()) {
            LocationInfo locationInfo = (LocationInfo) value.getValue();
            if (locationInfo != null) {
                updateLocationInfo(locationInfo.dataSnapshot, locationInfo.location);
            }
        }
        Iterator<Map.Entry<String, LocationInfo>> it = this.locationInfos.entrySet().iterator();
        while (it.hasNext()) {
            if (!geoHashQueriesContainGeoHash(((LocationInfo) it.next().getValue()).geoHash)) {
                it.remove();
            }
        }
        checkAndFireReady();
    }

    /* access modifiers changed from: private */
    public void childAdded(DataSnapshot dataSnapshot) {
        GeoLocation locationValue = GeoFire.getLocationValue(dataSnapshot);
        if (locationValue != null) {
            updateLocationInfo(dataSnapshot, locationValue);
            return;
        }
        throw new AssertionError("Got Datasnapshot without location with key " + dataSnapshot.getKey());
    }

    /* access modifiers changed from: private */
    public void childChanged(DataSnapshot dataSnapshot) {
        GeoLocation locationValue = GeoFire.getLocationValue(dataSnapshot);
        if (locationValue != null) {
            updateLocationInfo(dataSnapshot, locationValue);
            return;
        }
        throw new AssertionError("Got Datasnapshot without location with key " + dataSnapshot.getKey());
    }

    /* access modifiers changed from: private */
    public void childRemoved(DataSnapshot dataSnapshot) {
        final String key = dataSnapshot.getKey();
        if (this.locationInfos.get(key) != null) {
            this.geoFire.getDatabaseRefForKey(key).addListenerForSingleValueEvent(new ValueEventListener() {
                public void onCancelled(DatabaseError databaseError) {
                }

                /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
                    r6 = (com.firebase.geofire.GeoQuery.LocationInfo) com.firebase.geofire.GeoQuery.access$800(r5.this$0).remove(r3);
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onDataChange(com.google.firebase.database.DataSnapshot r6) {
                    /*
                        r5 = this;
                        com.firebase.geofire.GeoQuery r0 = com.firebase.geofire.GeoQuery.this
                        monitor-enter(r0)
                        com.firebase.geofire.GeoLocation r6 = com.firebase.geofire.GeoFire.getLocationValue(r6)     // Catch:{ all -> 0x0055 }
                        if (r6 == 0) goto L_0x000f
                        com.firebase.geofire.core.GeoHash r1 = new com.firebase.geofire.core.GeoHash     // Catch:{ all -> 0x0055 }
                        r1.<init>((com.firebase.geofire.GeoLocation) r6)     // Catch:{ all -> 0x0055 }
                        goto L_0x0010
                    L_0x000f:
                        r1 = 0
                    L_0x0010:
                        if (r1 == 0) goto L_0x001a
                        com.firebase.geofire.GeoQuery r6 = com.firebase.geofire.GeoQuery.this     // Catch:{ all -> 0x0055 }
                        boolean r6 = r6.geoHashQueriesContainGeoHash(r1)     // Catch:{ all -> 0x0055 }
                        if (r6 != 0) goto L_0x0053
                    L_0x001a:
                        com.firebase.geofire.GeoQuery r6 = com.firebase.geofire.GeoQuery.this     // Catch:{ all -> 0x0055 }
                        java.util.Map r6 = r6.locationInfos     // Catch:{ all -> 0x0055 }
                        java.lang.String r1 = r3     // Catch:{ all -> 0x0055 }
                        java.lang.Object r6 = r6.remove(r1)     // Catch:{ all -> 0x0055 }
                        com.firebase.geofire.GeoQuery$LocationInfo r6 = (com.firebase.geofire.GeoQuery.LocationInfo) r6     // Catch:{ all -> 0x0055 }
                        if (r6 == 0) goto L_0x0053
                        boolean r1 = r6.inGeoQuery     // Catch:{ all -> 0x0055 }
                        if (r1 == 0) goto L_0x0053
                        com.firebase.geofire.GeoQuery r1 = com.firebase.geofire.GeoQuery.this     // Catch:{ all -> 0x0055 }
                        java.util.Set r1 = r1.eventListeners     // Catch:{ all -> 0x0055 }
                        java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0055 }
                    L_0x0038:
                        boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0055 }
                        if (r2 == 0) goto L_0x0053
                        java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0055 }
                        com.firebase.geofire.GeoQueryDataEventListener r2 = (com.firebase.geofire.GeoQueryDataEventListener) r2     // Catch:{ all -> 0x0055 }
                        com.firebase.geofire.GeoQuery r3 = com.firebase.geofire.GeoQuery.this     // Catch:{ all -> 0x0055 }
                        com.firebase.geofire.GeoFire r3 = r3.geoFire     // Catch:{ all -> 0x0055 }
                        com.firebase.geofire.GeoQuery$7$1 r4 = new com.firebase.geofire.GeoQuery$7$1     // Catch:{ all -> 0x0055 }
                        r4.<init>(r2, r6)     // Catch:{ all -> 0x0055 }
                        r3.raiseEvent(r4)     // Catch:{ all -> 0x0055 }
                        goto L_0x0038
                    L_0x0053:
                        monitor-exit(r0)     // Catch:{ all -> 0x0055 }
                        return
                    L_0x0055:
                        r6 = move-exception
                        monitor-exit(r0)     // Catch:{ all -> 0x0055 }
                        throw r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.firebase.geofire.GeoQuery.C08507.onDataChange(com.google.firebase.database.DataSnapshot):void");
                }
            });
        }
    }

    public synchronized void addGeoQueryEventListener(GeoQueryEventListener geoQueryEventListener) {
        addGeoQueryDataEventListener(new EventListenerBridge(geoQueryEventListener));
    }

    public synchronized void addGeoQueryDataEventListener(final GeoQueryDataEventListener geoQueryDataEventListener) {
        if (!this.eventListeners.contains(geoQueryDataEventListener)) {
            this.eventListeners.add(geoQueryDataEventListener);
            if (this.queries == null) {
                setupQueries();
            } else {
                for (Map.Entry next : this.locationInfos.entrySet()) {
                    String str = (String) next.getKey();
                    final LocationInfo locationInfo = (LocationInfo) next.getValue();
                    if (locationInfo.inGeoQuery) {
                        this.geoFire.raiseEvent(new Runnable() {
                            public void run() {
                                geoQueryDataEventListener.onDataEntered(locationInfo.dataSnapshot, locationInfo.location);
                            }
                        });
                    }
                }
                if (canFireReady()) {
                    this.geoFire.raiseEvent(new Runnable() {
                        public void run() {
                            geoQueryDataEventListener.onGeoQueryReady();
                        }
                    });
                }
            }
        } else {
            throw new IllegalArgumentException("Added the same listener twice to a GeoQuery!");
        }
    }

    public synchronized void removeGeoQueryEventListener(GeoQueryEventListener geoQueryEventListener) {
        removeGeoQueryEventListener((GeoQueryDataEventListener) new EventListenerBridge(geoQueryEventListener));
    }

    public synchronized void removeGeoQueryEventListener(GeoQueryDataEventListener geoQueryDataEventListener) {
        if (this.eventListeners.contains(geoQueryDataEventListener)) {
            this.eventListeners.remove(geoQueryDataEventListener);
            if (!hasListeners()) {
                reset();
            }
        } else {
            throw new IllegalArgumentException("Trying to remove listener that was removed or not added!");
        }
    }

    public synchronized void removeAllListeners() {
        this.eventListeners.clear();
        reset();
    }

    public synchronized GeoLocation getCenter() {
        return this.center;
    }

    public synchronized void setCenter(GeoLocation geoLocation) {
        this.center = geoLocation;
        if (hasListeners()) {
            setupQueries();
        }
    }

    public synchronized double getRadius() {
        return this.radius / 1000.0d;
    }

    public synchronized void setRadius(double d) {
        this.radius = GeoUtils.capRadius(d) * 1000.0d;
        if (hasListeners()) {
            setupQueries();
        }
    }

    public synchronized void setLocation(GeoLocation geoLocation, double d) {
        this.center = geoLocation;
        this.radius = GeoUtils.capRadius(d) * 1000.0d;
        if (hasListeners()) {
            setupQueries();
        }
    }
}

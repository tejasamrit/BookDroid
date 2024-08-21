package com.google.firebase.database.core;

import com.google.firebase.database.core.view.QuerySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ZombieEventManager implements EventRegistrationZombieListener {
    private static ZombieEventManager defaultInstance = new ZombieEventManager();
    final HashMap<EventRegistration, List<EventRegistration>> globalEventRegistrations = new HashMap<>();

    private ZombieEventManager() {
    }

    public static ZombieEventManager getInstance() {
        return defaultInstance;
    }

    public void recordEventRegistration(EventRegistration eventRegistration) {
        synchronized (this.globalEventRegistrations) {
            List list = this.globalEventRegistrations.get(eventRegistration);
            if (list == null) {
                list = new ArrayList();
                this.globalEventRegistrations.put(eventRegistration, list);
            }
            list.add(eventRegistration);
            if (!eventRegistration.getQuerySpec().isDefault()) {
                EventRegistration clone = eventRegistration.clone(QuerySpec.defaultQueryAtPath(eventRegistration.getQuerySpec().getPath()));
                List list2 = this.globalEventRegistrations.get(clone);
                if (list2 == null) {
                    list2 = new ArrayList();
                    this.globalEventRegistrations.put(clone, list2);
                }
                list2.add(eventRegistration);
            }
            eventRegistration.setIsUserInitiated(true);
            eventRegistration.setOnZombied(this);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        r1 = r7.clone(com.google.firebase.database.core.view.QuerySpec.defaultQueryAtPath(r7.getQuerySpec().getPath()));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void unRecordEventRegistration(com.google.firebase.database.core.EventRegistration r7) {
        /*
            r6 = this;
            java.util.HashMap<com.google.firebase.database.core.EventRegistration, java.util.List<com.google.firebase.database.core.EventRegistration>> r0 = r6.globalEventRegistrations
            monitor-enter(r0)
            java.util.HashMap<com.google.firebase.database.core.EventRegistration, java.util.List<com.google.firebase.database.core.EventRegistration>> r1 = r6.globalEventRegistrations     // Catch:{ all -> 0x0081 }
            java.lang.Object r1 = r1.get(r7)     // Catch:{ all -> 0x0081 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x0081 }
            r2 = 1
            r3 = 0
            r4 = 0
            if (r1 == 0) goto L_0x0030
        L_0x0010:
            int r5 = r1.size()     // Catch:{ all -> 0x0081 }
            if (r4 >= r5) goto L_0x0024
            java.lang.Object r5 = r1.get(r4)     // Catch:{ all -> 0x0081 }
            if (r5 != r7) goto L_0x0021
            r1.remove(r4)     // Catch:{ all -> 0x0081 }
            r4 = 1
            goto L_0x0025
        L_0x0021:
            int r4 = r4 + 1
            goto L_0x0010
        L_0x0024:
            r4 = 0
        L_0x0025:
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x0030
            java.util.HashMap<com.google.firebase.database.core.EventRegistration, java.util.List<com.google.firebase.database.core.EventRegistration>> r1 = r6.globalEventRegistrations     // Catch:{ all -> 0x0081 }
            r1.remove(r7)     // Catch:{ all -> 0x0081 }
        L_0x0030:
            if (r4 != 0) goto L_0x003a
            boolean r1 = r7.isUserInitiated()     // Catch:{ all -> 0x0081 }
            if (r1 != 0) goto L_0x0039
            goto L_0x003a
        L_0x0039:
            r2 = 0
        L_0x003a:
            com.google.firebase.database.core.utilities.Utilities.hardAssert(r2)     // Catch:{ all -> 0x0081 }
            com.google.firebase.database.core.view.QuerySpec r1 = r7.getQuerySpec()     // Catch:{ all -> 0x0081 }
            boolean r1 = r1.isDefault()     // Catch:{ all -> 0x0081 }
            if (r1 != 0) goto L_0x007f
            com.google.firebase.database.core.view.QuerySpec r1 = r7.getQuerySpec()     // Catch:{ all -> 0x0081 }
            com.google.firebase.database.core.Path r1 = r1.getPath()     // Catch:{ all -> 0x0081 }
            com.google.firebase.database.core.view.QuerySpec r1 = com.google.firebase.database.core.view.QuerySpec.defaultQueryAtPath(r1)     // Catch:{ all -> 0x0081 }
            com.google.firebase.database.core.EventRegistration r1 = r7.clone(r1)     // Catch:{ all -> 0x0081 }
            java.util.HashMap<com.google.firebase.database.core.EventRegistration, java.util.List<com.google.firebase.database.core.EventRegistration>> r2 = r6.globalEventRegistrations     // Catch:{ all -> 0x0081 }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x0081 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x0081 }
            if (r2 == 0) goto L_0x007f
        L_0x0061:
            int r4 = r2.size()     // Catch:{ all -> 0x0081 }
            if (r3 >= r4) goto L_0x0074
            java.lang.Object r4 = r2.get(r3)     // Catch:{ all -> 0x0081 }
            if (r4 != r7) goto L_0x0071
            r2.remove(r3)     // Catch:{ all -> 0x0081 }
            goto L_0x0074
        L_0x0071:
            int r3 = r3 + 1
            goto L_0x0061
        L_0x0074:
            boolean r7 = r2.isEmpty()     // Catch:{ all -> 0x0081 }
            if (r7 == 0) goto L_0x007f
            java.util.HashMap<com.google.firebase.database.core.EventRegistration, java.util.List<com.google.firebase.database.core.EventRegistration>> r7 = r6.globalEventRegistrations     // Catch:{ all -> 0x0081 }
            r7.remove(r1)     // Catch:{ all -> 0x0081 }
        L_0x007f:
            monitor-exit(r0)     // Catch:{ all -> 0x0081 }
            return
        L_0x0081:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0081 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.core.ZombieEventManager.unRecordEventRegistration(com.google.firebase.database.core.EventRegistration):void");
    }

    public void zombifyForRemove(EventRegistration eventRegistration) {
        synchronized (this.globalEventRegistrations) {
            List list = this.globalEventRegistrations.get(eventRegistration);
            if (list != null && !list.isEmpty()) {
                if (eventRegistration.getQuerySpec().isDefault()) {
                    HashSet hashSet = new HashSet();
                    for (int size = list.size() - 1; size >= 0; size--) {
                        EventRegistration eventRegistration2 = (EventRegistration) list.get(size);
                        if (!hashSet.contains(eventRegistration2.getQuerySpec())) {
                            hashSet.add(eventRegistration2.getQuerySpec());
                            eventRegistration2.zombify();
                        }
                    }
                } else {
                    ((EventRegistration) list.get(0)).zombify();
                }
            }
        }
    }

    public void onZombied(EventRegistration eventRegistration) {
        unRecordEventRegistration(eventRegistration);
    }
}

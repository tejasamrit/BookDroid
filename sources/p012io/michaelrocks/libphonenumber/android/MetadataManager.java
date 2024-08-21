package p012io.michaelrocks.libphonenumber.android;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import p012io.michaelrocks.libphonenumber.android.Phonemetadata;

/* renamed from: io.michaelrocks.libphonenumber.android.MetadataManager */
final class MetadataManager {
    static final String ALTERNATE_FORMATS_FILE_PREFIX = "/io/michaelrocks/libphonenumber/android/data/PhoneNumberAlternateFormatsProto";
    static final String MULTI_FILE_PHONE_NUMBER_METADATA_FILE_PREFIX = "/io/michaelrocks/libphonenumber/android/data/PhoneNumberMetadataProto";
    static final String SHORT_NUMBER_METADATA_FILE_PREFIX = "/io/michaelrocks/libphonenumber/android/data/ShortNumberMetadataProto";
    private static final Logger logger = Logger.getLogger(MetadataManager.class.getName());
    private final Set<Integer> alternateFormatsCountryCodes = AlternateFormatsCountryCodeSet.getCountryCodeSet();
    private final ConcurrentHashMap<Integer, Phonemetadata.PhoneMetadata> alternateFormatsMap = new ConcurrentHashMap<>();
    private final MetadataLoader metadataLoader;
    private final ConcurrentHashMap<String, Phonemetadata.PhoneMetadata> shortNumberMetadataMap = new ConcurrentHashMap<>();
    private final Set<String> shortNumberMetadataRegionCodes = ShortNumbersRegionCodeSet.getRegionCodeSet();

    MetadataManager(MetadataLoader metadataLoader2) {
        this.metadataLoader = metadataLoader2;
    }

    /* access modifiers changed from: package-private */
    public Phonemetadata.PhoneMetadata getAlternateFormatsForCountry(int i, String str) {
        if (!this.alternateFormatsCountryCodes.contains(Integer.valueOf(i))) {
            return null;
        }
        return getMetadataFromMultiFilePrefix(Integer.valueOf(i), this.alternateFormatsMap, str);
    }

    /* access modifiers changed from: package-private */
    public Phonemetadata.PhoneMetadata getShortNumberMetadataForRegion(String str, String str2) {
        if (!this.shortNumberMetadataRegionCodes.contains(str)) {
            return null;
        }
        return getMetadataFromMultiFilePrefix(str, this.shortNumberMetadataMap, str2);
    }

    /* access modifiers changed from: package-private */
    public Set<String> getSupportedShortNumberRegions() {
        return Collections.unmodifiableSet(this.shortNumberMetadataRegionCodes);
    }

    /* access modifiers changed from: package-private */
    public <T> Phonemetadata.PhoneMetadata getMetadataFromMultiFilePrefix(T t, ConcurrentHashMap<T, Phonemetadata.PhoneMetadata> concurrentHashMap, String str) {
        Phonemetadata.PhoneMetadata phoneMetadata = concurrentHashMap.get(t);
        if (phoneMetadata != null) {
            return phoneMetadata;
        }
        String str2 = str + "_" + t;
        List<Phonemetadata.PhoneMetadata> metadataFromSingleFileName = getMetadataFromSingleFileName(str2, this.metadataLoader);
        if (metadataFromSingleFileName.size() > 1) {
            logger.log(Level.WARNING, "more than one metadata in file " + str2);
        }
        Phonemetadata.PhoneMetadata phoneMetadata2 = metadataFromSingleFileName.get(0);
        Phonemetadata.PhoneMetadata putIfAbsent = concurrentHashMap.putIfAbsent(t, phoneMetadata2);
        return putIfAbsent != null ? putIfAbsent : phoneMetadata2;
    }

    private static List<Phonemetadata.PhoneMetadata> getMetadataFromSingleFileName(String str, MetadataLoader metadataLoader2) {
        InputStream loadMetadata = metadataLoader2.loadMetadata(str);
        if (loadMetadata != null) {
            List<Phonemetadata.PhoneMetadata> metadataList = loadMetadataAndCloseInput(loadMetadata).getMetadataList();
            if (metadataList.size() != 0) {
                return metadataList;
            }
            throw new IllegalStateException("empty metadata: " + str);
        }
        throw new IllegalStateException("missing metadata: " + str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0034 A[SYNTHETIC, Splitter:B:24:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0038 A[Catch:{ IOException -> 0x003c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static p012io.michaelrocks.libphonenumber.android.Phonemetadata.PhoneMetadataCollection loadMetadataAndCloseInput(java.io.InputStream r5) {
        /*
            java.lang.String r0 = "error closing input stream (ignored)"
            java.lang.String r1 = "cannot load/parse metadata"
            r2 = 0
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x002b }
            r3.<init>(r5)     // Catch:{ IOException -> 0x002b }
            io.michaelrocks.libphonenumber.android.Phonemetadata$PhoneMetadataCollection r2 = new io.michaelrocks.libphonenumber.android.Phonemetadata$PhoneMetadataCollection     // Catch:{ all -> 0x0026 }
            r2.<init>()     // Catch:{ all -> 0x0026 }
            r2.readExternal(r3)     // Catch:{ IOException -> 0x001f }
            r3.close()     // Catch:{ IOException -> 0x0016 }
            goto L_0x001e
        L_0x0016:
            r5 = move-exception
            java.util.logging.Logger r1 = logger
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            r1.log(r3, r0, r5)
        L_0x001e:
            return r2
        L_0x001f:
            r2 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException     // Catch:{ all -> 0x0026 }
            r4.<init>(r1, r2)     // Catch:{ all -> 0x0026 }
            throw r4     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r1 = move-exception
            r2 = r3
            goto L_0x0032
        L_0x0029:
            r1 = move-exception
            goto L_0x0032
        L_0x002b:
            r3 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException     // Catch:{ all -> 0x0029 }
            r4.<init>(r1, r3)     // Catch:{ all -> 0x0029 }
            throw r4     // Catch:{ all -> 0x0029 }
        L_0x0032:
            if (r2 == 0) goto L_0x0038
            r2.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x0044
        L_0x0038:
            r5.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x0044
        L_0x003c:
            r5 = move-exception
            java.util.logging.Logger r2 = logger
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            r2.log(r3, r0, r5)
        L_0x0044:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p012io.michaelrocks.libphonenumber.android.MetadataManager.loadMetadataAndCloseInput(java.io.InputStream):io.michaelrocks.libphonenumber.android.Phonemetadata$PhoneMetadataCollection");
    }
}

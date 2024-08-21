package p012io.michaelrocks.libphonenumber.android;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import p012io.michaelrocks.libphonenumber.android.Phonemetadata;

/* renamed from: io.michaelrocks.libphonenumber.android.MultiFileMetadataSourceImpl */
final class MultiFileMetadataSourceImpl implements MetadataSource {
    private final String alternateFormatsFilePrefix;
    private final ConcurrentHashMap<String, Phonemetadata.PhoneMetadata> geographicalRegions;
    private final MetadataManager metadataManager;
    private final ConcurrentHashMap<Integer, Phonemetadata.PhoneMetadata> nonGeographicalRegions;
    private final String phoneNumberMetadataFilePrefix;
    private final String shortNumberFilePrefix;

    MultiFileMetadataSourceImpl(String str, String str2, String str3, MetadataLoader metadataLoader) {
        this.geographicalRegions = new ConcurrentHashMap<>();
        this.nonGeographicalRegions = new ConcurrentHashMap<>();
        this.phoneNumberMetadataFilePrefix = str;
        this.alternateFormatsFilePrefix = str2;
        this.shortNumberFilePrefix = str3;
        this.metadataManager = new MetadataManager(metadataLoader);
    }

    MultiFileMetadataSourceImpl(MetadataLoader metadataLoader) {
        this("/io/michaelrocks/libphonenumber/android/data/PhoneNumberMetadataProto", "/io/michaelrocks/libphonenumber/android/data/PhoneNumberAlternateFormatsProto", "/io/michaelrocks/libphonenumber/android/data/ShortNumberMetadataProto", metadataLoader);
    }

    public Phonemetadata.PhoneMetadata getMetadataForRegion(String str) {
        return this.metadataManager.getMetadataFromMultiFilePrefix(str, this.geographicalRegions, this.phoneNumberMetadataFilePrefix);
    }

    public Phonemetadata.PhoneMetadata getMetadataForNonGeographicalRegion(int i) {
        if (!isNonGeographical(i)) {
            return null;
        }
        return this.metadataManager.getMetadataFromMultiFilePrefix(Integer.valueOf(i), this.nonGeographicalRegions, this.phoneNumberMetadataFilePrefix);
    }

    public Phonemetadata.PhoneMetadata getAlternateFormatsForCountry(int i) {
        return this.metadataManager.getAlternateFormatsForCountry(i, this.alternateFormatsFilePrefix);
    }

    public Phonemetadata.PhoneMetadata getShortNumberMetadataForRegion(String str) {
        return this.metadataManager.getShortNumberMetadataForRegion(str, this.shortNumberFilePrefix);
    }

    private boolean isNonGeographical(int i) {
        List list = CountryCodeToRegionCodeMap.getCountryCodeToRegionCodeMap().get(Integer.valueOf(i));
        if (list.size() != 1 || !PhoneNumberUtil.REGION_CODE_FOR_NON_GEO_ENTITY.equals(list.get(0))) {
            return false;
        }
        return true;
    }
}

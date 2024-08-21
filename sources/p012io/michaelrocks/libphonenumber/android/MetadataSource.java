package p012io.michaelrocks.libphonenumber.android;

import p012io.michaelrocks.libphonenumber.android.Phonemetadata;

/* renamed from: io.michaelrocks.libphonenumber.android.MetadataSource */
interface MetadataSource {
    Phonemetadata.PhoneMetadata getAlternateFormatsForCountry(int i);

    Phonemetadata.PhoneMetadata getMetadataForNonGeographicalRegion(int i);

    Phonemetadata.PhoneMetadata getMetadataForRegion(String str);

    Phonemetadata.PhoneMetadata getShortNumberMetadataForRegion(String str);
}

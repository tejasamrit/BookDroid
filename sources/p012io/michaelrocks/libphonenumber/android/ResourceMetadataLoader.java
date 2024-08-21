package p012io.michaelrocks.libphonenumber.android;

import java.io.InputStream;

/* renamed from: io.michaelrocks.libphonenumber.android.ResourceMetadataLoader */
class ResourceMetadataLoader implements MetadataLoader {
    private final Class<?> loaderClass;

    public ResourceMetadataLoader() {
        this(ResourceMetadataLoader.class);
    }

    public ResourceMetadataLoader(Class<?> cls) {
        this.loaderClass = cls;
    }

    public InputStream loadMetadata(String str) {
        return this.loaderClass.getResourceAsStream(str);
    }
}

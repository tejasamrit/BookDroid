package p012io.michaelrocks.libphonenumber.android;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: io.michaelrocks.libphonenumber.android.AssetsMetadataLoader */
public class AssetsMetadataLoader implements MetadataLoader {
    private final AssetManager assetManager;

    public AssetsMetadataLoader(AssetManager assetManager2) {
        this.assetManager = assetManager2;
    }

    public InputStream loadMetadata(String str) {
        try {
            return this.assetManager.open(str.substring(1));
        } catch (IOException unused) {
            return null;
        }
    }
}

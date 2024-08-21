package com.google.firebase.platforminfo;

import android.content.Context;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.platforminfo.LibraryVersionComponent;

/* compiled from: LibraryVersionComponent */
final /* synthetic */ class LibraryVersionComponent$$Lambda$1 implements ComponentFactory {
    private final String arg$1;
    private final LibraryVersionComponent.VersionExtractor arg$2;

    private LibraryVersionComponent$$Lambda$1(String str, LibraryVersionComponent.VersionExtractor versionExtractor) {
        this.arg$1 = str;
        this.arg$2 = versionExtractor;
    }

    public static ComponentFactory lambdaFactory$(String str, LibraryVersionComponent.VersionExtractor versionExtractor) {
        return new LibraryVersionComponent$$Lambda$1(str, versionExtractor);
    }

    public Object create(ComponentContainer componentContainer) {
        return LibraryVersion.create(this.arg$1, this.arg$2.extract((Context) componentContainer.get(Context.class)));
    }
}

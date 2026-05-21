package mono.factories.mod.newloader.api;

import mono.factories.mod.newloader.source.provider.CodeSource;

import java.io.IOException;

public interface LoaderEntryInfoLoader {
    LoaderEntryInfo load(CodeSource codeSource);
}

package mono.factories.mod.newloader.dml;

import mono.factories.mod.newloader.source.provider.CodeSource;

public interface ModLoader {
    void load(CodeSource source, DMLConfig config);
}

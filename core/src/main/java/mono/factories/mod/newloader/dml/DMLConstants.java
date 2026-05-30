package mono.factories.mod.newloader.dml;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.DefaultHolder;

public class DMLConstants {
    public static final DefaultHolder<Identifier> DEFAULT_MOD_LOADER_ID = new DefaultHolder<>(new Identifier("DMLoader"));
    public static final Identifier DEFAULT_MOD_PATH = new Identifier("mod");
}

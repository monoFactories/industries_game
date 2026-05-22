package mono.factories.mod.registries;


import mono.factories.mod.Mod;
import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;

public class ModRegistries {
    public static final Registry<Mod> mods = new DefaultRegistry<>(); // "root:mod_id" -> mod
}

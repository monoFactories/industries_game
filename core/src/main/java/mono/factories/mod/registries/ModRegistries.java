package mono.factories.mod.registries;


import mono.factories.mod.Mod;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;

public class ModRegistries {
    public static final Registry<Mod> mods = new StandardRegistry<>(); // "root:mod_id" -> mod
}

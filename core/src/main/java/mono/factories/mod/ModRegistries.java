package mono.factories.mod;


import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;

public class ModRegistries {
    public static final Registry<Mod> mods = new StandardRegistry<>(); // "root:mod_id" -> mod
}

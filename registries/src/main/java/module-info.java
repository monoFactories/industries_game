module registries {
    requires transitive it.unimi.dsi.fastutil;
    requires transitive com.google.gson;
    exports mono.factories.registries.registry;
    exports mono.factories.registries.id;
}
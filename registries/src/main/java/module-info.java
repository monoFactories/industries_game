module registries {
    requires transitive it.unimi.dsi.fastutil;
    requires transitive com.google.gson;
    requires transitive org.apache.logging.log4j;
    requires transitive org.apache.logging.log4j.core;
    exports mono.factories.registries.registry;
    exports mono.factories.registries.id;
    exports mono.factories.registries.storage;
    exports mono.factories.registries.actions;
    exports mono.factories.utils.exceptions;
    exports mono.factories.registries.registry.protection;
    exports mono.factories.utils.io;
}
package mono.factories.mod.newloader.dml;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;

public record ModEntry(Identifier modID, Registry<Object> data) {
}

package mono.factories.dependencies;

import mono.factories.registries.id.Identifiable;
import mono.factories.registries.id.Identifier;

public interface HasDependency extends Identifiable {
    Identifier[] dependencies();
}

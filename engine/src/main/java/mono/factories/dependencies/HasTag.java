package mono.factories.dependencies;

import mono.factories.registries.id.Identifiable;
import mono.factories.registries.id.Identifier;

public interface HasTag extends Identifiable {
    Identifier[] dependencies();
    Identifier[] tags();
}

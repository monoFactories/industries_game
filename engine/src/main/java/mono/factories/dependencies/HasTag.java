package mono.factories.dependencies;

import mono.factories.registries.id.Identifier;

public interface HasTag {
    Identifier[] dependencies();
    Identifier[] tags();
}

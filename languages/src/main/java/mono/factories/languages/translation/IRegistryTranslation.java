package mono.factories.languages.translation;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.Storage2;

public interface IRegistryTranslation {
    void translate(Storage2<Identifier, String> translateKey);
}

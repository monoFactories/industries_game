package mono.factories.languages.translation;

import mono.factories.languages.language.Language;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.RegistryRegistries;
import mono.factories.registries.storage.Storage2;

public final class RegistryFormatTranslator {
    public static void translate(RegistryTranslationFormat format, String value) {
        if (format != null && value != null) {
            Registry<?> registry = RegistryRegistries.root.get(format.registryID);
            if (registry instanceof IRegistryTranslation translate) {
                translate.translate(new Storage2<>(format.elementID, value));
            }
        }
    }

    public static void tryTranslate(String key, String value) {
        Storage2<Boolean, RegistryTranslationFormat> store2 = RegistryTranslationFormat.tryParse(key);
        if (store2.a()) {
            RegistryTranslationFormat parsed = store2.b();
            translate(parsed, value);
        }
    }

    public static void iterateTranslate(Language language) {
        if (language != null) {
            language.getTranslationMap().forEach((RegistryFormatTranslator::tryTranslate));
        }
    }
}

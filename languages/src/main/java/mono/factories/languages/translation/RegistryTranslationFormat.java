package mono.factories.languages.translation;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.Storage2;

import java.util.Objects;

public final class RegistryTranslationFormat { /* "id:registry.id.element",   "registry.element" -> "root_mod:registry.root_mod:element"*/
    public static final char IDENTIFIERS_SPLITTER = '.';
    public final Identifier registryID, elementID;

    public RegistryTranslationFormat(Identifier elementID, Identifier registryID) {
        if (elementID == null || registryID == null) throw new NullPointerException("One of the parameters is null");
        this.elementID = elementID;
        this.registryID = registryID;
    }

    public static RegistryTranslationFormat parse(String str) {
         String[] identifiers = str.split(String.valueOf(IDENTIFIERS_SPLITTER));
         return new RegistryTranslationFormat(new Identifier(identifiers[0]), new Identifier(identifiers[1]));
    }
    public static Storage2<Boolean, RegistryTranslationFormat> tryParse(String str) {
        try {
            String[] identifiers = str.split(String.valueOf(IDENTIFIERS_SPLITTER));
            return new Storage2<>(true, new RegistryTranslationFormat(new Identifier(identifiers[0]), new Identifier(identifiers[1])));
        } catch (Exception e) {
            return new Storage2<>(false, null);
        }
    }

    @Override
    public String toString() {
        return "[registry=" + registryID + ", element=" + elementID + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof RegistryTranslationFormat that)) return false;
        return Objects.equals(registryID, that.registryID) && Objects.equals(elementID, that.elementID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registryID, elementID);
    }
}

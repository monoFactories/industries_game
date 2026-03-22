package mono.factories.languages.language;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.RegistryRegistries;
import mono.factories.registries.registry.StandardRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanguageRegistries {
    // opcode -> name; en_en = English;
    // Identifier (opcode) = String name of language; root:en_en = English;
    // To obtain an Identifier object from a language opcode, you must use the Identifier(String) constructor.
    //
    //For example, the opcode en_en has Identifier(“en_en”) = “root:en_en”.

    //see Languages class

    public static final Registry<String> LANG_OPCODES = new StandardRegistry<>();
}

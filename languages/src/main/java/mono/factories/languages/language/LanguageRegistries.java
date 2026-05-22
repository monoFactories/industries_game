package mono.factories.languages.language;

import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;

public class LanguageRegistries {
    // opcode -> name; en_en = English;
    // Identifier (opcode) = String name of language; root:en_en = English;
    // To obtain an Identifier object from a language opcode, you must use the Identifier(String) constructor.
    //
    //For example, the opcode en_en has Identifier(“en_en”) = “root:en_en”.

    //see Languages class

    public static final Registry<String> LANG_OPCODES = new DefaultRegistry<>();
}

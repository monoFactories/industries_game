package mono.factories.languages.language;

import mono.factories.registries.id.Identifier;

import static mono.factories.languages.language.LanguageRegistries.LANG_OPCODES;

public class Languages {
    public static final Identifier
            CHINA = reg("zh_CN", "china"),
            ENGLISH = reg("en", "international"),
            JAPAN = reg("ja_JP", "japan"),
            SPAIN = reg("es_ES", "spain"),
            ARABIAN = reg("ar", "arabic"),
            PORTUGAL = reg("pt_PT", "portugal"),
            GERMAN = reg("de_DE", "germany"),
            FRANCE = reg("fr_FR", "france"),
            RUSSIA = reg("ru_RU", "russia"),
            INDONESIAN = reg("id_ID", "indonesia"),
            KOREA = reg("ko_KR", "korea"),
            TURKEY = reg("tr_TR", "turkey"),
            MALAYA = reg("ms_MY", "malaysia"),
            ITALIA = reg("it_IT", "italy"),
            VIETNAM = reg("vi_VN", "vietnam"),
            THAILAND = reg("th_TH", "thailand"),
            NEDERLAND = reg("nl_NL", "netherlands"),
            POLAND = reg("pl_PL", "poland"),
            SWEDEN = reg("sv_SE", "sweden"),
            FINLAND = reg("fi_FI", "finland");

    private static Identifier reg(String code, String name) {
        return LANG_OPCODES.register(new Identifier(code), name);
    }
}

package mono.factories.languages.language;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.registries.id.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class Language {
    public static final Logger logger = LogManager.getLogger(Language.class);
    public static final Gson gson = new Gson();
    private static Language currentLanguage = new Language(Languages.ENGLISH.getName(), Languages.ENGLISH);

    private final Object2ObjectOpenHashMap<String, String> map = new Object2ObjectOpenHashMap<>();
    private final String name;
    private final Identifier opcode;

    public Language(String name, Identifier opcode) {
        this.name = name;
        this.opcode = opcode;
        logger.debug("Created new Language instance: name={}, opcode={}", name, opcode);
    }

    public String getName() {
        return name;
    }

    public Identifier getOpcode() {
        return opcode;
    }

    // Метод для получения map переводов
    public Object2ObjectOpenHashMap<String, String> getTranslationMap() {
        return map;
    }

    public static String translate(String str) {
        String result = currentLanguage.map.get(str);
        if (result == null) {
            logger.warn("Translation not found for key: {}", str);
            return str; // Возвращаем оригинальный текст
        }
        logger.debug("Translated '{}' to '{}'", str, result);
        return result;
    }

    public static void setCurrentLanguage(Language currentLanguage) {
        if (currentLanguage != null) {
            Language.currentLanguage = currentLanguage;
            logger.info("Current language changed to: {}", currentLanguage.getName());
        } else {
            logger.warn("Attempt to set null as current language");
        }
    }

    // Выделяем логику parse в отдельный метод
    public static void parseLanguage(InputStream is) {
        try (Reader r = new InputStreamReader(is)) {
            logger.debug("Starting to parse language file");
            JsonObject jo = gson.fromJson(r, JsonObject.class);
            fillTranslationMap(jo);
        } catch (Exception e) {
            logger.error("Error while parsing language file", e);
        }
    }

    // Отдельный метод для заполнения map — разделение логики
    private static void fillTranslationMap(JsonObject jo) {
        int[] entriesCount = {0};
        jo.entrySet().forEach(sje -> {
            String key = sje.getKey();
            JsonElement e = sje.getValue();
            if (e.isJsonPrimitive() && e.getAsJsonPrimitive().isString()) {
                String value = e.getAsString();
                currentLanguage.map.put(key, value);
                entriesCount[0]++;
            }
        });
        logger.info("Successfully parsed {} translation entries", entriesCount[0]);
    }
}
package mono.factories.registries.id;

import com.google.gson.*;

import java.lang.reflect.Type;

public final class Identifier implements Cloneable { // constructors-public: (String), (String, String)
    public static final IdentifierJson JSON = new IdentifierJson();
    public static final String STANDARD_SPACE = "root_mod";
    public static final char STANDARD_SEPARATION_CHARACTER = ':';

    private final String space;
    private final String name;

    public Identifier(String space, String name) {
        if (space == null || name == null) throw new NullPointerException("“One of the parameters is null”");
        this.space = space;
        this.name = name;
    }

    private Identifier(String[] parts) {
        this(parts[0], parts[1]);
    }

    private Identifier(String id, char separator, String standardSpace) {
        this(parser(id, separator, standardSpace));
    }

    public Identifier(String id) {
        this(id, STANDARD_SEPARATION_CHARACTER, STANDARD_SPACE);
    }

    public String getName() {
        return name;
    }

    public String getSpace() {
        return space;
    }

    @Override
    public Identifier clone() throws CloneNotSupportedException {
        Identifier identifier = (Identifier) super.clone();
        return new Identifier(this.space, this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Identifier id) return id.name.equals(name) && id.space.equals(space);
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * space.hashCode() + name.hashCode();
    }

    @Override
    public String toString() {
        return space + STANDARD_SEPARATION_CHARACTER + name;
    }

    private static String[] parser(String line, char separator, String space) {
        String[] complete = new String[]{space, line};
        int number = line.indexOf(separator);
        if (number >= 0) {
            complete[1] = line.substring(number + 1);
            if (number > 0) {
                complete[0] = line.substring(0, number);
            }
        }
        return complete;
    }

    public static Identifier create(String id, String customStandardSpace) {
        return new Identifier(id, STANDARD_SEPARATION_CHARACTER, customStandardSpace);
    }

    public static Identifier read(JsonElement je) {
        if (je != null) {

        }
        return null;
    }
    public final static class IdentifierJson implements JsonSerializer<Identifier>, JsonDeserializer<Identifier> {

        @Override
        public Identifier deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement.isJsonArray()) {
                JsonArray ja = jsonElement.getAsJsonArray();
                int s = ja.size();
                if (s >= 2) {
                    return new Identifier(ja.get(0).getAsString(), ja.get(1).getAsString());
                } else if (s == 1) {
                    return new Identifier(ja.get(0).getAsString());
                }
            } else if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive jp = jsonElement.getAsJsonPrimitive();
                if (jp.isString()) {
                    String str = jp.getAsString();
                    return new Identifier(str);
                }
            }
            return null;
        }

        @Override
        public JsonElement serialize(Identifier identifier, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(identifier.space + STANDARD_SEPARATION_CHARACTER + identifier.name);
        }
    }
}
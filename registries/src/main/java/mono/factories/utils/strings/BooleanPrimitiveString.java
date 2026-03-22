package mono.factories.utils.strings;

public class BooleanPrimitiveString {
    public static final byte TRUE_MASK = 1;
    public static final byte FALSE_MASK = 0;

    public static boolean read(String v) {
        byte read = BytePrimitiveString.read(v);
        return read == TRUE_MASK;
    }

    public static String write(boolean v) {
        return BytePrimitiveString.write(v ? TRUE_MASK : FALSE_MASK);
    }
}

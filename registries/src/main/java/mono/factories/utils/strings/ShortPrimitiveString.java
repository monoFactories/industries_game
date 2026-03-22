package mono.factories.utils.strings;

public class ShortPrimitiveString {
    public static final int MASK = 0xFFFF;
    public static short read(String v) {
        if (v == null || v.isEmpty()) return 0;
        char ch = v.charAt(0);
        return (short) ch;
    }

    public static String write(short v) {
        return String.valueOf((char) v);
    }
}

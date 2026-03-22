package mono.factories.utils.strings;

public class BytePrimitiveString {
    public static final int MASK = 0xFF;

    public static byte read(String v) {
        if (v == null || v.isEmpty()) return 0;
        char ch = v.charAt(0);
        return (byte) (int) ch;
    }

    public static String write(byte v) {
        int unsigned = v & MASK;
        return String.valueOf((char) unsigned);
    }
}

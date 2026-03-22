package mono.factories.utils.strings;

public class DoublePrimitiveString {
    public static double read(String v) {
        long raw = LongPrimitiveString.read(v);
        return Double.longBitsToDouble(raw);
    }

    public static String write(double v) {
        long raw = Double.doubleToRawLongBits(v);
        return LongPrimitiveString.write(raw);
    }
}

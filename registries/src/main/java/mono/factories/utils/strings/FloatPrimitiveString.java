package mono.factories.utils.strings;

public class FloatPrimitiveString {
    public static float read(String v) {
        int raw = IntPrimitiveString.read(v);
        return Float.intBitsToFloat(raw);
    }

    public static String write(float v) {
        int raw = Float.floatToRawIntBits(v);
        return IntPrimitiveString.write(raw);
    }
}

package mono.factories.utils.strings;

import java.nio.charset.StandardCharsets;

public class IntPrimitiveString {
    public static String write(int v) {
        byte[] p = new byte[]{
                (byte) ((v >> 24) & 0xFF),
                (byte) ((v >> 16) & 0xFF),
                (byte) ((v >> 8)  & 0xFF),
                (byte) (v        & 0xFF)
        };
        return new String(p, StandardCharsets.ISO_8859_1);
    }

    public static int read(String s) {
        if (s.length() != 4) {
            return 0;
        }
        byte[] bytes = s.getBytes(StandardCharsets.ISO_8859_1);
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8)  |
                (bytes[3] & 0xFF);
    }
}

package mono.factories.utils.strings;

import java.nio.charset.StandardCharsets;

public class LongPrimitiveString {
    public static String write(long v) {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) ((v >> 56) & 0xFF);
        bytes[1] = (byte) ((v >> 48) & 0xFF);
        bytes[2] = (byte) ((v >> 40) & 0xFF);
        bytes[3] = (byte) ((v >> 32) & 0xFF);
        bytes[4] = (byte) ((v >> 24) & 0xFF);
        bytes[5] = (byte) ((v >> 16) & 0xFF);
        bytes[6] = (byte) ((v >> 8)  & 0xFF);
        bytes[7] = (byte) (v         & 0xFF);
        return new String(bytes, StandardCharsets.ISO_8859_1);
    }

    /**
     * Восстанавливает long из строки длиной 8 символов.
     * @param s строка длиной 8 символов (иначе возвращает 0)
     * @return восстановленное значение long
     */
    public static long read(String s) {
        if (s == null || s.length() != 8) {
            return 0L;
        }
        byte[] bytes = s.getBytes(StandardCharsets.ISO_8859_1);
        return ((bytes[0] & 0xFFL) << 56) |
                ((bytes[1] & 0xFFL) << 48) |
                ((bytes[2] & 0xFFL) << 40) |
                ((bytes[3] & 0xFFL) << 32) |
                ((bytes[4] & 0xFFL) << 24) |
                ((bytes[5] & 0xFFL) << 16) |
                ((bytes[6] & 0xFFL) << 8)  |
                (bytes[7] & 0xFFL);
    }
}

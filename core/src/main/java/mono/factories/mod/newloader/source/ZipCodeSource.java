package mono.factories.mod.newloader.source;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipCodeSource implements CodeSource, Closeable {
    private final ZipFile zipFile;

    public ZipCodeSource(ZipFile zipFile) {
        this.zipFile = zipFile;
    }

    @Override
    public Collection<String> list(String path) {
        if (!path.isEmpty()) {
            if (!path.endsWith("/")) {
                path = path + "/";
            }
        }
        String finalPath = path;
        return zipFile.stream()
                .map(ZipEntry::getName)
                .filter(name -> name.startsWith(finalPath))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public boolean exists(String path) {
        ZipEntry entry = zipFile.getEntry(path);
        if (entry != null) return true;
        // Проверка директории (может не иметь явной записи)
        return zipFile.stream().anyMatch(e -> e.getName().startsWith(path + "/"));
    }

    @Override
    public boolean isDirectory(String path) {
        // Если путь заканчивается на '/', скорее всего, директория
        if (path.endsWith("/")) return true;
        ZipEntry entry = zipFile.getEntry(path);
        return entry != null && entry.isDirectory();
    }

    @Override
    public boolean isFile(String path) {
        ZipEntry entry = zipFile.getEntry(path);
        return entry != null && !entry.isDirectory();
    }

    @Override
    public InputStream openInputStream(String filePath) throws IOException {
        ZipEntry entry = zipFile.getEntry(filePath);
        if (entry == null || entry.isDirectory()) {
            throw new FileNotFoundException("Файл не найден в архиве: " + filePath);
        }
        return zipFile.getInputStream(entry);
    }

    @Override
    public String readAsString(String filePath) throws IOException {
        try (InputStream is = openInputStream(filePath)) {
            return new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .collect(Collectors.joining("\n"));
        }
    }

    @Override
    public byte[] readAsBytes(String filePath) throws IOException {
        try (InputStream is = openInputStream(filePath)) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[8192];
            int n;
            while ((n = is.read(data)) != -1) {
                buffer.write(data, 0, n);
            }
            return buffer.toByteArray();
        }
    }

    @Override
    public void close() throws IOException {
        zipFile.close();
    }
}

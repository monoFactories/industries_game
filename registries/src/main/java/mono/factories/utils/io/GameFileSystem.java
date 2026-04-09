package mono.factories.utils.io;

import mono.factories.registries.LazyInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class GameFileSystem {
    public static final Logger logger = LogManager.getLogger(GameFileSystem.class);

    public static final LazyInitializer<GameFileSystem> GAME_FILE_SYSTEM = new LazyInitializer<>(() -> {
        try {
            return new GameFileSystem(getGameDirectoryPath());
        } catch (Exception e) {
            logger.error("couldn't create game file system", e);
            throw new RuntimeException(e);
        }
    });
    private final Path directoryPath;

    public GameFileSystem(Path directoryPath) {
        if (!directoryPath.isAbsolute()) throw new IllegalArgumentException("directoryPath not absolute");
        if (!Files.exists(directoryPath)) throw new IllegalArgumentException("directoryPath not exists");
        if (!Files.isDirectory(directoryPath)) throw new IllegalArgumentException("directoryPath not is directory");
        this.directoryPath = directoryPath.toAbsolutePath().normalize();
    }

    public Path normalizePath(Path path) {
        Objects.requireNonNull(path, "path couldn't be null");
        if (path.isAbsolute()) {
            return path;
        }
        return directoryPath.resolve(path).normalize();
    }

    public Path normalizePath(String path) {
        Objects.requireNonNull(path, "path couldn't be null");
        Path pathP = Paths.get(path);
        return normalizePath(pathP);
    }

    public Path getDirectoryPath() {
        return directoryPath;
    }

    private static Path getGameDirectoryPath() throws URISyntaxException {
        return Paths.get(GameFileSystem.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().getParent();
    }
}

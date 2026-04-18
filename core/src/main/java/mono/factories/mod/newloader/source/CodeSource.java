package mono.factories.mod.newloader.source;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

/**
 * Интерфейс для работы с источником кода как с архивом или файловой папкой.
 * Поддерживает операции навигации, чтения, записи и метаданных.
 */
public interface CodeSource {

    // === НАВИГАЦИЯ И СТРУКТУРА ===

    /**
     * Получить список имён файлов и папок в указанной директории.
     * @param path путь к директории (пустая строка — корень)
     * @return коллекция имён элементов
     */
    Collection<String> list(String path);
    /**
     * Проверить существование файла или папки.
     * @param path полный путь к элементу
     * @return true, если элемент существует
     */
    boolean exists(String path);

    /**
     * Проверить, является ли элемент директорией.
     * @param path полный путь
     * @return true для директории
     */
    boolean isDirectory(String path);

    /**
     * Проверить, является ли элемент файлом.
     * @param path полный путь
     * @return true для файла
     */
    boolean isFile(String path);

    // === ЧТЕНИЕ ДАННЫХ ===

    /**
     * Открыть поток для чтения файла.
     * @param filePath путь к файлу
     * @return InputStream для чтения
     */
    InputStream openInputStream(String filePath) throws IOException;

    /**
     * Прочитать содержимое файла как строку.
     * @param filePath путь к файлу
     * @return содержимое файла в виде строки
     */
    String readAsString(String filePath) throws IOException;

    /**
     * Прочитать содержимое файла как массив байтов.
     * @param filePath путь к файлу
     * @return массив байтов файла
     */
    byte[] readAsBytes(String filePath) throws IOException;

}

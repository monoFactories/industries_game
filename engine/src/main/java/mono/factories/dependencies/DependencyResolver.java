package mono.factories.dependencies;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.StandardRegistry;
import mono.factories.registries.storage.Storage2;

import java.util.*;
import java.util.function.Consumer;

public class DependencyResolver<T extends HasDependency> extends StandardRegistry<T> {
    private volatile List<T> lastOut = null;
    private volatile boolean hasChange = true;

    public DependencyResolver() {
    }

    public synchronized List<T> resolve() {
        if (!hasChange)
            return lastOut;
        Map<Identifier, T> objectById = new HashMap<>();
        Map<Identifier, List<Identifier>> dependencies = new HashMap<>();
        Map<Identifier, Integer> inDegree = new HashMap<>(); // количество входящих рёбер

        for (T obj : values()) {
            Identifier id = obj.id(); // предполагаем, что есть метод getId()
            objectById.put(id, obj);
            dependencies.put(id, Arrays.asList(obj.dependencies()));
            inDegree.put(id, 0);
        }

        // подсчёт входящих рёбер
        for (List<Identifier> deps : dependencies.values()) {
            for (Identifier dep : deps) {
                inDegree.compute(dep, (k, v) -> (v == null) ? 1 : v + 1);
            }
        }

        // Шаг 2: Инициализация очереди (независимые объекты)
        Queue<Identifier> queue = new LinkedList<>();
        for (Map.Entry<Identifier, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        // Шаг 3: Топологическая сортировка
        List<T> sorted = new ArrayList<>();
        while (!queue.isEmpty()) {
            Identifier id = queue.poll();
            T obj = objectById.get(id);
            sorted.add(obj);

            for (Identifier dep : dependencies.get(id)) {
                inDegree.compute(dep, (k, v) -> v - 1);
                if (inDegree.get(dep) == 0) {
                    queue.offer(dep);
                }
            }
        }
        hasChange = false;
        return Collections.unmodifiableList(sorted);
    }

    @Override
    public synchronized boolean remove(Identifier id) {
        hasChange = true;
        return super.remove(id);
    }

    @Override
    public synchronized Storage2<Identifier, T> registerStorage(Identifier id, T item) {
        hasChange = true;
        return super.registerStorage(id, item);
    }

    @Override
    public synchronized Identifier register(Identifier id, T item) {
        hasChange = true;
        return super.register(id, item);
    }
}

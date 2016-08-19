package Radiacia.server.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * Безопасен при работе в многопоточной среде
 *
 * version 0.1
 */
public class IDManager {
    private volatile Set<Long> ids;
    private volatile long minValue;

    public IDManager() {
        this.ids = new HashSet<>();
        this.minValue = 1;
    }

    public IDManager(Long minValue) {
        this.minValue = minValue;
    }

    public IDManager(Set<Long> ids) {
        this.ids = ids;
    }

    /**
     * Возвращает свободный id и резервирует его
     *
     * @return Следующий свободный id
     */
    public synchronized Long getNextID() {
        Long nextId = countNextId();
        ids.add(nextId);
        return nextId;
    }

    private long countNextId() {
        long nextId = minValue;

        Iterator<Long> iterator = ids.iterator();
        while (iterator.hasNext()) {
            long id = iterator.next();
            if (id >= nextId) nextId = id+1;
        }

        return nextId;
    }

    public boolean contains(Long id) {
        return ids.contains(id);
    }

    /**
     * Делает id доступным для использования
     */
    public void remove(Long id) {
        ids.remove(id);
    }
}

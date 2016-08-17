package Radiacia.server;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * Безопасен при работе в многопоточной среде
 *
 * version 0.1
 */
public class IDManager {
    private volatile long maxId;

    public IDManager() {
        this.maxId = 0;
    }

    public IDManager(long beginID) {
        this.maxId = beginID;
    }

    /**
     * @return свободный id
     */
    public synchronized long incID() {
        return ++maxId;
    }

    public synchronized long decID() {
        return --maxId;
    }

    public synchronized Long getID() {
        return maxId;
    }
}

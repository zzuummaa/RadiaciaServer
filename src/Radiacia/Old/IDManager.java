package Radiacia.Old;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * Безопасен при работе в многопоточной среде
 *
 * version 0.1
 */
public class IDManager {
    private volatile int maxId = 0;

    /**
     * @return свободный id
     */
    public synchronized int getID() {
        return ++maxId;
    }
}

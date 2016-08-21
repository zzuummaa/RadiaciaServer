package Radiacia.server.eventlisteners;

import java.util.Collection;

/**
 * Created by Cntgfy on 20.08.2016.
 *
 * Генерирует события
 */
public interface EventGenerator {
    public void addDataListener(DataListener dl);

    public boolean removeListener(DataListener l);

    public Collection<DataListener> removeAllListeners();
}

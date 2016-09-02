package Radiacia.base;

import java.util.Collection;

/**
 * Created by Cntgfy on 20.08.2016.
 *
 * Генерирует события
 */
public interface EventGeneratorInterface {
    public void addListener(DataListenerInterface dl);

    public boolean removeListener(DataListenerInterface l);

    //public void notifyListeners();

    public Collection<DataListenerInterface> removeAllListeners();
}

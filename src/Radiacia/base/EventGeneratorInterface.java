package Radiacia.base;

import Radiacia.data.Data;

/**
 * Created by Cntgfy on 20.08.2016.
 *
 * Генерирует события
 */
public interface EventGeneratorInterface {
    public void addListener(DataListenerInterface dl);

    public boolean removeListener(DataListenerInterface l);

    public void notifyListeners(Data data);
}

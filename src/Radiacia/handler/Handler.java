package Radiacia.handler;

import Radiacia.data.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Реализует обработку данных
 *
 * <code>A</code> - входные данные
 * <code>B</code> - выходные данные
 */
public abstract class Handler<A extends Data, B extends Data> {
    private Collection<B> outData;

    /**
     * Обрабатывает вохдную коллекцию данных, составляя выходную
     */
    public void handle(Collection<A> inData) {
        if (inData == null) return;

        Iterator<A> iterator = inData.iterator();
        outData = new ArrayList<>();

        while (iterator.hasNext()) {
            B data = handle(iterator.next());
            if (data != null) outData.add(data);
        }
    }

    /**
     * Обрабатывает входные данные, возвращая выходные
     *
     * Используется при обработки коллекции
     * @see #handle(java.util.Collection)
     *
     * @return пустую коллекцию, если данных нет
     */
    public abstract B handle(A data);

    public Collection<B> getOutData() {
        Collection<B> tmp = outData;
        outData = null;

        return tmp == null ? new ArrayList<>() : tmp;
    }
}

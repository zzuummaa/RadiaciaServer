package Radiacia.handler;

import Radiacia.data.Data;

import java.util.*;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Реализует обработку данных
 *
 * <code>A</code> - входные данные
 * <code>B</code> - выходные данные
 */
public abstract class CollectionHandler<A extends Data, B extends Data> implements Handler<A, B> {
    private Collection<A> inData = new LinkedList<>();
    private List<B> outData = new LinkedList<>();

    public void handle() {
        handle(inData);
        inData.clear();
    }

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
     * @return null, если данных нет
     */
    public abstract B handle(A data);

    /**
     * Возвращает обработанные данные, удаляя ссылку на них
     *
     * @return коллекцию выходных данных
     *         или пусткю коллекцию, если выходных данных нет
     */
    public Collection<B> getAllOutData() {
        Collection<B> tmp = outData;
        outData = new LinkedList<>();

        return tmp;
    }

    /**
     * @return первый обработанный элемент,
     *         null, если элементов нет
     */
    public B getOutData() {
        return outData.isEmpty() ? null : outData.remove(0);
    }

    /**
     * Аналогичен <code>addInData(A data)</code>
     * @see #addInData(Radiacia.data.Data)
     *
     * @param inData
     */
    public void addInData(Collection<A> inData) {
        this.inData.addAll(inData);
    }

    /**
     * Добавляет данные для последующей обработки
     *
     * обработаются при вызове метода <code>handle()</code>
     * @see #handle()
     *
     * @param data
     */
    public void addInData(A data) {
        this.inData.add(data);
    }
}

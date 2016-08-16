package Radiacia.handler;

import Radiacia.data.Data;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Реализует обработку данных
 *
 * <code>A</code> - входные данные
 * <code>B</code> - выходные данные
 */
public interface Handler<A extends Data, B extends Data> {
    /**
     * Обрабатывает входные данные, возвращая выходные
     *
     * @return null, если данных нет
     */
    public B handle(A data);

    /**
     * Добавляет данные для последующей обработки
     *
     * @param data
     */
    public void addInData(A data);

    /**
     * @return обработанный элемент
     */
    public B getOutData();

    /**
     * Обрабатывает данные, добавленные с помощью <code>addInData(A data)</code>
     * @see #addInData(Radiacia.data.Data)
     */
    public void handle();
}

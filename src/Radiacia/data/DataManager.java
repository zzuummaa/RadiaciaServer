package Radiacia.data;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * Позвляет управлять данными и обрабатывать их.
 *
 * version 0.5
 */
public interface DataManager<A extends Data> {
    /**
     * @param a данные для обработки
     */
    public void add(A a);

    /**
     * @return выходные данные после обработки
     */
    public A getData();

    /**
     * @return массив содержщий выходные данные
     */
    public A[] getAllData();

    /**
     * @return true - если есть обработанные данные
     *         false- если нету обработанных данных
     */
    public boolean isOutDataReady();
}

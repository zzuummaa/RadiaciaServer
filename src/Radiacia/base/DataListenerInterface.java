package Radiacia.base;

import Radiacia.data.Data;

/**
 * Created by Cntgfy on 18.08.2016.
 *
 * Позволяет получать события при приходе данных
 * определенного типа
 */
public interface DataListenerInterface {
    /**
     * Вызывает метод, соответствующий типу пришедших
     * данных.
     *
     * @param data данные, которые должны вызывать событие
     */
    public void initEvent(Data data);
}

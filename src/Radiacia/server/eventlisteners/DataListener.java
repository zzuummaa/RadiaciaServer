package Radiacia.server.eventlisteners;

import Radiacia.data.Data;

/**
 * Created by Cntgfy on 18.08.2016.
 *
 * Позволяет получать события при приходе данных
 * определенного типа
 */
public interface DataListener {
    /**
     * Вызывает метод, соответствующий типу пришедших
     * данных.
     *
     * @param data данные, которые должны вызывать событие
     */
    public void initEvent(Data data);
}

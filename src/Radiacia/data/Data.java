package Radiacia.data;

import Radiacia.Client;

import java.io.Serializable;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * Данные, которые могут быть переданы
 *
 * version 0.5
 */
public interface Data extends Serializable {
    //Поле заполняется получателем данных
    public Client owner = null;
}

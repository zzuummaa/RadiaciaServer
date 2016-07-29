package Radiacia.data;

import java.io.Serializable;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * Данные, которые могут быть переданы
 *
 * version 0.5
 */
public interface Data<A> extends Serializable {
    //Поле заполняется получателем данных
    public void setOwner(A owner);

    public A getOwner();
}

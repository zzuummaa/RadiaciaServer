package Radiacia.data;

import Radiacia.Client;

import java.io.Serializable;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * Данные, которые могут быть переданы
 *
 * Передаются с помощью механизма сериализации
 */
public class Data<A extends Serializable> implements Serializable {
    private Client owner;
    protected A data;

    //Поле заполняется получателем данных
    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public Client getOwner() {
        return owner;
    }

    public void setData(A data) {
        this.data = data;
    }

    public A getData() {
        return data;
    }
}

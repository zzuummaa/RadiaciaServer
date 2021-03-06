package Radiacia.data;

import java.io.Serializable;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * Данные, которые могут быть переданы
 *
 * Передаются с помощью механизма сериализации
 *
 * <code>A</code> - тип данных
 * <code>B</code> - тип owner
 */
public class Data<A extends Serializable, B> implements Serializable {
    protected transient B owner;
    protected A data;

    public Data() {
    }

    public Data(Data<A, B> data) {
        if (data == null) return;

        this.owner = data.owner;
        this.data = data.data;
    }

    public void setOwner(B owner) {
        this.owner = owner;
    }

    public B getOwner() {
        return owner;
    }

    public void setData(A data) {
        this.data = data;
    }

    public A getData() {
        return data;
    }
}

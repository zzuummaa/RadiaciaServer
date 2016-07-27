package Radiacia.data;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Позволяет работать с несколькими объектами данных
 */
public class MultiData implements Data {
    public Data[] data;

    public MultiData(int dataLength) {
        this.data = new Data[dataLength];
    }

    public MultiData(Data[] data) {
        this.data = data;
    }
}

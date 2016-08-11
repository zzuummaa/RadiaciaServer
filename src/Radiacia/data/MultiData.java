package Radiacia.data;

import java.io.*;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Служит контейнером для нескольких объектов типа Data
 */
public class MultiData extends Data<Data[]> implements Externalizable {

    public MultiData(int dataLength) {
        this.data = new Data[dataLength];
    }

    public MultiData(Data... data) {
        this.data = data;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        if (data == null) {
            out.write(0);
        } else {
            out.writeInt(data.length);
            for (int i = 0; i < data.length; i++) {
                out.writeObject(data[i]);
            }
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        if (in.read() == 0) {
            data = null;
        } else {
            data = new Data[in.readInt()];
            for (int i = 0; i < data.length; i++) {
                data[i] = (Data) in.readObject();
            }
        }
    }
}

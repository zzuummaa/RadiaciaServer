package Radiacia.data;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Служит контейнером для нескольких объектов типа Data
 */
public class MultiData<A, B> extends Data<A[], B> implements Externalizable {

    public MultiData(A... data) {
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
            int dataLength = in.readInt();

            ArrayList<A> listData = new ArrayList<>(dataLength);
            for (int i = 0; i < dataLength; i++) {
                data[i] = (A) in.readObject();
            }
            data = (A[]) listData.toArray();
        }
    }
}

package Radiacia.server.io;

import Radiacia.data.Data;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by Cntgfy on 24.07.2016.
 *
 * version 0.5
 */
public class DataOutputStream extends ObjectOutputStream {
    public DataOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    public void writeData(Data data) throws IOException {
        super.writeObject(data);
    }
}

package Radiacia.server.io;

import Radiacia.data.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by Cntgfy on 24.07.2016.
 *
 * Добавлена возможность читать Data вызовом метода readData()
 *
 * version 0.5
 */
public class DataInputStream extends ObjectInputStream {
    public DataInputStream(InputStream in) throws IOException {
        super(in);
    }

    /**
     * @return считанный объект Data
     * @throws IOException
     */
    public Data readData() throws IOException {
        Object object = null;
        try {
            object = super.readObject();
        } catch (ClassNotFoundException e) {
            IOException exception = new IOException();
            exception.addSuppressed(e);
            throw exception;
        }

        if (object == null) throw new IOException("reading null object");

        if (object instanceof Data) {
            return (Data) object;
        } else {
            throw new IOException("reading object isn't Data. Object is " + object.getClass().getName() );
        }
    }
}

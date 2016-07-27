package Radiacia.Old;

import java.io.IOException;

/**
 * Created by Cntgfy on 23.07.2016.
 */
public class Main_TestExceptions {
    public static void main(String[] args) throws IOException {
        try {
            throw new IOException("first exception");
        } catch (IOException e) {
            IOException exception = new IOException("second exception");
            exception.addSuppressed(e);
            throw exception;
        }
    }
}

package Radiacia;

import Radiacia.client.Client;
import Radiacia.data.MultiData;

import java.io.IOException;

/**
 * Created by Cntgfy on 27.07.2016.
 */
public class MultiClient implements Client<MultiData> {
    @Override
    public void write(MultiData data) throws IOException {

    }

    @Override
    public MultiData read() throws IOException {
        return null;
    }

    @Override
    public void connect() throws IOException {

    }

    @Override
    public void disconnect() throws IOException {

    }

    @Override
    public boolean isConnected() {
        return false;
    }
}

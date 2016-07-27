package Radiacia;

import Radiacia.data.Data;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Cntgfy on 27.07.2016.
 */
public class SocketClient implements Client {
    private Socket socket;

    public SocketClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void write(Data data) throws IOException {

    }

    @Override
    public Data read() throws IOException {
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

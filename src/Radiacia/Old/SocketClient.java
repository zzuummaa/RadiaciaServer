package Radiacia.Old;

import Radiacia.Client;
import Radiacia.data.Data;
import Radiacia.io.DataInputStream;
import Radiacia.io.DataOutputStream;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * version 0.5
 */
public class SocketClient implements Client {
    private Socket socket;
    private String address;
    private volatile int port;

    private DataInputStream in;
    private DataOutputStream out;

    public SocketClient(String address, int port) {
        synchronized (this) {
            this.address = address;
            this.port = port;
        }
    }

    public SocketClient(Socket socket) throws IOException {
        synchronized (this) {
            this.socket = socket;

            this.address = socket.getInetAddress().getHostAddress();
            this.port = socket.getPort();

            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        }
    }

    @Override
    public synchronized void write(Data data) throws NullPointerException, IOException {
        out.writeData(data);
    }

    @Override
    public synchronized Data read() throws IOException {
        return in.readData();
    }

    private volatile boolean isConnected;

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Устанавливает сокет соединение
     *
     * @throws IOException не удалось установить соединение
     */
    @Override
    public synchronized void connect() throws IOException {
        if (socket != null) throw new IOException("client already connected");

        socket = new Socket(address, port);
    }

    /**
     * Отключает сокет
     *
     * @throws IOException если не удалось отключить соект
     */
    @Override
    public void disconnect() throws IOException {
        if (socket == null) throw new IOException("client wasn't connected");

        socket.close();
        socket = null;
    }
}

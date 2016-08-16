package Radiacia.client;

import Radiacia.client.Client;
import Radiacia.data.Data;
import sun.security.util.Debug;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Cntgfy on 27.07.2016.
 */
public class SocketClient implements Client {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    /**
     * @param socket соединенный сокет, не null
     */
    public SocketClient(Socket socket) throws IOException {
        this.socket = socket;

        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void write(Data data) throws IOException {
        out.writeObject(data);
        out.flush();

        System.out.println("client: write data");
    }

    private static final String READ_EXCEPTION_MESSAGE = "reading object isn't instance of Data";

    /**
     * @return
     * @throws IOException когда не удается прочитать объект класса, имплементирующего Data
     *         @see Radiacia.data.Data
     */
    @Override
    public Data read() throws IOException {
        try {
            Object obj = in.readObject();

            if (obj instanceof Data) {
                System.out.println("client: read data");
                return (Data) obj;
            }
            else throw new IOException(READ_EXCEPTION_MESSAGE);
        } catch (ClassNotFoundException e) {
            IOException ioException = new IOException(READ_EXCEPTION_MESSAGE);
            ioException.addSuppressed(e);

            throw ioException;
        }
    }

    /**
     * не поддерживается
     */
    @Override
    public void connect() throws IOException {

    }

    /**
     * @throws IOException
     */
    @Override
    public void disconnect() throws IOException {
        try {
            write(new Data());
        } catch (IOException e) {
            Debug.println("client", "can't write disconnect message to socket");
        } finally {
            socket.close();
            isConnected = false;
        }
    }

    private boolean isConnected = true;

    @Override
    public boolean isConnected() {
        return isConnected;
    }
}

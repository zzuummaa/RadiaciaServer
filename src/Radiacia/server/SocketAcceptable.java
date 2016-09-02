package Radiacia.server;

import Radiacia.base.Acceptable;
import Radiacia.server.client.SocketClient;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Реализует сообщение с помощью сокет соединения
 *
 */
public class SocketAcceptable implements Acceptable<SocketClient> {
    private ServerSocket serverSocket;

    public SocketAcceptable(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public SocketClient accept() throws IOException {
        return new SocketClient(serverSocket.accept());
    }

    @Override
    public void close() throws IOException {
        serverSocket.close();
    }
}

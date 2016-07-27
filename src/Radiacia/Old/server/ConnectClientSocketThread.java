package Radiacia.Old.server;

import Radiacia.Client;
import Radiacia.Old.SocketClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * Обрабратывает подключения сокет клиентов в отдельном потоке
 *
 * version 0.7
 */
public class ConnectClientSocketThread extends Thread{
    private ManagerOfClients managerOfClients;
    private ServerSocket serverSocket;

    /**
     * @param serverSocket не null
     * @param managerOfClients
     */
    public ConnectClientSocketThread(ServerSocket serverSocket, ManagerOfClients managerOfClients) {
        this.serverSocket = serverSocket;
        this.managerOfClients = managerOfClients;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();

                if (isInterrupted()) return;

                Client client = new SocketClient(socket);
                new ConnectClient(managerOfClients, client);
            } catch (IOException e) {
                if (!isInterrupted()) e.printStackTrace();
            }
        }
    }
}

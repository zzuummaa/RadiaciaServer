package Radiacia.server;

import Radiacia.client.Client;
import Radiacia.client.ClientConnectThread;
import Radiacia.client.SocketClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cntgfy on 17.08.2016.
 */
public class Main_TestClientManager {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketServer socketServer = new SocketServer(new ServerSocket(9090));
        ServerListenThread slth = new ServerListenThread(socketServer, true);

        ClientManager clientManager = slth.getClientManager();

        Client client = new SocketClient(new Socket("localhost", 9090));
        connectClient(client);

        Thread.sleep(20);
        printClients(clientManager.getClients());

        clientManager.disconnect((long) 1);
        slth.interrupt();
        socketServer.close();
    }

    private static void connectClient(Client client) {
        new ClientConnectThread(client).start();
    }

    private static void printClients(Map<Long, Client> clients) {
        Set<Long> ids = clients.keySet();

        for(Long id: ids) {
            System.out.println("id=" + id + " client=" + clients.get(id));
        }
    }
}

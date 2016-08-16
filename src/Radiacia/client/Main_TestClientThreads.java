package Radiacia.client;

import Radiacia.data.ConnectData;
import Radiacia.server.SocketServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Cntgfy on 16.08.2016.
 */
public class Main_TestClientThreads {
    private static SocketClient serverClient;

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketServer server = new SocketServer(new ServerSocket(9090));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverClient = server.accept();

                    ClientConnectThread ccth = new ClientConnectThread(serverClient, 1);
                    ccth.start();
                    ccth.join();

                    printInfo(ccth.getConnectData());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        SocketClient clientClient = new SocketClient(new Socket("localHost", 9090));
        ClientConnectThread ccth = new ClientConnectThread(clientClient, 2);
        ccth.start();
        ccth.join();

        printInfo(ccth.getConnectData());
    }

    public static void printInfo(ConnectData connectData) {
        System.out.println("data=" + connectData.getData() + " id=" + connectData.getId());
    }
}

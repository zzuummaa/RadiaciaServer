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
    public static void main(String[] args) throws IOException, InterruptedException {
        connect();

    }

    public static void printInfo(ConnectData connectData) {
        System.out.println("data=" + connectData.getData() + " id=" + connectData.getId());
    }

    public void listen() throws IOException {
        SocketServer server = openServer();



        Client clientClient = new SocketClient(new Socket("localhost", 9090));
    }

    public static void connect() throws InterruptedException, IOException {
        SocketServer server = openServer();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SocketClient serverClient = server.accept();

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

        server.close();
    }

    private static SocketServer openServer() throws IOException {
        return new SocketServer(new ServerSocket(9090));
    }
}

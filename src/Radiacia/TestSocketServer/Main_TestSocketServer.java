package Radiacia.TestSocketServer;

import Radiacia.Client;
import Radiacia.SocketClient;
import Radiacia.SocketServer;
import Radiacia.handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Cntgfy on 28.07.2016.
 */
public class Main_TestSocketServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        SimpleServer simpleServer = new SimpleServer();

        Client client = new SocketClient(new Socket("localHost", 9090));

        Thread.sleep(30);

        client.disconnect();
    }

    private static class SimpleServer extends Thread {
        ClientHandler clientHandler;
        SocketServer socketServer;

        private SimpleServer() throws IOException {
            this.socketServer = new SocketServer(new ServerSocket(9090));
            this.clientHandler = new ClientHandler();
            start();
        }

        ArrayList<ListenThread> listenThreads = new ArrayList<>();
        ClientHandlerThread clientHandlerThread;

        @Override
        public void run() {
            clientHandlerThread = new ClientHandlerThread(clientHandler);
            clientHandlerThread.start();

            while (!isInterrupted()) {
                try {
                    listenThreads.add( new ListenThread(clientHandler, socketServer.accept()) );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

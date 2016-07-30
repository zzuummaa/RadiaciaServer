package Radiacia.TestSocketServer;

import Radiacia.Client;
import Radiacia.SocketClient;
import Radiacia.SocketServer;
import Radiacia.handler.ClientHandler;
import Radiacia.handler.HandlerThread;
import sun.security.util.Debug;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Cntgfy on 28.07.2016.
 */
public class Main_TestSocketServer {
    private static final String name = "Main test socket server";

    public static void main(String[] args) throws IOException, InterruptedException {
        SimpleServer simpleServer = new SimpleServer();

        Client client = new SocketClient(new Socket("localHost", 9090));

        Thread.sleep(30);

        client.disconnect();
        System.out.println(name + ": client disconnected");

        Thread.sleep(30);

        simpleServer.interrupt();
        simpleServer.socketServer.close();
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
        HandlerThread handlerThread;

        @Override
        public void run() {
            handlerThread = new HandlerThread(clientHandler);
            handlerThread.start();

            while (!isInterrupted()) {
                try {
                    listenThreads.add( new ListenThread(clientHandler, socketServer.accept()) );
                } catch (IOException e) {
                    if (!isInterrupted()) e.printStackTrace();
                }
            }
        }

        @Override
        public void interrupt() {
            super.interrupt();
            handlerThread.interrupt();
            try {
                socketServer.close();
            } catch (IOException e) {
                Debug.println("Simple server", "can't close socket server");
            }
        }
    }


}

package Radiacia.Old;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Cntgfy on 23.07.2016.
 */
public class Main_TestSocket {
    private static Socket serverAccept;

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(9090);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverAccept = serverSocket.accept();
                    Thread.sleep(50);
                    //serverSocket.close();
                    serverAccept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Socket socket = new Socket("localHost", 9090);

        System.out.println("socket after connection:" + socket.isConnected());
        Thread.sleep(60);
        socket.close();
        System.out.println("socket after server closing: " + socket.isConnected());

    }
}

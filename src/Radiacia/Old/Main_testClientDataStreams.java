package Radiacia.Old;

import Radiacia.data.ClientData;
import Radiacia.data.Data;
import Radiacia.io.DataInputStream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Cntgfy on 24.07.2016.
 */
public class Main_testClientDataStreams {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = serverSocket.accept();

                    //ClientDataOutputStream clientDataOutputStream = new ClientDataOutputStream(socket.getOutputStream());
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                    objectOutputStream.writeObject(new ClientData());

                    Thread.sleep(50);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Socket socket = new Socket("localHost", 9090);

        DataInputStream clientDataInputStream = new DataInputStream(socket.getInputStream());

        Data data = clientDataInputStream.readData();

        System.out.println(data);

        socket.close();
        serverSocket.close();
    }


}

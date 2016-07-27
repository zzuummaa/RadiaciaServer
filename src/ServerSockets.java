import java.awt.*;
import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Cntgfy on 15.11.2015.
 */
public class ServerSockets implements Runnable {
    private int port;
    private String response = null;
    private TextArea ta1;

    public ServerSockets(TextArea ta1) {
        this.ta1 = ta1;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setResponse(String reader) {
        this.response = reader;
    }

    public String getResponse() {
        return response;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Ip address: " + Inet4Address.getLocalHost());
            System.out.println("Server start");
            ta1.setText("Server start");

            while (true) {
                Socket accept = serverSocket.accept();
                System.out.println("Socked connected");

                PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(accept.getOutputStream())), true);
                BufferedReader readerSocket = new BufferedReader(new InputStreamReader(accept.getInputStream()));

                //response = readerSocket.readLine();
                //System.out.println("from socket: " + response);

                writer.println("response is getting: " + response);

                //System.out.println("Socked disconnect");
            }
        } catch (IOException e) {System.out.println(e.getStackTrace());}
    }
}

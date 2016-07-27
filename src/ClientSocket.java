import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Cntgfy on 15.11.2015.
 */
public class ClientSocket implements Runnable{
    private TextArea ta1;
    private String ip;
    private int port;
    private String message = null;
    Socket socket = new Socket();
    BufferedReader readerSocket;
    PrintWriter writerSocket;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setLb(TextArea ta1) {
        this.ta1 = ta1;
    }

    public void setMassage(String message) {
        this.message = message;
    }

    public Socket getSocket() {
        return socket;
    }

    public void run(){
        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            socket = new Socket(ipAddress, port);
            ta1.setText("socked connected");

            readerSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writerSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            Thread writeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("writer thread is alive!");
                    boolean isFirstTern = true;
                    while (true) {

                        if (message != null) {
                            writerSocket.println(message);
                            writerSocket.flush();
                            System.out.println("sending message: " + message);
                            message = null;
                        }
                    }
                    //System.out.println("Massage send");
                }
            });
            writeThread.start();
            System.out.println("writer thread started");
            while (!socket.isClosed());
            try {
                writeThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             /*{
                if (massage != null) {
                    final String s = massage;
                    massage = null;

                    writeThread.start();
                }

                if (readerSocket.ready()) {
                    Thread readThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String line = null;
                            try {
                                line = readerSocket.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                                ta1.setText(e.toString());
                            }
                            if (line != null) {
                                System.out.println(line);
                                ta1.setText("Переданная строка: " + line);
                            }
                        }
                    });
                    readThread.start();
                }
            }*/
        } catch (IOException e) {
            e.printStackTrace();
            ta1.setText(e.toString());}
    }
}

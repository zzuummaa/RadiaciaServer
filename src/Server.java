import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Created by Cntgfy on 14.11.2015.
 */
public class Server extends Frame {
    ServerSockets serverSockets;
    Thread serverThread = new Thread();
    TextArea ta1;
    Button bt1, bt2;

    public Server(String s) {
        super(s);
        setVisible(true);
        setBounds(10, 10, 400, 400);
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing program");
                if (!serverThread.isInterrupted())
                    serverThread.interrupt();
                System.exit(0);
            }
        });

        Label lb1 = new Label("Номер порта:");
        lb1.setBounds(50, 40, 100, 20);
        add(lb1);

        final TextField tf1 = new TextField("9090");
        tf1.setBounds(155, 40, 100, 20);
        add(tf1);

        bt1 = new Button("Start");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Thread.getAllStackTraces().toString());
                try {
                    if (!serverThread.isAlive()) {
                        System.out.println("not alive");
                        serverSockets.setPort(Integer.parseInt(tf1.getText()));
                        serverThread = new Thread(serverSockets, "server");
                        serverThread.start();
                        bt1.setLabel("Stop");
                    } else
                    {
                        serverThread.stop();
                        bt1.setLabel("Start");
                    }
                } catch (Exception e1) {e1.printStackTrace();}
            }
        });
        bt1.setBounds(50, 70, 170, 20);
        add(bt1);

        final TextField tf2 = new TextField();
        tf2.setBounds(50, 130, 210, 20);
        add(tf2);

        bt2 = new Button("Ok");
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverSockets.setResponse(tf2.getText());
                System.out.println("Reader set: " + serverSockets.getResponse());
            }
        });
        bt2.setBounds(50, 160, 240, 20);
        add(bt2);

        ta1 = new TextArea("Переданная строка: ");
        ta1.setBounds(50, 220, 300, 150);
        add(ta1);

        serverSockets = new ServerSockets(ta1);
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server("Server");
        //ServerSocketFromAndroid serverSocketFromAndroid = new ServerSocketFromAndroid();
    }

}
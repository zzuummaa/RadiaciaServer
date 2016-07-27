import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Created by Cntgfy on 14.11.2015.
 */
public class Client extends Frame {
    ClientSocket clientSocket = new ClientSocket();
    Thread clientThread = new Thread();
    Button bt1, bt2;
    TextArea ta1;
    TextField tf1, tf2, tf3;

    public Client(String s) {
        super(s);
        setVisible(true);
        setBounds(10, 10, 400, 400);
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (clientSocket.getSocket().isConnected())
                    try {
                        clientSocket.getSocket().close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                System.out.println("Closing program");
                System.exit(0);
            }
        });

        Label lb1 = new Label("Номер порта:");
        lb1.setBounds(50, 40, 100, 20);
        add(lb1);

        tf1 = new TextField("9092");
        tf1.setBounds(50, 70, 150, 20);
        add(tf1);

        tf2 = new TextField("192.168.1.100");
        tf2.setBounds(50, 100, 150, 20);
        add(tf2);

        bt1 = new Button("Run/Stop");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!clientThread.isAlive()) {
                        clientSocket.setPort(Integer.parseInt(tf1.getText()));
                        clientSocket.setIp(tf2.getText());
                        clientThread = new Thread(clientSocket, "client");
                        clientThread.start();
                        System.out.println("thread start");
                    } else {
                        clientSocket.getSocket().close();
                        clientThread.stop();
                        System.out.println("thread stop");
                        ta1.setText("socked disconnected");
                    }
                } catch (Exception e1) {e1.printStackTrace();}
            }
        });
        bt1.setBounds(50, 140, 100, 20);
        add(bt1);

        tf3 = new TextField("Massage");
        tf3.setBounds(50, 190, 150, 20);
        add(tf3);

        bt2 = new Button("Send");
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("set Massage");
                clientSocket.setMassage(tf3.getText().toString());
            }
        });
        bt2.setBounds(50, 220, 100, 20);
        add(bt2);

        ta1 = new TextArea("Переданная строка: ");
        ta1.setBounds(50, 250, 300, 120);
        add(ta1);

        clientSocket.setLb(ta1);
    }

    public static void main(String[] args) {
        Client client = new Client("Client");
    }

}
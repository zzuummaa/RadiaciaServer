package Radiacia.gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Cntgfy on 07.08.2016.
 */
public class RadiaciaServerGUI extends JFrame {
    private JPanel panel;

    public static void main(String[] args) {
        RadiaciaServerGUI radiaciaServerGUI = new RadiaciaServerGUI();
    }

    public RadiaciaServerGUI() {
        super("Radiacia server GUI");
        setSize(getToolkit().getScreenSize());
        setVisible(true);

        add(new GameWindow());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

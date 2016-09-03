package Radiacia;

import Radiacia.gui.RadiaciaServerGUI;
import Radiacia.server.client.GameClient;

import java.io.IOException;

/**
 * Created by Cntgfy on 20.08.2016.
 */
public class Main_TestGUIWithGame {
    private static RadiaciaServerGUI rsg;
    private static Thread updateGUIThread;

    public static void main(String[] args) throws IOException, InterruptedException {
        init();

        GameClient gc1 = Main_Test.connect();
        GameClient gc2 = Main_Test.connect();
    }

    public static void init() throws IOException {
        rsg = new RadiaciaServerGUI();

        updateGUIThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        rsg.repaint();
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updateGUIThread.start();

        Main_Test.init();
        rsg.gameWindow.setGamers(Main_Test.getGamers());
    }

    public static void close() throws IOException {
        updateGUIThread.interrupt();
        Main_Test.closeAll();
        rsg.dispose();
    }
}
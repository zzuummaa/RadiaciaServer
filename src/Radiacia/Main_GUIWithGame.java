package Radiacia;

import Radiacia.gui.RadiaciaServerGUI;

import java.io.IOException;

/**
 * Created by Cntgfy on 20.08.2016.
 *
 * Поднимает сервер, подключая графическое отображение игроков
 */
public class Main_GUIWithGame {
    private static RadiaciaServerGUI rsg;
    private static Thread updateGUIThread;

    public static void main(String[] args) throws IOException, InterruptedException {
        init();
    }

    public static void init() throws IOException {
        rsg = new RadiaciaServerGUI();

        updateGUIThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        rsg.gameWindow.setGamers(Main_Test.getGamers());
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
}

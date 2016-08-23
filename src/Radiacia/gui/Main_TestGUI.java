package Radiacia.gui;

import Radiacia.game.Gamer;
import Radiacia.game.Shot;

import java.util.ArrayList;

/**
 * Created by Cntgfy on 09.08.2016.
 */
public class Main_TestGUI {
    public static void main(String[] args) {
        final RadiaciaServerGUI serverGUI = new RadiaciaServerGUI();
        GameWindow gameWindow = serverGUI.gameWindow;

        final ArrayList<Shot> shots = new ArrayList<>();
        shots.add(new Shot(50, 50, 0));

        final ArrayList<Gamer> gamers = new ArrayList<>();
        Gamer gamer = new Gamer("GamerTest", 10, 10, 0);
        gamer.setAccuracy(100);
        gamers.add(gamer);

        gameWindow.addShots(shots);
        gameWindow.addGamers(gamers);

        Thread thread = new Thread(new Runnable() {
            private float direction = 0f;

            @Override
            public void run() {
                try {
                    while (true) {

                        shots.get(0).setDirection(direction);
                        gamers.get(0).setDirection(direction);

                        direction += 5f;
                        if (direction > 180) direction -= 360;

                        serverGUI.repaint();

                        System.out.println("direction: " + direction);
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                }
            }
        });
        thread.start();

        try {
            thread.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

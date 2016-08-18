package Radiacia.gui;

import Radiacia.Game.Gamer;
import Radiacia.Game.Shot;

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

        ArrayList<Gamer> gamers = new ArrayList<>();
        Gamer gamer = new Gamer("GamerTest", 10, 10, 0);
        gamer.setAccuracy(100);
        gamers.add(gamer);

        gameWindow.addShots(shots);
        gameWindow.addGamers(gamers);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Shot shot = shots.get(0);
                        shot.setDirection(shot.getDirection() + 90f);
                        if (shot.getDirection() > 180) shot.setDirection(shot.getDirection() - 360);
                        serverGUI.repaint();

                        System.out.println("direction: " + shot.getDirection());
                        Thread.sleep(1000);
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

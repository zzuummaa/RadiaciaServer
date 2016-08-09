package Radiacia.gui;

import Radiacia.Game.Shot;

import java.util.ArrayList;

/**
 * Created by Cntgfy on 09.08.2016.
 */
public class Main_TestGUI {
    public static void main(String[] args) {
        RadiaciaServerGUI serverGUI = new RadiaciaServerGUI();
        GameWindow gameWindow = serverGUI.gameWindow;

        ArrayList<Shot> shots = new ArrayList<>();
        //shots.add(new Shot(0, 0, 100));
        shots.add(new Shot(50, 50, 0));

        gameWindow.addShots(shots);
    }
}

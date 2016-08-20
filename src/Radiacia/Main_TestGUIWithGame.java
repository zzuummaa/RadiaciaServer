package Radiacia;

import Radiacia.gui.RadiaciaServerGUI;
import Radiacia.server.client.GameClient;

import java.io.IOException;

/**
 * Created by Cntgfy on 20.08.2016.
 */
public class Main_TestGUIWithGame {
    public static void main(String[] args) throws IOException, InterruptedException {
        RadiaciaServerGUI rsg = new RadiaciaServerGUI();

        Main_Test.init();
        rsg.gameWindow.setGamers(Main_Test.getGamers());

        GameClient gc1 = Main_Test.connect();
        GameClient gc2 = Main_Test.connect();
    }
}

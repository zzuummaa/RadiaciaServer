package Radiacia.handler;

import Radiacia.Game.Gamer;
import Radiacia.data.GamerData;
import Radiacia.data.ShotData;

/**
 * Created by Cntgfy on 16.08.2016.
 */
public class Main_TestHandler {
    public static void main(String[] args) {
        InitShotHandler ish = new InitShotHandler();
        ShotHitHandler shh = new ShotHitHandler();

        MultiHandler multiHandler = new MultiHandler();
        multiHandler.addHandler(shh, ShotData.class);
        multiHandler.addHandler(ish, GamerData.class);

        multiHandler.addInData(new ShotData(new Gamer()));
        multiHandler.addInData(new GamerData(new Gamer()));

        multiHandler.handle();
    }
}

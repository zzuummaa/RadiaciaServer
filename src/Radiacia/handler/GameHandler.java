package Radiacia.handler;

import Radiacia.Game.Gamer;
import Radiacia.data.ShotData;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public class GameHandler {
    private Collection<Gamer> gamers;
    private Collection<ShotData> shots;

    public GameHandler(Collection<Gamer> gamers) {
        this.gamers = gamers;
        handle();
    }

    public void handle() {
        initShots();

        Iterator<Gamer> gi = gamers.iterator();
        while (gi.hasNext()) checkHits(gi.next());
    }

    public void initShots() {
        shots = new LinkedList<>();

        Iterator<Gamer> iterator = gamers.iterator();
        while (iterator.hasNext()) {
            Gamer gamer = iterator.next();

            if (gamer.isShoot() && gamer.isALive()) {
                shots.add(new ShotData(gamer));
            }
        }
    }

    public void checkHits(Gamer gamer) {
        Iterator<ShotData> shi = shots.iterator();

        while (shi.hasNext()) {
            ShotData shD = shi.next();

            if (shD.getOwner() != gamer && shD.getData().isHit(gamer)) gamer.hit();
        }
    }
}

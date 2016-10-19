package Radiacia;

import Radiacia.base.GameMechanicsInterface;
import Radiacia.base.GamerContainerInterface;
import Radiacia.base.GamerInterface;

import java.util.Iterator;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public class GameMechanics implements GameMechanicsInterface {
    private GamerContainerInterface gamers;

    public GameMechanics(GamerContainerInterface gamers) {
        this.gamers = gamers;
    }

    @Override
    public void setGamers(GamerContainerInterface gamerContainer) {
        gamers = gamerContainer;
    }

    @Override
    public void update() {
        Iterator<GamerInterface> iterator = gamers.asCollection().iterator();

        while (iterator.hasNext()) {
            updateGamer(iterator.next());
        }
    }

    private void updateGamer(GamerInterface gamer) {
        if (!gamer.isShoot()) {
            return;
        }

        for (GamerInterface shotedGamer :gamers.asCollection()) {
            if (gamer.isShot(shotedGamer)) {
                shotedGamer.setAlive(false);
                gamer.setShoot(false);
                break;
            }
        }
    }
}

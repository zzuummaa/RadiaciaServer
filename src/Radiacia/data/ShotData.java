package Radiacia.data;

import Radiacia.Game.Gamer;
import Radiacia.Game.Shot;

/**
 * Created by Cntgfy on 11.08.2016.
 */
public class ShotData extends Data<Shot, Gamer> {
    /**
     * Инициализирует выстрел, если игрок стреляет в данный момент
     *
     * @param gamer игрок, вытрел которого инициализируется
     */
    public ShotData(Gamer gamer) {
        data = gamer.isShoot() ? new Shot(gamer) : null;
        gamer.setIsShoot(false);
        setOwner(gamer);
    }
}

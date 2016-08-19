package Radiacia.handler;

import Radiacia.game.Gamer;
import Radiacia.game.Shot;
import Radiacia.data.GamerData;
import Radiacia.data.ShotData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Cntgfy on 11.08.2016.
 *
 * Проверяет, в кого попали выстрелы
 */
public class ShotHitHandler extends CollectionHandler<ShotData, GamerData> {
    private Collection<GamerData> gamerData;

    public ShotHitHandler(Collection<GamerData> gamerData) {
        if (gamerData != null) this.gamerData = gamerData;
        else                   this.gamerData = new ArrayList<>();
    }

    /**
     * @param shotData данные о выстрелах
     * @param gamerData данные о игроках
     */
    public ShotHitHandler(Collection<ShotData> shotData, Collection<GamerData> gamerData) {
        this(gamerData);
        handle(shotData);
    }

    @Override
    public GamerData handle(ShotData data) {
        Iterator<GamerData> iterator = gamerData.iterator();

        while (iterator.hasNext()) {
            GamerData gamerData = iterator.next();
            Gamer gamer = gamerData.getData();
            Shot shot = data.getData();

            if (data.getOwner() != gamer && shot.isHit(gamer)) {
                gamer.hit();
                return new GamerData(gamer, gamerData.getOwner());
            }
        }

        return null;
    }
}

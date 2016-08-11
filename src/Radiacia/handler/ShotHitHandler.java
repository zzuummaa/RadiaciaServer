package Radiacia.handler;

import Radiacia.Game.Gamer;
import Radiacia.Game.Shot;
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
public class ShotHitHandler extends Handler<ShotData, GamerData> {
    private Collection<GamerData> gamerData;

    public ShotHitHandler() {
        gamerData = new ArrayList<>();
    }

    /**
     * @param shotData данные о выстрелах
     * @param gamerData данные о игроках
     */
    public ShotHitHandler(Collection<ShotData> shotData, Collection<GamerData> gamerData) {
        if (gamerData != null) this.gamerData = gamerData;
        else                   this.gamerData = new ArrayList<>();

        handle(shotData);
    }

    @Override
    public GamerData handle(ShotData data) {
        Iterator<GamerData> iterator = gamerData.iterator();

        while (iterator.hasNext()) {
            Gamer gamer = iterator.next().getData();
            Shot shot = data.getData();

            if (data.getOwner() != gamer && shot.isHit(gamer)) gamer.hit();;
        }

        return null;
    }
}

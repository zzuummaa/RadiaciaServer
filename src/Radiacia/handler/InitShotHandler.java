package Radiacia.handler;

import Radiacia.Game.Gamer;
import Radiacia.data.GamerData;
import Radiacia.data.ShotData;

import java.util.Collection;

/**
 * Created by Cntgfy on 11.08.2016.
 *
 * Инициализирует выстрелы игроков
 */
public class InitShotHandler extends Handler<GamerData,ShotData> {
    private Collection<ShotData> shotData;

    public InitShotHandler() {
    }

    public InitShotHandler(Collection<GamerData> gamerData) throws Exception {
        handle(gamerData);
    }

    @Override
    public ShotData handle(GamerData data) {
        Gamer gamer = data.getData();

        if (gamer.isALive() && gamer.isShoot()) return new ShotData(gamer);
        else                                    return null;
    }

}

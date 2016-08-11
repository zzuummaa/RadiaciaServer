package Radiacia.handler;

import Radiacia.data.GamerData;
import Radiacia.data.ShotData;

import java.util.Collection;

/**
 * Created by Cntgfy on 11.08.2016.
 */
public class GameHandler extends Handler<GamerData, GamerData> {
    private InitShotHandler ish;
    private ShotHitHandler shh;

    public GameHandler(Collection<GamerData> gamerData) {
        ish = new InitShotHandler();
        shh = new ShotHitHandler();
        handle(gamerData);
    }

    @Override
    public GamerData handle(GamerData data) {
        ShotData shotData = ish.handle(data);
        GamerData gamerData = shh.handle(shotData);

        return gamerData;
    }
}

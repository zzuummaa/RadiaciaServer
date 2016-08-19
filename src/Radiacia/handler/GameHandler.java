package Radiacia.handler;

import Radiacia.data.GamerData;
import Radiacia.data.ShotData;

import java.util.Collection;

/**
 * Created by Cntgfy on 11.08.2016.
 */
public class GameHandler extends CollectionHandler<GamerData, GamerData> {
    private InitShotHandler ish;
    private ShotHitHandler shh;

    public GameHandler(Collection<GamerData> gamerData) {
        ish = new InitShotHandler();
        handle(gamerData);
    }

    @Override
    public void handle(Collection<GamerData> inData) {
        shh = new ShotHitHandler(inData);
        super.handle(inData);
    }

    /**
     * Не отлажен
     */
    @Override
    public GamerData handle(GamerData data) {
        ShotData shotData = ish.handle(data);
        GamerData gamerData = shh.handle(shotData);

        return gamerData;
    }
}

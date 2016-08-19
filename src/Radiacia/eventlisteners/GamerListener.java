package Radiacia.eventlisteners;

import Radiacia.Game.Gamer;
import Radiacia.data.Data;
import Radiacia.data.GamerData;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public class GamerListener implements DataListener {
    private Gamer gamer;

    public GamerListener(Gamer gamer) {
        this.gamer = gamer;
    }

    @Override
    public void initEvent(Data data) {
        if (data instanceof GamerData) onGamerData((GamerData) data);
    }

    public void onGamerData(GamerData gd) {
        this.gamer.setGamer(gd.getData());
    }
}

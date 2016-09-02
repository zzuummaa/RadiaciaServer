package Radiacia.server.eventlisteners;

import Radiacia.base.DataListenerInterface;
import Radiacia.data.Data;
import Radiacia.data.GamerData;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public abstract class GameDataListener implements DataListenerInterface {
    @Override
    public void initEvent(Data data) {
        if (data instanceof GamerData) onGamerData((GamerData) data);
    }

    public abstract void onGamerData(GamerData gamerData);
}

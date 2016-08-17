package Radiacia.data.datamanager;

import Radiacia.data.Data;
import Radiacia.data.GamerData;
import Radiacia.handler.ClientHandler;

/**
 * Created by Cntgfy on 17.08.2016.
 */
public class GameOutDataManager{
    private ClientHandler ch;

    public GameOutDataManager() {
    }

    public void parse(Data data) {
        if (data instanceof GamerData) parse((GamerData) data);
    }

    public void parse(GamerData data) {
        ch.handle(data);
    }
}

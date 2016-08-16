package Radiacia.data;

import Radiacia.client.Client;
import Radiacia.Game.Gamer;

/**
 * Created by Cntgfy on 27.07.2016.
 */
public class GamerData extends Data<Gamer, Client>{
    //private Gamer gamer;


    public GamerData(Gamer data) {
        this.data = data;
    }

    @Override
    public void setData(Gamer data) {
        this.data = data;
    }

    @Override
    public Gamer getData() {
        return data;
    }
}

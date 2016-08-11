package Radiacia.data;

import Radiacia.Game.Gamer;

/**
 * Created by Cntgfy on 27.07.2016.
 */
public class GamerData extends Data<Gamer>{
    private Gamer gamer;

    @Override
    public void setData(Gamer data) {
        this.gamer = data;
    }

    @Override
    public Gamer getData() {
        return gamer;
    }
}

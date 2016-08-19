package Radiacia.gui.display;

import Radiacia.game.GameObject;
import Radiacia.game.Gamer;
import Radiacia.game.Shot;

import java.awt.*;

/**
 * Created by Cntgfy on 19.08.2016.
 *
 * Рисует игровые объекты, считая, что pos находится
 * в центре полотна рисования
 */
public class Artist {
    private GameObject pos;
    private Graphics g;

    private GamerDisplay gd;
    private ShotDisplay sd;

    public Artist(Graphics g, GameObject pos) {
        this.g = g;
        this.pos = pos;

        this.sd = new ShotDisplay(g, pos);
        this.gd = new GamerDisplay(g, pos);
    }

    public void setGraphics(Graphics g) {
        this.g = g;

        this.gd.setG(g);
        this.sd.setG(g);
    }

    public void draw(Gamer gamer) {
        new GamerDisplay(g, pos).draw(gamer);
    }

    public void draw(Shot shot) {
        new ShotDisplay(g, pos).draw(shot);
    }
}

package Radiacia.gui;

import Radiacia.Game.Shot;

import java.awt.*;

/**
 * Created by Cntgfy on 08.08.2016.
 *
 * Рисует игровые объекты
 */
public class GameArtist {
    private Graphics g;

    public GameArtist(Graphics g) {
        this.g = g;
    }

    public void setGraphics(Graphics g) {
        this.g = g;
    }

    public void drawShot(Shot shot) {

    }


}

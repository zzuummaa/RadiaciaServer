package Radiacia.gui.display;

import Radiacia.game.GameObject;
import Radiacia.game.Shot;

import java.awt.*;

/**
 * Created by Cntgfy on 19.08.2016.
 */
public class ShotDisplay extends GameObjectDisplay<Shot> {
    /**
     * @param g   полотно для отображения
     * @param pos место, которому соответствует центр полотна
     */
    public ShotDisplay(Graphics g, GameObject pos) {
        super(g, pos);
    }

    @Override
    public void draw(Shot shot) {
        draw(shot, drawPos(shot));
    }

    /**
     * Аналогично:
     * @see #drawGameObject(Radiacia.game.GameObject, int[], java.awt.Color)
     */
    public void draw(Shot shot, int[] pos) {
        drawGameObject(shot, pos, Color.RED);
    }
}

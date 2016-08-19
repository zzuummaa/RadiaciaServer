package Radiacia.gui.display;

import Radiacia.game.GameObject;
import Radiacia.game.Gamer;

import java.awt.*;

/**
 * Created by Cntgfy on 19.08.2016.
 */
public class GamerDisplay extends GameObjectDisplay<Gamer> {
    /**
     * @param g   полотно для отображения
     * @param pos место, которому соответствует центр полотна
     */
    public GamerDisplay(Graphics g, GameObject pos) {
        super(g, pos);
    }

    @Override
    public void draw(Gamer gamer) {
        draw(gamer, drawPos(gamer));
    }

    /**
     * Аналогично:
     * @see #drawGameObject(Radiacia.game.GameObject, int[], java.awt.Color)
     */
    public void draw(Gamer gamer, int[] pos) {
        g.setColor(Color.BLACK);
        int r = (int) (gamer.getAccuracy() / 2);
        g.drawOval(pos[0] - r, pos[1] - r, r*2, r*2);

        drawGameObject(gamer, pos, Color.BLUE);
    }
}

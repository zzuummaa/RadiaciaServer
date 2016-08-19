package Radiacia.gui.display;

import Radiacia.game.GameObject;
import Radiacia.math.CoordinateConversion3D;

import java.awt.*;

/**
 * Created by Cntgfy on 19.08.2016.
 */
public abstract class GameObjectDisplay<A extends GameObject> implements Display<A> {
    protected Graphics g;
    protected GameObject pos;

    /**
     * @param g полотно для отображения
     * @param pos место, которому соответствует центр полотна
     */
    public GameObjectDisplay(Graphics g, GameObject pos) {
        this.g = g;
        this.pos = pos;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    /**
     * @param gameObject объект для отображения
     * @param pos позиция на Graphic
     * @param goCol цвет объекта
     */
    protected void drawGameObject(GameObject gameObject, int[] pos, Color goCol) {
        int size = 10;
        g.setColor(goCol);
        g.fillOval(pos[0] - size/2, pos[1] - size/2, size, size);

        g.setColor(Color.BLACK);
        g.drawLine(pos[0], pos[1], pos[0] + (int) ( 4*size * Math.cos(Math.toRadians(gameObject.getDirection())) )
                , pos[1] + (int) ( 4*size * Math.sin(Math.toRadians(gameObject.getDirection())) ) );
    }

    private CoordinateConversion3D cc3 = new CoordinateConversion3D();
    /**
     * Вычисляет положние объекта по отношению
     * к позиции pos
     *
     * @param go объект, позицию которого ищем
     * @return позиция: [0] - x
     *                  [1] - y
     */
    public int[] drawPos(GameObject go) {
        double distanceBetween = cc3.distanceBetween(pos.getLatitude(), pos.getLongitude(), go.getLatitude(), go.getLongitude());

        float angleOnPlane = cc3.angleOnPlane(pos.getLatitude(), pos.getLongitude(), go.getLatitude(), go.getLongitude());

        int[] pos = new int[2];
        pos[0] = (int) (distanceBetween * -Math.sin(Math.toRadians(angleOnPlane)));
        pos[1] = (int) (distanceBetween * -Math.cos(Math.toRadians(angleOnPlane)));

        return pos;
    }
}

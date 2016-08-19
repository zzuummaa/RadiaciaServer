package Radiacia.gui;

import Radiacia.game.GameObject;
import Radiacia.game.Gamer;
import Radiacia.game.Shot;
import Radiacia.math.CoordinateConversion3D;

import java.awt.*;

/**
 * Created by Cntgfy on 08.08.2016.
 *
 * Рисует игровые объекты
 */
public class GameArtist {
    private Graphics g;
    private GameObject pos;

    public GameArtist(Graphics g, GameObject pos) {
        this.g = g;
        this.pos = pos;
    }

    public void setGraphics(Graphics g) {
        this.g = g;
    }

    CoordinateConversion3D cc3 = new CoordinateConversion3D();

    public void draw(Shot shot) {
        int[] pos = posGameObject(shot);

        drawGameObject(shot, pos, Color.RED);
    }

    public void draw(Gamer gamer) {
        int[] pos = posGameObject(gamer);

        g.setColor(Color.BLACK);
        int r = (int) (gamer.getAccuracy() / 2);
        g.drawOval(pos[0] - r, pos[1] - r, r*2, r*2);

        drawGameObject(gamer, pos, Color.BLUE);
    }

    private void drawGameObject(GameObject gameObject, int[] pos, Color goCol) {
        int size = 10;
        g.setColor(goCol);
        g.fillOval(pos[0] - size/2, pos[1] - size/2, size, size);

        g.setColor(Color.BLACK);
        g.drawLine(pos[0], pos[1], pos[0] + (int) ( 4*size * Math.cos(Math.toRadians(gameObject.getDirection())) )
                                 , pos[1] + (int) ( 4*size * Math.sin(Math.toRadians(gameObject.getDirection())) ) );
    }

    /**
     * Вычисляет положние объекта по отношению
     * к позиции pos
     *
     * @param go объект, позицию которого ищем
     * @return позиция: [0] - x
     *                  [1] - y
     */
    public int[] posGameObject(GameObject go) {
        double distanceBetween = cc3.distanceBetween(pos.getLatitude(), pos.getLongitude(), go.getLatitude(), go.getLongitude());

        float angleOnPlane = cc3.angleOnPlane(pos.getLatitude(), pos.getLongitude(), go.getLatitude(), go.getLongitude());

        int[] pos = new int[2];
        pos[0] = (int) (distanceBetween * -Math.sin(Math.toRadians(angleOnPlane)));
        pos[1] = (int) (distanceBetween * -Math.cos(Math.toRadians(angleOnPlane)));

        //System.out.println(angleOnPlane);

        return pos;
    }

    public void setPos(GameObject pos) {
        this.pos = pos;
    }

    public GameObject getPos() {
        return pos;
    }
}

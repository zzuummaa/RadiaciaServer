package Radiacia.gui;

import Radiacia.Game.GameObject;
import Radiacia.Game.Shot;
import Radiacia.math.CoordinateConversion3D;

import java.awt.*;

/**
 * Created by Cntgfy on 08.08.2016.
 *
 * Рисует игровые объекты
 */
public class GameArtist {
    private Graphics g;
    private double latitude;
    private double longitude;
    private int altitude = 100;

    public GameArtist(Graphics g, double latitude, double longitude) {
        this.g = g;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setGraphics(Graphics g) {
        this.g = g;
    }

    CoordinateConversion3D cc3 = new CoordinateConversion3D();

    public void drawShot(Shot shot) {
        int[] pos = posGameObject(shot);

        int scale = 10;
        g.setColor(Color.RED);
        g.fillOval(pos[0] - scale/2, pos[1] - scale/2, scale, scale);

        g.setColor(Color.BLACK);
        g.drawLine(pos[0], pos[1], pos[0] + scale*2, pos[1] + scale*2);
    }

    /**
     * Вычисляет положния объекта на плоскости рисования
     *
     * @param go объект, позицию которого ищем
     * @return позиция:
     *         pos[0] - x
     *         pos[1] - y
     */
    public int[] posGameObject(GameObject go) {
        double distanceBetween = cc3.distanceBetween(latitude, longitude, go.getLatitude(), go.getLongitude());

        float angleOnPlane = cc3.angleOnPlane(latitude, longitude, go.getLatitude(), go.getLongitude());

        int[] pos = new int[2];
        pos[0] = (int) (distanceBetween * Math.cos(angleOnPlane));
        pos[1] = (int) (distanceBetween * Math.sin(angleOnPlane));

        return pos;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getAltitude() {
        return altitude;
    }
}

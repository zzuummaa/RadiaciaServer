package Radiacia.gui;

import Radiacia.Game.Shoot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Cntgfy on 07.08.2016.
 */
public class GameWindow extends JPanel {
    private double latitude;
    private double longitude;
    private double altitude;

    private List<Shoot> shoots = new ArrayList<>();

    public GameWindow() {
        setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        design(g);
        coordinateInfo(g);
    }

    /**
     * Выводит информацию о месте, на которое сейчас направллен экран
     */
    private void coordinateInfo(Graphics g) {
        int beginX = 10;
        int beginY = 10;
        int deltaY = 20;
        g.drawString("latitude: " + latitude, beginX, beginY + deltaY);
        g.drawString("longitude: " + longitude, beginX, beginY + deltaY*2);
        g.drawString("altitude: " + altitude, beginX, beginY + deltaY*3);
    }

    public void design(Graphics g) {
        g.drawLine(0, 0, getWidth(), 0);
    }

    public void addShoots(Collection<Shoot> shoots) {
        this.shoots.addAll(shoots);
    }

    public Collection<Shoot> getShoots() {
        return shoots;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }
}

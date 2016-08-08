package Radiacia.gui;

import Radiacia.Game.Gamer;
import Radiacia.Game.Shot;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cntgfy on 07.08.2016.
 */
public class GameWindow extends JPanel {
    private double latitude;
    private double longitude;
    private double altitude;

    private Set<Shot> shoots = new HashSet<>();
    private Set<Gamer> gamers = new HashSet<>();

    public GameWindow() {
        setBackground(Color.WHITE);
    }

    GameArtist artist = new GameArtist(null, latitude, longitude);

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        design(g);
        coordinateInfo(g);

        artist.setGraphics(g);
        drawShots();
    }

    public void drawShots() {
        for (Shot shoot: shoots) {
            artist.drawShot(shoot);
        }
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

    public void addGamers(Collection<Gamer> gamers) {
        this.gamers.addAll(gamers);
    }

    public Collection<Gamer> getGamers() {
        return gamers;
    }

    public void addShoots(Collection<Shot> shoots) {
        this.shoots.addAll(shoots);
    }

    public Collection<Shot> getShoots() {
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

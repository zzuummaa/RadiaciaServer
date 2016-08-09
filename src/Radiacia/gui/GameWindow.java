package Radiacia.gui;

import Radiacia.Game.GameObject;
import Radiacia.Game.Gamer;
import Radiacia.Game.Shot;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Cntgfy on 07.08.2016.
 */
public class GameWindow extends JPanel {
    private GameObject pos;
    private double altitude = 100;

    private Set<Shot> shots = new HashSet<>();
    private Set<Gamer> gamers = new HashSet<>();

    public GameWindow() {
        setBackground(Color.WHITE);

        pos = new GameObject(0, 0, 0f);
    }

    GameArtist artist = new GameArtist(null, pos);

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        drawGameObjects(g);
        design(g);
        coordinateInfo(g);
    }

    /**
     * Преобразует координатную систему для рисования
     * и рисует игровые объекты
     */
    private void drawGameObjects(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        double scale = altitudeScale();
        g2d.scale(scale, scale);

        artist.setGraphics(g);
        drawShots();

        //Возвращение начального значения scale
        scale = 1 / scale;
        g2d.scale(scale, scale);
    }

    //Количество пикселей на метр
    public static final double ppm = 5000d;
    private double scale;

    /**
     * Возвращает высоту просмотра объектов в виде размера игровых эелементов
     */
    private double altitudeScale() {
        if (altitude != 0) {
            this.scale = ppm / (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / altitude);
        } else {
            this.scale = Double.MAX_VALUE;
        }

        return scale;
    }

    private void drawShots() {
        Iterator<Shot> iterator = shots.iterator();
        while (iterator.hasNext()){
            artist.draw(iterator.next());
        }
    }

    /**
     * Выводит информацию о месте, на которое сейчас направллен экран
     */
    private void coordinateInfo(Graphics g) {
        int beginX = 10;
        int beginY = 10;
        int deltaY = 20;
        g.drawString("latitude: " + pos.getLatitude(), beginX, beginY + deltaY);
        g.drawString("longitude: " + pos.getLongitude(), beginX, beginY + deltaY*2);
        g.drawString("altitude: " + (int) altitude, beginX, beginY + deltaY*3);
    }

    public void design(Graphics g) {
        g.drawLine(0, 0, getWidth(), 0);
    }

    public void addGamers(Collection<Gamer> gamers) {
        this.gamers.addAll(gamers);
        this.repaint();
    }

    public Collection<Gamer> getGamers() {
        return gamers;
    }

    public void addShots(Collection<Shot> shoots) {
        this.shots.addAll(shoots);
        this.repaint();
    }

    public Collection<Shot> getShots() {
        return shots;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public GameObject getPos() {
        return pos;
    }

    public void setPos(GameObject pos) {
        this.pos = pos;
        repaint();
    }

    /**
     * @return количество пикселей, видных на данной высоте
     * @see #altitudeScale() - вычисление
     */
    public double getScale() {
        return scale;
    }

    /**
     * Переводит пиксели на экране в метры на Земном шаре
     *
     * @param pixels
     * @return метры на Земном шаре
     */
    public double realMeters(double pixels) {
        return scale / ppm * pixels;
    }
}

package Radiacia.gui;

import Radiacia.game.GameObject;
import Radiacia.game.Gamer;
import Radiacia.game.Shot;
import Radiacia.gui.display.Artist;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Cntgfy on 07.08.2016.
 *
 * Управляет отображением игровых объектов на панель
 */
public class GameWindow extends JPanel {
    /**
     * Положение камеры на карте
     */
    private GameObject pos;
    private double altitude = 500;

    private Set<Shot> shots = new HashSet<>();
    private Set<Gamer> gamers = new HashSet<>();

    private JButton nextShotBt;
    private JButton nextGamerBt;

    private IterateGameObjectListener<Shot> iterateShotsListener;
    private IterateGameObjectListener<Gamer> iterateGamersListener;

    public GameWindow() {
        setBackground(Color.WHITE);

        nextShotBt = new JButton("Next shot");
        nextGamerBt = new JButton("Next gamer");

        pos = new GameObject(0, 0, 0f);
        artist = new Artist(null, pos);

        PositionMover positionMover = new PositionMover(this);
        addMouseMotionListener(positionMover);
        addMouseListener(positionMover);

        addNextBts(this);
    }

    /**
     * Добавляет кнопки перехода по игровым объектам к игровому окну
     */
    private void addNextBts(GameWindow gameWindow) {
        //nextShotBt.setSize(100, 40);
        iterateShotsListener = new IterateGameObjectListener<Shot>(gameWindow, gameWindow.getShots());
        nextShotBt.addActionListener(iterateShotsListener);
        gameWindow.add(nextShotBt);

        //nextGamerBt.setSize(100, 40);
        iterateGamersListener = new IterateGameObjectListener<Gamer>(gameWindow, gameWindow.getGamers());
        nextGamerBt.addActionListener(iterateGamersListener);
        gameWindow.add(nextGamerBt);
    }

    Artist artist;

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
        g2d.translate(getWidth()/2, getHeight()/2);
        g2d.scale(scale, scale);

        artist.setGraphics(g2d);
        drawShots();
        drawGamers();

        //Возвращение начального значения scale
        scale = 1 / scale;
        g2d.scale(scale, scale);
        g2d.translate(-getWidth()/2, -getHeight()/2);
    }

    //Количество пикселей на метр
    public static double ppm = 1;
    private double scale = 1;

    /**
     * Возвращает высоту просмотра объектов в виде размера игровых эелементов
     */
    private double altitudeScale() {
        if (altitude != 0) {
            this.ppm = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / altitude;
            this.scale = ppm;
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

    private void drawGamers() {
        Iterator<Gamer> iterator = gamers.iterator();
        while (iterator.hasNext()) {
            artist.draw(iterator.next());
        }
    }

    private static String format = "%s: %.8f";
    /**
     * Выводит информацию о месте, на которое сейчас направллен экран
     */
    private void coordinateInfo(Graphics g) {
        int beginX = 10;
        int beginY = 10;
        int deltaY = 20;

        g.drawString(String.format(format, "latitude", pos.getLatitude()), beginX, beginY + deltaY);
        g.drawString(String.format(format, "longitude", pos.getLongitude()), beginX, beginY + deltaY*2);
        g.drawString("altitude: " + (long) altitude, beginX, beginY + deltaY*3);
    }

    private void design(Graphics g) {
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

    public void setGamers(Set<Gamer> gamers) {
        this.gamers = gamers;
        this.iterateGamersListener.setGameObjects(gamers);
    }

    public void setShots(Set<Shot> shots) {
        this.shots = shots;
        this.iterateShotsListener.setGameObjects(shots);
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
        return pixels / ppm;
    }
}

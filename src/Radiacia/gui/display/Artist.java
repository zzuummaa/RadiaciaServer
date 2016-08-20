package Radiacia.gui.display;

import Radiacia.game.GameObject;
import Radiacia.game.Gamer;
import Radiacia.game.Shot;

import java.awt.*;

/**
 * Created by Cntgfy on 19.08.2016.
 *
 * Рисует игровые объекты, считая, что pos находится
 * в центре полотна рисования
 */
public class Artist {
    private GameObject pos;
    private Graphics2D g;

    private GamerDisplay gd;
    private ShotDisplay sd;

    /**
     * Масштаб рисования
     * Колличество пикселей на метр
     */
    private double scale;
    public static double ppm = 1;

    /**
     * Разрешение монитора
     */
    private static double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public Artist(Graphics2D g, GameObject pos) {
        this.g = g;
        this.pos = pos;

        this.sd = new ShotDisplay(g, pos);
        this.gd = new GamerDisplay(g, pos);
    }

    public void setGraphics(Graphics2D g) {
        this.g = g;

        this.gd.setG(g);
        this.sd.setG(g);
    }

    public void setPos(GameObject pos) {
        this.pos = pos;
    }

    public void draw(Gamer gamer) {
        gd.draw(gamer);
    }

    public void draw(Shot shot) {
        sd.draw(shot);
    }

    /**
     * Преобразует координаты Graphics для последующего рисования
     */
    public void convertCor(double altitude, int width, int height) {
        double scale = altitudeScale(altitude);
        g.translate(width/2, height/2);
        g.scale(scale, scale);
    }

    /**
     * Возвращает высоту просмотра объектов в виде размера игровых эелементов
     */
    private double altitudeScale(double altitude) {
        if (altitude != 0) {
            this.ppm = screenWidth / altitude;
            this.scale = ppm;
        } else {
            this.scale = Double.MAX_VALUE;
        }

        return scale;
    }

    /**
     * Возвращает состояние Graphics как перед применением <code>convertCor()</code>
     */
    public void cancelConvertCor() {
        scale = 1 / scale;
        g.scale(scale, scale);
        g.translate(-screenWidth/2, -screenHeight/2);
    }
}

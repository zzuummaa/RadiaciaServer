package Radiacia.gui;

import Radiacia.math.CoordinateConversion3D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Cntgfy on 19.08.2016.
 *
 * Перемещает камеру GameWindow при зажатии и перетаскивании мышью
 */
public class PositionMover implements MouseListener, MouseMotionListener {
    private static final CoordinateConversion3D cc3 = new CoordinateConversion3D();

    private GameWindow gw;

    private int x;
    private int y;

    public PositionMover(GameWindow gw) {
        this.gw = gw;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        double dMetersX = e.getX() - x;
        double dMetersY = e.getY() - y;

        //System.out.println("delta x in pics:  " + dMetersX);
        //System.out.println("delta y in pics:  " + dMetersY);

        dMetersX = gw.realMeters(dMetersX);
        dMetersY = gw.realMeters(dMetersY);

        double newLongitude = gw.getPos().getLongitude();
        newLongitude += cc3.deltaLongitude(dMetersX);

        double newLatitude = gw.getPos().getLatitude();
        newLatitude += cc3.deltaLatitude(newLongitude, dMetersY);

        gw.getPos().setLatitude(newLatitude);
        gw.getPos().setLongitude(newLongitude);

        x = e.getX();
        y = e.getY();

        gw.repaint();

        //System.out.println("delta x in meters " + dMetersX);
        //System.out.println("delta y in meters " + dMetersY);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

package Radiacia.gui;

import Radiacia.Game.Shot;
import Radiacia.math.CoordinateConversion3D;

import javax.swing.*;
import java.awt.event.*;
import java.util.Iterator;

/**
 * Created by Cntgfy on 08.08.2016.
 */
public class RadiaciaServerGUI extends JFrame {
    //Величина на которую умножается текущая высота над поверхностью Земли, при прокрутке колесика
    private double scroll = 0.1d;

    private static final CoordinateConversion3D cc3 = new CoordinateConversion3D();

    public GameWindow gameWindow;
    private JButton nextBt;

    public static void main(String[] args) {
        RadiaciaServerGUI radiaciaServerGUI = new RadiaciaServerGUI();
    }

    public RadiaciaServerGUI() {
        super("Radiacia server GUI");
        setSize(getToolkit().getScreenSize());
        setVisible(true);

        gameWindow = new GameWindow();
        add(gameWindow);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getID() == MouseEvent.MOUSE_WHEEL) {
                    gameWindow.setAltitude(gameWindow.getAltitude() + gameWindow.getAltitude() * scroll * e.getPreciseWheelRotation());
                    gameWindow.repaint();
                    //System.out.println("scroll: " + e.getPreciseWheelRotation());
                }
            }
        });

        PositionMover positionMover = new PositionMover();
        gameWindow.addMouseMotionListener(positionMover);
        gameWindow.addMouseListener(positionMover);

        nextBt = new JButton("Next shot");
        nextBt.setBounds(getWidth() - 120, 10, 100, 40);
        nextBt.addActionListener(new ActionListener() {
            Iterator<Shot> iterator = null;
            int i = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iterator == null) iterator = gameWindow.getShots().iterator();

                if (iterator.hasNext()) {
                    Shot shot = iterator.next();
                    gameWindow.getPos().setLatitude(shot.getLatitude());
                    gameWindow.getPos().setLongitude(shot.getLongitude());
                    gameWindow.repaint();
                } else {
                    iterator = null;
                    if (!gameWindow.getShots().isEmpty()) actionPerformed(e);
                }
            }
        });
        gameWindow.add(nextBt);
    }

    private class PositionMover implements MouseListener, MouseMotionListener {
        int x;
        int y;

        @Override
        public void mouseDragged(MouseEvent e) {
            double dMetersX = e.getX() - x;
            double dMetersY = e.getY() - y;

            //System.out.println("delta x in pics:  " + dMetersX);
            //System.out.println("delta y in pics:  " + dMetersY);

            dMetersX = gameWindow.realMeters(dMetersX);
            dMetersY = gameWindow.realMeters(dMetersY);

            double newLongitude = gameWindow.getPos().getLongitude();
            newLongitude += cc3.deltaLongitude(dMetersX);

            double newLatitude = gameWindow.getPos().getLatitude();
            newLatitude += cc3.deltaLatitude(newLongitude, dMetersY);

            gameWindow.getPos().setLatitude(newLatitude);
            gameWindow.getPos().setLongitude(newLongitude);

            x = e.getX();
            y = e.getY();

            gameWindow.repaint();

            //System.out.println("delta x in meters " + dMetersX);
            //System.out.println("delta y in meters " + dMetersY);
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
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
}

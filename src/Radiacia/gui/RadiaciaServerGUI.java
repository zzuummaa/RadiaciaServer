package Radiacia.gui;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Cntgfy on 08.08.2016.
 */
public class RadiaciaServerGUI extends JFrame {
    //Величина на которую умножается текущая высота над поверхностью Земли, при прокрутке колесика
    private double scroll = 0.15;

    public static void main(String[] args) {
        RadiaciaServerGUI radiaciaServerGUI = new RadiaciaServerGUI();
    }

    public RadiaciaServerGUI() {
        super("Radiacia server GUI");
        setSize(getToolkit().getScreenSize());
        setVisible(true);

        GameWindow gameWindow = new GameWindow();
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

        addMouseMotionListener(new MouseMotionListener() {
            int x;
            int y;

            @Override
            public void mouseDragged(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    private class MouseMover implements MouseListener {
        private boolean isPressed = false;

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            isPressed = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            isPressed = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}

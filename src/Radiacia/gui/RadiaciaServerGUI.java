package Radiacia.gui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Created by Cntgfy on 08.08.2016.
 *
 * Фрейм отображающий положение игроков на мировой карте
 */
public class RadiaciaServerGUI extends JFrame {
    //Величина на которую умножается текущая высота над поверхностью Земли, при прокрутке колесика
    private double scroll = 0.1d;

    public GameWindow gameWindow;

    public static void main(String[] args) {
        RadiaciaServerGUI radiaciaServerGUI = new RadiaciaServerGUI();
    }

    public RadiaciaServerGUI() {
        super("Radiacia server GUI");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize());
        addScrolling();

        gameWindow = new GameWindow();
        addGameWindow(gameWindow);

        setVisible(true);
    }

    /**
     * Добавляет возможность масштабировать карту колесиком мыши
     */
    private void addScrolling() {
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
    }

    /**
     * Добавляет игровое окно с кнопками к фрейму
     */
    private void addGameWindow(GameWindow gameWindow) {
        gameWindow.setSize(getSize());
        add(gameWindow);
    }
}

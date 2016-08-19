package Radiacia.gui;

import Radiacia.game.GameObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Cntgfy on 10.08.2016.
 *
 * Перебирает игровые объекты.
 */
public class IterateGameObjectListener<A extends GameObject> implements ActionListener {
    private GameWindow gameWindow;

    private Collection<A> gameObjects;
    private Iterator<A> iterator;

    public IterateGameObjectListener(GameWindow gameWindow, Collection<A> gameObjects) {
        this.gameWindow = gameWindow;
        this.gameObjects = gameObjects;
        this.iterator = gameObjects.iterator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (iterator.hasNext()) {
            A gameObject = iterator.next();

            gameWindow.getPos().setLatitude(gameObject.getLatitude());
            gameWindow.getPos().setLongitude(gameObject.getLongitude());
            gameWindow.repaint();
        } else {
            iterator = gameObjects.iterator();
            if (!gameObjects.isEmpty()) actionPerformed(e);
        }
    }

    /**
     * @param gameObjects не null
     */
    public void setGameObjects(Collection<A> gameObjects) {
        this.gameObjects = gameObjects;
    }
}

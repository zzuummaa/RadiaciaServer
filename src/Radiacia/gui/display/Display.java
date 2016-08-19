package Radiacia.gui.display;

import Radiacia.game.GameObject;

/**
 * Created by Cntgfy on 19.08.2016.
 *
 * Позволяет отображать игровые объекты на Graphic
 */
public interface Display<A extends GameObject> {
    /**
     * @param gameObject - объект, который отображаем
     */
    public void draw(A gameObject);
}

package Radiacia.base;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public interface GamerInterface {
    /**
     * Проверяет, попал ли игрок по другому игроку
     */
    boolean isShot(GamerInterface gamer);

    /**
     * Проверяет, стреляет ли игрок в данный момент
     */
    boolean isShoot();

    void setShoot(boolean isShoot);

    /**
     * Проверяет, жив ли игрок
     */
    boolean isAlive();

    void setAlive(boolean isAlive);

    void setPosition(PositionInterface position);

    PositionInterface getPosition();

    void setDirection(DirectionInterface direction);

    DirectionInterface getDirection();
}

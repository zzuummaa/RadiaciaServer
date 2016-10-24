package Radiacia;

import Radiacia.base.DirectionInterface;
import Radiacia.base.GamerInterface;
import Radiacia.base.PositionInterface;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 */
public class Gamer implements GamerInterface {
    private PositionInterface position;
    private DirectionInterface direction;

    private boolean isShoot;
    private boolean isAlive;

    public Gamer() {
        isAlive = true;
        isShoot = false;
        position = new Position(new double[3], 10);
        direction = new Direction(new double[3], 0.04d);
    }

    /**
     * Проверяет, попал ли один игрок в другого.
     *
     * Если выстрел игрока попадает в область, стреляя в которую возможно
     * попадание исходя из точности, то считается что он попал
     *
     * @param gamer
     * @return
     */
    @Override
    public boolean isShot(GamerInterface gamer) {
        double[] directVec = position.unitVectorTo(gamer.getPosition());
        double angularAccuracy = position.angularAccuracyTo(gamer.getPosition());
        DirectionInterface direct = new Direction(directVec, angularAccuracy);

        double deltaAngle = direction.angleWith(direct);

        if ( Math.abs(deltaAngle) > direction.angularAccuracyWith(direct) ) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isShoot() {
        return isShoot;
    }

    @Override
    public void setShoot(boolean isShoot) {
        this.isShoot = isShoot;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public void setPosition(PositionInterface position) {
        this.position = position;
    }

    @Override
    public PositionInterface getPosition() {
        return position;
    }

    @Override
    public void setDirection(DirectionInterface direction) {
        this.direction = direction;
    }

    @Override
    public DirectionInterface getDirection() {
        return direction;
    }
}

package Radiacia;

import Radiacia.base.DirectionInterface;
import Radiacia.base.GamerInterface;
import Radiacia.base.PositionInterface;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public class Gamer implements GamerInterface {
    private PositionInterface position;
    private DirectionInterface direction;

    private boolean isShoot;
    private boolean isAlive;

    public Gamer() {
        isAlive = true;
        isShoot = false;
        position = new Position();
        direction = new Direction();
    }

    @Override
    public boolean isShot(GamerInterface gamer) {
        DirectionInterface direction = this.getPosition().directionTo(gamer.getPosition());
        double delta = this.getDirection().angleWith(direction);

        if ( Math.abs(delta) > direction.getAccuracy() ) {
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
        return null;
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

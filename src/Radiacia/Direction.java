package Radiacia;

import Radiacia.base.DirectionInterface;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public class Direction implements DirectionInterface {
    private float angles[];

    public Direction() {
        this.angles = new float[3];
    }

    public Direction(float[] angles) {
        this.angles = angles;
    }

    @Override
    public DirectionInterface delta(DirectionInterface direction) {
        return new Direction();
    }

    @Override
    public float getAccuracy() {
        return 0;
    }

    @Override
    public float getSingleAngle() {
        return 0;
    }
}

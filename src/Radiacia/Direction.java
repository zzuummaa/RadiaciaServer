package Radiacia;

import Radiacia.base.DirectionInterface;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public class Direction extends Vector3D implements DirectionInterface {
    /**
     * Еденичный вектор в трехмерных декартовых координатах
     */
    private double angles[];

    public Direction() {
        this.angles = new double[3];
    }

    public Direction(double[] angles) {
        this.angles = angles;
    }

    /**
     * not work
     *
     * @return Точность в радианах [0; 2*PI]
     */
    @Override
    public double getAccuracy() {
        return 0.05d;
    }

    /**
     * Находит угол между <code>this</code> и <code>direction</code> направлениями
     *
     * @param direction
     * @return угол в радианах [0; 2*PI]
     */
    @Override
    public double angleWith(DirectionInterface direction) {
        Direction direct = (Direction) direction;

        return angleWith(this.angles, direct.angles);
    }
}

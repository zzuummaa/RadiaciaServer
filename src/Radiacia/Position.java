package Radiacia;

import Radiacia.base.DirectionInterface;
import Radiacia.base.PositionInterface;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 */
public class Position extends Vector3D implements PositionInterface {
    /**
     * Трехмерные декартовы координаты
     */
    private double[] coordinates;

    public Position() {
        this.coordinates = new double[3];
    }

    public Position(double[] coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Определяет направление вектора от <code>this</code> до
     * <code>position</code> в виде объекта класса <code>Direction</code>
     * @see Radiacia.Direction
     *
     * @param position
     * @throws java.lang.ClassCastException если <code>position</code>
     *         не является объектом класса Position
     * @return направление
     */
    @Override
    public DirectionInterface directionTo(PositionInterface position) throws ClassCastException {
        Position pos = (Position) position;

        double[] resVec = difference(pos.coordinates, this.coordinates);
        vecToAngles(resVec);

        return new Direction(resVec);
    }

    public double[] getCoordinates() {
        return coordinates;
    }
}

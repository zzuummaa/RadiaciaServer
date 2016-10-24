package Radiacia;

import Radiacia.base.PositionInterface;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 */
public class Position extends Vector3D implements PositionInterface<Position> {
    /**
     * Трехмерные декартовы координаты
     */
    public double[] coordinates;

    /**
     * Радиус сферы, ограничивающей возможное местонахождения
     */
    public double accuracy;

    public Position(double[] coordinates, double linearAccuracy) {
        this.coordinates = coordinates;
        this.accuracy = linearAccuracy;
    }

    public double[] directionTo(Position position) {
        double linearAccuracy = linearAccuracyTo(position);
        double angularAccuracy = toAngularAccuracy(linearAccuracy, abs(position.getCoordinates()));

        double[] directVec = positionTo(position);
        normalize(directVec);

        return directVec;
    }

    /**
     * Упрощенное вычисление общей точности
     */
    @Override
    public double linearAccuracyTo(Position position) {
        return accuracy + position.accuracy;
    }

    @Override
    public double angularAccuracyTo(Position position) {
        double[] vec = difference(coordinates, position.coordinates);

        return toAngularAccuracy(linearAccuracyTo(position), abs(vec));
    }

    /**
     * Определяет вектор от <code>this</code> до <code>position</code>
     *
     * @param position
     *
     * @return трехмерный вектор в декартовых координатах
     */
    @Override
    public double[] positionTo(Position position) throws ClassCastException {

        double[] resVec = difference(position.coordinates, this.coordinates);
        vecToAngles(resVec);

        return resVec;
    }

    @Override
    public double[] unitVectorTo(Position position) {
        double[] directVec = positionTo(position);
        normalize(directVec);

        return directVec;
    }

    public double[] getCoordinates() {
        return coordinates;
    }
}

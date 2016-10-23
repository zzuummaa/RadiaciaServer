package Radiacia;

import Radiacia.base.DirectionInterface;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 */
public class Direction extends Vector3D implements DirectionInterface<Direction> {
    /**
     * Еденичный вектор в трехмерных декартовых координатах
     */
    public double unitVec[];

    /**
     * Точность определяющяя максимальный угол отклонения вектора
     * <code>unitVec</code> от вектора действительных значений
     */
    public double accuracy;

    public Direction(double[] unitVec, double angularAccuracy) {
        this.unitVec = unitVec;
        this.accuracy = angularAccuracy;
    }

    @Override
    public double angleWith(Direction direction) {
        return angleWith(unitVec, direction.unitVec);
    }

    /**
     * Находит точность после вычитания двух векторов
     */
    @Override
    public double accuracyWith(Direction direction) {
        return accuracy + direction.accuracy;
    }
}

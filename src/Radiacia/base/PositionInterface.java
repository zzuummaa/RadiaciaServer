package Radiacia.base;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 */
public interface PositionInterface<A extends PositionInterface> {
    /**
     * Определение направления вектора от <code>this</code> позиции
     * в <code>position</code> позицию
     */
    double[] directionTo(A position);

    double linearAccuracyTo(A position);

    double angularAccuracyTo(A position);
}

package Radiacia.base;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 */
public interface PositionInterface<A extends PositionInterface> {
    double linearAccuracyTo(A position);

    /**
     * Точность определения направления между
     * <code>this</code> и <code>position</code> в радианах
     *
     * @return угол в радианах
     */
    double angularAccuracyTo(A position);

    /**
     * Вектор из <code>this</code> к <code>position</code>
     */
    double[] positionTo(A position);

    /**
     * Единичный вектор от <code>this</code> к <code>position</code>
     */
    double[] unitVectorTo(A position);
}

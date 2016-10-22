package Radiacia.base;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public interface PositionInterface {
    /**
     * Определение направления вектора между <code>this</code> позицией
     * и <code>position</code> позицией
     *
     * @return направление вектора из <code>this</code> в <code>position</code>
     */
    DirectionInterface directionTo(PositionInterface position);
}

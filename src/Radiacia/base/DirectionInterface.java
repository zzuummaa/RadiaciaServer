package Radiacia.base;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public interface DirectionInterface {
    double getAccuracy();

    /**
     * Опеределяет угол между <code>this</code> и <code>direction</code>
     *
     * @param direction
     * @return
     */
    double angleWith(DirectionInterface direction);
}

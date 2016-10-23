package Radiacia.base;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 */
public interface DirectionInterface<A extends DirectionInterface> {
    /**
     * Опеределяет угол между двумя направлениями
     *
     * @return угол между <code>this</code> и <code>direction</code>
     */
    double angleWith(A direction);

    /**
     * Находит общую при точность <code>this</code> точности и
     *
     * @param direction
     * @return
     */
    double accuracyWith(A direction);
}

package Radiacia.base;

import java.util.Map;

/**
 * Created by Fomenko_S.V. on 24.10.2016.
 */
public interface DirectionParserInterface<A> {
    void fillDirection(A direction, Map map);

    void fillMap(Map map, A direction);
}

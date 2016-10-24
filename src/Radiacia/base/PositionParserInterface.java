package Radiacia.base;

import java.util.Map;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 */
public interface PositionParserInterface<A> {
    void fillPosition(A position, Map map);

    void fillMap(Map map, A position);
}

package Radiacia.base;

import java.util.Map;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public interface GamerParserInterface {
    void fillGamer(GamerInterface gamer, Map map);

    void fillMap(Map map, GamerInterface gamer);
}

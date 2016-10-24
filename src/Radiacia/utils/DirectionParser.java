package Radiacia.utils;

import Radiacia.Direction;
import Radiacia.base.DirectionParserInterface;

import java.util.Map;

/**
 * Created by Fomenko_S.V. on 24.10.2016.
 */
public class DirectionParser implements DirectionParserInterface<Direction> {
    public static enum fields {
        unitVec,
        angularAccuracy
    }

    @Override
    public void fillDirection(Direction direction, Map map) {
        direction.unitVec = (double[]) map.get(fields.unitVec.toString());
        direction.accuracy = (double) map.get(fields.angularAccuracy.toString());
    }

    @Override
    public void fillMap(Map map, Direction direction) {
        map.put("unitVec", direction.unitVec);
        map.put("angularAccuracy", direction.accuracy);
    }
}

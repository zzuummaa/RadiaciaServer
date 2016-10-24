package Radiacia.utils;

import Radiacia.Position;
import Radiacia.base.PositionParserInterface;

import java.util.Map;

/**
 * Created by Fomenko_S.V. on 24.10.2016.
 */
public class PositionParser implements PositionParserInterface<Position> {
    public static enum fields {
        coordinates,
        linearAccuracy
    }

    @Override
    public void fillPosition(Position position, Map map) {
        position.coordinates = (double[]) map.get(fields.coordinates.toString());
        position.accuracy = (double) map.get(fields.linearAccuracy.toString());
    }

    @Override
    public void fillMap(Map map, Position position) {
        map.put(fields.coordinates.toString(), position.coordinates);
        map.put(fields.linearAccuracy.toString(), position.accuracy);
    }
}

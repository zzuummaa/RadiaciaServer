package Radiacia.utils;

import Radiacia.base.*;

import java.util.Map;

/**
 * Created by Fomenko_S.V. on 24.10.2016.
 */
public class GamerParser implements GamerParserInterface {
    private DirectionParserInterface directionParser;
    private PositionParserInterface positionParser;

    public static enum fields {
        isShoot,
        isAlive
    }

    public GamerParser(DirectionParserInterface directionParser, PositionParserInterface positionParser) {
        this.directionParser = directionParser;
        this.positionParser = positionParser;
    }

    @Override
    public void fillGamer(GamerInterface gamer, Map map) {
        gamer.setAlive((Boolean) map.get(fields.isAlive.toString()));
        gamer.setShoot((Boolean) map.get(fields.isShoot.toString()));

        directionParser.fillDirection(gamer.getDirection(), map);
        positionParser.fillPosition(gamer.getPosition(), map);
    }

    @Override
    public void fillMap(Map map, GamerInterface gamer) {
        map.put("isAlive", gamer.isAlive());
        map.put("isShoot", gamer.isShoot());

        directionParser.fillMap(map, gamer.getDirection());
        positionParser.fillMap(map, gamer.getPosition());
    }
}

package tests;

import Radiacia.Gamer;
import Radiacia.base.*;
import Radiacia.utils.GamerParser;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class GamerParserTest {
    private DirectionParserInterface directionParser = new DirectionParserInterface() {
        @Override
        public void fillDirection(Object direction, Map map) {

        }

        @Override
        public void fillMap(Map map, Object direction) {

        }
    };
    private PositionParserInterface positionParser = new PositionParserInterface() {
        @Override
        public void fillPosition(Object position, Map map) {

        }

        @Override
        public void fillMap(Map map, Object position) {

        }
    };

    private GamerParserInterface gamerParser = new GamerParser(directionParser, positionParser);
    private GamerInterface trueGamer = new Gamer();

    @Before
    public void before() {
        trueGamer.setAlive(true);
        trueGamer.setShoot(true);
    }

    @Test
    public void testFillGamer() throws Exception {
        Gamer actualGamer = new Gamer();
        actualGamer.setAlive(false);
        actualGamer.setShoot(false);

        Map<String, Object> values = new HashMap<>();
        values.put("isAlive", trueGamer.isAlive());
        values.put("isShoot", trueGamer.isShoot());

        gamerParser.fillGamer(actualGamer, values);

        assertEquals(trueGamer.isShoot(), actualGamer.isShoot());
        assertEquals(trueGamer.isAlive(), actualGamer.isAlive());
    }

    @Test
    public void testFillMap() throws Exception {
        Map<String, Object> values = new HashMap();
        gamerParser.fillMap(values, trueGamer);

        assertEquals(trueGamer.isAlive(), values.get("isAlive"));
        assertEquals(trueGamer.isShoot(), values.get("isShoot"));
    }
}
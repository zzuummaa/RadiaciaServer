package tests;

import Radiacia.Direction;
import Radiacia.base.DirectionParserInterface;
import Radiacia.utils.DirectionParser;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class DirectionParserTest {
    private DirectionParserInterface directionParser = new DirectionParser();
    private Direction trueDirection = new Direction(new double[]{1, 2, 3}, 4);

    @Test
    public void testFillDirection() throws Exception {
        Direction actualDirection = new Direction(new double[3], 0);

        Map<String, Object> values = new HashMap<>();
        values.put("unitVec", trueDirection.unitVec);
        values.put("angularAccuracy", trueDirection.accuracy);

        directionParser.fillDirection(actualDirection, values);

        assertEquals(trueDirection.accuracy, actualDirection.accuracy);
        assertEquals(trueDirection.unitVec, actualDirection.unitVec);
    }

    @Test
    public void testFillMap() throws Exception {
        Map<String, Object> values = new HashMap();
        directionParser.fillMap(values, trueDirection);

        assertEquals("invalid unit vector", trueDirection.unitVec, values.get("unitVec"));
        assertEquals("invalid accuracy", trueDirection.accuracy, values.get("angularAccuracy"));
    }
}
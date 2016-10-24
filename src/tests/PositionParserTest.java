package tests;

import Radiacia.Position;
import Radiacia.base.PositionParserInterface;
import Radiacia.utils.PositionParser;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class PositionParserTest {
    private PositionParserInterface positionParser = new PositionParser();
    private Position truePosition = new Position(new double[]{1, 2, 3}, 4);

    @Test
    public void testFillDirection() throws Exception {
        Position actualPosition = new Position(new double[3], 0);

        Map<String, Object> values = new HashMap<>();
        values.put("coordinates", truePosition.coordinates);
        values.put("linearAccuracy", truePosition.accuracy);

        positionParser.fillPosition(actualPosition, values);

        assertEquals(truePosition.accuracy, actualPosition.accuracy);
        assertEquals(truePosition.coordinates, actualPosition.coordinates);
    }

    @Test
    public void testFillMap() throws Exception {
        Map<String, Object> values = new HashMap();
        positionParser.fillMap(values, truePosition);

        assertEquals("invalid coordinates", truePosition.coordinates, values.get("coordinates"));
        assertEquals("invalid accuracy", truePosition.accuracy, values.get("linearAccuracy"));
    }
}
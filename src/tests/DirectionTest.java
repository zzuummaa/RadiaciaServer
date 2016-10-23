package tests;

import Radiacia.Direction;
import Radiacia.base.DirectionInterface;
import org.junit.Test;

import static java.lang.Math.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.failNotEquals;

public class DirectionTest {
    double unitVecCor = 1 / sqrt(3);
    double accuracy = 2*PI * 0.1d;
    double trueDirect = acos(1 / sqrt(3));

    DirectionInterface direction = new Direction(new double[]{unitVecCor, unitVecCor, unitVecCor}, accuracy);

    DirectionInterface directionX = new Direction(new double[]{1, 0, 0}, accuracy);
    DirectionInterface directionY = new Direction(new double[]{0, 1, 0}, accuracy);
    DirectionInterface directionZ = new Direction(new double[]{0, 0, 1}, accuracy);

    @Test
    public void testAngleWith() throws Exception {
        testDirections(direction.angleWith(directionX), trueDirect);
        testDirections(direction.angleWith(directionY), trueDirect);
        testDirections(direction.angleWith(directionZ), trueDirect);
    }

    private void testDirections(double actualDirect, double trueDirect) {
        if (actualDirect - trueDirect > PI / 360) {
            failNotEquals("Difference between angles: ", trueDirect / PI * 180, actualDirect / PI * 180);
        }
    }

    @Test
    public void testAccuracyWith() throws Exception {
        assertEquals(direction.accuracyWith(directionX), accuracy * 2, accuracy / 180);
    }
}
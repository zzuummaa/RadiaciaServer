package tests;

import Radiacia.Position;
import Radiacia.base.PositionInterface;
import org.junit.Test;

import static java.lang.Math.sqrt;
import static junit.framework.Assert.assertEquals;

public class PositionTest {
    double accuracy = 0.01d;

    double[] pos1 = new double[]{-2, -2, -2};
    double[] pos2 = new double[]{ 1,  1,  1};

    PositionInterface position1 = new Position(pos1, accuracy);
    PositionInterface position2 = new Position(pos2, accuracy);

    @Test
    public void testDirectionTo() throws Exception {
        double[] trueVec = new double[3];
        for (int i = 0; i < trueVec.length; i++) {
            trueVec[i] = pos2[i] - pos1[i];
        }

        double abs = sqrt(trueVec[0]*trueVec[0] + trueVec[1]*trueVec[1] + trueVec[2]*trueVec[2]);

        for (int i = 0; i < trueVec.length; i++) {
            trueVec[i] /= abs;
        }

        double[] actualVec = position1.directionTo(position2);

        for (int i = 0; i < trueVec.length; i++) {
            assertEquals(trueVec[i], actualVec[i], accuracy);
        }
    }
}
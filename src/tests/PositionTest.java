package tests;

import Radiacia.Position;
import Radiacia.base.PositionInterface;
import org.junit.Test;

import static java.lang.Math.*;
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

        double[] actualVec = position1.unitVectorTo(position2);

        vectorEquals(trueVec, actualVec, 2 * accuracy);
    }


    @Test
    public void testPositionTo() throws Exception {
        double[] truePos = {3d, 3d, 3d};
        double[] actualPos = position1.positionTo(position2);

        vectorEquals(truePos, actualPos, 2 * accuracy);
    }

    @Test
    public void testUnitVectorTo() throws Exception {
        double cor = 1 / sqrt(3);
        double[] trueVec = {cor, cor, cor};

        double[] actualVec = position1.unitVectorTo(position2);

        vectorEquals(trueVec, actualVec, 360 * PI);
    }

    private void vectorEquals(double[] trueVec, double[] actualVec, double accuracy) {
        for (int i = 0; i < trueVec.length; i++) {
            assertEquals(trueVec[i], actualVec[i], accuracy);
        }
    }
}
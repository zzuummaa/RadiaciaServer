package Radiacia;

import static java.lang.Math.acos;
import static java.lang.Math.sqrt;

/**
 * Created by Fomenko_S.V. on 22.10.2016.
 */
public class Vector3D {
    /**
     * Разность между двумя векторами
     *
     * @param pos1 уменьшаемое
     * @param pos2 вычитаемое
     * @return     частное
     */
    public double[] difference(double[] pos1, double[] pos2) {
        double[] res = new double[3];

        res[0] = pos1[0] - pos2[0];
        res[1] = pos1[1] - pos2[1];
        res[2] = pos1[2] - pos2[2];

        return res;
    }

    public void vecToAngles(double[] vec) {
        normalize(vec);

        vec[0] = acos(vec[0]);
        vec[1] = acos(vec[1]);
        vec[2] = acos(vec[2]);
    }

    public void normalize(double[] vec) {
        double len = sqrt(vec[0]*vec[0] + vec[1]*vec[1] + vec[2]*vec[2]);

        vec[0] = vec[0] / len;
        vec[1] = vec[1] / len;
        vec[2] = vec[2] / len;
    }

    public static double abs(double[] vec) {
        return sqrt(vec[0]*vec[0] + vec[1]*vec[1] + vec[2]*vec[2]);
    }

    public static double angleWith(double[] vec1, double[] vec2) {
        double cos = ( vec1[0]*vec2[0] + vec1[1]*vec2[1] + vec1[2]*vec2[2] ) /
                     ( abs(vec1) * abs(vec2) );

        return acos(cos);
    }
}

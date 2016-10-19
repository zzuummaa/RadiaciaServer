package Radiacia.base;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public interface DirectionInterface {
    DirectionInterface delta(DirectionInterface direction);

    float getAccuracy();

    float getSingleAngle();
}

package Radiacia;

import Radiacia.base.DirectionInterface;
import Radiacia.base.PositionInterface;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public class Position implements PositionInterface {
    private double[] coordinates;

    public Position() {
        this.coordinates = new double[3];
    }

    public Position(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public DirectionInterface direction(PositionInterface position) {
        Position pos = (Position) position;
        double[] cors = pos.getCoordinates();



        return null;
    }

    public double[] getCoordinates() {
        return coordinates;
    }
}

package Radiacia.base;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 */
public interface PositionParserInterface {
    void fillPosition(PositionInterface position, String data);

    String toData(PositionInterface position);
}

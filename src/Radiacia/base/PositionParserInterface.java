package Radiacia.base;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public interface PositionParserInterface {
    void fillPosition(PositionInterface position, String data);

    String toData(PositionInterface position);
}

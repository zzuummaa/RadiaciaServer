package Radiacia.base;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public interface GamerParserInterface {
    void fillGamer(GamerInterface gamer, String data);

    String toData(GamerInterface gamer);
}

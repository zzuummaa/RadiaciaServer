package tests;

import Radiacia.Gamer;
import Radiacia.base.DirectionInterface;
import Radiacia.base.GamerInterface;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class GamerTest {
    GamerInterface gamer1 = new Gamer();
    GamerInterface gamer2 = new Gamer();

    @Test
    public void testIsShot1() throws Exception {
        //Выстрел с попаданием
        gamer1.setDirection(new DirectionInterface() {
            @Override
            public double angleWith(DirectionInterface direction) {
                return 1d;
            }

            @Override
            public double accuracyWith(DirectionInterface direction) {
                return 2d;
            }
        });
        assertEquals(gamer1.isShot(gamer2), true);
    }

    @Test
    public void testIsShot2() throws Exception {
        //Промах
        gamer1.setDirection(new DirectionInterface() {
            @Override
            public double angleWith(DirectionInterface direction) {
                return 2d;
            }

            @Override
            public double accuracyWith(DirectionInterface direction) {
                return 0d;
            }
        });
        assertEquals(gamer1.isShot(gamer2), false);
    }
}
package Radiacia.tests;

import Radiacia.game.Gamer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class GamerTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Пересылка значения переменной isShoot c помощью сериализации
     */
    @Test
    public void SerialIsShootTest() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);

        Gamer test = new Gamer();
        test.setIsShoot(true);

        out.writeObject( test);
        out.close();

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);

        Gamer load = (Gamer) in.readObject();

        Assert.assertEquals(test.isShoot(), load.isShoot());
    }
}
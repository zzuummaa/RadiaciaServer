package Radiacia.data;

import Radiacia.Game.Gamer;

import java.io.*;

/**
 * Created by Cntgfy on 11.08.2016.
 */
public class Main_TestData {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);

        GamerData gData = new GamerData(new Gamer());
        gData.setData(new Gamer("TestGamer"));
        System.out.println(gData.getData());
        out.writeObject(gData);

        out.close();

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);

        in.close();

        System.out.println( ((GamerData) in.readObject()).getData() );
    }
}

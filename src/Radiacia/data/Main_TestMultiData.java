package Radiacia.data;

import Radiacia.ClientHandler;
import Radiacia.GameHandler;
import Radiacia.MultiHandler;

import java.util.ArrayList;

/**
 * Created by Cntgfy on 27.07.2016.
 */
public class Main_TestMultiData extends MultiHandler{
    public static void main(String[] args) throws Exception {
        MultiHandler multiHandler = new MultiHandler();
        multiHandler.handlers().add(new ClientHandler());
        multiHandler.handlers().add(new GameHandler());

        ArrayList<MultiData> multiData = new ArrayList<>();
        for (long i = 0; i < 1_000_000; i++) {
            MultiData tmp = new MultiData(new Data[]{new ClientData(), new GamerData()});
            multiData.add(tmp);
        }

        long beginTime = System.currentTimeMillis();
        multiHandler.handle(multiData);
        long endTime = System.currentTimeMillis();

        System.out.println("times to handle multiData: " + (endTime - beginTime));
    }
}

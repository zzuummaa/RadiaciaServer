package Radiacia.Old;

import Radiacia.data.ClientData;
import Radiacia.data.Data;

import java.util.Date;

/**
 * Created by Cntgfy on 24.07.2016.
 */
public class Main_TestInstanceOf {
    public static void main(String[] args) {
        int iterations = 1000000;

        System.out.println("simple check times: " + testSimpleCheck(iterations).getTime());
        System.out.println("instanceOf times:   " + testInstanceOf(iterations).getTime());
    }

    private static Date testSimpleCheck(int iterations) {
        Date begin = new Date(System.currentTimeMillis());

        for (int i = 0; i < iterations; i++) {
            checkInstanceOf();
        }

        Date end = new Date(System.currentTimeMillis());

        return new Date(end.getTime() - begin.getTime());
    }

    private static void simpleCheck() {
        Object object = new ClientData();
        //boolean isData = ((Data) object).contains(Data.DATA);
    }

    private static Date testInstanceOf(int iterations) {
        Date begin = new Date(System.currentTimeMillis());

        for (int i = 0; i < iterations; i++) {
            checkInstanceOf();
        }

        Date end = new Date(System.currentTimeMillis());

        return new Date(end.getTime() - begin.getTime());
    }

    private static void checkInstanceOf() {
        Object object = new ClientData();
        boolean isData = object instanceof Data;
    }
}

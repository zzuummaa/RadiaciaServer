package Radiacia.client;

import Radiacia.data.Data;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Слушает клиента и сохраняет данные
 */
public class ClientListenThread extends Thread {
    private LinkedList<Data> dataList;
    private Client client;

    public ClientListenThread(Client client) {
        this.client = client;
        this.dataList = new LinkedList();

        start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Data data = client.read();

                if (isInterrupted()) break;

                synchronized (dataList) {
                    dataList.add(data);
                }
            } catch (IOException e) {
                if (!isInterrupted()) {
                    e.printStackTrace();
                    interrupt();
                }
            }
        }
    }

    public Collection<Data> getData() {
        synchronized (dataList) {
            Collection tmp = dataList;
            dataList = new LinkedList<>();

            return tmp;
        }
    }
}

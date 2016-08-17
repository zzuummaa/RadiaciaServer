package Radiacia.client;

import Radiacia.data.Data;
import Radiacia.data.datamanager.DataClassifier;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Слушает клиента и сохраняет данные
 */
public class ClientListenThread extends Thread {
    private DataClassifier dc;
    private Collection<Data> dataList;
    private Client client;

    public ClientListenThread(Client client) {
        this.client = client;
        this.dataList = new LinkedList();

        start();
    }

    public ClientListenThread(DataClassifier dc, Client client) {
        this(client);
        this.dc = dc;
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
            Collection<Data> tmp;

            if (dc != null) {
                tmp = dc.parseFromClient(dataList);
                dc.reset();
            } else {
                tmp = dataList;
                dataList = new LinkedList<>();
            }

            return tmp;
        }
    }
}

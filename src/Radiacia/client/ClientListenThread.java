package Radiacia.client;

import Radiacia.data.Data;
import Radiacia.eventlisteners.DataListener;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Слушает клиента и сохраняет данные
 */
public class ClientListenThread extends Thread {
    private Collection<DataListener> listeners;
    private Client client;

    protected ClientListenThread() {
        super("ClientListenThread");
    }

    public ClientListenThread(Client client) {
        this();
        this.client = client;
        this.listeners = new LinkedList<>();

        start();
    }

    public ClientListenThread(Client client, DataListener... listeners) {
        this(client);

        for (int i = 0; i < listeners.length; i++) {
            this.listeners.add(listeners[i]);
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Data data = client.read();

                if (isInterrupted()) break;

                Iterator<DataListener> iterator = listeners.iterator();
                while (iterator.hasNext()) iterator.next().initEvent(data);

            } catch (IOException e) {
                if (!isInterrupted()) {
                    e.printStackTrace();
                    interrupt();
                }
            }
        }
    }
}

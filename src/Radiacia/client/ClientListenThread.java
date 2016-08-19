package Radiacia.client;

import Radiacia.data.Data;
import Radiacia.data.GamerData;
import Radiacia.eventlisteners.DataListener;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Слушает клиента и сохраняет данные
 */
public class ClientListenThread extends Thread {
    private volatile Set<DataListener> listeners;
    private Client client;

    private ClientListenThread() {
        this(null, null);
    }

    public ClientListenThread(Client client) {
        this(client, null);
    }

    public ClientListenThread(Client client, DataListener... listeners) {
        super("ClientListenThread");
        this.client = client;

        this.listeners = new HashSet<>();
        if (listeners != null) {
            for (int i = 0; i < listeners.length; i++) {
                this.listeners.add(listeners[i]);
            }
        }

        start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Data data = client.read();

                if (isInterrupted()) break;
                if (data instanceof GamerData) {
                    System.out.println("Gamer data!!!");
                }

                synchronized (listeners) {
                    Iterator<DataListener> iterator = listeners.iterator();
                    while (iterator.hasNext()) iterator.next().initEvent(data);
                }
            } catch (IOException e) {
                if (!isInterrupted()) {
                    e.printStackTrace();
                    interrupt();
                }
            }
        }
    }

    public synchronized void addListener(DataListener dl) {
        synchronized (listeners) {
            listeners.add(dl);
        }
    }

    public synchronized boolean removeListener(DataListener dl) {
        return listeners.remove(dl);
    }

    public synchronized void setListeners(Set<DataListener> listeners) {
        this.listeners = listeners;
    }

    public Set<DataListener> getListeners() {
        return listeners;
    }
}

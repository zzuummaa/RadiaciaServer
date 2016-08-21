package Radiacia.server.eventlisteners;

import Radiacia.data.ClientData;
import Radiacia.data.Data;
import Radiacia.data.GamerData;
import Radiacia.server.client.Client;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Cntgfy on 20.08.2016.
 *
 * Генератор событий для двух видов данных ClientData и GamerData
 *
 * @see Radiacia.server.eventlisteners.ClientDataListener
 * @see Radiacia.server.eventlisteners.GameDataListener
 */
public class GameClientEventGenerator implements EventGenerator {
    private Set<ClientDataListener> scdl;
    private Set<GameDataListener> sgdl;

    protected Thread clth;
    protected Client client;

    public GameClientEventGenerator(Client client) {
        this.scdl = new HashSet<>();
        this.sgdl = new HashSet<>();

        this.clth = new ClientListenThread();
        this.clth.start();
    }

    public GameClientEventGenerator(Client client, DataListener... dls) {
        this(client);

        for (int i = 0; i < dls.length; i++) {
            addDataListener(dls[i]);
        }
    }

    /**
     * Передает данные нужному множеству слушателей
     */
    private void initEvent(Data data) {
        if (data instanceof ClientData) initEvent(data, scdl); else
        if (data instanceof GamerData)  initEvent(data, sgdl);
    }

    /**
     * Инициализирует событие множества слушателей
     */
    private void initEvent(Data data, Set<? extends DataListener> sdl) {
        Iterator<? extends DataListener> dli = sdl.iterator();
        while (dli.hasNext()) dli.next().initEvent(data);
    }

    @Override
    public void addDataListener(DataListener dl) {
        if (dl instanceof ClientDataListener) scdl.add((ClientDataListener) dl);
        if (dl instanceof GameDataListener  ) sgdl.add((GameDataListener)   dl);
    }

    @Override
    public boolean removeListener(DataListener l) {
        boolean isRemoved = false;

        if (scdl.remove(l)) isRemoved = true;
        if (sgdl.remove(l)) isRemoved = true;

        return isRemoved;
    }

    @Override
    public Collection<DataListener> removeAllListeners() {
        Set<DataListener> sdl = new HashSet<>();

        sdl.addAll(scdl);
        sdl.addAll(sgdl);

        return sdl;
    }

    public Client getClient() {
        return client;
    }

    /**
     * Слушает клиента и передает события в <code>initEvent(Data data)</code>
     * @see #initEvent(Radiacia.data.Data)
     */
    protected class ClientListenThread extends Thread {
        ClientListenThread() {
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Data data = client.read();

                    if (isInterrupted()) break;

                    initEvent(data);
                } catch (IOException e) {
                    if (!isInterrupted()) {
                        e.printStackTrace();
                        interrupt();
                    }
                }
            }
        }
    }
}

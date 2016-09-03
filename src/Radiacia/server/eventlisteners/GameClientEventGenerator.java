package Radiacia.server.eventlisteners;

import Radiacia.base.DataListenerInterface;
import Radiacia.base.EventGeneratorInterface;
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
 * @see ClientDataListener
 * @see GameDataListener
 */
public class GameClientEventGenerator implements EventGeneratorInterface {
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

    public GameClientEventGenerator(Client client, DataListenerInterface... dls) {
        this(client);

        for (int i = 0; i < dls.length; i++) {
            addListener(dls[i]);
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
    private void initEvent(Data data, Set<? extends DataListenerInterface> sdl) {
        Iterator<? extends DataListenerInterface> dli = sdl.iterator();
        while (dli.hasNext()) dli.next().initEvent(data);
    }

    @Override
    public void addListener(DataListenerInterface dl) {
        if (dl instanceof ClientDataListener) scdl.add((ClientDataListener) dl);
        if (dl instanceof GameDataListener) sgdl.add((GameDataListener)   dl);
    }

    @Override
    public boolean removeListener(DataListenerInterface l) {
        boolean isRemoved = false;

        if (scdl.remove(l)) isRemoved = true;
        if (sgdl.remove(l)) isRemoved = true;

        return isRemoved;
    }

    @Override
    public void notifyListeners(Data data) {
        initEvent(data);
    }

    public Collection<DataListenerInterface> removeAllListeners() {
        Set<DataListenerInterface> sdl = new HashSet<>();

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

                    notifyListeners(data);
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

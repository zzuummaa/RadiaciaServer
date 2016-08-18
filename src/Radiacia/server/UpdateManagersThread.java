package Radiacia.server;

import Radiacia.client.Client;
import Radiacia.data.Data;
import Radiacia.data.datamanager.AbstractDataManager;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Cntgfy on 17.08.2016.
 *
 * Снимает данные клиентов и передает их в менеджеры
 */
public class UpdateManagersThread extends Thread {
    private Collection<AbstractDataManager> dmc;

    private ClientManager clientManager;

    protected UpdateManagersThread() {
        super("UpdateManagerThread");
    }

    public UpdateManagersThread(AbstractDataManager... dmc) {
        this();
        this.dmc = new LinkedList<>();

        for (int i = 0; i < dmc.length; i++) {
            this.dmc.add(dmc[i]);
        }
    }

    public UpdateManagersThread(boolean startNow, AbstractDataManager... dmc) {
        this(dmc);
        if (startNow) start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            parseData(clientManager);
        }
    }

    /**
     * Отправляет данные клиентов менеджерам
     *
     * @param clientManager содержит клиентов, данные которых будут сняты
     */
    private void parseData(ClientManager clientManager) {
        if (clientManager == null) return;

        synchronized (clientManager) {
            Iterator<Client> iterator = clientManager.getClients().values().iterator();
            while (iterator.hasNext()) parseFromClients(clientManager.getData(iterator.next()));

            parseSelf();
        }
    }

    /**
     * Передает коллекцию клиента менеджерам
     *
     * @param data коллекция клиента
     */
    private void parseFromClients(Collection<Data> data) {
        Iterator<AbstractDataManager> dmIterator = dmc.iterator();

        while (dmIterator.hasNext()) {
            dmIterator.next().parseFromClient(data);
        }
    }

    /**
     * Обрабатывает данные внутри менеджеров, сбрасывая входные данные
     */
    private void parseSelf() {
        Iterator<AbstractDataManager> dmIterator = dmc.iterator();
        while (dmIterator.hasNext()) {
            AbstractDataManager dm = dmIterator.next();

            dm.parseSelf();
            dm.reset();
        }
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }
}

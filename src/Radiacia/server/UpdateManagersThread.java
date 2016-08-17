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
    private Collection<AbstractDataManager> dms;

    private ClientManager clientManager;

    public UpdateManagersThread(AbstractDataManager... dms) {
        this.dms = new LinkedList<>();

        for (int i = 0; i < dms.length; i++) {
            this.dms.add(dms[i]);
        }
    }

    public UpdateManagersThread(boolean startNow, AbstractDataManager... dms) {
        this(dms);
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
        synchronized (clientManager) {
            if (clientManager == null) return;

            Iterator<Client> clientIterator = clientManager.getClients().values().iterator();

            while (clientIterator.hasNext()) {
                parseFromClients(clientManager, clientIterator.next());
            }
        }
    }

    private void parseFromClients(ClientManager cm, Client client) {
        Iterator<AbstractDataManager> dmIterator = dms.iterator();

        while (dmIterator.hasNext()) {
            Collection<Data> data = cm.getData(client);
            dmIterator.next().parseFromClient(data);
        }
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }
}

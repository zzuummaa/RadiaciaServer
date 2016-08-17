package Radiacia.server;

import Radiacia.client.Client;
import Radiacia.client.ClientConnectThread;
import Radiacia.client.ClientListenThread;
import Radiacia.data.ConnectData;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cntgfy on 17.08.2016.
 */
public class ClientManager {
    private ServerListenThread serverListenThread;
    private volatile Map<Long, Client> clients;
    private volatile Map<Client, Long> ids;
    private volatile Map<Client, ClientListenThread> clientListenThreads;

    private IDManager idManager;

    public ClientManager(ServerListenThread serverListenThread) {
        this.serverListenThread = serverListenThread;
        this.clients = new HashMap<>();
        this.ids = new HashMap<>();

        this.clientListenThreads = new HashMap<>();
        this.idManager = new IDManager();
    }

    public void connect(Client client) {
        new ClientConnectThread(client, this, idManager.incID()).start();
    }

    public synchronized void put(ClientConnectThread clientConnectThread) throws IOException {
        if (clientConnectThread == null) return;

        if (clientConnectThread.isSuccess()) {
            put(clientConnectThread.getConnectData());
        }
    }

    public synchronized void put(ConnectData cd) throws IOException {
        if (clients.containsKey(cd.getId())) disconnect(cd.getId());

        synchronized (cd) {
            clients.put(cd.getId(), cd.getOwner());
            ids.put(cd.getOwner(), cd.getId());
            clientListenThreads.put(cd.getOwner(), new ClientListenThread(cd.getOwner()));
        }
    }

    public Client getClient(Long id) {
        return clients.get(id);
    }

    public Collection getData(Client client) {
        return clientListenThreads.get(client).getData();
    }

    public Map<Long, Client> getClients() {
        return clients;
    }

    public synchronized void disconnect(Long id) throws IOException {
        if (!clients.containsKey(id)) return;

        Client client = clients.get(id);

        ids.remove(client);
        clients.remove(id).disconnect();
        clientListenThreads.remove(client).interrupt();
    }
}

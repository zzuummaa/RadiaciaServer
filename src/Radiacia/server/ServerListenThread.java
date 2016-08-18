package Radiacia.server;

import Radiacia.client.Client;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Слушает подключения клиентов
 */
public class ServerListenThread extends Thread {
    private Server server;
    private ClientManager clientManager;



    public ServerListenThread(Server server) {
        this.server = server;
        this.clientManager = new ClientManager(this);
    }

    public ServerListenThread(Server server, boolean startNow) {
        this(server);
        if (startNow) start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Client client = server.accept();
                clientManager.connect(client);
            } catch (IOException e) {
                if (!isInterrupted()) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        try {
            clientManager.disconnectAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientManager getClientManager() {
        return clientManager;
    }

    public Map<Long, Client> getClients() {
        return clientManager.getClients();
    }
}

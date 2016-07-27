package Radiacia.Old.server;

import Radiacia.Client;

import java.io.IOException;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * Присоединяет клиента к серверу
 * @see Radiacia.Old.server.ServerOfClientsI
 * @see Radiacia.Client
 *
 * Осуществляет потокобезопасное подключение клиента
 *
 * version 0.6
 */
public class ConnectClient extends Thread {
    private ServerOfClientsI serverOfClients;
    private Client client;

    /**
     * @param serverOfClients
     * @param client
     */
    public ConnectClient(ServerOfClientsI serverOfClients, Client client) {
        this.serverOfClients = serverOfClients;
        this.client = client;
        start();
    }

    @Override
    public void run() {
        synchronized (serverOfClients) {
            try {
                serverOfClients.connect(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

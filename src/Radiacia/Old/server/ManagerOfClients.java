package Radiacia.Old.server;

import Radiacia.Client;
import Radiacia.Old.IDManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * Безопасен при работе в многопоточной среде
 *
 * version 0.4
 */
public class ManagerOfClients implements ServerOfClientsI {
    protected Map<Integer, Client> clients;
    protected Map<Client, Integer> ids;

    private IDManager idManager;

    public ManagerOfClients() {
        synchronized (this) {
            clients = new HashMap<>();
            ids = new HashMap<>();
            idManager = new IDManager();
        }
    }

    /**
     * @param client которого хотим подключить
     * @throws IOException если
     */
    @Override
    public synchronized void connect(Client client) throws IOException, NullPointerException {
        if (client == null) throw new NullPointerException("null client.");

        client.connect();

        int id = idManager.getID();
        clients.put(id, client);
        ids.put(client, id);
    }

    /**
     * @param client которого хотим отключить, не null
     * @throws IOException если при отключении клиента возникло I/O исключение
     * @throws NoSuchElementException когда client не был подключен к серверу
     */
    @Override
    public synchronized void disconnect(Client client) throws IOException, NoSuchElementException {
        Integer id = ids.get(client);

        if (id == null) throw new NoSuchElementException("client was not connected to server.");

        disconnect(id, client);
    }

    /**
     * @param id клиента
     * @throws IOException если при отключении клиента возникло I/O исключение
     * @throws NoSuchElementException когда client не был подключен к серверу
     */
    public synchronized void disconnect(int id) throws IOException, NoSuchElementException {
        Client client = clients.get(id);

        if (client == null) throw new NoSuchElementException("client with id=" + id + " was not connected to server.");

        disconnect(id, client);
    }

    /**
     * Отключает клиента
     */
    protected synchronized void disconnect(Integer id, Client client) throws IOException{
        try {
            client.disconnect();
        } catch (IOException e) {
            IOException exception = new IOException("can't disconnect client with id " + id);
            exception.addSuppressed(e);
            throw exception;
        }

        ids.remove(client);
        clients.remove(id);
    }

    private volatile boolean isWorking;

    public boolean isWorking() {
        return isWorking;
    }

    /**
     * @throws IOException если пытаемся повторно запустить уже запущенные сервер
     */
    @Override
    public void start() throws IOException {
        if (isWorking) throw new IOException("server already working.");
        isWorking = true;
    }

    /**
     * @throws IOException если не удается откключить всех клиентов
     */
    @Override
    public synchronized void stop() throws IOException {
        boolean exceptionFlag = false;

        for (Client client: clients.values()) {
            try {
                client.disconnect();
            } catch (IOException e) {
                exceptionFlag = true;
            }
        }

        if (exceptionFlag == false) isWorking = false;
        else                        throw new IOException("server can't stop because some clients can't disconnected");
    }
}

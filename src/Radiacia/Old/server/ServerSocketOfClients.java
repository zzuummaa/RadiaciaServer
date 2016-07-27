package Radiacia.Old.server;

import Radiacia.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.NoSuchElementException;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * Реализует логику сервера посредством сокет соединения
 *
 * version 0.7
 */
public class ServerSocketOfClients implements ServerOfClientsI {
    private ManagerOfClients managerOfClients;
    private ConnectClientSocketThread connectClientThread;

    private final int port;
    private ServerSocket serverSocket;

    public ServerSocketOfClients(int port) throws IOException {
        this.port = port;
        this.managerOfClients = new ManagerOfClients();
    }

    public ServerSocketOfClients(ManagerOfClients managerOfClients, int port) throws IOException {
        this.port = port;
        this.managerOfClients = managerOfClients;
    }

    @Override
    public void connect(Client client) throws IOException, NoSuchElementException {
        managerOfClients.connect(client);
    }

    @Override
    public void disconnect(Client client) throws IOException, NoSuchElementException {
        managerOfClients.disconnect(client);
    }

    @Override
    public void start() throws IOException {
        managerOfClients.start();
        serverSocket = new ServerSocket(port);

        connectClientThread = new ConnectClientSocketThread(serverSocket, managerOfClients);
        connectClientThread.start();
    }

    /**
     * Останавливает сервер
     *
     * !!!Очередность действий:
     * @see #managerOfClients     1. Останавливает нить подключения клиентов
     * @see #connectClientThread 2. Останавливает сервер клиентов
     * @see #serverSocket        3. Останавливает сокет сервер
     *
     * @throws IOException
     */
    @Override
    public void stop() throws IOException {
        connectClientThread.interrupt();
        connectClientThread = null;

        managerOfClients.stop();
        //Не понятно, успеет ли сервер всех отключить всех клиентов перед закрытием serverSocket

        serverSocket.close();
        serverSocket = null;
    }
}

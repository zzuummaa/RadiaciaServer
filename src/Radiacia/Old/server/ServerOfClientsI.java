package Radiacia.Old.server;

import Radiacia.Client;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by Cntgfy on 23.07.2016.
 *
 * version 0.4
 */
public interface ServerOfClientsI extends Server {

    /**
     * @param client которого хотим подключить
     * @throws IOException если
     */
    public void connect(Client client) throws IOException, NoSuchElementException;

    /**
     * @param client которого хотим отключить, не null
     * @throws IOException если при отключении клиента возникло I/O исключение
     * @throws NoSuchElementException когда client не был подключен к серверу
     */
    public void disconnect(Client client) throws IOException, NoSuchElementException;
}

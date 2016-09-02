package Radiacia.base;

import Radiacia.server.client.Client;

import java.io.IOException;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Реализует слушание новых подключений
 */
public interface Acceptable<A extends Client> {
    /**
     * Подключает клиента. При вызове метод должен блокировать поток
     * до подключения.
     *
     * @return принятое подключение клиента
     */
    public A accept() throws IOException;

    /**
     * Закрывает сервер
     */
    public void close() throws IOException;
}

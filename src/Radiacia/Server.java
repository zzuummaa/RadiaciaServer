package Radiacia;

import java.io.IOException;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Реализует логику работы сервера.
 *
 * 1. Принять подключения.
 * 2. Состояние вкл/выкл сервера
 */
public interface Server<A extends Client> {
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

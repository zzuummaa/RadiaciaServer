package Radiacia;

import Radiacia.data.Data;

import java.io.IOException;

/**
 * Created by Cntgfy on 04.07.2016.
 *
 * Реализует сообщение двух объектов для записи и чтения объектов реализующих интерфейс Data
 *
 * version 0.5
 */
public interface Client<A extends Data> {
    /**
     * Передает данные связанному объекту
     *
     * @param data
     * @throws IOException если возникла ошибка при записи данных
     */
    public void write(A data) throws IOException;

    /**
     * Читает данные из потока ввода
     *
     * @return прочитанный объкет.
     * @throws IOException если считать данные не удалось
     */
    public A read() throws IOException;

    /**
     * @throws IOException если подключение не удалось
     */
    public void connect() throws IOException;

    /**
     * @throws IOException если возникла I/O ошибка
     */
    public void disconnect() throws IOException;

    /**
     * @return true - если клиент подключен
     *         false- елси клиент не подключен
     */
    public boolean isConnected();
}

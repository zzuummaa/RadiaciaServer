package Radiacia.handler;

import Radiacia.data.Data;

import java.util.Collection;

/**
 * Created by Cntgfy on 27.07.2016.
 *
 * Реализует обработку данных
 */
public interface Handler<A extends Data> {
    /**
     * добавляет данные для будущей обработки
     */
    public void add(A a);

    /**
     * Обрабатывает вохдную коллекцию данных, составляя выходную
     */
    public void handle(Collection<A> data) throws Exception;

    /**
     * Обрабатывает входящие данные
     */
    public void handle() throws Exception;
}

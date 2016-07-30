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
     *
     * Если вы для вызова метода используете <code>HandlerThread</code>
     * со стандартной реализацией <code>run()</code>, то помните,
     * что <code>HandlerThread</code></code> не обрабатывает исклчюения,
     * а только выводит сообщения о них
     * @see Radiacia.handler.Handler
     */
    public void handle() throws Exception;

    /**
     * @return есть ли необработаные данные
     *         true - есть
     *         false - нету
     */
    public boolean containsNotHandle();
}

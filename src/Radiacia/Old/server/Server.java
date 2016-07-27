package Radiacia.Old.server;

import java.io.IOException;

/**
 * Created by Cntgfy on 23.07.2016.
 */
public interface Server {
    /**
     * Запускает сервер
     *
     * @throws IOException елси сервер не может быть запущен
     */
    public void start() throws IOException;

    /**
     * @throws IOException если возникла I/O ошибка при остановке
     */
    public void stop() throws IOException;
}

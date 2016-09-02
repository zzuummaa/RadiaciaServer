package Radiacia.base;

import Radiacia.server.client.GameClient;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Cntgfy on 02.09.2016.
 *
 * Отвечает за подключение клиентов
 */
public interface AccountServiceInterface {
    /**
     * Проверяет, подключен ли клиент с данным id
     */
    public boolean contains(Long id);

    /**
     * @return карту пар id-client
     */
    public Map<Long, GameClient> getClients();

    /**
     * Подключает клиента
     *
     * @param gc
     * @throws IOException
     */
    public void connect(GameClient gc) throws IOException;
}

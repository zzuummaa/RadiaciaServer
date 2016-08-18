package Radiacia.server;

import Radiacia.data.ConnectData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cntgfy on 18.08.2016.
 *
 * Администрирует подключения
 */
public class AccountService {
    private IDManager idManager;
    private Map<Long, GameClient> clients;

    public AccountService() {
        this(new IDManager());
    }

    public AccountService(IDManager idManager) {
        this.idManager = idManager;
        this.clients = new HashMap<>();
    }

    /**
     * Пытается подключить клиента.
     *
     * Ситуации с id клиента:
     * id  = 0 - его подключат, выдав новый id
     * id != 0 - 1. id уже встречался на серве -> подключат на этот id
     *           2. id не встречался на серве  -> не подключат
     *
     * @param gc
     * @return
     */
    public synchronized void connect(GameClient gc) throws IOException {
        ConnectData cd = getServiceConnectData(gc);

        if (cd != null) connect(gc, cd);
    }

    /**
     * Обрабатывает id и выставляет id, с которым будет подключен к серву
     *
     * @return null, если id не нравится серву
     */
    private ConnectData getServiceConnectData(GameClient gc) {
        ConnectData cd = gc.getConD();

        if (!contains(cd.getId()) && cd.getId() == 0) {
            cd.setId(idManager.getNextID());
        } else {
            if (contains(cd.getId())) {
                clients.remove(cd.getId());
            } else {
                return null;
            }
        }

        return cd;
    }

    private void connect(GameClient gc, ConnectData cd) {
        gc.setConD(cd);
        clients.put(cd.getId(), gc);
    }

    public boolean contains(Long id) {
        return clients.containsKey(id);
    }

    public Map<Long, GameClient> getClients() {
        return clients;
    }
}

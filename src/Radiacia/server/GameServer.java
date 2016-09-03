package Radiacia.server;

import Radiacia.base.EventGeneratorInterface;
import Radiacia.base.ServerInterface;
import Radiacia.server.client.GameClient;
import Radiacia.server.services.AccountService;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Отвечает за связь логики игры и связи через сеть
 */
public class GameServer implements ServerInterface {
    private SocketAcceptable socketAcceptable;
    private GameServerListenThread slth;
    private AccountService as;

    public GameServer() throws IOException {
        this.socketAcceptable = new SocketAcceptable(new ServerSocket(9090));
        this.slth = new GameServerListenThread(socketAcceptable, true);
        this.as = (AccountService) slth.getAccountService();
    }

    public Map<Long, GameClient> getClients() {
        return new HashMap<>(as.getClients());
    }

    public GameClient getClient(Long id) {
        return as.getClients().get(id);
    }

    @Override
    public EventGeneratorInterface getEventGenerator() {
        return null;
    }

    @Override
    public boolean close() {
        try {
            closeClients();

            this.slth.interrupt();
            this.socketAcceptable.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeClients() throws IOException {
        Iterator<GameClient> iterator = as.getClients().values().iterator();

        while (iterator.hasNext()) {
            iterator.next().close();
        }
    }

    public AccountService getAccountService() {
        return slth.getAccountService();
    }
}

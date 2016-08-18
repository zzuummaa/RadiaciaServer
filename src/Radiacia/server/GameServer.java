package Radiacia.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Отвечает за связь логики игры и связи через сеть
 */
public class GameServer {
    private SocketServer socketServer;
    private GameServerListenThread slth;
    private AccountService as;

    public GameServer() throws IOException {
        this.socketServer = new SocketServer(new ServerSocket(9090));
        this.slth = new GameServerListenThread(socketServer, true);
        this.as = slth.getAccountService();
    }

    public Map<Long, GameClient> getClients() {
        return new HashMap<>(as.getClients());
    }

    public GameClient getClient(Long id) {
        return as.getClients().get(id);
    }

    public void close() throws IOException {
        closeClients();

        this.slth.interrupt();
        this.socketServer.close();
    }

    public GameServerListenThread getSlth() {
        return slth;
    }

    public void closeClients() throws IOException {
        Iterator<GameClient> iterator = as.getClients().values().iterator();

        while (iterator.hasNext()) {
            iterator.next().close();
        }
    }
}

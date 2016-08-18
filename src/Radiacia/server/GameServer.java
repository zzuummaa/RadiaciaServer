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
    private Collection<GameClient> gameClients;
    private GameServerListenThread slth;

    public GameServer() throws IOException {
        this.socketServer = new SocketServer(new ServerSocket(9090));
        this.slth = new GameServerListenThread(socketServer, gameClients, true);
        this.gameClients = new LinkedList<>();
    }

    public Map<Long, GameClient> getClients() {
        Iterator<GameClient> iterator = slth.getClients().iterator();

        Map<Long, GameClient> gameClients = new HashMap<>();
        while (iterator.hasNext()) {
            GameClient gc = iterator.next();
            gameClients.put(gc.getId(), gc);
        }

        return gameClients;
    }

    public GameClient getClient(Long id) {
        Iterator<GameClient> iterator = slth.getClients().iterator();

        while (iterator.hasNext()) {
            GameClient gc = iterator.next();
            if ( id.equals(gc.getId()) ) return gc;
        }

        return null;
    }

    public void close() throws IOException {
        this.slth.interrupt();
        this.socketServer.close();

        closeClients();
    }

    public void closeClients() throws IOException {
        Iterator<GameClient> iterator = slth.getClients().iterator();

        while (iterator.hasNext()) {
            iterator.next().close();
        }
    }
}

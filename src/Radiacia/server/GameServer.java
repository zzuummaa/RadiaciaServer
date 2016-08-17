package Radiacia.server;

import Radiacia.client.Client;
import Radiacia.data.datamanager.ClientDataManager;
import Radiacia.data.datamanager.GameDataManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;

/**
 * Created by Cntgfy on 16.08.2016.
 *
 * Отвечает за связь логики игры и связи через сеть
 */
public class GameServer {
    private SocketServer socketServer;

    private ClientDataManager clientDataManager;
    private GameDataManager gameDataManager;

    private ServerListenThread serverListenThread;

    public GameServer() throws IOException {
        this.socketServer = new SocketServer(new ServerSocket(9090));
        this.clientDataManager = new ClientDataManager();
        this.gameDataManager = new GameDataManager();
        this.serverListenThread = new ServerListenThread(socketServer, true);
    }

    public Map<Long, Client> getClients() {
        return serverListenThread.getClients();
    }

    public Client getClient(Long id) {
        return serverListenThread.getClients().get(id);
    }

    public void close() throws IOException {
        this.serverListenThread.interrupt();
        this.socketServer.close();
    }
}

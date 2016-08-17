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

    private ClientDataManager cdm;
    private GameDataManager gdm;
    private UpdateManagersThread upmth;

    private ServerListenThread slth;

    public GameServer() throws IOException {
        this.socketServer = new SocketServer(new ServerSocket(9090));

        this.cdm = new ClientDataManager();
        this.gdm = new GameDataManager();
        this.upmth = new UpdateManagersThread(true, cdm, gdm);

        this.slth = new ServerListenThread(socketServer, true);
    }

    public Map<Long, Client> getClients() {
        return slth.getClients();
    }

    public Client getClient(Long id) {
        return slth.getClients().get(id);
    }

    public void close() throws IOException {
        this.slth.interrupt();
        this.socketServer.close();
    }
}

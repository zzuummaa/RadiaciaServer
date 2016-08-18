package Radiacia.server;

import Radiacia.eventlisteners.GameConnectListener;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public class GameServerListenThread extends Thread {
    private Server server;
    private Collection<GameClient> gameClients;
    private IDManager idManager;

    public GameServerListenThread(Server server, Collection<GameClient> gameClients) {
        this(server, gameClients, false);
    }

    public GameServerListenThread(Server server, Collection<GameClient> gameClients, boolean startNow) {
        this.server = server;
        this.gameClients = gameClients;
        this.idManager = new IDManager();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                GameClient gameClient = new GameClient(server.accept());

                if (isInterrupted()) return;

                gameClient.addListener(new GameConnectListener(gameClient, idManager));
                gameClients.add(gameClient);
            } catch (IOException e) {
                if (!isInterrupted()) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    public Collection<GameClient> getClients() {
        return gameClients;
    }
}

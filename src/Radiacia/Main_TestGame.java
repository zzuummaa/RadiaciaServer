package Radiacia;

import Radiacia.Game.GameMachine;
import Radiacia.client.Client;
import Radiacia.client.SocketClient;
import Radiacia.server.GameClient;
import Radiacia.server.GameServer;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public class Main_TestGame {
    public static void main(String[] args) throws IOException, InterruptedException {
        GameServer gameServer = new GameServer();

        GameMachine gameMachine = new GameMachine(1);
        gameMachine.setGamers( new ClientsGamers(gameServer.getSlth()) );

        GameClient gc = connect(new Socket("localHost", 9090));
        Thread.sleep(20);

        printInfo(gameServer);
    }

    public static GameClient connect(Socket socket) throws IOException {
        Client client = new SocketClient(socket);
        GameClient gc = new GameClient(client);
        gc.connect();

        return gc;
    }

    public static void printInfo(GameServer gs) {
        printClientMap(gs.getClients());
    }

    public static void printClientMap(Map<Long, GameClient> clients) {
        Set<Long> ids = clients.keySet();

        for (Long id: ids) {
            System.out.println("id " + id + ":" + clients.get(id));
        }
    }
}

package Radiacia;

import Radiacia.Game.GameMachine;
import Radiacia.Game.Gamer;
import Radiacia.client.Client;
import Radiacia.client.SocketClient;
import Radiacia.data.GamerData;
import Radiacia.server.GameClient;
import Radiacia.server.GameServer;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public class Main_TestGame extends Thread {
    private static GameServer gameServer;

    public static void main(String[] args) throws IOException, InterruptedException {
        gameServer = new GameServer();

        GameMachine gameMachine = new GameMachine(1);
        gameMachine.setGamers( new ClientsGamers(gameServer.getSlth().getAccountService()) );
        gameMachine.start();

        GameClient gc1 = connect();
        GameClient gc2 = connect();
        Thread.sleep(20);

        sendShootGamer(gc1);

        Thread.sleep(600);

        printInfo(gameServer);

        while (!interrupted());

        gc1.close();
        gc2.close();
        gameServer.close();
    }

    public static void sendShootGamer(GameClient gameClient) throws IOException {
        Gamer gamer = new Gamer();
        gamer.setIsShoot(true);
        gameClient.getClient().write(new GamerData(gamer));
    }

    public static GameClient connect() throws IOException {
        Client client = new SocketClient(new Socket("localHost", 9090));
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

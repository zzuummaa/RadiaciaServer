package Radiacia;

import Radiacia.game.GameMachine;
import Radiacia.game.Gamer;
import Radiacia.server.GameServer;
import Radiacia.server.client.*;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cntgfy on 18.08.2016.
 *
 * Тестирует взаимодействие GameServer и GameMachine
 * @see Radiacia.server.GameServer
 * @see Radiacia.game.GameMachine
 */
public class Main_Test extends Thread {
    private static GameServer gameServer;
    private static GameMachine gameMachine;
    private static ClientsGamers gamers;
    private static boolean isInterrupted = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        init();

        GameClient gc1 = connect();
        GameClient gc2 = connect();
        Thread.sleep(30);

        sendShootGamer(gc1);

        Thread.sleep(600);

        printInfo(gameServer);

        while (!isInterrupted) yield();

        gc1.close();
        gc2.close();
        gameServer.close();
    }

    public static void sendShootGamer(GameClient gameClient) throws IOException {
        Gamer gamer = new ClientGamer(gameClient);
        gamer.setIsShoot(true);
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

    public static void init() throws IOException {
        gameServer = new GameServer();

        gameMachine = new GameMachine(1);
        gamers = new ClientsGamers(gameServer.getSlth().getAccountService());
        gameMachine.setGamers(gamers);
        gameMachine.start();
    }

    public static GameServer getGameServer() {
        return gameServer;
    }

    public static GameMachine getGameMachine() {
        return gameMachine;
    }

    public static ClientsGamers getGamers() {
        return gamers;
    }

    public void interrupt() {
        isInterrupted = true;
    }
}

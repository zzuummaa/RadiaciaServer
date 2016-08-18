package Radiacia.server;

import Radiacia.Game.Gamer;
import Radiacia.client.Client;
import Radiacia.client.SocketClient;
import Radiacia.data.GamerData;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Cntgfy on 18.08.2016.
 */
public class Main_TestGameServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        GameServer gameServer = new GameServer();

        Client client = new SocketClient(new Socket("localhost", 9090));
        Main_TestClientManager.connectClient(client);
        Thread.sleep(30);

        Gamer gamer = new Gamer();
        gamer.setIsShoot(true);
        client.write(new GamerData(gamer));
    }
}

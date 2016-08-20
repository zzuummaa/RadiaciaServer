package Radiacia;

import Radiacia.data.GamerData;
import Radiacia.game.Gamer;
import Radiacia.server.client.GameClient;
import Radiacia.server.client.SocketClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Cntgfy on 20.08.2016.
 */
public class Main_TestGameClient {
    private static GameClient gc;
    private static Gamer gamer;
    private static boolean isClosed = true;

    public static void main(String[] args) throws IOException {
        gamer = new Gamer();
        connect(0);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = reader.readLine();

            if (str.equals("reconnect")) {
                reconnect();
                continue;
            }

            if (str.equals("close")) {
                gc.close();
                continue;
            }

            if (str.equals("write")) {
                gc.getClient().write(new GamerData(gamer));
                continue;
            }

            String[] pars = str.split("=");

            if (pars[0].equals("isShoot")) {
                gamer.setIsShoot(Boolean.valueOf(pars[1]));
            }
        }
    }

    public static void reconnect() throws IOException {
        if (!isClosed) gc.close();
        connect(gc.getId());
    }

    public static void connect(long id) throws IOException {
        gc = new GameClient(new SocketClient(new Socket("localHost", 9090)), id);
        isClosed = false;
        gc.connect();
    }
}

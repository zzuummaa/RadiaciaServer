package Radiacia;

import Radiacia.game.Gamer;
import Radiacia.server.client.ClientGamer;
import Radiacia.server.client.GameClient;
import Radiacia.server.client.SocketClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Cntgfy on 20.08.2016.
 *
 * Временно сломано переподключение во имя науки :(
 */
public class Main_TestGameClient {
    private static GameClient gc;
    private static ClientGamer gamer;
    private static boolean isClosed = true;

    public static void main(String[] args) throws IOException {
        connect(0);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = reader.readLine();

            if (str.equals("reconnect")) {
                reconnect();
                continue;
            }

            if (str.equals("close")) {
                close();
                continue;
            }

            if (str.equals("exit")) {
                close();
                return;
            }

            if (str.equals("write")) {
                gamer.writeSelf();
                continue;
            }

            String[] pars = str.split("=");
            if (pars.length == 2) {
                setParam(gamer, pars);
            }

            System.out.println(gamer);
        }
    }

    public static void reconnect() throws IOException {
        close();
        connect(gc.getId());
    }

    public static void connect(long id) throws IOException {
        gc = new GameClient(new SocketClient(new Socket("localHost", 9090)), id);
        gc.connect();

        gamer = new ClientGamer(gc);

        isClosed = false;
    }

    public static void close() throws IOException {
        if (!isClosed) {
            gc.close();
            gamer.close();

            isClosed = true;
        }
    }

    public static void setParam(Gamer gamer, String[] value) {
        if (value.length == 2) {
            String field = value[0];
            String param = value[1];

            if (field.equals("isShoot")) gamer.setIsShoot(Boolean.valueOf(param));
            if (field.equals("latitude")) gamer.setLatitude(Double.valueOf(param));
            if (field.equals("longitude")) gamer.setLongitude(Double.valueOf(param));
            if (field.equals("isAlive")) gamer.setALive(Boolean.valueOf(param));
        }
    }
}

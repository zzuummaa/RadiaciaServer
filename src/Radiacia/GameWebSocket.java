package Radiacia;

import Radiacia.base.GamerContainerInterface;
import Radiacia.base.GamerInterface;
import Radiacia.base.GamerParserInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

/**
 * Created by Fomenko_S.V. on 19.10.2016.
 */
@WebSocket
public class GameWebSocket {
    private Logger logger = LogManager.getLogger(GameWebSocket.class);

    private Session session;
    private String name;
    private String logPrefix;
    private GamerInterface gamer;

    private GamerContainerInterface gamerContainer;
    private GamerParserInterface gamerParser;

    public GameWebSocket(String name, GamerParserInterface gamerParser, GamerContainerInterface gamerContainer) {
        this.name = name;
        this.logPrefix = name + "'s socket";
        this.gamerParser = gamerParser;
        this.gamerContainer = gamerContainer;

        this.gamer = new Gamer();
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        this.session = session;
        logger.info(logPrefix + " onOpen");
        gamerContainer.add(gamer);
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        logger.info(logPrefix + " onMessage: " + data);

        try {
            //Если геймер в данный момент стреляет, значит он еще не был обработан
            if (gamer.isShoot() || !gamer.isAlive()) {
                session.getRemote().sendString(gamerParser.toData(gamer));
            } else {
                gamerParser.fillGamer(gamer, data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        logger.info(logPrefix + " onClose");

        gamerContainer.remove(gamer);
    }
}

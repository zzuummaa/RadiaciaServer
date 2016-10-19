package Radiacia;

import Radiacia.base.AuthServiceInterface;
import Radiacia.base.GamerContainerInterface;
import Radiacia.base.GamerParserInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

/**
 * Created by Cntgfy on 19.10.2016.
 */
public class GameWebSocketCreator implements WebSocketCreator {
    private static final Logger logger = LogManager.getLogger(GameWebSocketCreator.class);
    private AuthServiceInterface authService;
    private GamerContainerInterface gamerContainer;
    private GamerParserInterface gamerParser;

    public GameWebSocketCreator(AuthServiceInterface authService, GamerParserInterface gamerParser, GamerContainerInterface gamerContainer) {
        this.authService = authService;
        this.gamerContainer = gamerContainer;
        this.gamerParser = gamerParser;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        String userName = authService.getUserName(req.getSession().getId());

        logger.info("socket created for " + userName);
        return new GameWebSocket(userName, gamerParser, gamerContainer);
    }
}

package Radiacia;

import Radiacia.base.AuthServiceInterface;
import Radiacia.base.GamerContainerInterface;
import Radiacia.base.GamerParserInterface;
import Radiacia.utils.PageGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Cntgfy on 19.10.2016.
 */
@WebServlet(name = "GameWebSocketServlet", urlPatterns = {"/game"} )
public class GameWebSocketServlet extends WebSocketServlet {
    private static final Logger logger = LogManager.getLogger(GameWebSocketServlet.class);
    private final static int timeout = 60 * 1000;

    private AuthServiceInterface authService;
    private GamerContainerInterface gamerContainer;
    private GamerParserInterface gamerParser;

    public GameWebSocketServlet(AuthServiceInterface authService, GamerParserInterface gamerParser, GamerContainerInterface gamerContainer) {
        this.authService = authService;
        this.gamerContainer = gamerContainer;
        this.gamerParser = gamerParser;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(timeout);
        factory.setCreator(new GameWebSocketCreator(authService, gamerParser, gamerContainer));
        logger.info("Socket servlet configured");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getSession().getId();
        if (authService.containsSession(id)) {
            logger.info("user " + authService.getUserName(id) + " request socket connection");
            resp.getWriter().println(PageGenerator.getPage("game.html", new HashMap<String, Object>()));
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            logger.info("unknown session");
            resp.setStatus(HttpServletResponse.SC_SEE_OTHER);
        }
    }
}

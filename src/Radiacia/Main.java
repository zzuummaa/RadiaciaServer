package Radiacia;

import Radiacia.base.*;
import Radiacia.utils.DirectionParser;
import Radiacia.utils.GamerParser;
import Radiacia.utils.PositionParser;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Fomenko_S.V. on 18.10.2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        AuthServiceInterface authService = new AuthService();
        GamerContainerInterface gamerContainer = new GamerContainer();
        final GameMechanicsInterface gameMechanics = new GameMechanics(gamerContainer);
        GamerParserInterface gamerParser = new GamerParser(new DirectionParser(), new PositionParser());

        context.addServlet(new ServletHolder(new LoginServlet(authService)), "/login");
        context.addServlet(new ServletHolder(new GameWebSocketServlet(authService, gamerParser, gamerContainer)), "/game");

        server.setHandler(context);

        Timer timer = new Timer("GameMechanics timer");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameMechanics.update();
            }
        }, 1000);

        server.start();
        server.join();
    }
}

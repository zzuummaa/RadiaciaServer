package Radiacia;

import Radiacia.base.AuthServiceInterface;
import Radiacia.utils.PageGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cntgfy on 18.10.2016.
 */
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);
    private AuthServiceInterface authService;

    public LoginServlet(AuthServiceInterface authService) {
        this.authService = authService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.getPage("/authform.html", new HashMap<String, Object>()));
        resp.setContentType("text/html;charset=utf-8");

        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String name = req.getParameter("name");
        String safeName = name == null ? "NoName" : name;

        authService.saveUserName(req.getSession().getId(), safeName);
        logger.info("doGet name=" + safeName);

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}

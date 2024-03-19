package org.example.Servlets.Actor;

import org.example.Constants.WebConstants;
import org.example.Dao.ActorDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { WebConstants.prefix + "/redactActor/*"})
public class RedactActorServlet extends HttpServlet {
    private ActorDao actorDao = new ActorDao();
    @Override
    public void init() throws ServletException {
        super.init();
        log("init");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/static/Actors/RedactActor.jsp");
        int id = Integer.parseInt(WebConstants.getUriId(req.getRequestURI()));
        req.setAttribute("actor", actorDao.getActor(id));
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

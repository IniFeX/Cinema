package org.example.Servlets.Actor;

import org.example.Constants.WebConstants;
import org.example.Dao.ActorDao;
import org.example.Dao.UserDao;
import org.example.Models.Actor;
import org.example.Models.Director;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

@WebServlet(urlPatterns = { WebConstants.prefix + "/actors"})
public class ActorsServlet extends HttpServlet {
    private ActorDao actorDao = new ActorDao();
    private UserDao userDao = new UserDao();
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/static/Actors/Actors.jsp");
        req.setAttribute("actors", actorDao.getActors());
        int userId = WebConstants.getUserIdFromCookies(req.getCookies());
        req.setAttribute("user", userDao.getUser(userId));
        dispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("fullName");
        int year = Integer.parseInt(req.getParameter("year"));
        int month = Integer.parseInt(req.getParameter("month"));
        int day = Integer.parseInt(req.getParameter("day"));
        Date date = new GregorianCalendar(year, month, day).getTime();
        Actor director = new Actor().toBuilder()
                .dateOfBirth(date)
                .fullName(name)
                .build();
        actorDao.createOrUpdate(director);
        resp.sendRedirect("http://localhost:8080/JspApi_war" +WebConstants.prefix + "/actors" );
    }
    @Override
    public void destroy() {
    }
}

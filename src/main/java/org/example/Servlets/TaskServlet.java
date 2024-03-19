package org.example.Servlets;

import org.example.Constants.WebConstants;
import org.example.Dao.ActorDao;
import org.example.Dao.FilmDao;
import org.example.Models.Actor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { WebConstants.prefix + "/task"})
public class TaskServlet extends HttpServlet {
    private ActorDao actorDao = new ActorDao();
    private FilmDao filmDao = new FilmDao();
    private List<Actor> actorsWithNFilms = null;
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/static/Task.jsp");
        req.setAttribute("actors_directors", actorDao.getActorsAndDirectors());
        req.setAttribute("current_films", filmDao.thisAndLastYearFilms());

        req.setAttribute("actors_n_films", actorsWithNFilms);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         int numOfFilms = Integer.parseInt(req.getParameter("num"));
         actorsWithNFilms = actorDao.getActorsWithNFilms(numOfFilms);
         resp.sendRedirect("http://localhost:8080/JspApi_war" +WebConstants.prefix + "/task");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int years = Integer.parseInt(req.getParameter("years"));
        filmDao.deleteFilmsWithAge(years);
        resp.sendRedirect("http://localhost:8080/JspApi_war" +WebConstants.prefix + "/task");
    }

    @Override
    public void destroy() {
    }
}

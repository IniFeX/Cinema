package org.example.Servlets.Film;

import org.example.Constants.WebConstants;
import org.example.Dao.ActorDao;
import org.example.Dao.DirectorDao;
import org.example.Dao.FilmDao;
import org.example.Models.Actor;
import org.example.Models.Director;
import org.example.Models.Film;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet(urlPatterns = { WebConstants.prefix + "/createFilm"})
public class CreateFilmServlet extends HttpServlet {
    private FilmDao filmDao = new FilmDao();
    private ActorDao actorDao = new ActorDao();
    private DirectorDao directorDao = new DirectorDao();
    private List<Actor> actors = null;
    private List<Actor> chosenActors = new ArrayList<>();
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
        if (actors == null){
            actors = actorDao.getActors();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/static/Films/CreateFilm.jsp");
        req.setAttribute("directors", directorDao.getDirectors());
        req.setAttribute("chosen_actors", chosenActors);
        req.setAttribute("actors", actors);
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String directorId = req.getParameter("director");
        int year = Integer.parseInt(req.getParameter("year"));
        int month = Integer.parseInt(req.getParameter("month"));
        int day = Integer.parseInt(req.getParameter("day"));
        Date date = new GregorianCalendar(year, month, day).getTime();
        Director director = directorDao.getDirector(Integer.parseInt(directorId));
        System.out.println(req.getAttribute("actors"));
        Film film = new Film().toBuilder()
                .name(name)
                .director(director)
                .actors(chosenActors)
                .issuedAt(date)
                        .build();
        filmDao.create(film);
        actors = null;
        chosenActors = new ArrayList<>();
        resp.sendRedirect("http://localhost:8080/JspApi_war" + WebConstants.prefix +"/films");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("actor");
        chosenActors.add(actors.stream().filter(act -> act.getId() == Integer.parseInt(id)).findAny().get());
        actors = actors.stream().filter(act -> act.getId() != Integer.parseInt(id)).toList();
        resp.sendRedirect("http://localhost:8080/JspApi_war" + WebConstants.prefix +"/createFilm");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

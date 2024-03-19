package org.example.Servlets.Film;

import org.example.Constants.WebConstants;
import org.example.Dao.FilmDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = { WebConstants.prefix + "/last_films"})
public class LastYearFilmsServlet extends HttpServlet {
    private FilmDao filmDao = new FilmDao();
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/static/Films.jsp");
        req.setAttribute("films", filmDao.thisAndLastYearFilms());
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

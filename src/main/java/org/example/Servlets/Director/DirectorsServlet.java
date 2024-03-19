package org.example.Servlets.Director;

import org.example.Constants.WebConstants;
import org.example.Dao.DirectorDao;
import org.example.Dao.UserDao;
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

@WebServlet(urlPatterns = { WebConstants.prefix + "/directors"})
public class DirectorsServlet extends HttpServlet {
    private DirectorDao directorDao = new DirectorDao();
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/static/Directors/Directors.jsp");
        req.setAttribute("directors", directorDao.getDirectors());
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
        System.out.println(year + "-" + month + "-" + day);
        Director director = new Director().toBuilder()
                .dateOfBirth(date)
                .fullName(name)
                .build();
        directorDao.createOrUpdate(director);
        resp.sendRedirect("http://localhost:8080/JspApi_war" +WebConstants.prefix + "/directors" );
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

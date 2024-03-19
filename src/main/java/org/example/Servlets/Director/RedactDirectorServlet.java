package org.example.Servlets.Director;

import org.example.Constants.WebConstants;
import org.example.Dao.DirectorDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {WebConstants.prefix + "/redactDirector/*"})
public class RedactDirectorServlet extends HttpServlet {
    private DirectorDao directorDao = new DirectorDao();
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/static/Directors/RedactDirector.jsp");
        int id = Integer.parseInt(WebConstants.getUriId(req.getRequestURI()));
        req.setAttribute("director", directorDao.getDirector(id));
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

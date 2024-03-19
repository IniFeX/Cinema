package org.example.Servlets.User;

import org.example.Constants.WebConstants;
import org.example.Dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet(urlPatterns = {WebConstants.prefix +"/redactUser"})
public class RedactUserServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/static/Users/Redact.jsp");
        Optional<Cookie> cookie = Arrays.stream(req.getCookies()).filter(x -> x.getName().equals(WebConstants.cookie))
                        .findAny();
        req.setAttribute("user", userDao.getUser(Integer.parseInt(cookie.get().getValue())));
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {

    }
}

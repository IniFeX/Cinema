package org.example.Servlets.User;

import org.example.Constants.WebConstants;
import org.example.Dao.UserDao;
import org.example.Models.Role;
import org.example.Models.User;

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

@WebServlet(urlPatterns = {WebConstants.prefix +"/profile"})
public class ProfileServlet extends HttpServlet {
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
        Optional<Cookie> authData
                = Arrays.stream(req.getCookies()).filter(cookie -> cookie.getName().equals(WebConstants.cookie)).findAny();
        req.setAttribute("user", userDao.getUser(Integer.parseInt(authData.get().getValue())));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/static/Users/VievUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie(WebConstants.cookie, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        resp.addCookie(cookie);
        resp.sendRedirect("http://localhost:8080/JspApi_war/auth");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Cookie> authData
                = Arrays.stream(req.getCookies()).filter(cookie -> cookie.getName().equals(WebConstants.cookie)).findAny();
        userDao.delete(Integer.parseInt(authData.get().getValue()));
        Cookie cookie = new Cookie(WebConstants.cookie, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        resp.addCookie(cookie);
        resp.sendRedirect("http://localhost:8080/JspApi_war/auth");
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Cookie> authData
                = Arrays.stream(req.getCookies()).filter(cookie -> cookie.getName().equals(WebConstants.cookie)).findAny();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(Integer.parseInt(authData.get().getValue()), username, password, Role.USER);
        userDao.create(user);
        resp.sendRedirect("http://localhost:8080/JspApi_war/api/profile");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

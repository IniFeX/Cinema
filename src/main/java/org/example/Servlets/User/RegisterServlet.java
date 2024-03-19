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

@WebServlet(urlPatterns = { "/register", WebConstants.prefix + "/redUser" })
public class RegisterServlet extends HttpServlet {
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/static/Users/CreateUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User().toBuilder()
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .role(Role.USER)
                .build();
        int id = userDao.create(user);
        System.out.println("id: " + id);
        Cookie cookie = new Cookie(WebConstants.cookie, Integer.toString(id));
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        resp.addCookie(cookie);
        resp.sendRedirect("http://localhost:8080/JspApi_war/api/main");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

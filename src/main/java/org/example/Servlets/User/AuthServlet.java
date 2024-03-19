package org.example.Servlets.User;

import org.example.Constants.WebConstants;
import org.example.Dao.UserDao;
import org.example.Models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/auth"})
public class AuthServlet extends HttpServlet {
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
            RequestDispatcher dispatcher = req.getRequestDispatcher("/static/Users/Auth.jsp");
            dispatcher.forward(req, resp);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            Cookie cookie = new Cookie(WebConstants.cookie, Integer.toString(user.getId()));
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            resp.addCookie(cookie);
            resp.sendRedirect("http://localhost:8080/JspApi_war/api/main");
        }
        else {
            resp.sendRedirect("http://localhost:8080/JspApi_war/auth");
        }
    }

    @Override
        public void destroy() {
            super.destroy();
        }
}

package org.example;

import org.example.Constants.WebConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns = { WebConstants.prefix + "/main"})
public class MainServlet extends HttpServlet {
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
//        Cookie cookie = new Cookie("autht", "");
//        cookie.setMaxAge(0);
//        resp.addCookie(cookie);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/static/Main.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}

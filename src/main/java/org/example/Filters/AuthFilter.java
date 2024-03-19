package org.example.Filters;

import org.example.Constants.WebConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(urlPatterns = { "/api/*"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Optional<Cookie> authData
                = Arrays.stream(httpRequest.getCookies()).filter(cookie -> cookie.getName()
                .equals(WebConstants.cookie)).findAny();
        if (!authData.isEmpty()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        else {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendRedirect("http://localhost:8080/JspApi_war/auth");
        }
    }
}

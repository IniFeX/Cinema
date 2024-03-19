package org.example.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "HiddenHttpMethodFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class HiddenHttpMethodFilter implements Filter {



    /**
     * Default method parameter: {@code _method}
     */
    public static final String DEFAULT_METHOD_PARAM = "_method";

    private String methodParam = DEFAULT_METHOD_PARAM;

    /**
     * Set the parameter name to look for HTTP methods.
     *
     * @see #DEFAULT_METHOD_PARAM
     */
    public void setMethodParam(String methodParam) {
        this.methodParam = methodParam;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String paramValue = request.getParameter(this.methodParam);

        if ("POST".equals(request.getMethod()) && paramValue != null && paramValue.trim().length() > 0) {
            String method = paramValue.toUpperCase(Locale.ENGLISH);
            HttpServletRequest wrapper = (HttpServletRequest) new HttpMethodRequestWrapper(request, method);
            filterChain.doFilter(wrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }


    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

        @Override
        public String getMethod() {
            return this.method;
        }
    }

}

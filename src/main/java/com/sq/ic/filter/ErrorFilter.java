package com.sq.ic.filter;

import javax.servlet.*;
import java.io.IOException;

public class ErrorFilter implements Filter {
    public static final String ERROR_URI = "/handleError";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            request.setAttribute(ERROR_URI, e);
//            转发到ErrorController
            request.getRequestDispatcher(ERROR_URI).forward(request, response);
        }
    }
}

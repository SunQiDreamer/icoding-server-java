package com.sq.ic.filter;

import javax.servlet.*;
import java.io.IOException;

public class ErrorFilter implements Filter {

    public static final String ERROR_URI = "/handleError";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            servletRequest.setAttribute(ERROR_URI, e);
            servletRequest.getRequestDispatcher(ERROR_URI)
                    .forward(servletRequest, servletResponse);
        }
    }
}

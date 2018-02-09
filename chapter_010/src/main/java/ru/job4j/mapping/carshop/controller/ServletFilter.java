package ru.job4j.mapping.carshop.controller;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created on 23.01.18.
 * Authorization.
 * @author Wamdue
 * @version 1.0
 */
public class ServletFilter implements Filter {
    /**
     * Not in use.
     * @param filterConfig - not in use.
     */
    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * Checks if user if logged in.
     * @param servletRequest - servlet request.
     * @param servletResponse - servlet response.
     * @param filterChain - chain of filters.
     * @throws IOException - exception.
     * @throws ServletException - exception.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getRequestURI().contains("signin.do")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpSession session = req.getSession();

            if (session.getAttribute("user") == null) {
                resp.sendRedirect(String.format("%s/signin.do", req.getContextPath()));
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * Not in use.
     */
    @Override
    public void destroy() {
    }
}

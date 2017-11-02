package ru.job4j.crud.controller;

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
 * Created on 31.10.17.
 * Check if user logged in.
 * @author Wamdue
 * @version 1.0
 */
public class AuthFilter implements Filter {
    /**
     * Dummy method.
     * @param filterConfig filter config.
     * @throws ServletException - exception.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Filter session info.
     * @param servletRequest - servlter request.
     * @param servletResponse - servlet response.
     * @param filterChain - other filters.
     * @throws IOException - exception.
     * @throws ServletException - exception.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getRequestURI().contains("/signin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpSession session = req.getSession();
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            synchronized (session) {
                if (session.getAttribute("login") == null) {
                    resp.sendRedirect(String.format("%s/signin", req.getContextPath()));
                    return;
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    /**
     * Dummy method.
     */
    @Override
    public void destroy() {

    }
}
package ru.job4j.fin.filters;

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
 * Created on 23.11.17.
 * Filter login.
 * @author Wamdue
 * @version 1.0
 */
public class LoginFilter implements Filter {
    /**
     * Not implemented
     * @param filterConfig - filter config.
     * @throws ServletException - servlet exception.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Filtering login.
     * @param servletRequest - servlet request.
     * @param servletResponse - servlet response.
     * @param filterChain - filter chain.
     * @throws IOException - io exception.
     * @throws ServletException - servlet exception.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getRequestURI().contains("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpSession session = req.getSession();
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            if (session.getAttribute("login") == null) {
                resp.sendRedirect(String.format("%s/login", req.getContextPath()));
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * Not implemented.
     */
    @Override
    public void destroy() {

    }
}

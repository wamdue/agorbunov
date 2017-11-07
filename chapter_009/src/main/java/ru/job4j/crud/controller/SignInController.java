package ru.job4j.crud.controller;

import ru.job4j.crud.model.DBConnection;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created on 31.10.17.
 * Authorisation.
 * @author Wamdue
 * @version 1.0
 */
public class SignInController extends HttpServlet {
    /**
     * Redirecting to welcome page.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exception.
     * @throws IOException - exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/login_view.jsp").forward(req, resp);
    }

    /**
     * Analisation input information.
     * @param req - user information.
     * @param resp - response.
     * @throws ServletException - exception.
     * @throws IOException - exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection connection = DBConnection.getInstance();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = connection.credentialUser(login, password);
        if (user != null) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", login);
                if (user.getRole() == Role.ADMIN) {
                    session.setAttribute("role", user.getRole());
                } else {
                    session.setAttribute("id", user.getId());
                }
            }
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "User login or password incorrect.");
            doGet(req, resp);
        }
    }
}

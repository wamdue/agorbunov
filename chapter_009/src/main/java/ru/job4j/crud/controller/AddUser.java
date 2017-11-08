package ru.job4j.crud.controller;

import ru.job4j.crud.model.DBConnection;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created on 24.10.17.
 * Add new user to db.
 * @author Wamdue
 * @version 1.0
 */
public class AddUser extends HttpServlet {
    /**
     * Connecton to db.
     */
    private DBConnection connection = DBConnection.getInstance();

    /**
     * Redirecting to jsp.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exception.
     * @throws IOException - exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/add_user.jsp").forward(req, resp);
    }

    /**
     * Submitting new information to db.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setRole(Role.valueOf(req.getParameter("role")));
        user.setCreateDate(new Timestamp(new Date().getTime()));
        user.setCity(req.getParameter("city"));
        user.setCountry(req.getParameter("country"));
        connection.addUser(user);
        resp.sendRedirect(req.getContextPath());
    }
}

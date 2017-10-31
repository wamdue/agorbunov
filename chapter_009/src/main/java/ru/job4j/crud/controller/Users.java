package ru.job4j.crud.controller;

import ru.job4j.crud.model.DBConnection;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created on 23.10.17
 * Simple crud servlet.
 * @author Wamdue
 * @version 1.0
 */
public class Users extends HttpServlet {
    /**
     * Establish connection to db.
     */
    private DBConnection connection = DBConnection.getInstance();

    /**
     * Show user by entered name.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = connection.listOfUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}

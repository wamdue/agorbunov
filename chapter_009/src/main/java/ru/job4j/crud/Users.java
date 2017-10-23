package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created on 23.10.17
 * Simple crud servlet.
 * @author Wamdue
 * @version 1.0
 */
public class Users extends HttpServlet {

    private DBConnection connection = new DBConnection();

    /**
     * Show user by entered name.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(this.connection.getUserByName(name).toString());
        writer.flush();

    }

    /**
     * Insert new user in db.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        user.setCreateDate(new Timestamp(new Date(req.getParameter("createdate")).getTime()));
        this.connection.addUser(user);
    }

    /**
     * Update user information.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = this.connection.getUserByName(req.getParameter("name"));
        user.setName(req.getParameter("newName"));
        user.setLogin(req.getParameter("newLogin"));
        user.setEmail(req.getParameter("newEmail"));
        this.connection.updateUser(user);
    }

    /**
     * Delete user by name.
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.connection.deleteUser(req.getParameter("name"));
    }

}

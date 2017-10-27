package ru.job4j.crud;

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
     * Submitting new information to db.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection connection = new DBConnection();
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        user.setCreateDate(new Timestamp(new Date().getTime()));
        connection.addUser(user);
        connection.closeConnection();
        resp.sendRedirect(req.getContextPath());
    }
}

package ru.job4j.crud.controller;

import ru.job4j.crud.model.DBConnection;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 24.10.17.
 * Servlet for updating info about user in db.
 * @author Wamdue
 * @version 1.0
 */
public class UpdateUser extends HttpServlet {
    /**
     * Internal connection to db.
     */
    private DBConnection connection = DBConnection.getInstance();

    /**
     * Updating information about user, and save it to db.
     * Redirecting back to source page.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = connection.getUserById(Integer.valueOf(req.getParameter("id")));
        user.setName(req.getParameter("newname"));
        user.setLogin(req.getParameter("newlogin"));
        user.setEmail(req.getParameter("newemail"));
        user.setPassword(req.getParameter("newpassword"));
        if (req.getParameter("newrole") != null) {
            user.setRole(Role.valueOf(req.getParameter("newrole")));
        }
        user.setCity(req.getParameter("newcity"));
        user.setCountry(req.getParameter("newcountry"));
        this.connection.updateUser(user.getId(), user);
        resp.sendRedirect(req.getContextPath());
    }
}

package ru.job4j.crud.controller;

import ru.job4j.crud.model.DBConnection;
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
     * Shows welcome screen to update user.
     * @param req - request.
     * @param resp - response
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = this.connection.getUserById(Integer.valueOf(req.getParameter("id")));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/update_user.jsp").forward(req, resp);
    }

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
        this.connection.updateUser(user.getId(), user);
        resp.sendRedirect(req.getContextPath());
    }
}

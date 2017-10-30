package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 26.10.17
 * Servlet for deleting user from db.
 * @author Wamdue
 * @version 1.0
 */
public class DeleteUser extends HttpServlet {
    /**
     * Deleing user from db without welcome screen.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection connection = DBConnection.getInstance();
        int id = Integer.valueOf(req.getParameter("id"));
        connection.deleteUser(id);
        resp.sendRedirect(req.getContextPath());

    }
}

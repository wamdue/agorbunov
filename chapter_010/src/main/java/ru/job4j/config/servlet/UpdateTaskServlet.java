package ru.job4j.config.servlet;

import ru.job4j.config.db.Connection;
import ru.job4j.config.db.DB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 15.01.18.
 * Update task status.
 * @author Wamdue
 * @version 1.0
 */
public class UpdateTaskServlet extends HttpServlet {
    /**
     * Link to db.
     */
    private Connection connection = DB.INSTANCE.getConnection();

    /**
     * Change current task status to oposit.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exception.
     * @throws IOException - exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        this.connection.updateStatus(id);
    }
}

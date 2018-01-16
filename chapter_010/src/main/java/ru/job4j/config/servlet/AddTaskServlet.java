package ru.job4j.config.servlet;

import ru.job4j.config.db.Connection;
import ru.job4j.config.db.DB;
import ru.job4j.config.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created on 15.01.18.
 * Add new task to db.
 * @author Wamdue
 * @version 1.0
 */
public class AddTaskServlet extends HttpServlet {
    /**
     * Connection link.
     */
    private Connection connection = DB.INSTANCE.getConnection();

    /**
     * Get data from request, and insert new task to db.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exception.
     * @throws IOException - exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("desc");
        Item item = new Item();
        item.setDescription(description);
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        this.connection.addTask(item);
    }
}

package ru.job4j.config.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.config.db.Connection;
import ru.job4j.config.db.DB;
import ru.job4j.config.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created on 11.01.18.
 * Json controller get full data.
 * @author Wamdue
 * @version 1.0
 */
public class LoadToPageServlet extends HttpServlet {

    /**
     * Local connection to db.
     */
    private Connection connection = DB.INSTANCE.getConnection();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        List<Item> items;
        ObjectMapper mapper = new ObjectMapper();
        String status = req.getParameter("flag");
        if ("false".equals(status)) {
            items = this.connection.getActiveTasks();
        } else {
            items = this.connection.getListOfTasks();
        }
        mapper.writeValue(out, items);
    }

}

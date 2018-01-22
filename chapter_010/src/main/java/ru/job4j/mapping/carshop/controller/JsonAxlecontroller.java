package ru.job4j.mapping.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.mapping.carshop.model.Connect;
import ru.job4j.mapping.carshop.model.DB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created on 22.01.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public class JsonAxlecontroller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        DB db = Connect.INSTANCE.getConnection();
        mapper.writeValue(out, db.getAxles());

    }
}

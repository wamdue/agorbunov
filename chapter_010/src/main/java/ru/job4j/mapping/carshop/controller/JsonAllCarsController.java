package ru.job4j.mapping.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.model.Connect;
import ru.job4j.mapping.carshop.model.DB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created on 25.01.18.
 * Load all cars on main page.
 * @author Wamdue
 * @version 1.0
 */
public class JsonAllCarsController extends HttpServlet {
    /**
     * Db connection.
     */
    private DB db = Connect.INSTANCE.getConnection();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Car> cars = this.db.getCars();
        OutputStream out = resp.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, cars);
    }
}

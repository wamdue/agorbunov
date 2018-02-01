package ru.job4j.mapping.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.model.Connect;
import ru.job4j.mapping.carshop.model.repository.CarRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created on 31.01.18.
 * Filterlist of cars.
 * @author Wamdue
 * @version 1.0
 */
public class FormFilterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String pic = req.getParameter("pic");
        String time = req.getParameter("time");

        int brandId = Integer.valueOf(brand);
        int p = "true".equals(pic) ? 1 : 0;
        int t = "true".equals(time) ? 1 : 0;

        CarRepository repository = new CarRepository(Connect.INSTANCE.getConnection());
        List<Car> list = repository.getByFilter(brandId, p, t);
        ObjectMapper mapper = new ObjectMapper();
        OutputStream out = resp.getOutputStream();
        mapper.writeValue(out, list);
    }
}

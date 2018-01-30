package ru.job4j.mapping.carshop.controller;

import ru.job4j.mapping.carshop.entity.User;
import ru.job4j.mapping.carshop.model.Connect;
import ru.job4j.mapping.carshop.model.DB;
import ru.job4j.mapping.carshop.model.repository.CarRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 25.01.18.
 * Changing car sold status in db.
 * @author Wamdue
 * @version 1.0
 */
public class JsonChangeStatus extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DB db = Connect.INSTANCE.getConnection();
        String userId = (String) req.getSession().getAttribute("user");
        String carId = req.getParameter("id");
        CarRepository carRepository = new CarRepository(db);
        User user = carRepository.getById(Integer.valueOf(carId)).getUser();
        if (user.getId() == Integer.valueOf(userId)) {
            carRepository.changCarStatus(Integer.valueOf(carId));
        }
    }
}

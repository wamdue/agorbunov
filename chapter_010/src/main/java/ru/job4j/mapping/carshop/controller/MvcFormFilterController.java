package ru.job4j.mapping.carshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.model.repository.Cars;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 09.02.18.
 * Filter controller.
 * @author Wamdue
 * @version 1.0
 */
@Controller
public class MvcFormFilterController {
    /**
     * Link to car repository.
     */
    private final Cars repository;

    /**
     * Main constructor.
     * @param repository - car repository.
     */
    @Autowired
    public MvcFormFilterController(Cars repository) {
        this.repository = repository;
    }

    /**
     * Apply filter to list of cars.
     * @param req - servlet request.
     * @return - filtered list.
     */
    @RequestMapping(value = "/filter.do", method = RequestMethod.GET)
    @ResponseBody
    public List<Car> setFilter(HttpServletRequest req) {
        String brand = req.getParameter("brand");
        String pic = req.getParameter("pic");
        String time = req.getParameter("time");
        int brandId = Integer.valueOf(brand);
        int p = "true".equals(pic) ? 1 : 0;
        int t = "true".equals(time) ? 1 : 0;
        return this.repository.getByFilter(brandId, p, t);
    }
}

package ru.job4j.mapping.carshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.model.dao.CarDao;

import java.util.List;

/**
 * Created on 08.02.18.
 * Get list of cars in json.
 * @author Wamdue
 * @version 1.0
 */
@Controller
public class MvcCarsJsonController {
    /**
     * Link to car dao.
     */
    private final CarDao carDao;

    /**
     * Main constructor.
     * @param carDao - car dao bean.
     */
    @Autowired
    public MvcCarsJsonController(CarDao carDao) {
        this.carDao = carDao;
    }

    /**
     * Getting list of all cars.
     * @return list in json format.
     */
    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    @ResponseBody
    public List<Car> getCarsJson() {
        return this.carDao.getList();
    }
}

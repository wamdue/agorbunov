package ru.job4j.mapping.carshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.mapping.carshop.entity.User;
import ru.job4j.mapping.carshop.model.repository.CarRepository;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 09.02.18.
 * Change car status.
 * @author Wamdue
 * @version 1.0
 */
@Controller
public class MvcChangeStatusController {
    /**
     * Link to car repository.
     */
    private final CarRepository carStorage;

    /**
     * Main constructor.
     * @param carStorage - repository bean.
     */
    @Autowired
    public MvcChangeStatusController(CarRepository carStorage) {
        this.carStorage = carStorage;
    }

    /**
     * Method to change status, if user create this record, change can be done.
     * @param req - request servlet.
     * @return - redirect.
     */
    @RequestMapping(value = "/status.do", method = RequestMethod.POST)
    public String changeCarStatus(HttpServletRequest req) {
        String userId = (String) req.getSession().getAttribute("user");
        String carId = req.getParameter("id");
        User user = this.carStorage.getById(Integer.valueOf(carId)).getUser();
        if (user.getId() == Integer.valueOf(userId)) {
            this.carStorage.changCarStatus(Integer.valueOf(carId));
        }
        return "redirect:/index.do";
    }
}
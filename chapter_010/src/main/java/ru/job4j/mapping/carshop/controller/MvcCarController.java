package ru.job4j.mapping.carshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 07.02.18.
 * Welcome page controller.
 * @author Wamdue
 * @version 1.0
 */
@Controller
public class MvcCarController {
    /**
     * Show welcome page.
     * @return - welcome page.
     */
    @RequestMapping(name = "/index.do", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }
}

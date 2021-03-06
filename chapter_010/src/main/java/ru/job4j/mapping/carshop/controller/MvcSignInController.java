package ru.job4j.mapping.carshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.mapping.carshop.entity.User;
import ru.job4j.mapping.carshop.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created on 08.02.18.
 * Register user.
 * @author Wamdue
 * @version 1.0
 */
@Controller
public class MvcSignInController {
    /**
     * User repository link.
     */
    private final UserRepository userRepository;

    /**
     * Main constructor.
     * @param userRepository - user repository bean.
     */
    @Autowired
    public MvcSignInController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Show welcome page.
     * @return - welcome page.
     */
    @RequestMapping(value = "/signin.do", method = RequestMethod.GET)
    public String getLogin() {
        return "signin";
    }

    /**
     * Registering user in session.
     * @param req - servlet request.
     * @return - redirect to home page.
     */
    @RequestMapping(value = "/signin.do", method = RequestMethod.POST)
    public String setLogin(HttpServletRequest req) {
        String name = req.getParameter("login");
        User user  = this.userRepository.getUserByName(name);
        if (user == null) {
            user = new User();
            user.setName(name);
            this.userRepository.create(user);
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", String.valueOf(user.getId()));
        return "welcome";
    }
}

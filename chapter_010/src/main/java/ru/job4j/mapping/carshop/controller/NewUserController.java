package ru.job4j.mapping.carshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.mapping.carshop.entity.Authority;
import ru.job4j.mapping.carshop.entity.User;
import ru.job4j.mapping.carshop.model.repository.Authorities;
import ru.job4j.mapping.carshop.model.repository.Users;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 16.02.18.
 * Create new user controller.
 * @author Wamdue
 * @version 1.0
 */
@Controller
@RequestMapping("/newUser.do")
public class NewUserController {
    /**
     * Users repository.
     */
    private final Users users;
    private final Authorities authorities;

    /**
     * Main constructor.
     * @param users - repository.
     * @param authorities - authorities repository.
     */
    @Autowired
    public NewUserController(Users users, Authorities authorities) {
        this.users = users;
        this.authorities = authorities;
    }

    /**
     * Show welcome page.
     * @return - page name.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getForm() {
        return "newUser";
    }

    /**
     * Add new user to db, or return page with error.
     * @param req - servlet request.
     * @param model - parameters.
     * @return - redirect to sign in page, or return with error.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createUser(HttpServletRequest req, Model model) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");
        String result = "signin";
        if (!password.equals(confirm)) {
            model.addAttribute("error", "Passwords must be equal.");
            result = "newUser";
        } else if (users.findByName(username) != null) {
            model.addAttribute("error", "User with this name already exist.");
            result = "newUser";
        } else {
            User user = new User();
            user.setName(username);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            Authority authority = new Authority();
            authority.setUser(user);
            authority.setRole("ROLE_USER");
            this.authorities.save(authority);
        }
        return result;
    }
}

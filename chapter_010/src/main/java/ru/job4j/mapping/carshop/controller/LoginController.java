package ru.job4j.mapping.carshop.controller;

import ru.job4j.mapping.carshop.entity.User;
import ru.job4j.mapping.carshop.model.Connect;
import ru.job4j.mapping.carshop.model.DB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created on 23.01.18.
 * User login information.
 * @author Wamdue
 * @version 1.0
 */
public class LoginController extends HttpServlet {
    /**
     * Redirect to login page.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exception.
     * @throws IOException - exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/signin.jsp").forward(req, resp);
    }

    /**
     * Get user id and set it to session attribute.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exception.
     * @throws IOException - exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("login");
        DB db = Connect.INSTANCE.getConnection();
        User user  = db.getUserByName(name);
        int id = -1;
        if (user == null) {
            user = new User();
            user.setName(name);
            id = db.createUser(user);
        } else {
            id = user.getId();
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", String.valueOf(id));
        resp.sendRedirect(String.format("%s/welcome.html", req.getContextPath()));
    }
}

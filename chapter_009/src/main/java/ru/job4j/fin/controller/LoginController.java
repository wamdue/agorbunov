package ru.job4j.fin.controller;

import ru.job4j.fin.dao.UserDao;
import ru.job4j.fin.entity.User;
import ru.job4j.fin.model.PSConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created on 22.11.17.
 *
 * @author Wamdue
 * @version 1.0
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/fin/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String val = req.getParameter("checkIn");
        UserDao userDao = new UserDao(PSConnection.getInstance().getConnection());
        for (User user : userDao.getAll()) {
            if (val.equals(user.getName())) {
                HttpSession session = req.getSession();
                session.setAttribute("name", val);

            }
        }
    }
}

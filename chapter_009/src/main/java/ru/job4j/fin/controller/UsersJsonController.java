package ru.job4j.fin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.fin.dao.UserDao;
import ru.job4j.fin.entity.User;
import ru.job4j.fin.model.PSConnection;
import ru.job4j.fin.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;

/**
 * Created on 22.11.17.
 *
 * @author Wamdue
 * @version 1.0
 */
public class UsersJsonController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream out = resp.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        Connection connection = PSConnection.getInstance().getConnection();
        UserDao userDao = new UserDao(connection);
        UserRepository repository = new UserRepository(connection);
        List<User> list = userDao.getAll();
        for (User user : list) {
            User temp = repository.getUserbyId(user.getId());
            String value = mapper.writeValueAsString(temp);
            mapper.writeValue(out, temp);
            System.out.println(value);
        }
    }
}

package ru.job4j.fin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.fin.dao.UserDao;
import ru.job4j.fin.entity.User;
import ru.job4j.fin.enums.Connect;
import ru.job4j.fin.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 22.11.17.
 * Json generator, for all users.
 * @author Wamdue
 * @version 1.0
 */
public class UsersJsonController extends HttpServlet {
    /**
     * List convert to json.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        Connection connection = Connect.INSTANCE.getConnection();
        UserDao userDao = new UserDao(connection);
        UserRepository repository = new UserRepository(connection);
        List<User> list = userDao.getAll();
        List<User> tempList = new ArrayList<>();
        for (User user : list) {
            User temp = repository.getUserById(user.getId());
            tempList.add(temp);
        }
        mapper.writeValue(out, tempList);
    }
}

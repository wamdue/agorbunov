package ru.job4j.fin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.fin.entity.User;
import ru.job4j.fin.enums.Connect;
import ru.job4j.fin.repository.UserRepository;
import ru.job4j.fin.util.SearchType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Wamdue
 * @version 1.0
 * @since 26.11.2017
 */
public class SearchJsonController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Connection connection = Connect.INSTANCE.getConnection();
        UserRepository repository = new UserRepository(connection);
        String type = req.getParameter("searchType");
        String value = req.getParameter("search");
        List<User> users = repository.searchMethod(new SearchType(type, value));
        ObjectMapper mapper = new ObjectMapper();
        OutputStream out = resp.getOutputStream();
        mapper.writeValue(out, users);
    }

}

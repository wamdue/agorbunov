package ru.job4j.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.crud.model.DBConnection;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created on 10.11.17.
 * Json data output.
 * @author Wamdue
 * @version 1.0
 */
public class JsonUsersController extends HttpServlet {

    /**
     * Show all users in json format.
     * @param req - request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection connection = DBConnection.getInstance();
        resp.setContentType("application/json");
        OutputStream out = resp.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = connection.listOfUsers();
        mapper.writeValue(out, users);
    }
}
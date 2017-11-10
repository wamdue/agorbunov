package ru.job4j.crud.controller;

import ru.job4j.crud.model.DBConnection;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();
        writer.append("[");
        for (User user : connection.listOfUsers()) {
            writer.append("{");
            writer.append("\"id\" : \"").append(String.valueOf(user.getId())).append("\", ");
            writer.append("\"name\" : \"").append(user.getName()).append("\", ");
            writer.append("\"login\" : \"").append(user.getLogin()).append("\", ");
            writer.append("\"email\" : \"").append(user.getEmail()).append("\", ");
            writer.append("\"city\" : \"").append(user.getCity()).append("\", ");
            writer.append("\"country\" : \"").append(user.getCountry()).append("\", ");
            writer.append("\"role\" : \"").append(user.getRole().name()).append("\"");
            writer.append("}");
        }
        writer.append("]");
    }
}

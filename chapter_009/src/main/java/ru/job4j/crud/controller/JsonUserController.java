package ru.job4j.crud.controller;

import ru.job4j.crud.model.DBConnection;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** Json code generator, single user info.
 * @author Wamdue
 * @version 1.0
 * @since 16.11.2017
 */
public class JsonUserController extends HttpServlet {
    /**
     * Sending info about user by id.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - servlet exception.
     * @throws IOException - IO exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = DBConnection.getInstance().getUserById(Integer.valueOf(req.getParameter("id")));
        PrintWriter writer = resp.getWriter();
        writer.append("{").append("\"id\" : \"").append(String.valueOf(user.getId())).append("\", ");
        writer.append("\"name\" : \"").append(user.getName()).append("\", ");
        writer.append("\"login\" : \"").append(user.getLogin()).append("\", ");
        writer.append("\"password\" : \"").append(user.getPassword()).append("\", ");
        writer.append("\"email\" : \"").append(user.getEmail()).append("\", ");
        writer.append("\"city\" : \"").append(user.getCity()).append("\", ");
        writer.append("\"country\" : \"").append(user.getCountry()).append("\", ");
        writer.append("\"role\" : \"").append(user.getRole().name()).append("\"} ");
    }
}

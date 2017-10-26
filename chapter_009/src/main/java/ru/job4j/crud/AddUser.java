package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created on 24.10.17
 * Add new user to db.
 * @author Wamdue
 * @version 1.0
 */
public class AddUser extends HttpServlet {
    /**
     * Shows welcome screen/
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        writer.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Title</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <form method=\"post\">\n"
                + "        <input type=\"text\" name=\"name\"/>\n"
                + "        <input type=\"text\" name=\"login\"/>\n"
                + "        <input type=\"text\" name=\"email\"/>\n"
                + "        <input type=\"submit\" value=\"add user\"/>\n"
                + "    </form>\n"
                + "</body>\n"
                + "</html>");
        writer.flush();
    }

    /**
     * Submitting new information to db.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection connection = new DBConnection();
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        user.setCreateDate(new Timestamp(new Date().getTime()));
        connection.addUser(user);
        connection.closeConnection();
        resp.sendRedirect(req.getContextPath());
    }
}

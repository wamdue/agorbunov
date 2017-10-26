package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created on 23.10.17
 * Simple crud servlet.
 * @author Wamdue
 * @version 1.0
 */
public class Users extends HttpServlet {

    private DBConnection connection = new DBConnection();

    /**
     * Show user by entered name.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder("<table>");
        sb.append("<tr>");
        sb.append("<td>id</td>");
        sb.append("<td>name</td>");
        sb.append("<td>login</td>");
        sb.append("<td>email</td>");
        sb.append("<td>create date</td>");
        sb.append("<td>Actions</td>");
        sb.append("</tr>");
        for (User user : this.connection.listOfUsers()) {
            sb.append("<tr>");
            sb.append("<td>").append(user.getId()).append("</td>");
            sb.append("<td>").append(user.getName()).append("</td>");
            sb.append("<td>").append(user.getLogin()).append("</td>");
            sb.append("<td>").append(user.getEmail()).append("</td>");
            sb.append("<td>").append(new Date(user.getCreateDate().getTime())).append("</td>");
            sb.append("<td>")
                    .append("<form action=\"" + req.getContextPath() + "/updateuser\" method=\"get\">")
                    .append("<button type=\"submit\" name=\"id\" value=\"").append(user.getId()).append("\">Edit</button>")
                    .append("</form>");
            sb.append("<form action=\"" + req.getContextPath() + "/deleteuser\" method=\"post\">")
                    .append("<button type=\"submit\" name=\"id\" value=\"").append(user.getId()).append("\">Delete</button>")
                    .append("</form>");
            sb.append("</td>");
            sb.append("<tr>");
        }
        sb.append("</table>");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Title</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<a href=\"" + req.getContextPath() + "/adduser\">add new user</a>"
                + sb.toString()
                + "</body>\n"
                + "</html>");
        writer.flush();

    }

    @Override
    public void destroy() {
        this.connection.closeConnection();
    }
}

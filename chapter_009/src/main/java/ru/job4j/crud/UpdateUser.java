package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created on 24.10.17
 * Servlet for updating info about user in db.
 * @author Wamdue
 * @version 1.0
 */
public class UpdateUser extends HttpServlet {

    private DBConnection connection = new DBConnection();

    /**
     * Shows welcome screen to update user.
     * @param req - request.
     * @param resp - response
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = this.connection.getUserById(Integer.valueOf(req.getParameter("id")));
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
                + "        new name <input type=\"text\" name=\"newname\" value=\"" + user.getName() + "\"/>\n"
                + "        new login <input type=\"text\" name=\"newlogin\" value=\"" + user.getLogin() + "\"/>\n"
                + "        new email <input type=\"text\" name=\"newemail\" value=\"" + user.getEmail() + "\">\n"
                + "        <input type=\"submit\" value=\"Update user\">\n"
                + "    </form>\n"
                + "</body>\n"
                + "</html>");
        writer.flush();
    }

    /**
     * Updating information about user, and save it to db.
     * Redirecting back to source page.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = connection.getUserById(Integer.valueOf(req.getParameter("id")));
        user.setName(req.getParameter("newname"));
        user.setLogin(req.getParameter("newlogin"));
        user.setEmail(req.getParameter("newemail"));
        this.connection.updateUser(user.getId(), user);
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * Close db connection.
     */
    @Override
    public void destroy() {
        this.connection.closeConnection();
    }
}

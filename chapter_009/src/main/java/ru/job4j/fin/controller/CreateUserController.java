package ru.job4j.fin.controller;

import ru.job4j.fin.dao.MusicTypeDao;
import ru.job4j.fin.dao.RoleDao;
import ru.job4j.fin.entity.Address;
import ru.job4j.fin.entity.MusicType;
import ru.job4j.fin.entity.User;
import ru.job4j.fin.model.PSConnection;
import ru.job4j.fin.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created on 24.11.17.
 * Creating user in db.
 * @author Wamdue
 * @version 1.0
 */
public class CreateUserController extends HttpServlet {

    /**
     * Managing data from post.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String[] types = req.getParameterValues("musicTypes[]");
        String[] roles = req.getParameterValues("roles[]");

        Connection connection = PSConnection.getInstance().getConnection();
        UserRepository repository = new UserRepository(connection);
        RoleDao roleDao = new RoleDao(connection);
        MusicTypeDao musicTypeDao = new MusicTypeDao(connection);

        User user = new User();
        Address addr = new Address();
        addr.setAddress(address);
        user.setName(name);
        user.setAddress(addr);

        if (types != null) {
            for (String id : types) {
                int i = Integer.valueOf(id);
                MusicType type = musicTypeDao.findById(i);
                user.addMusicType(type);
            }
        }

        if (roles != null) {
            for (String id : roles) {
                int i = Integer.valueOf(id);
                user.addRole(roleDao.findById(i));
            }
        }

        repository.createUser(user);
        resp.sendRedirect(req.getContextPath());
    }
}

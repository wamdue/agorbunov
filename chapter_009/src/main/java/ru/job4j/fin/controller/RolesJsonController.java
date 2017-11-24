package ru.job4j.fin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.fin.dao.RoleDao;
import ru.job4j.fin.entity.Role;
import ru.job4j.fin.model.PSConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created on 24.11.17.
 * Generating json with roles.
 * @author Wamdue
 * @version 1.0
 */
public class RolesJsonController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        OutputStream out = resp.getOutputStream();
        RoleDao roleDao = new RoleDao(PSConnection.getInstance().getConnection());
        ObjectMapper mapper = new ObjectMapper();
        List<Role> roles = roleDao.getAll();
        mapper.writeValue(out, roles);
    }
}

package ru.job4j.fin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.fin.dao.MusicTypeDao;
import ru.job4j.fin.entity.MusicType;
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
 * Generating json with Music types.
 * @author Wamdue
 * @version 1.0
 */
public class MusicTypeJsonController extends HttpServlet {
    /**
     * Response with json text.
     * @param req - reques.
     * @param resp - response.
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        MusicTypeDao typeDao = new MusicTypeDao(PSConnection.getInstance().getConnection());
        List<MusicType> list = typeDao.getAll();
        OutputStream out = resp.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, list);
    }
}

package ru.job4j.mapping.carshop.model.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.mapping.carshop.entity.Body;
import ru.job4j.mapping.carshop.model.DB;

import java.util.List;

/**
 * Created on 29.01.18.
 *
 * @author Wamdue
 * @version 1.0
 */
@Component
public class BodyDao extends AbstractDao<Body> {
    /**
     * Main constructor.
     *
     * @param db - db connection.
     */
    @Autowired
    public BodyDao(DB db) {
        super(db);
    }

    /**
     * Get body by id.
     * @param id - id.
     * @return - body.
     */
    @Override
    public Body getById(int id) {
        Session session = this.getDb().getSession();
        Body body = session.get(Body.class, id);
        session.close();
        return body;
    }

    /**
     * Get body list from db.
     * @return - list.
     */
    @Override
    public List<Body> getList() {
        Session session = this.getDb().getSession();
        @SuppressWarnings("unchecked")
        List<Body> list = session.createQuery("from Body").list();
        session.close();
        return list;
    }

}

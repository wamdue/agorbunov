package ru.job4j.mapping.carshop.model.dao;

import org.hibernate.Session;
import ru.job4j.mapping.carshop.entity.Engine;
import ru.job4j.mapping.carshop.model.DB;

import java.util.List;

/**
 * Created on 29.01.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public class EngineDao extends AbstractDao<Engine> {
    /**
     * Main constructor.
     *
     * @param db - db connection.
     */
    public EngineDao(DB db) {
        super(db);
    }

    /**
     * Get engine by id.
     * @param id - id.
     * @return - engine.
     */
    @Override
    public Engine getById(int id) {
        Session session = this.getDb().getSession();
        Engine engine = session.get(Engine.class, id);
        session.close();
        return engine;
    }

    /**
     * Get engine list from db.
     * @return - list.
     */
    @Override
    public List<Engine> getList() {
        Session session = this.getDb().getSession();
        @SuppressWarnings("unchecked")
        List<Engine> list = session.createQuery("from Engine").list();
        session.close();
        return list;
    }
}

package ru.job4j.mapping.carshop.model.dao;

import org.hibernate.Session;
import ru.job4j.mapping.carshop.entity.Axle;
import ru.job4j.mapping.carshop.model.DB;

import java.util.List;

/**
 * Created on 29.01.18.
 * Axle dao.
 * @author Wamdue
 * @version 1.0
 */
public class AxleDao extends AbstractDao<Axle> {
    /**
     * Main constructor.
     *
     * @param db - db connection.
     */
    public AxleDao(DB db) {
        super(db);
    }

    /**
     * Get axle by id.
     * @param id - id.
     * @return - axle.
     */
    @Override
    public Axle getById(int id) {
        Session session = this.getDb().getSession();
        Axle axle = session.get(Axle.class, id);
        session.close();
        return axle;
    }

    /**
     * Get axle list from db.
     * @return - list.
     */
    @Override
    public List<Axle> getList() {
        Session session = this.getDb().getSession();
        @SuppressWarnings("unchecked")
        List<Axle> list = session.createQuery("from Axle").list();
        session.close();
        return list;
    }

}

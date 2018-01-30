package ru.job4j.mapping.carshop.model.dao;

import org.hibernate.Session;
import ru.job4j.mapping.carshop.entity.Pic;
import ru.job4j.mapping.carshop.model.DB;

import java.util.List;

/**
 * Created on 29.01.18.
 * Pic dao.
 * @author Wamdue
 * @version 1.0
 */
public class PicDao extends AbstractDao<Pic> {
    /**
     * Main constructor.
     *
     * @param db - db connection.
     */
    public PicDao(DB db) {
        super(db);
    }

    /**
     * Get pic by id.
     * @param id - id.
     * @return - pic.
     */
    @Override
    public Pic getById(int id) {
        Session session = this.getDb().getSession();
        Pic pic = session.get(Pic.class, id);
        session.close();
        return pic;
    }

    /**
     * Get pic list from db.
     * @return - list.
     */
    @Override
    public List<Pic> getList() {
        Session session = this.getDb().getSession();
        @SuppressWarnings("unchecked")
        List<Pic> list = session.createQuery("from Pic").list();
        session.close();
        return list;
    }
}

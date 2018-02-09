package ru.job4j.mapping.carshop.model.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.mapping.carshop.entity.Gearbox;
import ru.job4j.mapping.carshop.model.DB;

import java.util.List;

/**
 * Created on 29.01.18.
 * Gearbox dao.
 * @author Wamdue
 * @version 1.0
 */
@Component
public class GearboxDao extends AbstractDao<Gearbox> {
    /**
     * Main constructor.
     *
     * @param db - db connection.
     */
    @Autowired
    public GearboxDao(DB db) {
        super(db);
    }

    /**
     * Get gearbox by id.
     * @param id - id.
     * @return - gearbox.
     */
    @Override
    public Gearbox getById(int id) {
        Session session = this.getDb().getSession();
        Gearbox gearbox = session.get(Gearbox.class, id);
        session.close();
        return gearbox;
    }

    /**
     * Get gearbox list from db.
     * @return - list.
     */
    @Override
    public List<Gearbox> getList() {
        Session session = this.getDb().getSession();
        @SuppressWarnings("unchecked")
        List<Gearbox> list = session.createQuery("from Gearbox").list();
        session.close();
        return list;
    }
}

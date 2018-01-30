package ru.job4j.mapping.carshop.model.dao;

import org.hibernate.Session;
import ru.job4j.mapping.carshop.entity.Brand;
import ru.job4j.mapping.carshop.model.DB;

import java.util.List;

/**
 * Created on 29.01.18.
 * Brand dao.
 * @author Wamdue
 * @version 1.0
 */
public class BrandDao extends AbstractDao<Brand> {
    /**
     * Main constructor.
     *
     * @param db - db connection.
     */
    public BrandDao(DB db) {
        super(db);
    }

    /**
     * Get brand by id.
     * @param id - id.
     * @return - brand.
     */
    @Override
    public Brand getById(int id) {
        Session session = this.getDb().getSession();
        Brand brand = session.get(Brand.class, id);
        session.close();
        return brand;
    }

    /**
     * Get brand list from db.
     * @return - list.
     */
    @Override
    public List<Brand> getList() {
        Session session = this.getDb().getSession();
        @SuppressWarnings("unchecked")
        List<Brand> list = session.createQuery("from Brand").list();
        session.close();
        return list;
    }
}

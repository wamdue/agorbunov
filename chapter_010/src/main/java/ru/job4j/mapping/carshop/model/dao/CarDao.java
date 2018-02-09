package ru.job4j.mapping.carshop.model.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.model.DB;

import java.util.List;

/**
 * Created on 29.01.18.
 * Car dao.
 * @author Wamdue
 * @version 1.0
 */
@Component
public class CarDao extends AbstractDao<Car> {
    /**
     * Main constructor.
     *
     * @param db - db connection.
     */
    @Autowired
    public CarDao(DB db) {
        super(db);
    }

    /**
     * Get car by id.
     * @param id - id.
     * @return - car.
     */
    @Override
    public Car getById(int id) {
        Session session = this.getDb().getSession();
        Car car = session.get(Car.class, id);
        session.close();
        return car;
    }

    /**
     * Get engine list from db.
     * @return - list.
     */
    @Override
    public List<Car> getList() {
        Session session = this.getDb().getSession();
        @SuppressWarnings("unchecked")
        List<Car> list = session.createQuery("from Car").list();
        session.close();
        return list;
    }

}

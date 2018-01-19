package ru.job4j.mapping.carshop.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.mapping.carshop.entity.Axle;
import ru.job4j.mapping.carshop.entity.Body;
import ru.job4j.mapping.carshop.entity.Brand;
import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.entity.Engine;
import ru.job4j.mapping.carshop.entity.Gearbox;
import ru.job4j.mapping.carshop.entity.User;

import java.util.List;

/**
 * Created on 19.01.18.
 * DB connection utils.
 * @author Wamdue
 * @version 1.0
 */
public class DB {
    private SessionFactory factory;

    DB () {
        this.init();
    }

    private void init() {
        this.factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
    @SuppressWarnings("unchecked")
    public List<Brand> getBrands() {
        Session session = this.factory.openSession();
        List<Brand> brands = session.createQuery("from Brand").list();
        session.close();
        return brands;
    }
    @SuppressWarnings("unchecked")
    public List<Gearbox> getGearboxes() {
        Session session = this.factory.openSession();
        List<Gearbox> gearboxes = session.createQuery("from Gearbox").list();
        session.close();
        return gearboxes;
    }
    @SuppressWarnings("unchecked")
    public List<Axle> getAxles() {
        Session session = this.factory.openSession();
        List<Axle> axles = session.createQuery("from Axle").list();
        session.close();
        return axles;
    }
    @SuppressWarnings("unchecked")
    public List<Engine> getEngines() {
        Session session = this.factory.openSession();
        List<Engine> engines = session.createQuery("from Engine").list();
        session.close();
        return engines;
    }
    @SuppressWarnings("unchecked")
    public List<Body> getBodies() {
        Session session = this.factory.openSession();
        List<Body> bodies = session.createQuery("from Body").list();
        session.close();
        return bodies;
    }
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Session session = this.factory.openSession();
        List<User> users = session.createQuery("from User").list();
        session.close();
        return users;
    }
    @SuppressWarnings("unchecked")
    public List<Car> getCars() {
        Session session = this.factory.openSession();
        List<Car> cars = session.createQuery("from Car").list();
        session.close();
        return cars;
    }

    /**
     * Add new user to db.
     * @param user - user to add.
     * @return - generated user id.
     */
    public int createUser(User user) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        return user.getId();
    }

}

package ru.job4j.mapping.carshop.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.mapping.carshop.entity.Axle;
import ru.job4j.mapping.carshop.entity.Body;
import ru.job4j.mapping.carshop.entity.Brand;
import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.entity.Engine;
import ru.job4j.mapping.carshop.entity.Gearbox;
import ru.job4j.mapping.carshop.entity.Pic;
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
        int id = -1;
        Session session = this.factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            id = user.getId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.close();
        return id;
    }

    /**
     * Get user by name.
     * @param userName - user name.
     * @return - user id, or -1 if not found.
     */
    public User getUserByName(String userName) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        List list =  session.createQuery(String.format("from User where name = '%s'", userName.toLowerCase())).list();
        User user;
        if (list.size() == 0) {
            user = new User();
            user.setName(userName);
            session.save(user);
        } else {
            user = (User) list.get(0);
        }
        session.close();
        return user;
    }

    /**
     * Get brand by id.
     * @param id - brand id.
     * @return brand.
     */
    public Brand getBrandById(int id) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        Brand brand = session.get(Brand.class, id);
        session.close();
        return brand;
    }

    /**
     * Get body by id.
     * @param id - body id.
     * @return body.
     */
    public Body getBodyById(int id) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        Body body = session.get(Body.class, id);
        session.close();
        return body;
    }
    /**
     * Get axle by id.
     * @param id - axle id.
     * @return axle.
     */
    public Axle getAxleById(int id) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        Axle axle = session.get(Axle.class, id);
        session.close();
        return axle;
    }
    /**
     * Get engine by id.
     * @param id - engine id.
     * @return engine.
     */
    public Engine getEngineById(int id) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        Engine engine = session.get(Engine.class, id);
        session.close();
        return engine;
    }
    /**
     * Get gearbox by id.
     * @param id - gearbox id.
     * @return gearbox.
     */
    public Gearbox getGearboxById(int id) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        Gearbox gearbox = session.get(Gearbox.class, id);
        session.close();
        return gearbox;
    }
    /**
     * Get user by id.
     * @param id - user id.
     * @return user.
     */
    public User getUserById(int id) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    /**
     * Add new car to db.
     * @param car - car to add.
     * @return car id.
     */
    public int addNewCar(Car car) {
        Session session = this.factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            session.save(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.close();
        return car.getId();
    }

    public void changCarStatus(int carId) {
        Session session = this.factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            Car car = session.get(Car.class, carId);
            car.setId(carId == 0 ? 1 : 0);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.close();
    }

    public void updateCar(Car car) {
        Session session = this.factory.openSession();
        Transaction transaction = null;
        try {
            session.getTransaction();
            session.update(car);
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void savePics(List<Pic> list) {
        Session session = this.factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            for (Pic p : list) {
                session.save(p);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.close();
    }
}
package ru.job4j.config.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.config.model.Item;

import java.util.List;

/**
 * Created on 11.01.18.
 * Connection to db via hibernate.
 * @author Wamdue
 * @version 1.0
 */
public class Connection {
    /**
     * Connection factory.
     */
    private SessionFactory factory;

    /**
     * Main constructor.
     */
    protected Connection() {
        this.connect();
    }

    /**
     * Creating factory.
     */
    private void connect() {
        this.factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    /**
     * Get all records from db.
     * @return list of records.
     */
    @SuppressWarnings("unchecked")
    public List<Item> getListOfTasks() {
        Session session = this.factory.openSession();
        List<Item> list = session.createQuery("from Item").list();
        session.close();
        return list;
    }

    /**
     * Get list of active tasks.
     * @return list of active tasks.
     */
    @SuppressWarnings("unchecked")
    public List<Item> getActiveTasks() {
        Session session = this.factory.openSession();
        List<Item> list = session.createQuery("from Item where status = 0").list();
        session.close();
        return list;
    }

    /**
     * Change task status.
     * @param id - id of task.
     */
    public void updateStatus(int id) {
        Transaction transaction = null;
        try (Session session = this.factory.openSession()) {
            session.beginTransaction();
            transaction = session.getTransaction();
            Item item = session.get(Item.class, id);
            item.setStatus(item.getStatus() == 0 ? 1 : 0);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * Add new task to db.
     * @param item - new task.
     * @return - id of task.
     */
    public int addTask(Item item) {
        Transaction transaction = null;
        try (Session session = this.factory.openSession()) {
            session.beginTransaction();
            transaction = session.getTransaction();
            session.save(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return item.getId();
    }
}

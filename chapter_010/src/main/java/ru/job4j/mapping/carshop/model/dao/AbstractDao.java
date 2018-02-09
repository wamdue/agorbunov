package ru.job4j.mapping.carshop.model.dao;

import org.hibernate.Session;
import ru.job4j.mapping.carshop.model.DB;

/**
 * Created on 29.01.18.
 * Abstract dao class.
 * @param <E> - class to work with.
 * @author Wamdue
 * @version 1.0
 */
public abstract class AbstractDao<E> implements DaoInt<E> {
    /**
     * Db connection.
     */
    private final DB db;

    /**
     * Main constructor.
     * @param db - db connection.
     */
    public AbstractDao(DB db) {
        this.db = db;
    }

    /**
     * Get db link.
     * @return db.
     */
    public DB getDb() {
        return db;
    }

    /**
     * Add new record to db.
     * @param e - data.
     */
    @Override
    public void create(E e) {
        try(Session session = this.getDb().getSession()) {
            session.beginTransaction();
            session.save(e);
            session.getTransaction().commit();
        } catch (Exception ex) {
            this.getDb().getFactory().getCurrentSession().getTransaction().rollback();
        }
    }

    /**
     * Delete record from db.
     * @param e - data.
     */
    @Override
    public void delete(E e) {
        try (Session session = this.getDb().getSession()) {
            session.beginTransaction();
            session.delete(e);
            session.getTransaction().commit();
        } catch (Exception ex) {
            this.getDb().getFactory().getCurrentSession().getTransaction().rollback();
        }
    }

    /**
     * Update record in db.
     * @param e - new record.
     */
    @Override
    public void update(E e) {
        try (Session session = this.getDb().getSession()) {
            session.beginTransaction();
            session.update(e);
            session.getTransaction().commit();
        } catch (Exception ex) {
            this.getDb().getFactory().getCurrentSession().getTransaction().rollback();
        }
    }
}

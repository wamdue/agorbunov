package ru.job4j.mapping.carshop.model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
        Session session = this.getDb().getSession();
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            session.save(e);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.close();
    }

    /**
     * Delete record from db.
     * @param e - data.
     */
    @Override
    public void delete(E e) {
        Session session = this.getDb().getSession();
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            session.delete(e);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.close();
    }

    /**
     * Update record in db.
     * @param e - new record.
     */
    @Override
    public void update(E e) {
        Session session = this.getDb().getSession();
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            session.update(e);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.close();
    }
}

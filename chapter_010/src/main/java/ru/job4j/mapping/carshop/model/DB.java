package ru.job4j.mapping.carshop.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created on 19.01.18.
 * DB connection utils.
 * @author Wamdue
 * @version 1.0
 */
public class DB {
    /**
     * Hibernate session factory.
     */
    private SessionFactory factory;

    /**
     * Main constructor.
     */
    DB() {
        this.init();
    }

    /**
     * Building session factory.
     */
    private void init() {
        this.factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    /**
     * Open session.
     * @return session.
     */
    public Session getSession() {
        return this.factory.openSession();
    }

}
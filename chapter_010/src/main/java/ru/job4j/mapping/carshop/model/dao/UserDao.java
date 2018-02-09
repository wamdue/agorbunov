package ru.job4j.mapping.carshop.model.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.mapping.carshop.entity.User;
import ru.job4j.mapping.carshop.model.DB;

import java.util.List;

/**
 * Created on 29.01.18.
 * User Dao.
 * @author Wamdue
 * @version 1.0
 */
@Component
public class UserDao extends AbstractDao<User> {
    /**
     * Main constructor.
     *
     * @param db - db connection.
     */
    @Autowired
    public UserDao(DB db) {
        super(db);
    }

    /**
     * Get user by id.
     * @param id - id.
     * @return - user.
     */
    @Override
    public User getById(int id) {
        Session session = this.getDb().getSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    /**
     * Get user list from db.
     * @return - list.
     */
    @Override
    public List<User> getList() {
        Session session = this.getDb().getSession();
        @SuppressWarnings("unchecked")
        List<User> list = session.createQuery("from User").list();
        session.close();
        return list;
    }

}

package ru.job4j.mapping.carshop.model.repository;

import org.hibernate.Session;
import ru.job4j.mapping.carshop.entity.User;
import ru.job4j.mapping.carshop.model.DB;
import ru.job4j.mapping.carshop.model.dao.UserDao;

import java.util.List;

/**
 * Created on 29.01.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public class UserRepository extends UserDao {

    /**
     * Main constructor.
     *
     * @param db - db connection.
     */
    public UserRepository(DB db) {
        super(db);
    }

    /**
     * Get user by name.
     * @param userName - user name.
     * @return - user id, or -1 if not found.
     */
    public User getUserByName(String userName) {
        Session session = this.getDb().getSession();
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

}

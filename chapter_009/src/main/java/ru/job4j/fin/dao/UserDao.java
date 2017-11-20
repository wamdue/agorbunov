package ru.job4j.fin.dao;

import ru.job4j.fin.controller.PSConnection;
import ru.job4j.fin.entity.User;

import java.util.List;

/**
 * Created on 20.11.17.
 *
 * @author Wamdue
 * @version 1.0
 */
public class UserDao implements EntityDao<User> {

    private PSConnection psConnection;

    public UserDao(PSConnection connection) {
        this.psConnection = connection;
    }

    @Override
    public int add(User user) {

        return 0;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean update(int id, User user) {
        return false;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}

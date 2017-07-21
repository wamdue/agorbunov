package ru.job4j.generics;

/**
 * Created on 20.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class UserStore implements Store<User> {
    /**
     * Storage of users.
     */
    private SimpleArray<User> users;

    /**
     * Main constructor
     * @param size - maximum store size;
     */
    public UserStore(int size) {
        users = new SimpleArray<>(size);
    }

    /**
     * Adding new user to store.
     * @param user - user to add.
     */
    @Override
    public void add(User user) {
        users.add(user);
    }

    /**
     *
     * @param old - old item.
     * @param updated - new item.
     */
    @Override
    public void update(User old, User updated) {
        if (users.getId(old) >= 0) {
            users.update(users.getId(old), updated);
        }
    }

    /**
     *
     * @param user - user to delete.
     */
    @Override
    public void delete(User user) {
        users.delete(user);
    }
}

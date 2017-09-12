package ru.job4j.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 11.09.17
 * Thread safe collection.
 * @author Wamdue
 * @version 1.0
 */
@ThreadSafe
public class UserStorage {
    /**
     * Lock object.
     */
    private final Object lock = new Object();

    @GuardedBy("lock")
    private Set<User> users = new HashSet<>();

    /**
     * Adding new user to collection.
     * @param user - user to add.
     */
    public void add(User user) {
        synchronized (lock) {
            users.add(user);
        }
    }

    /**
     * Remove from collection.
     * @param user - remove user from collection.
     */
    public void remove(User user) {
        synchronized (lock) {
            users.remove(user);
        }
    }

    /**
     *
     * @param user - user to update.
     * @return - true if user contains in collection, and he can be replaced,
     * otherwise false, without adding user.
     */
    public boolean udpate(User user) {
        synchronized (lock) {
            boolean result = false;
            if (users.contains(user)) {
                users.add(user);
                result = true;
            }
            return result;
        }
    }

    /**
     * Searching user by id.
     * @param id - id of the source user.
     * @return user if could be found, or false if not.
     */
    private User getUserById(int id) {

        synchronized (lock) {
            User user = null;
            for (User u : users) {
                if (u.getId() == id) {
                    user = u;
                    break;
                }
            }
            return user;
        }

    }

    /**
     * Transfering money from one user to another.
     * @param from - user who want to pay.
     * @param to - user who will receive.
     * @param amount - amount to transfer.
     * @return - true if user from have enough money, otherwise false.
     */
    public boolean tranfer (int from, int to, int amount) {
        synchronized (lock) {
            boolean result = false;
            User userFrom = getUserById(from);
            User userTo = getUserById(to);

            if (userFrom.getAmount() >= amount) {
                userFrom.setAmount(userFrom.getAmount() - amount);
                userTo.setAmount(userTo.getAmount() + amount);
                result = true;
            }
            return result;
        }
    }
}

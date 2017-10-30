package ru.job4j.monitor;

/**
 * Created on 11.09.17.
 * Simple user class.
 * @author Wamdue
 * @version 1.0
 */
public class User {
    /**
     * User id.
     */
    private int id;
    /**
     * Amount of money.
     */
    private int amount;

    /**
     * Main constructor.
     * @param id - user id.
     * @param amount - amount of money.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Get user id.
     * @return user id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get user amount of money.
     * @return user amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Set new amount of money.
     * @param amount - new amount.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * New equals method.
     * @param o - object to compare.
     * @return true if compares, or false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return id == user.id;
    }

    /**
     * Get new hashcode.
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return id;
    }
}

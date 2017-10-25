package ru.job4j.monitor;

/**
 * Created on 11.09.17
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

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

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

    @Override
    public int hashCode() {
        return id;
    }
}

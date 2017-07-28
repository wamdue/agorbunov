package ru.job4j.map;

import java.util.Calendar;

/**
 * Created on 28.07.17
 * User class.
 * @author Wamdue
 * @version 1.0
 */
public class User {
    /**
     * User name.
     */
    private String name;
    /**
     * Date of birthday.
     */
    private Calendar birthday;
    /**
     * Number of children.
     */
    private int children;

    /**
     * Empty constructor.
     */
    public User() {
    }

    /**
     * Main constructor.
     * @param name - user name.
     * @param birthday - birthday date.
     * @param children - number of children.
     */
    public User(String name, Calendar birthday, int children) {
        this.name = name;
        this.birthday = birthday;
        this.children = children;
    }

    /**
     *
     * @return user name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name - new user name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return date of birthday.
     */
    public Calendar getBirthday() {
        return birthday;
    }

    /**
     *
     * @param birthday set correct date of birthday.
     */
    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return current number of children.
     */
    public int getChildren() {
        return children;
    }

    /**
     *
     * @param children = new number of children.
     */
    public void setChildren(int children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (children != user.children) return false;
        if (!name.equals(user.name)) return false;
        return birthday.equals(user.birthday);
    }
}

package ru.job4j.sort;

/**
 * Created on 18.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class User implements Comparable<User> {
    /**
     * @param age - User age.
     */
    private int age;
    /**
     * @param name - user name
     */
    private String name;

    /**
     *
     * @param age - set user age.
     * @param name - set user name.
     */
    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    /**
     *
     * @return current age.
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @return current name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param o - object to compare.
     * @return - result of the comparing.
     */
    @Override
    public int compareTo(User o) {
        return this.age > o.age ? 1 : this.age < o.age ? -1 : 0;
    }
}

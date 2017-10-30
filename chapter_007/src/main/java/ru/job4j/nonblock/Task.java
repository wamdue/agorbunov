package ru.job4j.nonblock;

/**
 * Created on 19.09.17.
 * Dummy interface.
 * @author Wamdue
 * @version 1.0
 */
public interface Task {
    /**
     * Get name of the task.
     * @return name of the task.
     */
    String getName();

    /**
     * Set new name to the task.
     * @param name - new name.
     */
    void setName(String name);

    /**
     * Get age.
     * @return - age.
     */
    int getAge();

    /**
     * Setting new age.
     * @param age - new age.
     */
    void setAge(int age);
}

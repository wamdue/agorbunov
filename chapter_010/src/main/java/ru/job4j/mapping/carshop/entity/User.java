package ru.job4j.mapping.carshop.entity;

import java.util.List;

/**
 * Created on 19.01.18.
 * User information.
 * @author Wamdue
 * @version 1.0
 */
public class User {
    /**
     * User id.
     */
    private int id;
    /**
     * User name.
     */
    private String name;
    /**
     * List of cars in sale.
     */
    private List<Car> cars;

    /**
     * Get User id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new User id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get User name.
     * @return - name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new User name.
     * @param name - new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get list of cars in sale.
     * @return - list of cars.
     */
    public List<Car> getCars() {
        return cars;
    }

    /**
     * Set new list of cars.
     * @param cars - new list.
     */
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}

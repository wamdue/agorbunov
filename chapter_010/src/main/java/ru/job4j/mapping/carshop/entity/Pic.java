package ru.job4j.mapping.carshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Created on 23.01.18.
 * Path to pic.
 * @author Wamdue
 * @version 1.0
 */
public class Pic {
    /**
     * Id.
      */
    private int id;
    /**
     * Link to car.
     */
    @JsonBackReference
    private Car car;
    /**
     * Path to picture.
     */
    private byte[] path;

    /**
     * Get id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new car od.
     * @param id - car id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get car id.
     * @return car id.
     */
    public Car getCar() {
        return this.car;
    }

    /**
     * Set car id.
     * @param car - new value.
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Get picture path.
     * @return path.
     */
    public byte[] getPath() {
        return path;
    }

    /**
     * Set new path to picture.
     * @param path - path.
     */
    public void setPath(byte[] path) {
        this.path = path;
    }
}

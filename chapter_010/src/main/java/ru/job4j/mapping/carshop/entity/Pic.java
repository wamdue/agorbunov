package ru.job4j.mapping.carshop.entity;

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
    private int car_id;
    /**
     * Path to picture.
     */
    private String path;

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
    public int getCar_id() {
        return car_id;
    }

    /**
     * Set car id.
     * @param car_id - new value.
     */
    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    /**
     * Get picture path.
     * @return path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Set new path to picture.
     * @param path - path.
     */
    public void setPath(String path) {
        this.path = path;
    }
}

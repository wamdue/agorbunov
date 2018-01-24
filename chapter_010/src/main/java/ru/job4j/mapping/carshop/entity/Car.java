package ru.job4j.mapping.carshop.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created on 19.01.18.
 * Car information.
 * @author Wamdue
 * @version 1.0
 */
public class Car {
    /**
     * Car id.
     */
    private int id;
    /**
     * Car name.
     */
    private String name;
    /**
     * Car description.
     */
    private String description;
    /**
     * Engine type.
     */
    private Engine engine;
    /**
     * Gearbox type.
     */
    private Gearbox gearbox;
    /**
     * Car brand.
     */
    private Brand brand;
    /**
     * Car axle.
     */
    private Axle axle;
    /**
     * Body type.
     */
    private Body body;
    /**
     * Sale status.
     */
    private int status;
    /**
     * Car creation date.
     */
    private Date carCreated;
    /**
     * Car post date.
     */
    private Timestamp post;
    /**
     * Posted user.
     */
    private User user;
    /**
     * Car price.
     */
    private int price;
    /**
     * Car picture.
     */
    private List<Pic> pics;

    /**
     * Get car id.
     * @return - id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get car name.
     * @return car name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new car name.
     * @param name - new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get car description.
     * @return - description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set new car description.
     * @param description - new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get engine type.
     * @return engine type.
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Set new engine type.
     * @param engine - new engine type.
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * Get gearbox type.
     * @return - type.
     */
    public Gearbox getGearbox() {
        return gearbox;
    }

    /**
     * Set new gerabox type.
     * @param gearbox - new type.
     */
    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    /**
     * Get car brand.
     * @return brand.
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * Set new car brand.
     * @param brand - brand.
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * Get body type.
     * @return body.
     */
    public Body getBody() {
        return body;
    }

    /**
     * Set new body type.
     * @param body - new body.
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * Get car status - 0 sold, 1 - in sale.
     * @return - sale status.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Set new sale status.
     * @param status - new status.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Get car creation date.
     * @return - creation date.
     */
    public Date getCarCreated() {
        return carCreated;
    }

    /**
     * Set new car creation date.
     * @param carCreated - date.
     */
    public void setCarCreated(Date carCreated) {
        this.carCreated = carCreated;
    }

    /**
     * Get time of car posting.
     * @return date.
     */
    public Timestamp getPost() {
        return post;
    }

    /**
     * Set new time posting.
     * @param post - new date.
     */
    public void setPost(Timestamp post) {
        this.post = post;
    }

    /**
     * Get user.
     * @return - user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set new user.
     * @param user - new user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get car axle.
     * @return - axle.
     */
    public Axle getAxle() {
        return axle;
    }

    /**
     * Set new car axle.
     * @param axle new axle.
     */
    public void setAxle(Axle axle) {
        this.axle = axle;
    }

    /**
     * Get car price.
     * @return price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Set new car price.
     * @param price new car price.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Get path to car picture.
     * @return - path.
     */
    public List<Pic> getPics() {
        return this.pics;
    }

    /**
     * Set new path to car picture.
     * @param pics - new path.
     */
    public void setPics(List<Pic> pics) {
        this.pics = pics;
    }
}

package ru.job4j.lsp.food;

import java.util.Date;

/**
 * Created on 26.12.17.
 * Main food class.
 * @author Wamdue
 * @version 1.0
 */
public class Food {
    /**
     * Food name.
     */
    private String name;
    /**
     * Expire date.
     */
    private Date expireDate;
    /**
     * Production date.
     */
    private Date createDate;
    /**
     * Current price.
     */
    private double price;
    /**
     * Discount sum.
     */
    private double discount;

    /**
     * Get food name.
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set food name.
     * @param name - new food name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get expire date.
     * @return - expire date.
     */
    public Date getExpireDate() {
        return this.expireDate;
    }

    /**
     * Set new expire date.
     * @param expireDate - new expire date.
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Get create date.
     * @return - createDate.
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * Set creatDate.
     * @param createDate - new create date.
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Get food price.
     * @return - price.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Set food price.
     * @param price - food price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get food discount.
     * @return discount.
     */
    public double getDiscount() {
        return this.discount;
    }

    /**
     * Set new discount.
     * @param discount - new discount.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }
}

package ru.job4j.lsp.food;

import java.util.Date;

/**
 * Created on 09.01.18.
 * REcycled food.
 * @author Wamdue
 * @version 1.0
 */
public class RecycleFood extends Food {
    /**
     * Link to main food object.
     */
    private Food food;
    /**
     * Recycle flag.
     */
    private boolean isRecycled;

    /**
     * Main constructor.
     * @param food - link to food.
     * @param isRecycled - recycled food or not.
     */
    public RecycleFood(Food food, boolean isRecycled) {
        this.food = food;
        this.isRecycled = isRecycled;
    }

    /**
     * Is food recycled.
     * @return - yes true or false.
     */
    public boolean isRecycled() {
        return isRecycled;
    }

    /**
     * Get food name.
     * @return name.
     */
    public String getName() {
        return this.food.getName();
    }

    /**
     * Set food name.
     * @param name - new food name.
     */
    public void setName(String name) {
        this.food.setName(name);
    }

    /**
     * Get expire date.
     * @return - expire date.
     */
    public Date getExpireDate() {
        return this.food.getExpireDate();
    }

    /**
     * Set new expire date.
     * @param expireDate - new expire date.
     */
    public void setExpireDate(Date expireDate) {
        this.food.setExpireDate(expireDate);
    }

    /**
     * Get create date.
     * @return - createDate.
     */
    public Date getCreateDate() {
        return this.food.getCreateDate();
    }

    /**
     * Set creatDate.
     * @param createDate - new create date.
     */
    public void setCreateDate(Date createDate) {
        this.food.setCreateDate(createDate);
    }

    /**
     * Get food price.
     * @return - price.
     */
    public double getPrice() {
        return this.food.getPrice();
    }

    /**
     * Set food price.
     * @param price - food price.
     */
    public void setPrice(double price) {
        this.food.setPrice(price);
    }

    /**
     * Get food discount.
     * @return discount.
     */
    public double getDiscount() {
        return this.food.getDiscount();
    }

    /**
     * Set new discount.
     * @param discount - new discount.
     */
    public void setDiscount(double discount) {
        this.food.setDiscount(discount);
    }
}

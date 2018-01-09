package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.Food;

/**
 * Created on 09.01.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public class SShop extends SStorage {
    /**
     * Shop reference.
     */
    private final Shop shop;
    /**
     * Main constructor.
     * @param size - storage size.
     * @param shop - shop reference.
     */
    public SShop(Shop shop, int size) {
        super(size);
        this.shop = shop;
    }

    /**
     * Add food to storage.
     * @param food - food to add.
     * @return result.
     */
    public boolean addFood(Food food) {
        boolean condition = false;
        if (!isFull()) {
            condition = this.shop.addFood(food);
        }
        return condition;
    }
}

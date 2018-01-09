package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.RecycleFood;

/**
 * Created on 09.01.18.
 * Realisation recycled food shop storage.
 * @author Wamdue
 * @version 1.0
 */
public class RShop extends RStorage {
    /**
     * Shop reference.
     */
    private final Shop shop;

    /**
     * Main constructor.
     * @param shop - shop reference.
     */
    public RShop(Shop shop) {
        this.shop = shop;
    }

    /**
     * Add recycled food to storage.
     * @param food - food to add.
     * @return - result.
     */
    @Override
    public boolean addFood(RecycleFood food) {
        return this.shop.addFood(food);
    }
}

package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.CFood;

/**
 * Created on 09.01.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public class TShop extends TStorage {
    /**
     * Shop reference.
     */
    private final Shop shop;

    /**
     * Main constructor.
     * @param shop - shop reference.
     * @param temp - storage temp.
     */
    public TShop(Shop shop, double temp) {
        super(temp);
        this.shop = shop;
    }

    /**
     * Add food to shop.
     * @param food - food to store.
     * @return result.
     */
    @Override
    public boolean addFood(CFood food) {
        return this.shop.addFood(food);
    }
}

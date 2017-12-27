package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.Food;

/**
 * Created on 26.12.17.
 * Warehouse storage.
 * @author Wamdue
 * @version 1.0
 */
public class Warehouse extends Storage {
    /**
     * Add food only if food can store long enough.
     * @param food - food to add.
     * @return - true if food stored.
     */
    @Override
    public boolean addFood(Food food) {
        boolean condition = false;
        if (food.getExpireDate().getTime() > System.currentTimeMillis()) {
            long all = food.getExpireDate().getTime() - food.getCreateDate().getTime();
            long curr = food.getExpireDate().getTime() - System.currentTimeMillis();
            double result = (1 - (double) curr / all) * 100;
            if (result < 25) {
                condition = super.addFood(food);
            }
        }

        return condition;
    }
}

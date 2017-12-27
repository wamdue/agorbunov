package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.Food;

/**
 * Created on 26.12.17.
 * Trash.
 * @author Wamdue
 * @version 1.0
 */
public class Trash extends Storage {
    /**
     * Add food only if expire date less then current date.
     * @param food - food to add.
     * @return - true if food stored.
     */
    @Override
    public boolean addFood(Food food) {
        boolean condition = false;
        if (food.getExpireDate().getTime() > System.currentTimeMillis()) {
            condition = super.addFood(food);
        }
        return condition;
    }
}

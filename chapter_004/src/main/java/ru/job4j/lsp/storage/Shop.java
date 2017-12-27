package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.Food;

/**
 * Created on 26.12.17.
 * Shop storage.
 * @author Wamdue
 * @version 1.0
 */
public class Shop extends Storage {

    /**
     * Add to show food, if food is not so fresh, to store.
     * @param food - food to add.
     * @return true if stored.
     */
    @Override
    public boolean addFood(Food food) {
        boolean condition = false;
        if (food.getExpireDate().getTime() > System.currentTimeMillis()) {
            long all = food.getExpireDate().getTime() - food.getCreateDate().getTime();
            long curr = food.getExpireDate().getTime() - System.currentTimeMillis();
            double result = (1 - (double) curr / all) * 100;
            if (result >= 25 && result < 75) {
                condition = super.addFood(food);
            } else if (result >= 75 && result < 100) {
                food.setDiscount(20.0);
                condition = super.addFood(food);
            }
        }
        return condition;
    }
}

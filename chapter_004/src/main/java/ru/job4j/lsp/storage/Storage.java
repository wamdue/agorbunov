package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 26.12.17.
 * Main storage class.
 * @author Wamdue
 * @version 1.0
 */
public class Storage {
    /**
     * Storage list.
     */
    private List<Food> storage = new ArrayList<>();

    /**
     * Add new food to storage.
     * @param food - food to add.
     * @return - true if food stored.
     */
    public boolean addFood(Food food) {

        this.storage.add(food);
        return true;
    }

    /**
     * Remove food from storage.
     * @param food - food to remove.
     */
    public void removeFood(Food food) {
        this.storage.remove(food);
    }

    /**
     * Link to storage.
     * @return - storage.
     */
    public List<Food> getStorage() {
        return this.storage;
    }
}

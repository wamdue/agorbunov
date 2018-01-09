package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.RecycleFood;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 09.01.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public class RStorage {
    /**
     * Storage list.
     */
    private List<RecycleFood> storage = new ArrayList<>();

    /**
     * Add new food to storage.
     * @param food - food to add.
     * @return - true if food stored.
     */
    public boolean addFood(RecycleFood food) {
        this.storage.add(food);
        return true;
    }

    /**
     * Remove food from storage.
     * @param food - food to remove.
     */
    public void removeFood(RecycleFood food) {
        this.storage.remove(food);
    }

    /**
     * Link to storage.
     * @return - storage.
     */
    public List<RecycleFood> getStorage() {
        return this.storage;
    }
}

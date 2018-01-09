package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.Food;

/**
 * Created on 09.01.18.
 * Limited storage size.
 * @author Wamdue
 * @version 1.0
 */
public class SStorage extends Storage {
    /**
     * Storage size.
     */
    private final int size;

    /**
     * Main constructor.
     * @param size - storage size.
     */
    public SStorage(int size) {
        this.size = size;
    }

    /**
     * If have free space, then add to storage.
     * @param food - food to add.
     * @return result.
     */
    @Override
    public boolean addFood(Food food) {
        return super.addFood(food);
    }

    /**
     * Checks if storage if full.
     * @return result.
     */
    public boolean isFull() {
        return size <= super.getStorage().size();
    }
}

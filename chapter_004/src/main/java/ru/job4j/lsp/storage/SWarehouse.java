package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.Food;

/**
 * Created on 09.01.18.
 * Limited size storage.
 * @author Wamdue
 * @version 1.0
 */
public class SWarehouse extends SStorage {
    /**
     * Warehouse reference.
     */
    private final Warehouse warehouse;
    /**
     * Main constructor.
     * @param size - storage size.
     * @param warehouse - warehouse reference.
     */
    public SWarehouse(int size, Warehouse warehouse) {
        super(size);
        this.warehouse = warehouse;
    }

    /**
     * Add food to storage.
     * @param food - food to add.
     * @return result.
     */
    @Override
    public boolean addFood(Food food) {
        boolean condition = false;
        if (!isFull()) {
            condition = this.warehouse.addFood(food);
        }
        return condition;
    }
}

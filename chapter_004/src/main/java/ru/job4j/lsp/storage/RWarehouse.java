package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.RecycleFood;

/**
 * Created on 09.01.18.
 * Realisation recycle food storage.
 * @author Wamdue
 * @version 1.0
 */
public class RWarehouse extends RStorage {
    /**
     * Warehouse reference.
     */
    private final Warehouse warehouse;

    /**
     * Main constructor.
     * @param warehouse - warehouse reference.
     */
    public RWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Add food to warehouse.
     * @param food - food to add.
     * @return result.
     */
    @Override
    public boolean addFood(RecycleFood food) {
        return this.warehouse.addFood(food);
    }
}

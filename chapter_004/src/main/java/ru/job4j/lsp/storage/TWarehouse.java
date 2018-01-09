package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.CFood;

/**
 * Created on 09.01.18.
 * Warehouse storage with temp.
 * @author Wamdue
 * @version 1.0
 */
public class TWarehouse extends TStorage {
    /**
     * Warehouse reference.
     */
    private final Warehouse warehouse;

    /**
     * Main constructor.
     * @param warehouse - warehouse reference.
     * @param temp - warehouse temp storage.
     */
    public TWarehouse(Warehouse warehouse, double temp) {
        super(temp);
        this.warehouse = warehouse;
    }

    /**
     * Add food to storage.
     * @param food - food to store.
     * @return result.
     */
    @Override
    public boolean addFood(CFood food) {
        boolean condition = false;
        if (food.getTemp() == this.getTemp()) {
            condition = this.warehouse.addFood(food);
        }
        return condition;
    }
}


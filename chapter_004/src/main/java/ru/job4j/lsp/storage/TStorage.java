package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.CFood;
/**
 * Created on 09.01.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public class TStorage extends Storage {
    /**
     * Storage temp.
     */
    private double temp;

    /**
     * Main constructor.
     * @param temp - storage temp.
     */
    public TStorage(double temp) {
        this.temp = temp;
    }

    /**
     * Add food to storage.
     * @param food - food to store.
     * @return result.
     */
    public boolean addFood(CFood food) {
        return super.addFood(food);
    }

    /**
     * Get storing temp.
     * @return temp value.
     */
    public double getTemp() {
        return temp;
    }
}

package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.CFood;

/**
 * Created on 09.01.18.
 * Trash for low temp food.
 * @author Wamdue
 * @version 1.0
 */
public class TTrash extends TStorage {
    /**
     * Trash reference.
     */
    private final Trash trash;

    /**
     * Main constructor.
     * @param temp - storage temp, not in use.
     * @param trash - trash reference.
     */
    public TTrash(double temp, Trash trash) {
        super(temp);
        this.trash = trash;
    }

    /**
     * Add food to trash.
     * @param food - food to store.
     * @return result.
     */
    @Override
    public boolean addFood(CFood food) {
        return this.trash.addFood(food);
    }
}

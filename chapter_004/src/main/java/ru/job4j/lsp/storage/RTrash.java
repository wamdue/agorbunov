package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.RecycleFood;

/**
 * Created on 09.01.18.
 * Not recycled trash.
 * @author Wamdue
 * @version 1.0
 */
public class RTrash extends RStorage {
    /**
     * Trash reference.
     */
    private final Trash trash;

    /**
     * Main constructor.
     * @param trash - trash reference.
     */
    public RTrash(Trash trash) {
        this.trash = trash;
    }

    /**
     * Add food to trash, if not recycled.
     * @param food - food to add.
     * @return result.
     */
    @Override
    public boolean addFood(RecycleFood food) {
        boolean result = false;
        if (!food.isRecycled()) {
            result = this.trash.addFood(food);
        }
        return result;
    }
}

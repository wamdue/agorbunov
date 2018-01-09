package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.RecycleFood;


/**
 * Created on 08.01.18.
 * Recycle trash.
 * @author Wamdue
 * @version 1.0
 */
public class RecycleTrash extends RStorage {
    /**
     * Link to trash object.
     */
    private final Trash trash;

    /**
     * Main constructor.
     * @param trash - link to trash object..
     */
    public RecycleTrash(Trash trash) {
        this.trash = trash;
    }

    /**
     * Add food to recycle storage if only in supports recycling.
     * @param food - food to add.
     * @return - result.
     */
    @Override
    public boolean addFood(RecycleFood food) {
        boolean result = false;
        if (food.isRecycled()) {
            result = this.trash.addFood(food);
        }
        return result;
    }
}

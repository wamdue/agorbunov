package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.Food;

/**
 * Created on 09.01.18.
 * Trash storage with limited capacity.
 * @author Wamdue
 * @version 1.0
 */
public class STrash extends SStorage {
    /**
     * Trash reference.
     */
    private final Trash trash;

    /**
     * Main constructor.
     * @param size - storage size.
     * @param trash - trash reference.
     */
    public STrash(int size, Trash trash) {
        super(size);
        this.trash = trash;
    }

    /**
     * Add foor to storage.
     * @param food - food to add.
     * @return result.
     */
    @Override
    public boolean addFood(Food food) {
        boolean condition = false;
        if (!super.isFull()) {
            condition = this.trash.addFood(food);
        }
        return condition;
    }
}

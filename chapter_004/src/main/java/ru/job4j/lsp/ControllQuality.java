package ru.job4j.lsp;

import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.storage.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 27.12.17.
 * Fills storages.
 * @author Wamdue
 * @version 1.0
 */
public class ControllQuality {
    /**
     * List of storages.
     */
    private List<Storage> list = new ArrayList<>();

    /**
     * Add new storage to list.
     * @param storage - storage to add.
     */
    public void addStorage(Storage storage) {
        this.list.add(storage);
    }

    /**
     * Add food to storage.
     * @param food - food to add.
     */
    public void add(Food food) {
        for (Storage storage : this.list) {
            if (storage.addFood(food)) {
                break;
            }
        }
    }
}

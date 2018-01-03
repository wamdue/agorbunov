package ru.job4j.lsp;

import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.storage.Storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 27.12.17.
 * Fills storages.
 * @author Wamdue
 * @version 1.0
 */
public class ControlQuality {
    /**
     * List of storages.
     */
    private final List<Storage> list = new ArrayList<>();

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
     * @return - true if have available storage, otherwise false.
     */
    public boolean  add(Food food) {
        boolean result = false;
        for (Storage storage : this.list) {
            if (storage.addFood(food)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Get storage list.
     * @return - storage list.
     */
    public List<Storage> getList() {
        return Collections.unmodifiableList(list);
    }
}

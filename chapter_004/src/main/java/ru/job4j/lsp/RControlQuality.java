package ru.job4j.lsp;

import ru.job4j.lsp.food.RecycleFood;
import ru.job4j.lsp.storage.RStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 09.01.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public class RControlQuality {
    /**
     * List of storages.
     */
    private final List<RStorage> list = new ArrayList<>();

    /**
     * Add new storage to list.
     * @param storage - storage to add.
     */
    public void addStorage(RStorage storage) {
        this.list.add(storage);
    }

    /**
     * Add food to storage.
     * @param food - food to add.
     * @return - true if have available storage, otherwise false.
     */
    public boolean  add(RecycleFood food) {
        boolean result = false;
        for (RStorage storage : this.list) {
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
    public List<RStorage> getList() {
        return Collections.unmodifiableList(list);
    }

}

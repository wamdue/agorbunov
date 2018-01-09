package ru.job4j.lsp;

import ru.job4j.lsp.food.RecycleFood;
import ru.job4j.lsp.storage.TStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 09.01.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public class TControlQuality {
    /**
     * List of storages.
     */
    private final List<TStorage> list = new ArrayList<>();

    /**
     * Add new storage to list.
     * @param storage - storage to add.
     */
    public void addStorage(TStorage storage) {
        this.list.add(storage);
    }

    /**
     * Add food to storage.
     * @param food - food to add.
     * @return - true if have available storage, otherwise false.
     */
    public boolean  add(RecycleFood food) {
        boolean result = false;
        for (TStorage storage : this.list) {
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
    public List<TStorage> getList() {
        return Collections.unmodifiableList(list);
    }


}

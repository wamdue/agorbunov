package ru.job4j.generics;

/**
 * Created on 27.07.17.
 * Abstract store view.
 * @author Wamdue
 * @version 1.0
 */
public abstract class AbstractStore implements Store<Base> {
    /**
     * Base storage.
     */
    private  SimpleArray<Base> simpleArray;

    /**
     * Main constructor.
     * @param size - size of store.
     */
    public AbstractStore(int size) {
        simpleArray = new SimpleArray<>(size);
    }

    /**
     * Add new item to store.
     * @param base - new item.
     */
    @Override
    public void add(Base base) {
        simpleArray.add(base);
    }

    /**
     * Update item.
     * @param old - old item.
     * @param updated - new item.
     */
    @Override
    public void update(Base old, Base updated) {
        if (simpleArray.getId(old) >= 0) {
            simpleArray.update(simpleArray.getId(old), updated);
        }
    }

    /**
     * Delete item from store.
     * @param base - item to delte.
     */
    @Override
    public void delete(Base base) {
        simpleArray.delete(base);
    }

    /**
     * Get base storage.
     * @return storage.
     */
    public SimpleArray<Base> getSimpleArray() {
        return simpleArray;
    }

    /**
     * Set new storage.
     * @param simpleArray - new Storage.
     */
    public void setSimpleArray(SimpleArray<Base> simpleArray) {
        this.simpleArray = simpleArray;
    }
}

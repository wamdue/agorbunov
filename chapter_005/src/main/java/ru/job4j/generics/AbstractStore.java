package ru.job4j.generics;

/**
 * Created on 27.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public abstract class AbstractStore implements Store<Base> {
    protected SimpleArray<Base> simpleArray;

    public AbstractStore(int size) {
        simpleArray = new SimpleArray<>(size);
    }

    @Override
    public void add(Base base) {
        simpleArray.add(base);
    }

    @Override
    public void update(Base old, Base updated) {
        if (simpleArray.getId(old) >= 0) {
            simpleArray.update(simpleArray.getId(old), updated);
        }
    }

    @Override
    public void delete(Base base) {
        simpleArray.delete(base);
    }
}

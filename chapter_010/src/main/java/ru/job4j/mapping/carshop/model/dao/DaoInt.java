package ru.job4j.mapping.carshop.model.dao;

import java.util.List;

/**
 * Created on 29.01.18.
 * Base dao interface.
 * @author Wamdue
 * @version 1.0
 * @param <E> - class to work with.
 */
public interface DaoInt<E> {
    /**
     * Get record by id.
     * @param id - id.
     * @return record.
     */
    E getById(int id);
    /**
     * Create record.
     * @param e - data.
     */
    void create(E e);

    /**
     * Delete record.
     * @param e - data.
     */
    void delete(E e);

    /**
     * Update record.
     * @param e - new record.
     */
    void update(E e);

    /**
     * Get list of records.
     * @return - list.
     */
    List<E> getList();
}

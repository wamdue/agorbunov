package ru.job4j.fin.dao;

import java.util.List;

/**
 * Created on 20.11.17.
 * Interface for work with user.
 * @author Wamdue
 * @version 1.0
 * @param <E> - class storage.
 */
public interface EntityDao<E> {
    /**
     * Add new entity to db.
     * @param e - entity.
     * @return 1 if successful, 0 if not.
     */
    int add(E e);

    /**
     * Delete entity from db.
     * @param e - entity to delete.
     * @return - true if successful, or false if not.
     */
    boolean delete(E e);

    /**
     * Update entity in db with current id.
     * @param id - entity id to update.
     * @param e - updated entity.
     * @return true if successful, or false if not.
     */
    boolean update(int id, E e);

    /**
     * Get entity by id.
     * @param id - id to search.
     * @return - entity or null if not found.
     */
    E findById(int id);

    /**
     * Get all entities from db.
     * @return - list of entities.
     */
    List<E> getAll();
}

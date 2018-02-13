package ru.job4j.mapping.carshop.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mapping.carshop.entity.Pic;

/**
 * Created on 13.02.18.
 * Pics repository.
 * @author Wamdue
 * @version 1.0
 */
public interface Pics extends CrudRepository<Pic, Integer> {
}

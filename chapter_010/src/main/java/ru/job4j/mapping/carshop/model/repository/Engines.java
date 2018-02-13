package ru.job4j.mapping.carshop.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mapping.carshop.entity.Engine;

/**
 * Created on 13.02.18.
 * Engines repository.
 * @author Wamdue
 * @version 1.0
 */
public interface Engines extends CrudRepository<Engine, Integer> {
}

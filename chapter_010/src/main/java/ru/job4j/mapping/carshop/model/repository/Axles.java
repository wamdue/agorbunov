package ru.job4j.mapping.carshop.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mapping.carshop.entity.Axle;

/**
 * Created on 13.02.18.
 * Axles repository.
 * @author Wamdue
 * @version 1.0
 */
public interface Axles extends CrudRepository<Axle, Integer> {
}

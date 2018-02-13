package ru.job4j.mapping.carshop.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mapping.carshop.entity.Body;

/**
 * Created on 13.02.18.
 * Bodies repository.
 * @author Wamdue
 * @version 1.0
 */
public interface Bodies extends CrudRepository<Body, Integer> {
}

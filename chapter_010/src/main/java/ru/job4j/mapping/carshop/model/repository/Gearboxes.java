package ru.job4j.mapping.carshop.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mapping.carshop.entity.Gearbox;

/**
 * Created on 13.02.18.
 * Gearboxes repository.
 * @author Wamdue
 * @version 1.0
 */
public interface Gearboxes extends CrudRepository<Gearbox, Integer> {
}

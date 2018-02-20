package ru.job4j.mapping.carshop.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mapping.carshop.entity.Authority;

/**
 * Created on 20.02.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public interface Authorities extends CrudRepository<Authority, Integer> {
}

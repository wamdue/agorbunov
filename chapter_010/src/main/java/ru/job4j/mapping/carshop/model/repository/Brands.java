package ru.job4j.mapping.carshop.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mapping.carshop.entity.Brand;

/**
 * Created on 13.02.18.
 * Brands repository.
 * @author Wamdue
 * @version 1.0
 */
public interface Brands extends CrudRepository<Brand, Integer> {
}

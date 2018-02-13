package ru.job4j.mapping.carshop.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mapping.carshop.entity.Car;

/**
 * Created on 13.02.18.
 * Cars repository.
 * @author Wamdue
 * @version 1.0
 */
public interface Cars extends CrudRepository<Car, Integer> {
}

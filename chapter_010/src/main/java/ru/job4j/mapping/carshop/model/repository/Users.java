package ru.job4j.mapping.carshop.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mapping.carshop.entity.User;

/**
 * Created on 13.02.18.
 * Users repository.
 * @author Wamdue
 * @version 1.0
 */
public interface Users extends CrudRepository<User, Integer> {
    /**
     * Get user by name.
     * @param name - name.
     * @return - user.
     */
    User findByName(String name);
}

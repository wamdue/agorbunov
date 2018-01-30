package ru.job4j.mapping.carshop.model.repository;

import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.model.DB;
import ru.job4j.mapping.carshop.model.dao.CarDao;

/**
 * Created on 29.01.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public class CarRepository extends CarDao {
    /**
     * Main constructor.
     *
     * @param db - db connection.
     */
    public CarRepository(DB db) {
        super(db);
    }

    /**
     * Changing car status.
     * @param carId - car id to change status.
     */
    public void changCarStatus(int carId) {
        Car car = this.getById(carId);
        car.setStatus(car.getStatus() == 0 ? 1 : 0);
        this.update(car);
    }

}

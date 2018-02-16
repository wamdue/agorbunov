package ru.job4j.mapping.carshop.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.mapping.carshop.entity.Car;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 13.02.18.
 * Cars repository.
 * @author Wamdue
 * @version 1.0
 */
public interface Cars extends CrudRepository<Car, Integer> {
    /**
     * Change car sold status.
     * @param id - car id.
     */
    default void changeStatus(int id) {
        Car car = this.findById(id).get();
        car.setStatus(car.getStatus() == 0 ? 1 : 0);
        this.save(car);
    }

    /**
     * Apply filter by fields.
     * @param brand - brand id, if > 0 then apply/
     * @param pic if > 0 then show only with pics.
     * @param date if > 0 then show only fresh records.
     * @return - filtered list.
     */
    @SuppressWarnings("unchecked")
    default List<Car> getByFilter(int brand, int pic, int date) {
        List<Car> list = (List<Car>) this.findAll();
        if (brand > 0) {
            list = list.stream().filter(x -> x.getBrand().getId() == brand).collect(Collectors.toList());
        }
        if (date > 0) {
            LocalDate local = LocalDate.now();
            Timestamp timestamp = new Timestamp(local.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            list = list.stream().filter(x -> x.getPost().getTime() >= timestamp.getTime()).collect(Collectors.toList());
        }
        if (pic > 0) {
            list = list.stream().filter(c -> c.getPics().size() > 0).collect(Collectors.toList());
        }
        return list;
    }

}

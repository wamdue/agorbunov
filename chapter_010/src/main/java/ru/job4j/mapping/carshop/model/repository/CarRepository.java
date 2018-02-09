package ru.job4j.mapping.carshop.model.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.model.DB;
import ru.job4j.mapping.carshop.model.dao.CarDao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 29.01.18.
 * Extending car dao class.
 * @author Wamdue
 * @version 1.0
 */
@Component
public class CarRepository extends CarDao {
    /**
     * Temp queue.
     */
    private static final String QUEUE = "from Car where 1 = 1 ";
    /**
     * Main constructor.
     *
     * @param db - db connection.
     */
    @Autowired
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

    /**
     * Apply filter by fields.
     * @param brand - brand id, if > 0 then apply/
     * @param pic if > 0 then show only with pics.
     * @param date if > 0 then show only fresh records.
     * @return - filtered list.
     */
    @SuppressWarnings("unchecked")
    public List<Car> getByFilter(int brand, int pic, int date) {
        StringBuilder sb = new StringBuilder();
        sb.append(QUEUE);
        List<Car> list;
        try (Session session = this.getDb().getSession()) {
            if (brand > 0) {
                sb.append("and brand_id = :brand ");
            }
            if (date > 0) {
                sb.append("and post >= :time");
            }
            Query query = session.createQuery(sb.toString());
            if (brand > 0) {
                query.setParameter("brand", brand);
            }
            if (date > 0) {
                LocalDate local = LocalDate.now();
                Timestamp timestamp = new Timestamp(local.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                query.setParameter("time", timestamp);
            }
            list = query.list();
            if (pic > 0) {
                list = list.stream().filter(c -> c.getPics().size() > 0).collect(Collectors.toList());
            }
        }
        return list;
    }

}

package ru.job4j.mapping.carshop.model.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.mapping.carshop.entity.Axle;
import ru.job4j.mapping.carshop.entity.Body;
import ru.job4j.mapping.carshop.entity.Brand;
import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.entity.Engine;
import ru.job4j.mapping.carshop.entity.Gearbox;
import ru.job4j.mapping.carshop.entity.Pic;
import ru.job4j.mapping.carshop.entity.User;
import ru.job4j.mapping.carshop.model.Connect;
import ru.job4j.mapping.carshop.model.DB;
import ru.job4j.mapping.carshop.model.repository.Axles;
import ru.job4j.mapping.carshop.model.repository.Bodies;
import ru.job4j.mapping.carshop.model.repository.Brands;
import ru.job4j.mapping.carshop.model.repository.Cars;
import ru.job4j.mapping.carshop.model.repository.Engines;
import ru.job4j.mapping.carshop.model.repository.Gearboxes;
import ru.job4j.mapping.carshop.model.repository.Users;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 01.02.18.
 * Testing carshop queries.
 * @author Wamdue
 * @version 1.0
 */
public class CarDaoTest {
    /**
     * Db connection.
     */
    private final DB db = Connect.INSTANCE.getConnection();
    /**
     * Link to car repository.
     */
    private final Cars carDao;
    /**
     * Link to body list.
     */
    private final Bodies bodyDao;
    /**
     * Link to axle list.
     */
    private final Axles axleDao;
    /**
     * Link to gearbox list.
     */
    private final Gearboxes gearboxDao;
    /**
     * Link to engine list.
     */
    private final Engines engineDao;
    /**
     * Link to brand list.
     */
    private final Brands brandDao;
    /**
     * Link to user list.
     */
    private final Users userDao;

    /**
     * Main constructor.
     * @param carDao - car repository.
     * @param bodyDao - body repository.
     * @param axleDao - axle repository.
     * @param gearboxDao - gearbox repository.
     * @param engineDao - engine repository.
     * @param brandDao - brand repository.
     * @param userDao - user repository.
     */
    @Autowired
    public CarDaoTest(Cars carDao, Bodies bodyDao, Axles axleDao, Gearboxes gearboxDao, Engines engineDao, Brands brandDao, Users userDao) {
        this.carDao = carDao;
        this.bodyDao = bodyDao;
        this.axleDao = axleDao;
        this.gearboxDao = gearboxDao;
        this.engineDao = engineDao;
        this.brandDao = brandDao;
        this.userDao = userDao;
    }

    /**
     * Fill tables with temp data.
     */
    @Before
    public void fillTables() {
        Body body = new Body();
        body.setName("1");
        this.bodyDao.save(body);

        Engine engine = new Engine();
        engine.setName("1");
        this.engineDao.save(engine);

        Axle axle = new Axle();
        axle.setName("1");
        this.axleDao.save(axle);

        Brand brand = new Brand();
        brand.setName("1");
        this.brandDao.save(brand);

        Gearbox gearbox = new Gearbox();
        gearbox.setName("1");
        this.gearboxDao.save(gearbox);

        User user = new User();
        user.setName("1");
        this.userDao.save(user);


    }

    /**
     * Creating new car record.
     * Expect: can find it in db by id.
     */
    @Test
    public void createTest() {
        Car car = this.createCar("first");
        this.carDao.save(car);
        assertThat(car.getName(), is(carDao.findById(car.getId()).get().getName()));
    }

    /**
     * Updating record in db.
     * Expect: i can see changes on record.
     */
    @Test
    public void updateTest() {
        Car car = this.createCar("first");
        this.carDao.save(car);
        String expect = "second";
        car.setName("second");
        this.carDao.save(car);
        assertThat(expect, is(this.carDao.findById(car.getId()).get().getName()));
    }

    /**
     * Delete record from db.
     * Expect: record cannot be found in db.
     */
    @Test
    public void deleteTest() {
        Car car = this.createCar("delete");
        this.carDao.save(car);
        this.carDao.delete(car);
        boolean expect = true;
        List<Car> carList = (List<Car>) this.carDao.findAll();
        assertThat(expect, is(!carList.contains(car)));
    }

    /**
     * Add new record to db.
     * Expect:to find record in list of records.
     */
    @Test
    public void getListTest() {
        Car car = this.createCar("third");
        this.carDao.save(car);

        boolean expect = true;
        List<Car> carList = (List<Car>) this.carDao.findAll();
        assertThat(expect, is(carList.contains(car)));
    }

    /**
     * Changing car record status.
     * Expect: see changes in status.
     */
    @Test
    public void changeCarStatusTest() {
        Car car = this.createCar("status");
        this.carDao.save(car);
        int oldStatus = car.getStatus();
        this.carDao.changeStatus(car.getId());
        boolean expect = true;
        assertThat(expect, is(this.carDao.findById(car.getId()).get().getStatus() != oldStatus));
    }

    /**
     * Testing filter of new cars.
     * Add pic to a new car, and to a car posted yesterday.
     * Expect: cars posted yesterday, without pics, will not be in list.
     */
    @Test
    public void filterTest() {
        Car one = this.createCar("first");
        Car two = this.createCar("second");
        Car three = this.createCar("third");
        LocalDateTime date = LocalDateTime.now();
        two.setPost(new Timestamp(date.minusDays(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
        Pic pic = new Pic();
        pic.setPath("".getBytes());
        two.addPic(pic);
        three.addPic(pic);
        this.carDao.save(one);
        this.carDao.save(two);
        this.carDao.save(three);
        List<Car> cars = this.carDao.getByFilter(1, 1, 1);
        int expect = 1;
        assertThat(expect, is(cars.size()));
    }

    /**
     * Creating new car to insert into db.
     * @param name - name of car.
     * @return - new car.
     */
    private Car createCar(String name) {
        Car car = new Car();
        car.setName(name);
        car.setUser(this.userDao.findById(1).get());
        car.setPost(new Timestamp(System.currentTimeMillis()));
        car.setBrand(this.brandDao.findById(1).get());
        car.setGearbox(this.gearboxDao.findById(1).get());
        car.setEngine(this.engineDao.findById(1).get());
        car.setAxle(this.axleDao.findById(1).get());
        car.setBody(this.bodyDao.findById(1).get());
        return car;
    }
}
package ru.job4j.mapping.carshop.model.dao;

import org.junit.Before;
import org.junit.Test;
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
import ru.job4j.mapping.carshop.model.repository.CarRepository;

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
    private final CarRepository carDao = new CarRepository(db);
    /**
     * Link to body list.
     */
    private final BodyDao bodyDao = new BodyDao(db);
    /**
     * Link to axle list.
     */
    private final AxleDao axleDao = new AxleDao(db);
    /**
     * Link to gearbox list.
     */
    private final GearboxDao gearboxDao = new GearboxDao(db);
    /**
     * Link to engine list.
     */
    private final EngineDao engineDao = new EngineDao(db);
    /**
     * Link to brand list.
     */
    private final BrandDao brandDao = new BrandDao(db);
    /**
     * Link to user list.
     */
    private final UserDao userDao = new UserDao(db);

    /**
     * Fill tables with temp data.
     */
    @Before
    public void fillTables() {
        Body body = new Body();
        body.setName("1");
        this.bodyDao.create(body);

        Engine engine = new Engine();
        engine.setName("1");
        this.engineDao.create(engine);

        Axle axle = new Axle();
        axle.setName("1");
        this.axleDao.create(axle);

        Brand brand = new Brand();
        brand.setName("1");
        this.brandDao.create(brand);

        Gearbox gearbox = new Gearbox();
        gearbox.setName("1");
        this.gearboxDao.create(gearbox);

        User user = new User();
        user.setName("1");
        this.userDao.create(user);


    }

    /**
     * Creating new car record.
     * Expect: can find it in db by id.
     */
    @Test
    public void createTest() {
        Car car = this.createCar("first");
        this.carDao.create(car);
        assertThat(car.getName(), is(carDao.getById(car.getId()).getName()));
    }

    /**
     * Updating record in db.
     * Expect: i can see changes on record.
     */
    @Test
    public void updateTest() {
        Car car = this.createCar("first");
        this.carDao.create(car);
        String expect = "second";
        car.setName("second");
        this.carDao.update(car);
        assertThat(expect, is(this.carDao.getById(car.getId()).getName()));
    }

    /**
     * Delete record from db.
     * Expect: record cannot be found in db.
     */
    @Test
    public void deleteTest() {
        Car car = this.createCar("delete");
        this.carDao.create(car);
        this.carDao.delete(car);
        boolean expect = true;
        assertThat(expect, is(!this.carDao.getList().contains(car)));
    }

    /**
     * Add new record to db.
     * Expect:to find record in list of records.
     */
    @Test
    public void getListTest() {
        Car car = this.createCar("third");
        this.carDao.create(car);

        boolean expect = true;
        assertThat(expect, is(this.carDao.getList().contains(car)));
    }

    /**
     * Changing car record status.
     * Expect: see changes in status.
     */
    @Test
    public void changeCarStatusTest() {
        Car car = this.createCar("status");
        this.carDao.create(car);
        int oldStatus = car.getStatus();
        this.carDao.changCarStatus(car.getId());
        boolean expect = true;
        assertThat(expect, is(this.carDao.getById(car.getId()).getStatus() != oldStatus));
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
        this.carDao.create(one);
        this.carDao.create(two);
        this.carDao.create(three);
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
        car.setUser(this.userDao.getById(1));
        car.setPost(new Timestamp(System.currentTimeMillis()));
        car.setBrand(this.brandDao.getById(1));
        car.setGearbox(this.gearboxDao.getById(1));
        car.setEngine(this.engineDao.getById(1));
        car.setAxle(this.axleDao.getById(1));
        car.setBody(this.bodyDao.getById(1));
        return car;
    }
}
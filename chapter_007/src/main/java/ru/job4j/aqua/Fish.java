package ru.job4j.aqua;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on 26.10.17.
 * Fish info.
 * @author Wamdue
 * @version 1.0
 */
public class Fish {
    /**
     * Static variable to generate life time, and gender.
     */
    private static Random random = new Random();
    /**
     * Static id counter.
     */
    private static AtomicInteger integer = new AtomicInteger(0);
    /**
     * Life time of the fish.
     */
    private int lifeTime;
    /**
     * Serial number.
     */
    private int id;
    /**
     * Gender of the fish.
     */
    private Gender gender;
    /**
     * Time of the fish creation.
     */
    private Date birthday;

    /**
     * Main constructor.
     * Generating fish lifetime, gender, and set`s id.
     */
    public Fish() {
        this.lifeTime = random.nextInt(10000);
        this.gender = Gender.values()[(int) (Math.random() * 2)];
        this.id = integer.getAndIncrement();
        this.birthday = new Date();
    }

    /**
     * get life time of the fish.
     * @return lifeTime.
     */
    public int getLifeTime() {
        return lifeTime;
    }

    /**
     * Get gender of the fish.
     * @return - gender.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Get serial number of the fish.
     * @return - id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get birthday of the fish.
     * @return - birthday.
     */
    public Date getBirthday() {
        return this.birthday;
    }

    /**
     * View fish info.
     * @return - fish info.
     */
    @Override
    public String toString() {

        return String.format("Fish № %d", this.id);
    }
}

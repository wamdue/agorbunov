package ru.job4j.mapping.carstorage;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created on 17.01.18.
 * Engine information.
 * @author Wamdue
 * @version 1.0
 */
@Entity
@Table(name = "engine")
public class Engine {
    /**
     * Engine id.
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    /**
     * Engine name.
     */
    @Column(name = "name")
    private String name;

    /**
     * Get Engine id.
     * @return engine id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set engine id.
     * @param id - engine id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get engine name.
     * @return - engine name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set engine name.
     * @param name engine name.
     */
    public void setName(String name) {
        this.name = name;
    }
}

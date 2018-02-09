package ru.job4j.mapping.carstorage;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created on 17.01.18.
 * Gearbox information.
 * @author Wamdue
 * @version 1.0
 */
@Entity
@Table(name = "gearbox")
public class Gearbox {
    /**
     * Gearbox id.
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    /**
     * Gearbox name.
     */
    @Column(name = "name")
    private String name;

    /**
     * Get Gearbox id.
     * @return Gearbox id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set Gearbox id.
     * @param id - Gearbox id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Gearbox name.
     * @return - Gearbox name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set Gearbox name.
     * @param name Gearbox name.
     */
    public void setName(String name) {
        this.name = name;
    }
}

package ru.job4j.mapping.carstorage;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created on 17.01.18.
 * Chassy information.
 * @author Wamdue
 * @version 1.0
 */
@Entity
@Table(name = "chassy")
public class Chassy {
    /**
     * Chassy id.
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    /**
     * Chassy name.
     */
    @Column(name = "name")
    private String name;

    /**
     * Get Chassy id.
     * @return Chassy id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set Chassy id.
     * @param id - Chassy id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Chassy name.
     * @return - Chassy name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set Chassy name.
     * @param name Chassy name.
     */
    public void setName(String name) {
        this.name = name;
    }
}

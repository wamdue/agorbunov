package ru.job4j.mapping.carstorage;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Created on 17.01.18.
 * Car information.
 * @author Wamdue
 * @version 1.0
 */
@Entity
@Table(name="car")
public class Car {
    /**
     * Car id.
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int id;
    /**
     * Car name.
     */
    @Column(name = "name")
    private String name;
    /**
     * Car engine.
     */
    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;
    /**
     * Car gearbox.
     */
    @ManyToOne
    @JoinColumn(name = "gearbox_id")
    private Gearbox gearbox;
    /**
     * Car chassy.
     */
    @ManyToOne
    @JoinColumn(name = "chassy_id")
    private Chassy chassy;

    /**
     * Get car id.
     * @return car id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set car id.
     * @param id - car id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get car name.
     * @return - car name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set car name.
     * @param name - car name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get car engine link.
     * @return - engine.
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Set car engine.
     * @param engine - car engine.
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * Get car gearbox.
     * @return - gearbox.
     */
    public Gearbox getGearbox() {
        return gearbox;
    }

    /**
     * Set car gearbox.
     * @param gearbox - car gearbox.
     */
    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    /**
     * Get car chassy.
     * @return - chassy.
     */
    public Chassy getChassy() {
        return this.chassy;
    }

    /**
     * Set car chassy.
     * @param chassy - car chassy.
     */
    public void setChassy(Chassy chassy) {
        this.chassy = chassy;
    }
}

package ru.job4j.mapping.carstorage;

/**
 * Created on 17.01.18.
 * Car information.
 *
 * @author Wamdue
 * @version 1.0
 */
public class Car {
    /**
     * Car id.
     */
    private int id;
    /**
     * Car name.
     */
    private String name;
    /**
     * Car engine.
     */
    private Engine engine;
    /**
     * Car gearbox.
     */
    private Gearbox gearbox;
    /**
     * Car chassy.
     */
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

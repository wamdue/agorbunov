package ru.job4j.fin.entity;

/**
 * Created on 20.11.17.
 * Available genres.
 * @author Wamdue
 * @version 1.0
 */
public class MusicType {
    /**
     * Id.
     */
    private int id;
    /**
     * Name of type.
     */
    private String name;

    /**
     * Get type id.
     * @return - id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new type id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get music type name.
     * @return - music type name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new music type name.
     * @param name - new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Overriding equals method.
     * @param o - object to compare.
     * @return - result of comparing.
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MusicType musicType = (MusicType) o;

        if (id != musicType.id) {
            return false;
        }
        return name.equals(musicType.name);
    }

    /**
     * Overriding hashcode.
     * @return - current hashcode.
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}

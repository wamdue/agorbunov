package ru.job4j.generics;

/**
 * Created on 20.07.17
 * Role class.
 * @author Wamdue
 * @version 1.0
 */
public class Role extends Base {
    /**
     * Id of the element.
     */
    private String id;

    /**
     * Main constructor.
     * @param id - id of the item.
     */
    public Role(String id) {
        this.id = id;
    }

    /**
     *
     * @return current id.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Set new id.
     * @param id - new id.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }
}

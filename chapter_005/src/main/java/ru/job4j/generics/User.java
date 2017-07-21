package ru.job4j.generics;

/**
 * Created on 20.07.17
 * User class.
 * @author Wamdue
 * @version 1.0
 */
public class User extends Base {
    /**
     * Id of the element.
     */
    private String id;

    /**
     * Main constructor.
     * @param id - id of the item.
     */
    public User(String id) {
        this.id = id;
    }
    /**
     *
     * @return - current id.
     */
    @Override
    public String getId() {
        return null;
    }

    /**
     *
     * @param id - new id.
     */
    @Override
    public void setId(String id) {

    }
}

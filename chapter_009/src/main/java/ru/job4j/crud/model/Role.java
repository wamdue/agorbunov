package ru.job4j.crud.model;

/**
 * Created on 31.10.17.
 * Users roles.
 * @author Wamdue
 * @version 1.0
 */
public enum Role {
    /**
     * User who can change all users, and add new.
     */
    ADMIN,
    /**
     * User who can change only self info.
     */
    USER
}

package ru.job4j.fin.entity;

/**
 * Created on 20.11.17.
 * Possible roles.
 * @author Wamdue
 * @version 1.0
 */
public enum Role {
    /**
     * Simple user.
     */
    USER,
    /**
     * User who can make decisions.
     */
    MANDATOR,
    /**
     * User who can do all.
     */
    ADMIN
}

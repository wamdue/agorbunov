package ru.job4j.query;

/**
 * Created on 02.10.17.
 * Possible actions.
 * @author Wamdue
 * @version 1.0
 */
public enum Action {
    /**
     * More >.
     */
    MORE,
    /**
     * Less <.
     */
    LESS,
    /**
     * More or equal >=.
     */
    MORE_OR_EQUAL,
    /**
     * Less or equal <=.
     */
    LESS_OR_EQUAL,
    /**
     * Equal =.
     */
    EQUAL,
    /**
     * Not equal !=.
     */
    NOT_EQUAL,
    /**
     * Null is null.
     */
    NULL,
    /**
     * Not null is not null.
     */
    NOT_NULL,
    /**
     * Contain like.
     */
    CONTAIN,
    /**
     * In in.
     */
    IN
}

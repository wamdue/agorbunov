package ru.job4j.query;

/**
 * Created on 02.10.17.
 * Behaviors.
 * @author Wamdue
 * @version 1.0
 */
public class Line {
    /**
     * Behave.
     */
    private Behave behave = Behave.NULL;
    /**
     * Field.
     */
    private String field;
    /**
     * Action.
     */
    private Action action;
    /**
     * Value.
     */
    private String value;

    /**
     * Get current behave.
     * @return - behave.
     */
    public Behave getBehave() {
        return behave;
    }

    /**
     * Set new behave.
     * @param behave - behave.
     */
    public void setBehave(Behave behave) {
        this.behave = behave;
    }

    /**
     * Get current field.
     * @return - field.
     */
    public String getField() {
        return field;
    }

    /**
     * Set new field.
     * @param field - new field.
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * Get current action.
     * @return - action.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Set new action.
     * @param action - new action.
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * Get current value.
     * @return - value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set new value.
     * @param value - new value.
     */
    public void setValue(String value) {
        this.value = value;
    }
}

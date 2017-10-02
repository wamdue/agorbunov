package ru.job4j.query;

/**
 * Created on 02.10.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class Line {
    private Behave behave = Behave.NULL;
    private String field;
    private Action action;
    private String value;

    public Behave getBehave() {
        return behave;
    }

    public void setBehave(Behave behave) {
        this.behave = behave;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

package ru.job4j.tracker;
/**
* Abstract class for menu actions.
*/
public abstract class BaseAction implements UserAction {
    /**
    * @param key - number of action.
    */
    private int key;
    /**
     * @param name - action name.
    */
    private String name;
    /**
    * Main constructor.
    * @param key - number of action.
    * @param name - action name.
    */
    public BaseAction(int key, String name) {
	this.key = key;
	this.name = name;
    }
    /**
    * @return - return menu key.
    */
    public int key() {
	return key;
    }
    /**
    * @return action number and name.
    */
    public String info() {
	return String.format("	%d. %s", key, name);
    }

}
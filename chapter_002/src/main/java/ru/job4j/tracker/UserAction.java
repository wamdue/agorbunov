package ru.job4j.tracker;
/**
* User actions.
*/
public interface UserAction {
    /**
    * Read key.
    * @return key.
    */
    int key();
    /**
    * Do action.
    * @param input - method of input.
    * @param tracker - storage.
    */
    void execute(Input input, Tracker tracker);
    /**
    * @return Info about action.
    */
    String info();
}
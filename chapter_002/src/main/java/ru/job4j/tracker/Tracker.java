package ru.job4j.tracker;
import java.util.Arrays;
/**
* List of all tasks.
*/
public class Tracker {
    /**
    * @param items - array of tasks.
    */
    private Item[] items = new item[100];
    /**
    * @param position - id of last element.
    */
    private int position = 0;
    /**
    * @param item - item to add and generate id for it.
    */
    public Item add(Item item) {
	this.items[position++] = item;
	return item;
    }
    /**
    * @param item - item for update.
    */
    public void update(Item item) {
	for (int i = 0; i < position; i++) {
	    if (items[i].getId().equals(item.getId())) {
		items[i] = item;
		break;
	    }
	}
    }
    /**
    * @param item - delete current item from array.
    */
    public void delete(Item item) {
	for (int i = 0; i < position; i++) {
	    if(items[i].getId().equals(item.getId())) {
		System.arrayCopy(items, i + 1, items, i, position--);
		break;
	    }
	}
    }
    public Item[] findAll() {
	return this.items;
    }
    /**
    *
    */
    public Item[] findByName(String key) {
	int counter = 0;
	for (int i = 0; i < position; i++) {
	    if (items[i].getName().contains(key)) {
		counter++;
	    }
	}
    }
    /**
    * @param id - id the task, which you want to find.
    * @return searched item, or null if not found.
    */
    public Item findById(String id) {
	for (int i = 0; i < position; i++) {
	    if (items[i].getId().equals(id)) {
		return items[i];
	    }
	}
	return null;
    }
    /**
    * @return position - return number of filled buckets.
    */
    public int size() {
	return position;
    }
}
package ru.job4j.tracker;
import java.util.Arrays;
import java.util.Random;
/**
* List of all tasks.
*/
public class Tracker {
    /**
    * @param RN - variable for generate inique id.
    */
    private static final Random RN = new Random();
    /**
    * @param items - array of tasks.
    */
    private Item[] items = new Item[100];
    /**
    * @param position - id of last element.
    */
    private int position = 0;
    /**
    * @param item - item to add and generate id for it.
    */
    public Item add(Item item) {
	if(position == 100) {
	    position--;
	}
	item.setId(this.generateId());
	items[position++] = item;
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
		System.arraycopy(items, i + 1, items, i, position--);
		break;
	    }
	}
    }
    public Item[] findAll() {
	return Arrays.copyOf(items, position);
    }
    /**
    * @param key - value to search in items.
    * @return array - array of finded values.
    */
    public Item[] findByName(String key) {
	Item[] array = new Item[1];
	int counter = 0;
	for (int i = 0; i < position; i++) {
	    if (items[i].getName().contains(key)) {
		if (counter == array.length) {
		    array = Arrays.copyOf(array, counter + 1);
		}
		array[counter++] = items[i];
	    }
	}
	return array[0] == null ? null : array;
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
    /**
    * @return - new generated id.
    */
    String generateId() {
	return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
    }
}
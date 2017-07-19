package ru.job4j.tracker;
import java.util.ArrayList;
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
    * @param items - list of tasks.
    */
    private ArrayList<Item> items = new ArrayList<>();
    /**
    * @param position - id of last element.
    */
    private int position = 0;
    /**
    * @param item - item to add and generate id for it.
    */
    public Item add(Item item) {
    	item.setId(this.generateId());
    	items.add(item);
    	return item;
   	}
    /**
    * @param item - item for update.
    */
    public void update(Item item) {
    	for (int i = 0; i < items.size(); i++) {
    		if (items.get(i).getId().equals(item.getId())) {
    			items.set(i, item);
    			break;
    		}
    	}
    }
    /**
    * @param item - delete current item from array.
    */
    public void delete(Item item) {
    	items.remove(item);
    }

    /**
     *
     * @return list as array.
     */
    public ArrayList<Item> findAll() {
        return items;
    }
    /**
    * @param key - value to search in items.
    * @return array - array of finded values.
    */
    public ArrayList<Item> findByName(String key) {
    	ArrayList<Item> array = new ArrayList<>();
    	for (int i = 0; i < items.size(); i++) {
    		if (items.get(i).getName().contains(key)) {
    			array.add(items.get(i));
    		}
    	}
    	return array;
    }
    /**
    * @param id - id the task, which you want to find.
    * @return searched item, or null if not found.
    */
    public Item findById(String id) {
    	for (int i = 0; i < items.size(); i++) {
    		Item item = items.get(i);
    		if (item.getId().equals(id)) {
    			return item;
    		}
    	}
    	return null;
    }
    /**
    * @return position - return number of filled buckets.
    */
    public int size() {
    	return items.size();
    }
    /**
    * @return - new generated id.
    */
    String generateId() {
    	return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
    }
}
package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Class for testing Tracker.
*/
public class TrackerTest {
    /**
    * Adding 5 elements, and expection 5 elements in array.
    */
    @Test
    public void whenAddNewItemThenIncrase() {
	Tracker tracker = new Tracker();
	for (int i = 0; i < 5; i++) {
	    tracker.add(new Item(Integer.toString(i), Integer.toString(i)));
	}
	int expected = 5;
	assertThat(tracker.size(), is(expected));
    }
    /**
    * Adding 5 elements, then remove 1, end expecting 4.
    */
    @Test
    public void whenRemoveOneThenLess() {
	Tracker tracker = new Tracker();
	ArrayList<Item> items = new ArrayList<>();
	Item item = null;
	for (int i = 0; i < 5; i++) {
	    item = new Item(Integer.toString(i), Integer.toString(i));
	    items.add(item);
	    tracker.add(item);
	}
	tracker.delete(items.get(1));
	int expected = 4;
	assertThat(tracker.size(), is(expected));
    }
    /**
    * Compare two arrays.
    */
    @Test
    public void whenHaveTwoEqualArraysThenEqual() {
	ArrayList<Item> items = new ArrayList<>();
	Tracker tracker = new Tracker();
	Item item = null;
	for (int i = 0; i < 5; i++) {
	    item = new Item(Integer.toString(i), Integer.toString(i));
	    items.add(item);
	    tracker.add(item);
	}
	assertThat(tracker.findAll(), is(items));
    }
    /**
    * Find item by name.
    */
    @Test
    public void whenHaveNameThenReturnByName() {
	Tracker tracker = new Tracker();
	ArrayList<Item> items = new ArrayList<>();
	Item item = null;
	for (int i = 0; i < 5; i++) {
	    item = new Item(Integer.toString(i), Integer.toString(i));
	    items.add(item);
	    tracker.add(item);
	}
	ArrayList<Item> expected = new ArrayList<>();
	expected.add(items.get(2));
	assertThat(tracker.findByName("2"), is(expected));
    }
    /**
    * Find item by id.
    */
    @Test
    public void whenHaveIdThenReturnById() {
	Tracker tracker = new Tracker();
	ArrayList<Item> items = new ArrayList<>();
	Item item = null;
	for (int i = 0; i < 5; i++) {
	    item = new Item(Integer.toString(i), Integer.toString(i));
	    items.add(item);
	    tracker.add(item);
	}
	assertThat(tracker.findById(1), is(items.get(1)));
    }
    /**
    * Update item.
    */
    @Test
    public void whenNeedToUpdateThenUpdate() {
	Tracker tracker = new Tracker();
	ArrayList<Item> items = new ArrayList<>();
	Item item = null;
	for (int i = 0; i < 5; i++) {
	    item = new Item(Integer.toString(i), Integer.toString(i));
	    items.add(item);
	    tracker.add(item);
	}
	items.get(1).setName("TestName");
	tracker.update(items.get(1));
	assertThat(tracker.findAll(), is(items));
    }
}
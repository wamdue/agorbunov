package ru.job4j.tracker;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
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
	Item[] items = new Item[5];
	Item item = null;
	for (int i = 0; i < 5; i++) {
	    item = new Item(Integer.toString(i), Integer.toString(i));
	    item.setId(Integer.toString(i));
	    items[i] = item;
	    tracker.add(item);
	}
	tracker.delete(items[1]);
	int expected = 4;
	assertThat(tracker.size(), is(expected));
    }
    /**
    * Compare two arrays.
    */
    @Test
    public void whenHaveTwoEqualArraysThenEqual() {
	Item[] items = new Item[5];
	Tracker tracker = new Tracker();
	Item item = null;
	for (int i = 0; i < 5; i++) {
	    item = new Item(Integer.toString(i), Integer.toString(i));
	    item.setId(Integer.toString(i));
	    items[i] = item;
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
	Item[] items = new Item[5];
	Item item = null;
	for (int i = 0; i < 5; i++) {
	    item = new Item(Integer.toString(i), Integer.toString(i));
	    item.setId(Integer.toString(i));
	    items[i] = item;
	    tracker.add(item);
	}
	Item[] expected = new Item[1];
	expected[0] = items[2];
	assertThat(tracker.findByName("2"), is(expected));
    }
    /**
    * Find item by id.
    */
    @Test
    public void whenHaveIdThenReturnById() {
	Tracker tracker = new Tracker();
	Item[] items = new Item[5];
	Item item = null;
	for (int i = 0; i < 5; i++) {
	    item = new Item(Integer.toString(i), Integer.toString(i));
	    items[i] = item;
	    tracker.add(item);
	    item.setId(Integer.toString(i));
	}
	assertThat(tracker.findById("1"), is(items[1]));
    }
    /**
    * Update item.
    */
    @Test
    public void whenNeedToUpdateThenUpdate() {
	Tracker tracker = new Tracker();
	Item[] items = new Item[5];
	Item item = null;
	for (int i = 0; i < 5; i++) {
	    item = new Item(Integer.toString(i), Integer.toString(i));
	    item.setId(Integer.toString(i));
	    items[i] = item;
	    tracker.add(item);
	}
	items[1].setName("TestName");
	tracker.update(items[1]);
	assertThat(tracker.findAll(), is(items));
    }
}
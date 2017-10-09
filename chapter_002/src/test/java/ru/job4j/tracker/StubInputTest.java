package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Test for menu.
*/
public class StubInputTest {
	/**
	* Add new Task.
	* expected test name.
	*/
	@Test
	public void whenUserAddItemThenTrackerHasNewName() {
	    Tracker tracker = new Tracker();
	    Input input = new StubInput(new ArrayList<>(Arrays.asList("0", "test name", "desc", "6")));
	    new StartUi(input, tracker).init();
	    assertThat(tracker.findAll().get(0).getName(), is("test name"));
	}
	/**
	* Update existing item.
	* expect new name.
	*/
	@Test
	public void whenUpdateThenTrackerhasUpdatedValue() {
	    Tracker tracker = new Tracker();
	    Item item = tracker.add(new Item());
	    Input input = new StubInput(new ArrayList<>(Arrays.asList("2", String.valueOf(item.getId()), "new name", "desc", "6")));
	    new StartUi(input, tracker).init();
	    assertThat(tracker.findById(item.getId()).getName(), is("new name"));
	}
	/**
	* Add 3 items. 
	* expect match.
	*/
	@Test
	public void whenAddThreeItemsThenCanCompare() {
	    Tracker tracker = new Tracker();
	    ArrayList<Item> items = new ArrayList<>();
	    for (int i = 0; i < 3; i++) {
		items.add(tracker.add(new Item("name"+i, "desc"+i)));
	    }
	    Input input = new StubInput(new ArrayList<>(Arrays.asList("1", "6")));
	    new StartUi(input, tracker).init();
	    assertThat(tracker.findAll(), is(items));
    }
    /**
    * Add 3 items, then remove one.
    * expect 2 items in tracker.
    */
    @Test
    public void whenAddThreeRemoveOneThenHaveTwo() {
        Tracker tracker = new Tracker();
        ArrayList<Item> items = new ArrayList<>();
	int expected = 2;
        for (int i = 0; i < 3; i++) {
	    items.add(tracker.add(new Item("name"+i, "desc"+i)));
	}
        Input input = new StubInput(new ArrayList<>(Arrays.asList("3", String.valueOf(items.get(1).getId()),"1", "6")));
	new StartUi(input, tracker).init();
        assertThat(tracker.size(), is(expected));
    }
}

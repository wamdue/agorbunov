package ru.job4j.tracker;
import org.junit.Test;
import java.io.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
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
	    //ByteArrayOutputStream out = new ByteArrayOutputStream();
	    //System.setOut(new PrintStream(out));
	    Tracker tracker = new Tracker();
	    Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
	    new StartUi(input, tracker).init();
	    assertThat(tracker.findAll()[0].getName(), is("test name"));
	}
	/**
	* Update existing item.
	* expect new name.
	*/
	@Test
	public void whenUpdateThenTrackerhasUpdatedValue() {
	    Tracker tracker = new Tracker();
	    Item item = tracker.add(new Item());
	    Input input = new StubInput(new String[]{"2", item.getId(), "new name", "desc", "6"});
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
	    Item[] items = new Item[3];
	    for (int i = 0; i < 3; i++) {
		items[i] = tracker.add(new Item("name"+i, "desc"+i));
	    }
	    Input input = new StubInput(new String[]{"1", "6"});
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
        Item[] items = new Item[3];
	int expected = 2;
        for (int i = 0; i < 3; i++) {
	    items[i] = tracker.add(new Item("name"+i, "desc"+i));
	}
        Input input = new StubInput(new String[]{"3", items[1].getId(),"1", "6"});
	new StartUi(input, tracker).init();
        assertThat(tracker.size(), is(expected));
    }
}

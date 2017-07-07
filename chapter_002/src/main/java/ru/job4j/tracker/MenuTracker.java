package ru.job4j.tracker;
/**
* Class for work with menu.
*/
public class MenuTracker {
    /**
    * @param ADD - add new item to tracker.
    */
    public static final int ADD = 0;
    /**
    * @param SHOW_ALL - show all items in tracker.
    */
    public static final int SHOW_ALL = 1;
    /**
    * @param EDIT - edit item by id.
    */
    public static final int EDIT = 2;
    /**
    * @param DELETE - delete item by id.
    */
    public static final int DELETE = 3;
    /**
    * @param FIND_BY_ID - find item by id.
    */
    public static final int FIND_BY_ID = 4;
    /**
    * @param FIND_BY_NAME - find items by name.
    */
    public static final int FIND_BY_NAME = 5;
    /**
    * @param input - input system.
    */
    private Input input;
    /**
    * @param tracker - storage.
    */
    private Tracker tracker;
    /**
    * @param actions - menu content.
    */
    private UserAction[] actions = new UserAction[6];
    /**
    * Main constructor.
    * @param input - input system.
    * @param tracker - storage.
    */
    public MenuTracker(Input input, Tracker tracker) {
	this.input = input;
	this.tracker = tracker;
    }
    /**
    *
    */
    public void fillActions() {
	this.actions[0] = new AddItem();
	this.actions[1] = new MenuTracker.ShowAll();
	this.actions[2] = new EditItem();
	this.actions[3] = new DeleteItem();
	this.actions[4] = new FindItemById();
	this.actions[5] = new FindItemsByName();
    }
    /**
    * @param key - menu key.
    */
    public void select(int key) {
	this.actions[key].execute(this.input, this.tracker);
    }
    /**
    * Show menu.
    */
    public void show() {
	for (UserAction action : this.actions) {
	    if (action != null) {
		System.out.println(action.info());
	    }
	}
	System.out.println("	6 Exit.");
    }
    /**
    * Internal class for implemention UserAction.
    */
    private class AddItem implements UserAction {
	/**
	* @return menu key.
	*/
        public int key() {
	    return MenuTracker.ADD;
	}
	/**
	* Execute action - add item to tracker.
	* @param input - input system.
	* @param tracker - storage.
	*/
	public void execute(Input input, Tracker tracker) {
	    String name = input.ask("Input task`s name: ");
	    String desc = input.ask("Input task`s description: ");
	    tracker.add(new Item(name, desc));
	}
	/**
	* @return info string.
	*/
	public String info() {
	    return String.format("	%s. %s", this.key(), "Add the new item. ");
	}
    }
    /**
    * Internal static class for showing all items.
    */
    private static class ShowAll implements UserAction {
	/**
	* @return menu key.
	*/
        public int key() {
	    return MenuTracker.SHOW_ALL;
	}
	/**
	* Execute action - add item to tracker.
	* @param input - input system.
	* @param tracker - storage.
	*/
	public void execute(Input input, Tracker tracker) {
	    for (Item item : tracker.findAll()) {
		System.out.printf("id: %s; name: %s\n", item.getId(), item.getName());
	    }
	}
	/**
	* @return info string.
	*/
	public String info() {
	    return String.format("	%s. %s", this.key(), "Show all items. ");
	}
	
    }
}
/**
* External class for edit item.
*/
class EditItem implements UserAction {
    /**
    * @return menu key.
    */
    public int key() {
        return MenuTracker.EDIT;
    }
    /**
    * Execute action - edit item in tracker.
    * @param input - input system.
    * @param tracker - storage.
    */
    public void execute(Input input, Tracker tracker) {
	String id = input.ask("Enter task id for edit: ");
	Item item = tracker.findById(id);
	item.setName(input.ask("Enter new task name: "));
	item.setDesc(input.ask("Enter new task description: "));
    }
    /**
    * @return info string.
    */
    public String info() {
        return String.format("	%s. %s", this.key(), "Edit item. ");
    }
}
/**
* External class for delete item.
*/
class DeleteItem implements UserAction {
    /**
    * @return menu key.
    */
    public int key() {
        return MenuTracker.DELETE;
    }
    /**
    * Execute action - delete item from tracker.
    * @param input - input system.
    * @param tracker - storage.
    */
    public void execute(Input input, Tracker tracker) {
	String id = input.ask("Enter task id to delete: ");
	tracker.delete(tracker.findById(id));
    }
    /**
    * @return info string.
    */
    public String info() {
        return String.format("	%s. %s", this.key(), "Delete item. ");
    }
}
/**
* External class for return item by id.
*/
class FindItemById implements UserAction {
    /**
    * @return menu key.
    */
    public int key() {
        return MenuTracker.FIND_BY_ID;
    }
    /**
    * Execute action - find item in tracker.
    * @param input - input system.
    * @param tracker - storage.
    */
    public void execute(Input input, Tracker tracker) {
	String id = input.ask("Enter task id to find: ");
	Item item = tracker.findById(id);
	if (item != null) {
	    System.out.printf("id: %s; name: %s\n", item.getId(), item.getName());
	} else {
	    System.out.println("Task not founded.");
	}
    }
    /**
    * @return info string.
    */
    public String info() {
        return String.format("	%s. %s", this.key(), "Find item by Id. ");
    }
}
/**
* External class for return item by id.
*/
class FindItemsByName implements UserAction {
    /**
    * @return menu key.
    */
    public int key() {
        return MenuTracker.FIND_BY_NAME;
    }
    /**
    * Execute action - find item in tracker.
    * @param input - input system.
    * @param tracker - storage.
    */
    public void execute(Input input, Tracker tracker) {
	String id = input.ask("Enter task name to find: ");
	Item[] items = tracker.findByName(id);
	if (items != null && items.length > 0) {
	    for (Item item : items) {
	        System.out.printf("id: %s; name: %s\n", item.getId(), item.getName());
	    }
	} else {
	    System.out.println("Task not founded.");
	}
    }
    /**
    * @return info string.
    */
    public String info() {
        return String.format("	%s. %s", this.key(), "Find items by name. ");
    }
}


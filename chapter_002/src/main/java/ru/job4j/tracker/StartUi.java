package ru.job4j.tracker;
/**
* User interface class.
*/
public class StartUi {
    /**
    * @param menu - main menu.
    */
    private String[] menu = {"0 Add new Item",
	"1 Show all items",
	"2 Edit item",
	"3 Delete item",
	"4 Find item by Id",
	"5 Find items by name",
	"6 Exit program"};
    /**
    * @param input - class for drawing main menu, and read input.
    */
    private ConsoleInput input;
    /**
    * @param tracker - class tracker.
    */
    private Tracker tracker;
    /**
    * @param args - console arguments.
    * Entry point!
    */
    public static void main(String[] args) {
	new StartUi();
    }
    /**
    * Constructor with no args.
    */
    public StartUi() {
	this.init();
    }
    /**
    * Init class for UI.
    */
    public void init() {
	this.input = new ConsoleInput();
	this.tracker = new Tracker();
	int element;
	while (true) {
	    this.drawMenu();
	    try{
		element = Integer.valueOf(input.ask("select: "));
		if (element == 0) {
		    this.addElement();
		} else if (element == 1) {
		    this.showElements(tracker.findAll());
		} else if (element == 2) {
		    this.editElement();
		} else if (element == 3) {
		    this.deleteElement();
		} else if (element == 4) {
		    this.elementById();
		} else if (element == 5) {
		    this.elementsByName();
		} else if (element == 6) {
		    System.out.println("Have a nice day!");
		    break;
		} else {
		    continue;
		}
	    } catch (Exception ex) {
		System.out.println("Must be number from 0 to 6");
	    }
	    
	}
    }
    /**
    * Shows main menu.
    */
    public void drawMenu() {
	System.out.println();
	for (String s : menu) {
	    System.out.println(s);
	}
    }
    /**
    * Add new task to tracker.
    */
    public void addElement() {
	String name = this.input.ask("Enter task name: ");
	String desc = this.input.ask("Enter description: ");
	String[] comment = {this.input.ask("Enter comment: ")};
	tracker.add(new Item(name, desc, comment));
    }
    /**
    * Prints array of items.
    */
    public void showElements(Item[] items) {
	if (items != null && items.length > 0) {
	    for (Item item : items) {
	        System.out.println("Id: " + item.getId());
	        System.out.println("Creation time: " + item.getCreated());
	        System.out.println("Task name: " + item.getName());
	        System.out.println("Description: " + item.getDesc());
	        if (item.getComments() != null) {
    		    System.out.print("Comments: ");
		    for(String s : item.getComments()) {
		    System.out.println(s);
		    }
		}
		System.out.println("----------------");
	    }
	} else {
	    System.out.println("Task`s list is empty.");
	}
    }
    /**
    * Edit item from tracker.
    */
    public void editElement() {
	Item item = tracker.findById(this.input.ask("Enter task id for edit: "));
	String name = this.input.ask("Enter task name, or leave empty: ");
	String desc = this.input.ask("Enter new description or leave empty: ");
	item.setName(name != "" ? name : item.getName());
	item.setDesc(desc != "" ? desc : item.getDesc());
	tracker.update(item);
    }
    /**
    * Delete item from tracker.
    */
    public void deleteElement() {
	String temp = this.input.ask("Enter task id for delete: ");
	tracker.delete(tracker.findById(temp));
    }
    /**
    * Show items on console, founded by name.
    */
    public void elementsByName() {
	this.showElements(tracker.findByName(this.input.ask("Enter task name: ")));
    }
    /**
    * Show item on console, found by id.
    */
    public void elementById() {
	Item[] item = {tracker.findById(this.input.ask("Enter task id : "))};
	this.showElements(item);
    }
}
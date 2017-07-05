package ru.job4j.tracker;
/**
* User interface class.
*/
public class StartUi {
    /**
    * @param ADD - add new record.
    */
    private static final String ADD = "0";
    /**
    * @param SHOW_ALL - view all records.
    */
    private static final String SHOW_ALL = "1";
    /**
    * @param EDIT - edit record by id.
    */
    private static final String EDIT = "2";
    /**
    * @param DELETE - delete record by id.
    */
    private static final String DELETE = "3";
    /**
    * @param FIND_BY_ID - find record by id.
    */
    private static final String FIND_BY_ID = "4";
    /**
    * @param FIND_BY_NAME - find records by name.
    */
    private static final String FIND_BY_NAME = "5";
    /**
    * @param EXIT - exit program.
    */
    private static final String EXIT = "6";
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
    private Input input;
    /**
    * @param tracker - class tracker.
    */
    private Tracker tracker;
    /**
    * @param args - console arguments.
    * Entry point!
    */
    public static void main(String[] args) {
    	new StartUi(new ConsoleInput(), new Tracker()).init();
    }
    /**
    * Constructor.
    */
    public StartUi(Input input, Tracker tracker) {
    	this.input = input;
    	this.tracker = tracker;
    }
    /**
    * Init class for UI.
    */
    public void init() {
    	String element;
    	while (true) {
    		this.drawMenu();
    		try{
    			element = input.ask("select: ");
    			if (element.equals(ADD)) {
    				this.addElement();
    			} else if (element.equals(SHOW_ALL)) {
    				this.showElements(tracker.findAll());
    			} else if (element.equals(EDIT)) {
    				this.editElement();
    			} else if (element.equals(DELETE)) {
    				this.deleteElement();
    			} else if (element.equals(FIND_BY_ID)) {
    				this.elementById();
    			} else if (element.equals(FIND_BY_NAME)) {
    			    this.elementsByName();
    			} else if (element.equals(EXIT)) {
			    break;
    			} else {
    			    continue;
    				//System.out.println("Что-то пошло не так!");
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
//    	String[] comment = {this.input.ask("Enter comment: ")};
    	tracker.add(new Item(name, desc));
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
	String id = this.input.ask("Enter task id for delete: ");
	System.out.println("id = " + id);
	tracker.delete(tracker.findById(id));
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
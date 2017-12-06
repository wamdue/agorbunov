package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Class for work with menu.
 */
public class MenuTracker {
    /**
     * Add new item to tracker.
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
    private ArrayList<UserAction> actions = new ArrayList<>();

    /**
     * Main constructor.
     *
     * @param input   - input system.
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
        this.actions.add(new AddItem(MenuTracker.ADD, "Add new item."));
        this.actions.add(new MenuTracker.ShowAll(MenuTracker.SHOW_ALL, "Show all items."));
        this.actions.add(new EditItem(MenuTracker.EDIT, "Edit item."));
        this.actions.add(new DeleteItem(MenuTracker.DELETE, "Delete item"));
        this.actions.add(new FindItemById(MenuTracker.FIND_BY_ID, "Find item by id."));
        this.actions.add(new FindItemsByName(MenuTracker.FIND_BY_NAME, "Find items by name."));
    }

    /**
     * @param key - menu key.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
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
     * Close connection to db.
     */
    public void close() {
        this.tracker.close();
    }
    /**
     * Internal class for implementation UserAction.
     */
    private class AddItem extends BaseAction {
        /**
         * Constructor with main args.
         *
         * @param key  - menu key.
         * @param name - menu name.
         */
        AddItem(int key, String name) {
            super(key, name);
        }

        /**
         * Execute action - add item to tracker.
         *
         * @param input   - input system.
         * @param tracker - storage.
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Input task`s name: ");
            String desc = input.ask("Input task`s description: ");
            tracker.add(new Item(name, desc));
        }
    }

    /**
     * Internal static class for showing all items.
     */
    private static class ShowAll extends BaseAction {
        /**
         * Constructor with main args.
         *
         * @param key  - menu key.
         * @param name - menu name.
         */
        ShowAll(int key, String name) {
            super(key, name);
        }

        /**
         * Execute action - add item to tracker.
         *
         * @param input   - input system.
         * @param tracker - storage.
         */
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.findAll()) {
                System.out.printf("id: %s; name: %s\n", item.getId(), item.getName());
            }
        }
    }
}

/**
 * External class for edit item.
 */
class EditItem extends BaseAction {
    /**
     * Constructor with main args.
     *
     * @param key  - menu key.
     * @param name - menu name.
     */
    EditItem(int key, String name) {
        super(key, name);
    }

    /**
     * Execute action - edit item in tracker.
     *
     * @param input   - input system.
     * @param tracker - storage.
     */
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Enter task id for edit: ");
        Item item = tracker.findById(Integer.valueOf(id));
        item.setName(input.ask("Enter new task name: "));
        item.setDesc(input.ask("Enter new task description: "));
        tracker.update(item);
    }
}

/**
 * External class for delete item.
 */
class DeleteItem extends BaseAction {
    /**
     * Constructor with main args.
     *
     * @param key  - menu key.
     * @param name - menu name.
     */
    DeleteItem(int key, String name) {
        super(key, name);
    }

    /**
     * Execute action - delete item from tracker.
     *
     * @param input   - input system.
     * @param tracker - storage.
     */
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Enter task id to delete: ");
        tracker.delete(tracker.findById(Integer.valueOf(id)));
    }
}

/**
 * External class for return item by id.
 */
class FindItemById extends BaseAction {
    /**
     * Constructor with main args.
     *
     * @param key  - menu key.
     * @param name - menu name.
     */
    FindItemById(int key, String name) {
        super(key, name);
    }

    /**
     * Execute action - find item in tracker.
     *
     * @param input   - input system.
     * @param tracker - storage.
     */
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Enter task id to find: ");
        Item item = tracker.findById(Integer.valueOf(id));
        if (item != null) {
            System.out.printf("id: %s; name: %s\n", item.getId(), item.getName());
        } else {
            System.out.println("Task not founded.");
        }
    }
}

/**
 * External class for return item by id.
 */
class FindItemsByName extends BaseAction {
    /**
     * Constructor with main args.
     *
     * @param key  - menu key.
     * @param name - menu name.
     */
    FindItemsByName(int key, String name) {
        super(key, name);
    }

    /**
     * Execute action - find item in tracker.
     *
     * @param input   - input system.
     * @param tracker - storage.
     */
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Enter task name to find: ");
        ArrayList<Item> items = tracker.findByName(id);
        if (items != null && items.size() > 0) {
            for (Item item : items) {
                System.out.printf("id: %s; name: %s\n", item.getId(), item.getName());
            }
        } else {
            System.out.println("Task not founded.");
        }
    }
}


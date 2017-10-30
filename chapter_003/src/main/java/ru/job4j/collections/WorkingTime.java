package ru.job4j.collections;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created on 14.07.17.
 * Class for calculation working time of the collections.
 * @author Wamdue
 * @version 1.0
 */
public class WorkingTime {
    /**
     * Main constructor.
     */
    public WorkingTime() { }

    /**
     * Calculate insert working time.
     * @param collection - source collection.
     * @param amount - amount string need to insert.
     * @return - working time
     */
    public long add(Collection<String> collection, int amount) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(String.format("%s %d", collection.getClass().getName(), i));
        }
        long stopTime = System.currentTimeMillis();
        return stopTime - startTime;
    }

    /**
     * Calculate delete working time.
     * @param collection - source collection.
     * @param amount - how many records need to delete.
     * @return working time.
     */
    public long delete(Collection<String> collection, int amount) {
        long startTime = System.currentTimeMillis();
        Iterator<String> iterator = collection.iterator();
        for (int i = 0; i < amount; i++) {
            iterator.next();
            iterator.remove();
        }
        long stopTime = System.currentTimeMillis();
        return stopTime - startTime;
    }

    /**
     * Private method for information.
     * @param collection - source collection for interact.
     * @param count - number of iterations.
     */
    private void getResult(Collection<String> collection, int count) {
        System.out.println(collection.getClass().getName());
        System.out.printf("Insert: %d\n", this.add(collection, count));
        System.out.printf("Delete: %d\n", this.delete(collection, count));
    }

    /**
     * Main method, for demonstration.
     * @param args - not in use.
     */
    public static void main(String[] args) {
        WorkingTime time = new WorkingTime();
        ArrayList<String> list = new ArrayList<>();
        LinkedList<String> linked = new LinkedList<>();
        TreeSet<String> set = new TreeSet<>();
        time.getResult(list, 1000);
        time.getResult(linked, 1000);
        time.getResult(set, 1000);
    }
}

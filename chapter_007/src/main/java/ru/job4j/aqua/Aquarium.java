package ru.job4j.aqua;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created on 26.10.17.
 * Fish city.
 * @author Wamdue
 * @version 1.0
 */

public class Aquarium {
    /**
     * Fish storage.
     */
    private final List<Fish> fishList = new CopyOnWriteArrayList<>();
    /**
     * Thread for fish meetings.
     */
    private final Runnable meeting = new Meeting(this.fishList);
    /**
     * Thread for remove fish from the list.
     */
    private final Runnable deadFish = new DeadFish(this.fishList);

    /**
     * Main method for fish communication.
     */
    public void circle() {
        for (int i = 0; i < 10000; i++) {
            this.fishList.add(new Fish());
        }
        Thread first = new Thread(this.meeting);
        Thread second = new Thread(this.deadFish);
        first.start();
        second.start();

        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Популяция аквариума на момент %s составляет %d особей", new Date(), this.listSize());
        }
    }

    /**
     * Get population of community.
     * @return - storage size.
     */
    private int listSize() {
        return this.fishList.size();
    }

    /**
     * Method for visualisation.
     * @param args - not in use.
     */
    public static void main(String[] args) {
        new Aquarium().circle();
    }
}

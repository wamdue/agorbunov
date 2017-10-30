package ru.job4j.aqua;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 30.10.17.
 * Meeting event.
 * @author Wamdue
 * @version 1.0
 */
public class Meeting implements Runnable {
    /**
     * Main list to work with.
     */
    private final List<Fish> aquarium;

    /**
     * Main constructor.
     * @param aquarium - source list.
     */
    public Meeting(List<Fish> aquarium) {
        this.aquarium = aquarium;
    }

    /**
     * Method to emulate fishes dates.
     */
    @Override
    public void run() {
        while (aquarium.size() > 0) {
            Fish first = aquarium.get(ThreadLocalRandom.current().nextInt(aquarium.size()));
            Fish second = aquarium.get(ThreadLocalRandom.current().nextInt(aquarium.size()));
            if (first.getGender() != second.getGender()) {
                System.out.println(String.format("Встретились рыбки № %d и № %d", first.getId(), second.getId()));
                Fish newFish = new Fish();
                System.out.println(String.format("Родилась рыбка № %d", newFish.getId()));
                aquarium.add(newFish);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

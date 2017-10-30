package ru.job4j.aqua;

import java.util.List;

/**
 * Created on 30.10.17.
 * When Fish dies.
 * @author Wamdue
 * @version 1.0
 */
public class DeadFish implements Runnable {
    /**
     * Main list to work with.
     */
    private final List<Fish> aquarium;

    /**
     * Main constructor.
     * @param aquarium - source list.
     */
    public DeadFish(List<Fish> aquarium) {
        this.aquarium = aquarium;
    }

    /**
     * Method search fish, who need to die.
     */
    @Override
    public void run() {
        while (aquarium.size() > 0) {
            for (int i = 0; i < aquarium.size(); i++) {
                Fish victim = aquarium.get(i);
                if (victim.getLifeTime() + victim.getBirthday().getTime() < System.currentTimeMillis()) {
                    aquarium.remove(victim);
                    System.out.printf("Умерла рыбка № %d\n", victim.getId());
                    break;
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

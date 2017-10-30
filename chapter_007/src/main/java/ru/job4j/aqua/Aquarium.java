package ru.job4j.aqua;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

/**
 * Created on 26.10.17.
 * Fish city.
 * @author Wamdue
 * @version 1.0
 */

public class Aquarium {
    /**
     * Local random generator.
     */
    private Random random = new Random();
    /**
     * Fish storage.
     */
    private List<Fish> fishList = new ArrayList<>();
    /**
     * Map for correct responding on the random numbers.
     */
    private Map<Integer, Function<Integer, Boolean>> moves = new HashMap<>();

    public Aquarium() {
        this.init();
    }

    /**
     * Generates random meeting wish two fishes with opposite gender.
     */
    public Function<Integer, Boolean> meeting() {
        return id -> {
            Fish first = this.fishList.get(random.nextInt(this.listSize()));
            Fish second = this.fishList.get(random.nextInt(this.listSize()));
            boolean result = false;
            if (first.getGender() != second.getGender()) {
                System.out.println(String.format("Встретились рыбки № %d и № %d", first.getId(), second.getId()));
                Fish newFish = new Fish();
                System.out.println(String.format("Родилась рыбка № %d", newFish.getId()));
                fishList.add(newFish);
                result = true;
            }
            return result;
        };
    }

    /**
     * Kill fish who live longer then generated lifeTime.
     * @return - fish, or null if cannot find fish in storage.
     */
    public Function<Integer, Boolean> killFish() {
        return id -> {
            long currentTime = System.currentTimeMillis();
            boolean result = false;
            Fish victim = null;
            for (Fish fish : this.fishList) {
                if (fish.getLifeTime() + fish.getBirthday().getTime() < currentTime) {
                    victim = fish;
                    result = true;
                    break;
                }
            }
            if (result) {
                System.out.println(String.format("Умерла рыбка № %d", victim.getId()));
                fishList.remove(victim);
            }

            return result;
        };
    }

    /**
     * Primary initialisation.
     */
    private void init() {
        moves.put(0, meeting());
        moves.put(1, killFish());
    }

    /**
     * Main method for fish communication.
     */
    public void circle() {
        for (int i = 0; i < 10000; i++) {
            this.fishList.add(new Fish());
        }
        int counter = 0;
        while (true) {
            counter++;
            int val = this.random.nextInt(moves.size());
            if (!this.moves.get(val).apply(val)) {
                continue;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counter >= 10) {
                counter = 0;
                System.out.printf("Популяция аквариума на момент %s составляет %d особей", new Date(), this.listSize());
            }
        }
    }

    /**
     * Get population of community.
     * @return - storage size.
     */
    private int listSize() {
        return this.fishList.size();
    }

    public static void main(String[] args) {
        new Aquarium().circle();
    }
}

package ru.job4j.aqua;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
     * Add new fish to storage.
     * @return - new fish.
     */
    public Fish createFish() {
        Fish fish = new Fish();
        fishList.add(fish);
        return fish;
    }

    /**
     * Generates random meeting wish two fishes with opposite gender.
     * @return - lucky pair, or empty list, if gender is equal.
     */
    public List<Fish> meeting() {
        List<Fish> list = new ArrayList<>();
        Fish one = this.fishList.get(random.nextInt(this.listSize()));
        Fish two = this.fishList.get(random.nextInt(this.listSize()));
        if (one != two && one.getGender() != two.getGender()) {
            list.add(one);
            list.add(two);
        }
        return list;
    }

    /**
     * Kill fish who live longer then generated lifeTime.
     * @return - fish, or null if cannot find fish in storage.
     */
    public Fish killFish() {
        long currentTime = System.currentTimeMillis();
        Fish victim = null;
        for (Fish fish : this.fishList) {
            if (fish.getLifeTime() + fish.getBirthday().getTime() < currentTime) {
                victim = fish;
                break;
            }
        }
        return victim;
    }

    /**
     * Main method for fish communication.
     */
    public void circle() {
        for (int i = 0; i < 10000; i++) {
            this.createFish();
        }
        int counter = 0;
        while (true) {
            counter++;
            int val = this.random.nextInt(4);
            if (val == 0) {
                List<Fish> temp = this.meeting();
                if (temp.size() > 0) {
                    System.out.println(String.format("Встретились рыбки № %d и № %d", temp.get(0).getId(), temp.get(1).getId()));
                } else {
                    continue;
                }
            } else if (val == 1) {
                Fish fish = this.killFish();
                if (fish != null) {
                    this.fishList.remove(fish);
                    System.out.println(String.format("Умерла рыбка № %d", fish.getId()));
                } else {
                    continue;
                }
            } else if (val == 2) {
                System.out.println(String.format("Родилась рыбка № %d", this.createFish().getId()));
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
}

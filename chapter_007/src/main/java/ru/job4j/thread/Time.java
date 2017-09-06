package ru.job4j.thread;

/**
 * Created on 06.09.17
 * Class to make time limit for working source thread.
 * @author Wamdue
 * @version 1.0
 */
public class Time implements Runnable {
    /**
     * Working time limit.
     */
    private int timeLimit;
    /**
     * source thread.
     */
    private Thread victim;

    /**
     * Main constructor.
     * @param victim - thread to lookout
     * @param timeLimit - time limit in ms.
     */
    public Time(Thread victim, int timeLimit) {
        this.victim = victim;
        this.timeLimit = timeLimit;
    }

    @Override
    public void run() {
        int time = 0;
        try {
            while (time < timeLimit) {
                Thread.sleep(1);
                time++;
            }
            victim.interrupt();
        } catch (InterruptedException ex) {
            System.out.println("Time is run out.");
        }
    }
}

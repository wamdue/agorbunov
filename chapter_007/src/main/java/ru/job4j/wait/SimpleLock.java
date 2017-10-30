package ru.job4j.wait;

/**
 * Created on 18.09.17.
 * My simple lock realization.
 * @author Wamdue
 * @version 1.0
 */
public class SimpleLock {
    /**
     * Flag of lock state.
     */
    private boolean isLocked = false;

    /**
     * Making lock.
     */
    public synchronized void lock() {
        try {
            while (isLocked) {
                wait();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        isLocked = true;
    }

    /**
     * Unlocks target.
     */
    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}

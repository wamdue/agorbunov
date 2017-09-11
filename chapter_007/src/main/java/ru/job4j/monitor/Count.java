package ru.job4j.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created on 11.09.17
 * Increment value class.
 * @author Wamdue
 * @version 1.0
 */
@ThreadSafe
public class Count {
    /**
     * Lock object.
     */
    private final Object lock = new Object();
    /**
     * Source variable.
     */
    @GuardedBy("lock")
    private int inc = 0;

    /**
     * Increment method.
     * @return incremented item.
     */
    public int increment() {
        synchronized (lock) {
            return ++inc;
        }
    }
}

package ru.job4j.nonblock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created on 19.09.17
 * Simple cache class.
 * @author Wamdue
 * @version 1.0
 */
public class NonBlockCache {
    /**
     * internal map.
     */
    private ConcurrentHashMap<Integer, Task> map = new ConcurrentHashMap<>();
    /**
     * version counter.
     */
    private AtomicLong version = new AtomicLong(0);

    public void add(int id, Task task) {
        this.map.putIfAbsent(id, task);
        this.inc();
    }

    /**
     * update task if it can be fount in map.
     * @param id - id to search
     * @param task - new task.
     */
    public void update(int id, Task task) {
        long temp = this.getVersion();
        if (this.map.contains(id)){
            if (temp != this.getVersion()) {
                throw new OptimisticException();
            }
            map.computeIfPresent(id, (k, v) -> task);
            this.inc();
        }
    }

    /**
     * Version show.
     * @return current version.
     */
    private long getVersion() {
        return this.version.get();
    }

    /**
     * Delete task from cache.
     * @param id - id to delete.
     */
    public void delete(int id) {
        long temp = this.getVersion();
        if (this.map.contains(id)){
            if (temp != this.getVersion()) {
                throw new OptimisticException();
            }
            map.remove(id);
            this.inc();
        }
    }

    /**
     * method to calculate version counter.
     */
    private void inc() {
        boolean updated = false;
        while (!updated) {
            long prevVersion = this.version.get();
            updated = this.version.compareAndSet(prevVersion, prevVersion + 1);
        }
    }
}

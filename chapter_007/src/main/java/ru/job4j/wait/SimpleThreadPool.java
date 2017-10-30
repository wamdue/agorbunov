package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created on 18.09.17.
 * Simple thread pool realization.
 * @author Wamdue
 * @version 1.0
 */
@ThreadSafe
public class SimpleThreadPool {
    /**
     * Number of cores.
     */
    private int processorsCount;
    /**
     * Object monitor.
     */
    private final Object lock = new Object();
    /**
     * Flag of thread.
     */
    private boolean isRunning;
    /**
     * Main queue.
     */
    @GuardedBy("lock")
    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();

    /**
     * Main constructor.
     */
    public SimpleThreadPool() {
        init();
    }

    /**
     * init method, Creates threads == processors count.
     */
    private void init() {
        processorsCount = Runtime.getRuntime().availableProcessors();
        isRunning = true;
        for (int i = 0; i < processorsCount; i++) {
            new Thread(new TaskWorker()).start();
        }
    }

    /**
     * Adding work to queue.
     * @param work - add new work in queue and notifies threads about it.
     */
    public void add(Work work) {
        synchronized (lock) {
            queue.add(work);
            lock.notify();
        }
    }

    /**
     * Stops pool.
     */
    public void shutDown() {
        isRunning = false;
    }

    /**
     * internal class to manage tasks in queue.
     */
    private final class TaskWorker implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (isRunning) {
                    if (queue.size() == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Runnable task = queue.poll();
                        task.run();
                    }
                }
            }
        }
    }
}

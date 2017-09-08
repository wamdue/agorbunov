package ru.job4j.thread;

/**
 * Created on 08.09.17
 * Class for emulation parallel changing value/
 * @author Wamdue
 * @version 1.0
 */
public class Concurrent implements Runnable {
    /**
     * Class that we want to change.
     */
    private Dummy dummy;
    /**
     * new String for dummy class.
     */
    private String name;

    public Concurrent(Dummy dummy, String name) {
        this.dummy = dummy;
        this.name = name;
    }

    @Override
    public void run() {
        dummy.setName(name);
    }

    public static void main(String[] args) {
        Dummy dummy = new Dummy("Dummy");
        Concurrent first = new Concurrent(dummy, "First");
        Concurrent second = new Concurrent(dummy, "Second");
        Thread t1 = new Thread(first);
        Thread t2 = new Thread(second);
        t1.start();
        t2.start();
        System.out.println(dummy.getName());
    }
}

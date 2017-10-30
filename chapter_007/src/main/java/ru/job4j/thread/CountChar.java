package ru.job4j.thread;

/**
 * Created on 06.09.17.
 * Class to count chars int text.
 * @author Wamdue
 * @version 1.0
 */

public class CountChar implements Runnable {
    /**
     * Source text.
     */
    private String text;

    /**
     * Main constructor.
     * @param text - source text.
     */
    public CountChar(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        int count = 0;
        try {
            for (int i = 0; i < text.length(); i++) {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                count++;
                Thread.sleep(50);
            }
            System.out.printf("Chars in text : %d\n", count);
        } catch (InterruptedException ex) {
            System.out.println("Calculation takes to long, interrupted. ");
        }
    }

    /**
     * Main method to demonstrate programm.
     * @param args - arguments in command line.
     */
    public static void main(String[] args) {
        Thread countThread = new Thread(new CountChar("Hello my dear friend."));
        Thread timeThread = new Thread(new Time(countThread, 100));
        timeThread.setDaemon(true);
        timeThread.start();
        countThread.start();
    }
}

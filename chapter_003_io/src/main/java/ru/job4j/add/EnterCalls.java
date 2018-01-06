package ru.job4j.add;

import java.io.InputStream;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created on 06.01.18.
 * User input class.
 * @author Wamdue
 * @version 1.0
 */
public class EnterCalls implements Runnable {
    /**
     * Queue of cabin calls.
     */
    private final Queue<Integer> cabinCalls;
    /**
     * Queue of floor calls.
     */
    private final Queue<Integer> floorCalls;
    /**
     * Mark of cabin.
     */
    private static final String CABIN = "c";
    /**
     * Mark of floor.
     */
    private static final String FLOOR = "f";
    /**
     * Stop word.
     */
    private static final String STOP = "off";
    /**
     * Input method.
     */
    private final InputStream is;

    /**
     * Main constructor.
     * @param cabinCalls - queue of calls from cabin.
     * @param floorCalls - queue of calls on floor.
     * @param is - input method.
     */
    public EnterCalls(Queue<Integer> cabinCalls, Queue<Integer> floorCalls, InputStream is) {
        this.cabinCalls = cabinCalls;
        this.floorCalls = floorCalls;
        this.is = is;
    }

    /**
     * Main class to enter request from users.
     */
    @Override
    public void run() {
        String line;
        boolean isWorking = true;
        Scanner scanner = new Scanner(this.is);
        while (isWorking) {
            line = scanner.nextLine();

            if (STOP.equals(line)) {
                isWorking = false;
                continue;
            }

            if (line.split(" ").length > 1) {
                String status = line.split(" ")[0];
                int floor = Integer.valueOf(line.split(" ")[1]);
                if (CABIN.equals(status)) {
                    this.cabinCalls.offer(floor);
                } else if (FLOOR.equals(status)) {
                    this.floorCalls.offer(floor);
                } else {
                    System.out.println("Incorrect format, must be 'c' or 'f' and number after space.");
                }
            }
        }
    }
}

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
     * Queue of calls.
     */
    private final Queue<Call> calls;
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
     * @param calls - queue of calls.
     * @param is - input method.
     */
    public EnterCalls(Queue<Call> calls, InputStream is) {
        this.calls = calls;
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
                if (CABIN.equals(status) || FLOOR.equals(status)) {
                    this.calls.offer(new Call(floor, status.charAt(0)));
                } else {
                    System.out.println("Incorrect format, must be 'c' or 'f' and number after space.");
                }
            }
        }
    }
}

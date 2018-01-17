package ru.job4j.add;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created on 25.12.17.
 * Additional task, lift emulation.
 * @author Wamdue
 * @version 1.0
 */
public class Lift {
    /**
     * Number of floors.
     */
    private int floors;
    /**
     * Time needed to move to another floor.
     */
    private int floorPassTime;
    /**
     * Time spend on open/close doors.
     */
    private int openClose;
    /**
     * Get current floor.
     */
    private int currentFloor = 1;
    /**
     * Close doors string.
     */
    private static final String CLOSE = "Двери закрываются";
    /**
     * Open doors string.
     */
    private static final String OPEN = "Двери открываются";
    /**
     * Floor number string.
     */
    private static final String FLOOR = "Этаж № %d\n";
    /**
     * Queue of calls.
     */
    private final Queue<Call> calls;


    /**
     * Main constructor.
     * @param floors - number of floors, must be between 5 and 20.
     * @param floorHeight - height of the floor.
     * @param liftSpeed - lift speed.
     * @param openClose - open/close lift doors.
     * @param calls - cabin calls queue.
     */
    public Lift(int floors, double floorHeight, double liftSpeed, double openClose, Queue<Call> calls) {
        this.floors = floors;
        this.calls = calls;
        this.floorPassTime = (int) ((floorHeight / liftSpeed) * 1000);
        this.openClose = (int) (openClose * 1000);
    }

    /**
     * Lift moving up.
     * @param num - floor number.
     */
    private void moveUp(int num) {
        if (num <= this.floors) {
            int c = num - this.currentFloor;
            try {
                System.out.println(CLOSE);
                Thread.sleep(this.openClose);
                for (int i = 0; i < c; i++) {
                    Thread.sleep(this.floorPassTime);
                    System.out.printf(FLOOR, ++this.currentFloor);
                }
                System.out.println(OPEN);
                Thread.sleep(this.openClose);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Так высоко подняться не могу.");
        }
    }

    /**
     * Lift moving down.
     * @param num - floor number.
     */
    private void moveDown(int num) {
        if (num > 0) {
            int c = this.currentFloor - num;
            try {
                System.out.println(CLOSE);
                Thread.sleep(this.openClose);
                for (int i = c; i > 0; i--) {
                    Thread.sleep(this.floorPassTime);
                    System.out.printf(FLOOR, --this.currentFloor);
                }
                System.out.println(OPEN);
                Thread.sleep(this.openClose);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("В подвал лифт не ходит.");
        }

    }

    /**
     * Main loop method.
     */
    public void start() {
        System.out.printf("Лифт стоит на %d этаже\n", this.currentFloor);
        int position = 1;
        while (position > 0) {
            if (this.calls.size() == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

                position = this.calls.poll().getFloor();

            if (position <= 0) {
                System.out.println("Lift stopped");
                continue;
            }

            if (position > this.currentFloor) {
                this.moveUp(position);
            } else if (position < this.currentFloor) {
                this.moveDown(position);
            }
        }
    }

    /**
     * Main method.
     * @param args not in use.
     */
    public static void main(String[] args) {
        Queue<Call> calls = new PriorityBlockingQueue<>();
        Thread thread = new Thread(new EnterCalls(calls, System.in));
        thread.start();
        Lift lift = new Lift(20, 3.5, 1.1, 3, calls);
        lift.start();
    }

}


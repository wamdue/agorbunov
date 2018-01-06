package ru.job4j.add;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

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
     * Queue of calls from the cabin.
     */
    private final Queue<Integer> cabinCalls;
    /**
     * Queue of calls makes on floors.
     */
    private final Queue<Integer> floorCalls;


    /**
     * Main constructor.
     * @param floors - number of floors, must be between 5 and 20.
     * @param floorHeight - height of the floor.
     * @param liftSpeed - lift speed.
     * @param openClose - open/close lift doors.
     * @param cabinCalls - cabin calls queue.
     * @param floorCalls - floor calls queue.
     */
    public Lift(int floors, double floorHeight, double liftSpeed, double openClose, Queue<Integer> cabinCalls, Queue<Integer> floorCalls) {
        this.floors = floors;
        this.cabinCalls = cabinCalls;
        this.floorCalls = floorCalls;
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
            if (this.cabinCalls.size() == 0 && this.floorCalls.size() == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            if (this.cabinCalls.size() > 0) {
                position = this.cabinCalls.poll();
            } else {
                position = this.floorCalls.poll();
            }

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
        Queue<Integer> cabinCalls = new ConcurrentLinkedQueue<>();
        Queue<Integer> floorCalls = new ConcurrentLinkedQueue<>();
        Thread thread = new Thread(new EnterCalls(cabinCalls, floorCalls, System.in));
        thread.start();
        Lift lift = new Lift(20, 3.5, 1.1, 3, cabinCalls, floorCalls);
        lift.start();
    }

}


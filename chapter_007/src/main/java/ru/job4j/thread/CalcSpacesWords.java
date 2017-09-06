package ru.job4j.thread;

/**
 * Created on 06.09.17
 * Calculation class.
 * @author Wamdue
 * @version 1.0
 */
public class CalcSpacesWords {
    /**
     * Text to analise.
     */
    private  String text;

    /**
     * Main constructor.
     * @param text - text to analise.
     */
    public CalcSpacesWords(String text) {
        this.text = text;
    }

    /**
     * Thread to calculate amount of spaces in text.
     */
    private class CalcSpaces implements Runnable {
        private final String text;

        public CalcSpaces(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            int count = text.length() - text.replace(" ", "").length();
            System.out.printf("Spaces: %d;\n", count);
        }
    }

    /**
     * Thread to calculate amount of words in text.
     */
    private class CalcWords implements Runnable {
        private final String text;

        public CalcWords(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            String[] words = text.split(" ");
            System.out.printf("Words: %d;\n", words.length);
        }
    }

    /**
     * Demon class to watch for calculation time.
     */
    private class Demon extends Thread {
        private Thread spaces;
        private Thread words;

        public Demon(Thread spaces, Thread words) {
            this.spaces = spaces;
            this.words = words;
        }
        @Override
        public void run() {
            try {
                sleep(1000);
                if (spaces.isAlive() || words.isAlive()) {
                    spaces.interrupt();
                    words.interrupt();
                }
            } catch (InterruptedException e) {
                System.out.println("Calulation takes to long, interrupting!");
            }
        }
    }

    /**
     * Async calculation spaces and words.
     */
    public void calc() {
        System.out.println("Calculations starts.");

        Thread calcEnd = new Thread(() -> System.out.println("Calculation finished."));

        Thread spaces = new Thread(new CalcSpaces(text));
        Thread words = new Thread(new CalcWords(text));
        Demon demon = new Demon(spaces, words);

        demon.setDaemon(true);
        demon.start();

        spaces.start();
        words.start();
        try {
            spaces.join();
            words.join();
            if (spaces.isAlive() || words.isAlive())
            {
                spaces.interrupt();
                words.interrupt();
                System.out.println("Calculation takes to long time, interrupting.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        calcEnd.start();
    }

    public static void main(String[] args) {
        new CalcSpacesWords("Hello my dear friend.").calc();
    }
}
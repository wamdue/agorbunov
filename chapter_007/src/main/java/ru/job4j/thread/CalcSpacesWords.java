package ru.job4j.thread;

/**
 * Created on 06.09.17.
 * Calculation class.
 *
 * @author Wamdue
 * @version 1.0
 */
public class CalcSpacesWords {
    /**
     * Text to analise.
     */
    private String text;

    /**
     * Main constructor.
     *
     * @param text - text to analise.
     */
    public CalcSpacesWords(String text) {
        this.text = text;
    }

    /**
     * Thread to calculate amount of spaces in text.
     */
    private class CalcSpaces implements Runnable {
        /**
         * Source string to analize.
         */
        private final String text;

        /**
         * Main constructor.
         * @param text - source string.
         */
        CalcSpaces(String text) {
            this.text = text;
        }

        /**
         * Calculating spaces in string.
         */
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
        /**
         * Source string to analise.
         */
        private final String text;

        /**
         * Main constructor.
         * @param text - source string.
         */
        CalcWords(String text) {
            this.text = text;
        }

        /**
         * Calculating words in string.
         */
        @Override
        public void run() {
            long beginTime = System.currentTimeMillis();
            String[] words = text.split(" ");
            long endTime = System.currentTimeMillis();
            System.out.printf("Regex time: %d;\n", endTime - beginTime);
            int count = 0;
            beginTime = System.currentTimeMillis();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (c == ' ' || c == '.' || c == '!' || c == '?') {
                    count++;
                }
            }
            endTime = System.currentTimeMillis();
            System.out.printf("For time: %d;\n", endTime - beginTime);
            System.out.printf("Words: %d;\n", words.length);
            System.out.printf("Count word: %d;\n", count);
        }
    }

    /**
     * Demon class to watch for calculation time.
     */
    private class Demon extends Thread {
        /**
         * First thread to calculate spaces.
         */
        private Thread spaces;
        /**
         * Second thread to calculate words.
         */
        private Thread words;

        /**
         * Main constructor.
         * @param spaces - first thread.
         * @param words - second thread.
         */
        Demon(Thread spaces, Thread words) {
            this.spaces = spaces;
            this.words = words;
        }

        /**
         * Watching for work of two threads.
         */
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
            if (spaces.isAlive() || words.isAlive()) {
                spaces.interrupt();
                words.interrupt();
                System.out.println("Calculation takes to long time, interrupting.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        calcEnd.start();
    }

    /**
     * Main method for demonstrating work.
     * @param args - arguments in command line.
     */
    public static void main(String[] args) {
        new CalcSpacesWords("Hello my dear friend.").calc();
    }
}
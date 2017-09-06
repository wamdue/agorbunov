package ru.job4j.thread;

/**
 * Created on 06.09.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class CalcSpacesWords {
    private  String text;

    public CalcSpacesWords(String text) {
        this.text = text;
    }

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

    public void calc() {
        new Thread(new CalcSpaces(text)).start();
        new Thread(new CalcWords(text)).start();
    }

    public static void main(String[] args) {
        new CalcSpacesWords("Hello my dear friend.").calc();
    }
}

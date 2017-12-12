package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created on 12.12.17.
 * Simple console chat realisation.
 * @author Wamdue
 * @version 1.0
 */
public class ConsoleChat {
    /**
     * List of possible answers.
     */
    private List<String> answers;
    /**
     * File to write logs.
     */
    private final File chatLog = new File("log.txt");
    /**
     * Random generator.
     */
    private final Random random = new Random();

    /**
     * Main constructor.
     */
    public ConsoleChat() {
        try {
            this.answers = Files.readAllLines(Paths.get("answers.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start chat method.
     */
    public void startChat() {
        boolean isConversation = true;
        boolean isWorking = true;
        Scanner scanner = new Scanner(System.in);
        String line;
        try (FileOutputStream out = new FileOutputStream(chatLog, true);
             PrintStream stream = new PrintStream(out)) {

            while (isWorking) {
                line = scanner.nextLine();
                stream.println(line);
                if (line.equals("стоп")) {
                    isConversation = false;
                    continue;
                }

                if (line.equals("продолжить")) {
                    isConversation = true;
                    continue;
                }

                if (line.equals("закончить")) {
                    isWorking = false;
                    continue;
                }
                if (isConversation) {
                    String answer = this.getAnswer();
                    System.out.println(answer);
                    stream.println(answer);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Answer generator.
     * @return - generated answer.
     */
    private String getAnswer() {
        return answers.get(random.nextInt(answers.size()));
    }

    /**
     * Main method.
     * @param args - not in use.
     */
    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat();
        chat.startChat();
    }
}

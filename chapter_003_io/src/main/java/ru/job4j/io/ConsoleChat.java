package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
     * Stop word to stop answering on questions.
     */
    private static final String STOP = "стоп";
    /**
     * Word to continue answer on queastions.
     */
    private static final String CONTINUE = "продолжить";
    /**
     * Word to stop chat.
     */
    private static final String END = "закончить";

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
            File file = new File("answers.txt");
            this.answers = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start chat method.
     * @param inputStream - stream to enter words.
     * @param outputStream - output stream (console or other).
     */
    public void startChat(InputStream inputStream, OutputStream outputStream) {
        if (answers.size() > 0) {
            boolean isConversation = true;
            boolean isWorking = true;
            Scanner scanner = new Scanner(inputStream);
            String line;
            System.setOut(new PrintStream(outputStream));
            try (FileOutputStream out = new FileOutputStream(chatLog, true);
                 PrintStream stream = new PrintStream(out)) {

                while (isWorking) {
                    line = scanner.nextLine();
                    stream.println(line);
                    if (STOP.equals(line)) {
                        isConversation = false;
                        continue;
                    }

                    if (CONTINUE.equals(line)) {
                        isConversation = true;
                        continue;
                    }

                    if (END.equals(line)) {
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
        } else {
            System.out.println("No answers in file.");
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
        chat.startChat(System.in, System.out);
    }
}

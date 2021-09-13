package io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String pathBotAnswers;
    private final String pathWhenWeWriteChatLog;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final List<String> listLogChat;
    private boolean flag = true;

    public ConsoleChat(String path, String botAnswers) {
        this.pathWhenWeWriteChatLog = path;
        this.pathBotAnswers = botAnswers;
        listLogChat = new ArrayList<>();
    }

    public void run() {
        List<String> listBotPhrases = readPhrases();
        Scanner in = new Scanner(System.in);
        String outPrintRobo;
        while (flag) {
            String inner = in.nextLine();
            listLogChat.add(inner);
            if (inner.equals(STOP)) {
                this.saveLog(listLogChat);
                System.out.println("You are stopping program");
                listLogChat.add("You are stopping program");
                inner = in.nextLine();
                while (!inner.equals(CONTINUE)) {
                    this.saveLog(listLogChat);
                    listLogChat.add(inner);
                    System.out.println("Напишите -продолжить- для продолжения чата");
                    listLogChat.add("Напишите -продолжить- для продолжения чата");
                    inner = in.nextLine();
                }
                listLogChat.add(inner);
                outPrintRobo = listBotPhrases.get((int) (Math.random() * listBotPhrases.size()));
                listLogChat.add(outPrintRobo);
                System.out.println(outPrintRobo);
                continue;
            } else if (inner.equals(OUT)) {
                flag = false;
                break;
            }
            outPrintRobo = listBotPhrases.get((int) (Math.random() * listBotPhrases.size()));
            listLogChat.add(outPrintRobo);
            System.out.println(outPrintRobo);
        }
        in.close();
        this.saveLog(listLogChat);
    }

    private List<String> readPhrases() {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathBotAnswers, StandardCharsets.UTF_8))) {
            br.lines().forEach(stringList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(pathWhenWeWriteChatLog, StandardCharsets.UTF_8))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/main/java/io/data/logChat2.txt", "./src/main/java/io/data/text.txt");
        cc.run();
    }
}
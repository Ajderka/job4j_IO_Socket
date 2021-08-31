package io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String pathBotAnswers;
    private final String pathWhenWeWriteChatLog;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> listLogChat;

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
                System.out.println("You are stopping program");
                listLogChat.add("You are stopping program");
                while (!in.nextLine().equals(CONTINUE)) {
                    listLogChat.add(String.valueOf(in));
                    System.out.println("Напишите -продолжить- для продолжения чата");
                    listLogChat.add("Напишите -продолжить- для продолжения чата");
                }
                outPrintRobo = listBotPhrases.get((int) (Math.random() * (4 + 1)));
                listLogChat.add(outPrintRobo);
                System.out.println(outPrintRobo);
                continue;
            } else if (inner.equals(OUT)) {
                flag = false;
                break;
            }
            outPrintRobo = listBotPhrases.get((int) (Math.random() * (4 + 1)));
            listLogChat.add(outPrintRobo);
            System.out.println(outPrintRobo);
        }
        in.close();
        this.saveLog(listLogChat);
    }

    private List<String> readPhrases() {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathBotAnswers, Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(line -> stringList.add(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(pathWhenWeWriteChatLog, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(element -> pw.println(element));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/main/java/io/data/logChat.txt", "./src/main/java/io/data/text.txt");
        cc.run();
    }
}

//1. В этом задании необходимо создать программу 'Консольный чат'. Некоторое описание:
//
//        - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
//        - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
//        - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
//        - при вводе слова «закончить» программа прекращает работу.
//        - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
//Т.е. класс принимает в конструктор 2 параметра - имя файла, в который будет записан весь диалог между ботом и пользователем, и имя файла в котором находятся строки с ответами, которые будет использовать бот. Вам нужно реализовать методы:
//
//        - run(), содержит логику чата;
//        - readPhrases(), читает фразы из файла;
//        - saveLog(), сохраняет лог чата в файл.
package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private static final int ERROR_CODE = 404;

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String error : log) {
                out.printf("%s%n", error);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String s;
            while ((s = in.readLine()) != null) {
                String[] split = s.split(" ");
                int someError = Integer.parseInt(split[split.length - 2]);
                if (someError == ERROR_CODE) {
                    list.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> log = filter("src/main/java/io/data/log.txt");
        save(log, "src/main/java/io/data/404.txt");
    }
}

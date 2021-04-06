package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//нужно собрать все то что напарсили в список и передать его в метод для записи

public class Analyze {

    private void writingToFile(List<String> list, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (int i = 0; i <= list.size(); i += 2) {
                out.println("beginning at " + list.get(i) + " end at " + list.get(i + 1));
            }
        } catch (Exception e) {
            System.out.println("Ошибка записи в файл");
        }
    }

    public void unavailable(String source, String target) {
        List<String> list = new ArrayList<>();
        boolean readyToLineSeparator = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String s;
            while ((s = reader.readLine()) != null) {
                String[] split = s.split(" ");
                int type = Integer.parseInt(split[0]);
                if ((type == 400 || type == 500) && readyToLineSeparator) {
                    list.add(split[1]);
                    readyToLineSeparator = false;
                } else if ((type == 300 || type == 200) && !readyToLineSeparator) {
                    list.add(split[1]);
                    readyToLineSeparator = true;
                }
            }
        } catch (IOException ioException) {
            System.out.println("Ошибка ввода-вывода");
        }
        writingToFile(list, target);
    }
}

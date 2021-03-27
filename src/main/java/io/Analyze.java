package io;

import java.io.*;

public class Analyze {
    public void unavailable(String source, String target) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean readyToLineSeparator = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String s;
            while ((s = reader.readLine()) != null) {
                String[] strings = s.split(" ");
                int type = Integer.parseInt(strings[0]);
                if ((type == 400 || type == 500) && readyToLineSeparator) {
                    stringBuilder.append("beginning ").append(strings[1]).append("; ");
                    readyToLineSeparator = false;
                } else if ((type == 300 || type == 200) && !readyToLineSeparator) {
                    stringBuilder.append("end ").append(strings[1]).append(";").append(System.lineSeparator());
                    readyToLineSeparator = true;
                    try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
                        out.println(stringBuilder);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException ioException) {
            System.out.println("Ошибка ввода-вывода");
        }
    }
}

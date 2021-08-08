package io.data;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            String z;
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    z = Integer.toString(i * j);
                    out.write((z + " ").getBytes(StandardCharsets.UTF_8));
                }
                out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
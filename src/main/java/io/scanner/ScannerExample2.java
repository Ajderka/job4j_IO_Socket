package io.scanner;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ScannerExample2 {
    public static void main(String[] args) {
        var data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        var scan = new Scanner(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8))).useDelimiter(", ");
        while (scan.hasNext()) {
            System.out.println(scan.next());
            System.out.println("-----");
        }
    }
}

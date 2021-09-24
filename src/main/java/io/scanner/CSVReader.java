package io.scanner;

import java.io.*;
import java.util.*;

public class CSVReader {

    private final List<Integer> column = new ArrayList<>();
    private final String pathIn;
    private final String pathOut;
    private final String delimiter;
    private final List<String> filter;
    private final StringBuilder stringBuilder = new StringBuilder();

    public CSVReader(ArgsScanner argsScanner) {
        this.validateDir(argsScanner.getPathToRecourseFile());
        this.pathIn = argsScanner.getPathToRecourseFile();
        this.delimiter = argsScanner.getDelimiter();
        this.filter = List.of(argsScanner.getFilter().split(delimiter));
        this.pathOut = argsScanner.getOutput();

    }

    public static void handle(ArgsScanner argsScanner) {
        CSVReader csvReader = new CSVReader(argsScanner);
        csvReader.filterColumn();
        csvReader.callRecourses();
        csvReader.callOutFunction();
    }

    private void validateDir(String directory) {
        if (directory.length() == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        File file = new File(directory);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        System.out.println("Validate complete");
    }

    private void callRecourses() {
        try (Scanner scanner = new Scanner(new File(pathIn))) {
            while (scanner.hasNextLine()) {
                String[] strings = scanner.nextLine().split(delimiter);
                for (Integer i : column) {
                    stringBuilder.append(strings[i]).append(";");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(stringBuilder);
    }

    private void filterColumn() {
        try (Scanner scanner = new Scanner(new File(pathIn))) {
            if (scanner.hasNextLine()) {
                String[] strings = scanner.nextLine().split(delimiter);
                for (int i = 0; i < strings.length; i++) {
                    if (filter.contains(strings[i])) {
                        column.add(i);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void callOutFunction() {
        if (pathOut.equals("stdout")) {
            System.out.println(column);
        } else {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathOut))) {
                bufferedWriter.write(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
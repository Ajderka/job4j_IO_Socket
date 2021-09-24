package io.scanner;

import java.util.HashMap;
import java.util.Map;

public class ArgsScanner {

    private final Map<String, String> stringMap = new HashMap<>();

    public String getPathToRecourseFile() {
        return stringMap.get("-path");
    }

    public String getDelimiter() {
        return stringMap.get("-delimiter");
    }

    public String getOutput() {
        return stringMap.get("-out");
    }

    public String getFilter() {
        return stringMap.get("-filter");
    }

    public static ArgsScanner of(String[] args) {
        ArgsScanner parseStringInfo = new ArgsScanner();
        parseStringInfo.parse(args);
        return parseStringInfo;
    }

    private void parse(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("The command line is not complete");
        }
        for (int i = 0; i <= 3; i++) {
            String delimiter = "=";
            String[] subStr = args[i].split(delimiter, 2);
            if (i == 0 && subStr[0].equals("-path") && !subStr[1].isEmpty()) {
                stringMap.put(subStr[0], subStr[1]);
            } else if (i == 1 && subStr[0].equals("-delimiter") && !subStr[1].isEmpty()) {
                stringMap.put(subStr[0], subStr[1]);
            } else if (i == 2 && subStr[0].equals("-out") && !subStr[1].isEmpty()) {
                stringMap.put(subStr[0], subStr[1]);
            } else if (i == 3 && subStr[0].equals("-filter") && !subStr[1].isEmpty()) {
                stringMap.put(subStr[0], subStr[1]);
            } else {
                throw new IllegalArgumentException("The command line is not correct");
            }
        }
    }
}

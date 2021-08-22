package io.archiving;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> stringMap = new HashMap<>();

    public String getDirectory () {
        return stringMap.get("-d");
    }

    public String getExclude () {
        return stringMap.get("-e");
    }

    public String getOutput () {
        return stringMap.get("-o");
    }

    private void validateDir(String directory) {
        if (directory.length() == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        File file = new File(directory);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    private void parse(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("The command line is not complete");
        }
        for (int i = 0; i <= 2; i++) {
            String delimiter = "=";
            String[] subStr = args[i].split(delimiter, 2);
            if (i == 0 && subStr[0].equals("-d") && !subStr[1].isEmpty()) {
                stringMap.put(subStr[0], subStr[1]);
            } else if (i == 1 && subStr[0].equals("-e") && !subStr[1].isEmpty()) {
                stringMap.put(subStr[0], subStr[1]);
            } else if (i == 2 && subStr[0].equals("-o") && !subStr[1].isEmpty()) {
                stringMap.put(subStr[0], subStr[1]);
            } else {
                throw new IllegalArgumentException("The command line is not correct");
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        names.validateDir(names.getDirectory());
        System.out.println("Validate complete");
        return names;
    }
}
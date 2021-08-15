package io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("args size < 2");
        }
        for (String s : args) {
            String delimiter = "=";
            String[] subStr = s.split(delimiter, 2);
            if (subStr[0].isEmpty() || subStr[1].isEmpty()) {
                throw new IllegalArgumentException("args size < 2");
            }
            subStr[0] = subStr[0].substring(1);
            if (!subStr[0].contains("encoding")) {
                values.put(subStr[0], subStr[1]);
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
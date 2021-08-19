package io.archiving;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        } else if (args.length == 1) {
            throw new IllegalArgumentException("Incomplete input. Enter the path and extension.");
        }
        Path start = Paths.get(args[0]);
        List<Path> pathList = search(start, p -> p.toFile().getName().endsWith(args[1]));
        for (Path p : pathList) {
            System.out.println(p.toAbsolutePath());
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPathList();
    }
}
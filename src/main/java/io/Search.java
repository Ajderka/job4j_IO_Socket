package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args[0] == null) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        } else if (args[1] == null) {
            throw new IllegalArgumentException("There is no file extension.");
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

    /*Программа должна запускаться с параметрами. Первый параметр - начальная папка. Второй параметр -
     расширение файлов, которые нужно искать. Необходимо добавить валидацию данных параметров.*/
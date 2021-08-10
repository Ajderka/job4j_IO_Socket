package io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashSet<FileProperty> propertySet;
    private List<FileProperty> duplicate;

    public DuplicatesVisitor() {
        propertySet = new HashSet<>();
        duplicate = new ArrayList<>();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        if (propertySet.contains(fileProperty) && !duplicate.contains(fileProperty)) {
            duplicate.add(fileProperty);
            System.out.println(fileProperty.getName());
        } else {
            propertySet.add(fileProperty);
        }
        return super.visitFile(file, attrs);
    }
}
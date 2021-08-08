package io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private FileProperty fileProperty;
    private List<FileProperty> propertyList;
    private List<FileProperty> duplicate;

    public DuplicatesVisitor() {
        propertyList = new ArrayList<>();
        duplicate = new ArrayList<>();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!attrs.isDirectory()) {
            fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
            if (propertyList.contains(fileProperty)) {
                duplicate.add(fileProperty);
                System.out.println(fileProperty.getName());
            } else {
                propertyList.add(fileProperty);
            }
        }
        return super.visitFile(file, attrs);
    }
}
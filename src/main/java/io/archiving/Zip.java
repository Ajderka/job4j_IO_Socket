package io.archiving;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private final ArgsName argsName;
    private List<Path> pathList;

    public Zip(String[] args) {
        this.argsName = ArgsName.of(args);
        this.validateDir(argsName.getDirectory());
    }

    public void setPathList(List<Path> pathList) {
        this.pathList = pathList;
    }

    public List<Path> getPathList() {
        return pathList;
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
        System.out.println("Validate complete");
    }

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path f : sources) {
                zip.putNextEntry(new ZipEntry((f.toFile().getAbsoluteFile().getPath())));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(f)))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zipClass = new Zip(args);
        zipClass.setPathList(Search.search(Paths.get(zipClass.argsName.getDirectory()), p -> !p.equals(zipClass.argsName.getExclude())));
        packFiles(zipClass.getPathList(), new File(zipClass.argsName.getOutput()));
    }
}

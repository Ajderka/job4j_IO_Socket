package io.scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class CSVReader {
    private Map<Integer, String> infoMap = new HashMap<>();
    private int indexCount = 0;
    private ArgsScanner argsScanner;
    private final String[] args;
    private List<String> column = new ArrayList<>();
    private final List<Integer> indexList = new ArrayList<>();

    public void setArgsScanner(ArgsScanner argsScanner) {
        this.argsScanner = argsScanner;
    }

    public String[] getArgs() {
        return args;
    }

    public CSVReader(String[] args) {
        this.args = args;
    }

    private void runLogic() {
        this.callRecourses();
        this.filterColumn();
        this.callOutFunction();
    }

    private void callRecourses() {
        try (Scanner scanner = new Scanner(new File(argsScanner.getPathToRecourseFile())).useDelimiter(argsScanner.getDelimiter())) {
            while (scanner.hasNext()) {
                infoMap.put(indexCount++, scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(infoMap);
    }

    private void filterColumn() {
        try (Scanner scanner = new Scanner(argsScanner.getFilter()).useDelimiter(argsScanner.getDelimiter())) {
            for (Map.Entry<Integer, String> entry : infoMap.entrySet()) {
                if (!scanner.hasNext()) {
                    break;
                } else if (entry.getValue().equals(scanner.next())) {
                    indexList.add(entry.getKey());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(indexList);
        for (int i = 0; i < indexCount; i += 5) {
            for (Integer integer : indexList) {
                column.add(infoMap.get(i + integer));
            }
        }
        System.out.println(column);
    }

    private void callOutFunction() {
        if (argsScanner.getOutput().equals("stdout")) {
            System.out.println(column);
        } else {
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(argsScanner.getOutput()))) {
                for (String s : column) {
                    pw.println(s);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader(new String[]{"-path=C:\\Project\\job4j_IO_Socket\\src\\main\\java\\io\\scanner\\recourses.csv",
                "-delimiter=;", "-out=C:\\Project\\job4j_IO_Socket\\src\\main\\java\\io\\scanner\\output.txt", "-filter=name;age;education"});
        csvReader.setArgsScanner(ArgsScanner.of(csvReader.getArgs()));
        csvReader.runLogic();
    }
}
//2. Создать класс CSVReader. Задача класса прочитать данные из CSV файла и вывести их.
// В качестве входных данных задается путь к файлу path, разделитель delimiter, приемник данных out и фильтр по столбцам filter.
// Ключ -out имеет только два допустимых значения stdout или путь к файлу, куда нужно вывести.
// Например, если есть файл CSV со столбцами name, age, birthDate, education, children и программа запускается таким образом:
//        java -jar target/csvReader.jar -path=file.csv -delimiter=";"  -out=stdout -filter=name,age
//        то программа должна прочитать файл по пути file.txt и вывести только столбцы name, age в консоль. В качестве разделителя данных выступает ;
//        3. Для чтения аргументов использовать класс Args и задания "5.1. Именованные аргументы".
//        4. Программа должна запускаться с консоли в виде jar файла как показано в примере.
//        5. Для чтения файла использовать класс Scanner.
//        6. Добавить валидацию аргументов, как в классе Search.
//        7. Залить код на Github, приложить ссылку и перевести ответственного на Петр Арсентьева.
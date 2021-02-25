package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Теперь можно будет добавить новый файл, например, с комментариями и написать с ним тест.
 * Обратите внимание, что каждый метод теста будет работать со своим вариантов файла. В этом тесте не будет полей класса.
 * <p>
 * Задание.
 * <p>
 * 1. Реализуйте метод load по аналогии с методом toString. Метод load должен загружать пару ключ-значение в Map values.
 * 2. Реализуйте метод value(String key) он должен возвращать значение ключа.
 * 3. Напишите тест ConfigTest.
 * 4. Дополните тест проверками на чтение файла с комментарием, а также файла с нарушением шаблона ключ=значение (напр. ключ=)
 * в этом случае нужно ожидать исключение IllegalArgumentException
 **/
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public String getValues(String s) {
        return values.get(s);
    }

    public void load() throws IllegalArgumentException {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(s -> !s.isEmpty() && s.contains("="))
                    .forEach(s -> {
                        String[] strings = s.split("=");
                        if (strings.length <= 1) {
                            throw new IllegalArgumentException("Not good");
                        }
                        values.put(strings[0], strings[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(newElement -> out.add(newElement));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("src/main/java/io/data/pair_without_comment.properties"));
    }
}
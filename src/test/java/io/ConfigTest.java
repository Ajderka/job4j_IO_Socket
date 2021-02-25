package io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "src/main/java/io/data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.getValues("Ajderka"), is("student"));
        assertThat(config.getValues("Ivanna"), is("123"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "src/main/java/io/data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.getValues("Ajderka"), is("34"));
        assertThat(config.getValues("Ivanna"), is("35"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairNotFullComment() throws IllegalArgumentException{
        String path = "src/main/java/io/data/pair_notFull.properties";
        Config config = new Config(path);
        config.load();
    }
}

/**
 Теперь можно будет добавить новый файл, например, с комментариями и написать с ним тест.
 Обратите внимание, что каждый метод теста будет работать со своим вариантов файла. В этом тесте не будет полей класса.

 Задание.

 1. Реализуйте метод load по аналогии с методом toString. Метод load должен загружать пару ключ-значение в Map values.
 2. Реализуйте метод value(String key) он должен возвращать значение ключа.
 3. Напишите тест ConfigTest.
 4. Дополните тест проверками на чтение файла с комментарием, а также файла с нарушением шаблона ключ=значение (напр. ключ=)
 в этом случае нужно ожидать исключение IllegalArgumentException
 **/
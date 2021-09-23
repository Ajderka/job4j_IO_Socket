package io;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "src/main/java/io/data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.getValues("Ajderka"), ("student"));
        assertEquals(config.getValues("Ivanna"), ("123"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "src/main/java/io/data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.getValues("Ajderka"), ("34"));
        assertEquals(config.getValues("Ivanna"), ("35"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairNotFullComment() throws IllegalArgumentException {
        String path = "src/main/java/io/data/pair_notFull.properties";
        Config config = new Config(path);
        config.load();
    }
}
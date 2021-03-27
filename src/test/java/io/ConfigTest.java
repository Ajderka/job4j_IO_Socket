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
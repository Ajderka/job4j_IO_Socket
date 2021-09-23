package io;

import static org.junit.Assert.assertEquals;

import io.archiving.ArgsName;
import org.junit.Test;

public class ArgsNameTest {

    @Test
    public void whenGetDirectory() {
        ArgsName jvm = ArgsName.of(new String[]{"-d=c:\\project\\job4j\\", "-e=class", "-o=project.zip"});
        assertEquals(jvm.getDirectory(), ("c:\\project\\job4j\\"));
    }

    @Test
    public void whenGetClass() {
        ArgsName jvm = ArgsName.of(new String[]{"-d=c:\\project\\job4j\\", "-e=class", "-o=project.zip"});
        assertEquals(jvm.getExclude(), ("class"));
    }

    @Test
    public void whenGetOutput() {
        ArgsName jvm = ArgsName.of(new String[]{"-d=c:\\project\\job4j\\", "-e=class", "-o=project.zip"});
        assertEquals(jvm.getOutput(), ("project.zip"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLineIsNotComplete() {
        ArgsName jvm = ArgsName.of(new String[]{"-d=c:\\project\\job4j\\", "-o=project.zip"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLineIsNotCorrect() {
        ArgsName jvm = ArgsName.of(new String[]{"-d1=c:\\project\\job4j\\", "-e=class", "-o=project.zip"});
    }
}
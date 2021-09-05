package io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import io.archiving.ArgsScanner;
import org.junit.Test;

public class ArgsNameTest {

    @Test
    public void whenGetDirectory() {
        ArgsScanner jvm = ArgsScanner.of(new String[]{"-d=c:\\project\\job4j\\", "-e=class", "-o=project.zip"});
        assertThat(jvm.getDirectory(), is("c:\\project\\job4j\\"));
    }

    @Test
    public void whenGetClass() {
        ArgsScanner jvm = ArgsScanner.of(new String[]{"-d=c:\\project\\job4j\\", "-e=class", "-o=project.zip"});
        assertThat(jvm.getExclude(), is("class"));
    }

    @Test
    public void whenGetOutput() {
        ArgsScanner jvm = ArgsScanner.of(new String[]{"-d=c:\\project\\job4j\\", "-e=class", "-o=project.zip"});
        assertThat(jvm.getOutput(), is("project.zip"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLineIsNotComplete() {
        ArgsScanner jvm = ArgsScanner.of(new String[]{"-d=c:\\project\\job4j\\", "-o=project.zip"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLineIsNotCorrect() {
        ArgsScanner jvm = ArgsScanner.of(new String[]{"-d1=c:\\project\\job4j\\", "-e=class", "-o=project.zip"});
    }
}
package io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import io.archiving.ArgsName;
import org.junit.Test;

public class ArgsNameTest {

    @Test
    public void whenGetDirectory() {
        ArgsName jvm = ArgsName.of(new String[]{"-d=c:\\project\\job4j\\", "-e=class", "-o=project.zip"});
        assertThat(jvm.getDirectory(), is("c:\\project\\job4j\\"));
    }

    @Test
    public void whenGetClass() {
        ArgsName jvm = ArgsName.of(new String[]{"-d=c:\\project\\job4j\\", "-e=class", "-o=project.zip"});
        assertThat(jvm.getExclude(), is("class"));
    }

    @Test
    public void whenGetOutput() {
        ArgsName jvm = ArgsName.of(new String[]{"-d=c:\\project\\job4j\\", "-e=class", "-o=project.zip"});
        assertThat(jvm.getOutput(), is("project.zip"));
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
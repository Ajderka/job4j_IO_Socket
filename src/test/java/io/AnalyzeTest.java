package io;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class AnalyzeTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void drop() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01\n" +
                    "500 10:57:01\n" +
                    "400 10:58:01\n" +
                    "200 10:59:01\n" +
                    "500 11:01:02\n" +
                    "200 11:02:02");
        }
        Analyze analyze = new Analyze();
        analyze.unavailable(target.getAbsolutePath(), source.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("beginning at 10:57:01 end at 10:59:01" +
                "beginning at 11:01:02 end at 11:02:02"));
    }
}
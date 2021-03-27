package io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalyzeTest {

    @Test
    public void whenPairWithoutComment() {
        Analyze analyze = new Analyze();
        String inFile = "src/main/java/io/data/source.csv";
        String outFile = "src/main/java/io/data/unavailable.csv";
        analyze.unavailable(inFile,outFile);
    }
}
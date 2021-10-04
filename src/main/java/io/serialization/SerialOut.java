package io.serialization;

import io.logger.UsageLog4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SerialOut {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("C:\\Project\\job4j_IO_Socket\\src\\main\\java\\io\\serialization\\serial.bin"))) {
            objectOutputStream.writeObject(new Contact(30100, "0673821220"));
        } catch (IOException e) {
            LOG.error("IOException");
        }
    }
}

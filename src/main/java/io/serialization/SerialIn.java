package io.serialization;

import io.logger.UsageLog4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SerialIn {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        Contact contact;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("C:\\Project\\job4j_IO_Socket\\src\\main\\java\\io\\serialization\\serial.bin"))) {
            contact = (Contact) objectInputStream.readObject();
            System.out.println(contact);
        } catch (IOException | ClassNotFoundException e) {
            LOG.error("IOException | ClassNotFoundException");
        }
    }
}

package io.socket;

import io.logger.UsageLog4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                System.out.println("Client is here");
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("?msg=")) {
                            if (str.contains("Hello")) {
                                out.write("Hello, dear friend.".getBytes());
                            } else if (str.contains("Exit")) {
                                out.write("Bye - bye".getBytes());
                                server.close();
                            } else {
                                out.write("What".getBytes());
                            }
                        }
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("I/O error occurs when opening the socket", e);
        }
    }
}
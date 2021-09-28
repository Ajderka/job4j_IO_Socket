package io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                System.out.println("Client is here");
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("Hello\r\n")) {
                            out.write("Hello".getBytes());
                        } else if (str.contains("Exit\r\n")) {
                            out.write("Bye - bye".getBytes());
                            socket.close();
                            server.close();
                        } else {
                            out.write("What\r\n".getBytes());
                        }
                        out.flush();
                    }
                }
            }
        }
    }
}
package io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("Hello")) {
                            out.write("Hello, dear friend.".getBytes());
                        } else if (str.contains("Exit")) {
                            out.write("Bye - bye".getBytes());
                            server.close();
                        } else {
                            if (str.contains("=")) {
                                String[] strings = str.split("=");
                                StringBuilder stringBuilder = new StringBuilder();
                                for (char c : strings[1].toCharArray()) {
                                    if (c == ' ') {
                                        break;
                                    }
                                    stringBuilder.append(c);
                                }
                                out.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
                            }
                        }
                        out.flush();
                    }
                }
            }
        }
    }
}
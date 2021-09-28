package io.socket;


import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9000);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("Hello".getBytes(StandardCharsets.UTF_8));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        for (String str = bufferedReader.readLine(); str != null && !str.isEmpty(); str = bufferedReader.readLine()) {
            System.out.println(str);
        }
        socket.close();
    }
}

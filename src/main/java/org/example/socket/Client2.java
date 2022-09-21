package org.example.socket;

import java.io.*;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 8080);
             BufferedWriter writer =
                     new BufferedWriter(
                             new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to server");
            Thread.sleep(2000);
            String response = reader.readLine();
            System.out.println("Response: " + response);

        } catch (IOException e) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

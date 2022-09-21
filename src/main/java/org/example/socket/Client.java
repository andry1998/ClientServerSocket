package org.example.socket;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 8080);
             BufferedWriter writer =
                     new BufferedWriter(
                             new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to server");
            String response = reader.readLine();
            Thread.sleep(10000);
            System.out.println("Response: " + response);

        } catch (IOException e) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

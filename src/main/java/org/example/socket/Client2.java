package org.example.socket;

import java.io.*;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 8080)) {
            try {
                connectionClient(socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void connectionClient(Socket socket) {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to server");
            String response = reader.readLine();
            Thread.sleep(4000);
            System.out.println("Response: " + response);
        } catch (IOException e) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

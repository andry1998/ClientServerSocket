package org.example.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started");
            while (true)
                try {
                    Socket socket = serverSocket.accept();
                    new Thread(() -> {
                        try {
                            communicationSerer(socket);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void communicationSerer(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("phrases.txt")));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())))
        {
            List<String> listPhrases = new ArrayList<String>();
            Random rand = new Random();
            System.out.println("Client connected");
            String line = reader.readLine();
            while (line != null) {
                listPhrases.add(line);
                line = reader.readLine();
            }
            String response = listPhrases.get(rand.nextInt(listPhrases.size()));
            //Thread.sleep(2000);
            System.out.println(response);
            writer.write(response);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


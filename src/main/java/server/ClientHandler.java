package server;

import mapper.UserMapper;
import user.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>(); //stored alle clients, um zB an mehrere clients was zu schicken
    private Socket clientSocket;
    private ChatServer server;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private BufferedReader in;
    private PrintWriter out; //einfacher weil automatic flushing nach newLine character und leichteres error handling
    private User user;

    public ClientHandler(Socket clientSocket, ChatServer server) {
        try {
            this.clientSocket = clientSocket;
            this.server = server;
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //this.inputStream = clientSocket.getInputStream();
            //this.objectInputStream = new ObjectInputStream(inputStream);
            //UserMapper userMapper = (UserMapper) objectInputStream.readObject();
           // System.out.println(userMapper);
            this.out = new PrintWriter(clientSocket.getOutputStream(), true); //true = automatic flush
        } catch (IOException e) {
            //TODO: error handling message
            e.printStackTrace();
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                handleMessage(inputLine);
            }
        } catch (IOException e) {
           //TODO: error handlng
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void handleMessage(String message) {
        server.routeMessage(message);
        System.out.println("Received message: " + message); //msg from client
        sendMessage("Antwort vom Server " + message); //zurück zum client
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    private void closeConnection() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            // Handle the IOException during closing resources
            e.printStackTrace();
        }
    }
}


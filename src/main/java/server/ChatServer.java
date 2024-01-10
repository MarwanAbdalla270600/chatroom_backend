package server;

import user.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChatServer {
    private ServerSocket serverSocket;
    private List<ClientHandler> clientHandlers = new ArrayList<>(); //maintains all active users for broadcasting messages etc.

    public ChatServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("A new client has connected");
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clientHandlers.add(clientHandler);
                Thread thread = new Thread(clientHandler); //for creating a new thread
                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void routeMessage(String message) {
        ClientHandler handler = findClientHandlerByUsername(message);
        if (handler != null) {
            handler.sendMessage(message);
        }
    }

    private ClientHandler findClientHandlerByUsername(String username) {
        for (ClientHandler handler : clientHandlers) {
            if (handler.getUser() != null && handler.getUser().getUsername().equals(username)) {
                return handler;
            }
        }
        return null;
    }


    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}//end

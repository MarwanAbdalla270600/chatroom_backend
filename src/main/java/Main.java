import chat.PrivateChat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import service.UserService;
import user.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        //Starting server:
        User marwan = new User("marwan", "Ahjdsh212", 'm');
        User tomas = new User("tomas", "fran4z", 'm');
        User andreas = new User("andreas", "fra4343n4z", 'm');
        UserService.registerNewUser(tomas);
        UserService.registerNewUser(marwan);
        UserService.registerNewUser(andreas);

        PrivateChat chatMarwan = new PrivateChat(marwan.getUsername(), tomas.getUsername());
        marwan.addChatToUser(chatMarwan);
        tomas.addChatToUser(chatMarwan);

        PrivateChat chatAndreas = new PrivateChat(marwan.getUsername(), andreas.getUsername());
        marwan.addChatToUser(chatAndreas);
        andreas.addChatToUser(chatAndreas);

        ServerSocket serverSocket = new ServerSocket(12345);
        Socket socket = serverSocket.accept();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        String json = (String) in.readObject();
        char end = getEndPoint(json);

        switch (end) {
            case 'l':
                System.out.println("login");
                User loggedUser = User.fromJson(json);
                if (UserService.checkLogin(loggedUser)) {
                    out.println("true");
                    System.out.println("send success login");
                } else {
                    out.println("false");
                    System.out.println("send failure login");
                }
                break;
            case 'r':
                System.out.println("register");
                User registerUser = User.fromJson(json);
                if (UserService.registerNewUser(registerUser)) {
                    out.println("true");
                    System.out.println("send success register");
                } else {
                    out.println("false");
                    System.out.println("send failure register");
                }
                break;
            case 'f':
                System.out.println("add Friend");
                json = json.substring(0, json.length() - 1);
                String[] parts = json.split(";");
                String sender = parts[0];
                String receiver = parts[1];
                if (User.acceptFriendRequest(sender, receiver)) {
                    out.println("true");
                } else {
                    out.println("false");
                }

            case 'i':
                ObjectMapper om = new ObjectMapper();
                om.registerModule(new JavaTimeModule());
                json = json.substring(0, json.length() - 1);
                User tmp = UserService.registeredUsers.get(json);
                //System.out.println(tmp.getPrivateChats());
                String response = om.writeValueAsString(tmp.getPrivateChats());
                System.out.println(response);
                sleep(3000);
                ObjectOutputStream outObject = new ObjectOutputStream(socket.getOutputStream());
                //out.println("send");
                outObject.writeObject(response);
                outObject.close();
                //System.out.println(tmp.getPrivateChats());
        }

    }

    //User test = User.fromJson(json);
    //System.out.println(test);










        /*UserService userService = new UserService();

        //Creating Users
        User adam = userService.registerNewUser("Adam", "iwas123", 'm');
        User bura = userService.registerNewUser("Bura", "bla123", 'w');
        User dina = userService.registerNewUser("Dina", "lol123", 'w');
        User kevin = userService.registerNewUser("Kevin", "oka123", 'm');
        User dummy = userService.registerNewUser("Dummy", "jaaa123", 'm');
        User yolo = userService.registerNewUser("Yolo", "jaaa123", 'm');


        //Finding users in the system:
        System.out.println("\nSearch for Adam, Bura, Dina and Kevin: ");
        System.out.println("Found user: " + userService.findUser("Adam"));
        System.out.println("Found user: " + userService.findUser("Bura"));
        System.out.println("Found user: " + userService.findUser("Dina"));
        System.out.println("Found user: " + userService.findUser("Kevin"));

        System.out.println("\nSearch a not registered user: ");
        System.out.println("Search for User HerbertIwas");
        User unknownUser = userService.findUser("HerbertIwas");
        if (unknownUser == null) System.out.println("No user found in system");
        else System.out.println("Found user: " + unknownUser);



        //Change Username & Password of already registered users
        System.out.println("\nChange username Adam to HOLDEN: ");
        userService.changeUsername("Adam", "HOLDEN");

        System.out.println("\nChange password of Dina:");
        System.out.println("Current password of Dina: " + dina.getPassword());
        System.out.println("Change password to newTestPw12345");
        userService.changePassword("Dina", "newTestPw12345");
        System.out.println("New password of Dina: " + dina.getPassword());


        //Simulation FriendRequests:
        adam.sendFriendRequest(bura); //Adam sendet Bura eine Freundschaftsanfrage
        kevin.sendFriendRequest(dina);
        dina.sendFriendRequest(adam);

        //Test: sollte nicht gehen, da friendRequest schon einmal gesendet
        adam.sendFriendRequest(bura);
        //sollte auch nicht gehen, da Adam schon an Dina eine Req gesendet hat
        adam.sendFriendRequest(dina);

        //Check Anzahl FriendRequests bei Users:
        System.out.println("\n Printing Amount of FriendRequests of each user");

        System.out.println(adam.getUsername() + ": Anzahl ausstehende Requests: " + adam.getFriendRequests().size()); //sollte 2 sein
        System.out.println(bura.getUsername() + ": Anzahl ausstehende Requests: " + bura.getFriendRequests().size()); //sollte 1 sein
        System.out.println(kevin.getUsername() + ": Anzahl ausstehende Requests: " + kevin.getFriendRequests().size()); //sollte 1 sein
        System.out.println(dina.getUsername() + ": Anzahl ausstehende Requests: " + dina.getFriendRequests().size()); //sollte 2 sein

        //Check von wem Friend Requests sind:
        System.out.println("\nPrinting Pending Requests of each User:\n");

        User[] users = {adam, bura, kevin, dina};
        for (User user : users) {
            System.out.println(user.getUsername() + "'s friend requests:");
            for (FriendRequest request : user.getFriendRequests()) {
                System.out.println(request);
            }
            System.out.println();
        }

        //Überall wo in Print Statements "Adam" geschrieben ist, wird immer noch Adam anstatt HOLDEN ausgegeben

        //Accept & Decline of FriendRequests:
        System.out.println("\nTesting of Accepting & Declining of Requests: \n");

        System.out.println("Empty list of Adam before accepting Friend Request: " + adam.getFriendList()); // Friendslist sollte noch leer sein, weil nix accepted

        boolean isRequestAccepted = adam.acceptFriendRequest(adam.getFriendRequests().get(1)); // Adam accepts Dina's request
        System.out.println("Was the friend request accepted? " + isRequestAccepted);
        System.out.println("Adam's friends list after accepting: " + adam.getFriendList());

        isRequestAccepted = bura.acceptFriendRequest(bura.getFriendRequests().get(0)); // Bura accepts Adam's request
        System.out.println("Was Bura's friend request accepted? " + isRequestAccepted);

        System.out.println("\nDeclining a Pending Request: \n");
        System.out.println("Pending Requests for Dina before Declining Friend Request: " + dina.getFriendRequests().size() + " " + dina.getFriendRequests().get(0));
        boolean isRequestDeclined = dina.declineFriendRequest(dina.getFriendRequests().get(0)); // Dina declines Kevin's request
        System.out.println("Was the friend request declined? " + isRequestDeclined);
        System.out.println("Pending Requests for Dina after declining: " + dina.getFriendRequests().size());


        //Show updated Friendlist of each User:
        System.out.println("\nPrinting Friendlist of each user after accepting/declining pending requests: \n");

        System.out.println("Friendlist of Adam consists of: " + adam.getFriendList());
        System.out.println("Friendlist of Bura consists of: " + bura.getFriendList());
        System.out.println("Friendlist of Kevin consists of: " + kevin.getFriendList());
        System.out.println("Friendlist of Dina consists of: " + dina.getFriendList());

        //Testing Adding and Removing friends from friendlist:
        System.out.println("\nTesting Adding Friends and Removing Friends from Friendlist: \n");

        dummy.sendFriendRequest(yolo); //Dummy sends YOlo friendRequest
        yolo.acceptFriendRequest(yolo.getFriendRequests().get(0)); // yolo Accept friend request
        System.out.println("Friendlist of Dummy consists of: " + dummy.getFriendList()); // show friendlist of both
        System.out.println("Friendlist of Yolo consists of: " + yolo.getFriendList()); // both should be added vvrev

        kevin.sendFriendRequest(adam);
        adam.acceptFriendRequest(adam.getFriendRequests().get(0));
        yolo.sendFriendRequest(bura);
        bura.acceptFriendRequest(bura.getFriendRequests().get(0));

        //Sending Chat Messages: Adam -> Bura; Dina -> Adam;
        System.out.println("\n Sending Messages Simulation: \n");

        adam.sendMessage(dina, "Hey Dina, wie gehts? hier ist Adam");
        adam.sendMessage(bura, "Hey Bura, wie ghets, da ist Adam");
        adam.sendMessage(kevin, "yo KEVIN, was geht, Adam hier");
        dina.sendMessage(adam, "ADAM servus, ich bin Dina");
        bura.sendMessage(adam, "YOO ADAM, grüße von bura");
        kevin.sendMessage(adam, "hey Adam, hier is KEV");
        yolo.sendMessage(bura, "Yo Bura was geht ab??");

        //Displaying amount of chats
        System.out.println("\nDisplaying amount of chats:");
        System.out.println("Number of chats of Adam: " + adam.getPrivateChats().size());
        System.out.println("Number of chats of Dina: " + dina.getPrivateChats().size());
        System.out.println("Number of chats of Yolo: " + yolo.getPrivateChats().size());
        System.out.println("Number of chats of Bura: " + bura.getPrivateChats().size());
        System.out.println("Number of chats of Kevin: " + kevin.getPrivateChats().size());

        System.out.println("\nDisplaying chat details: ");
        System.out.println(adam.getPrivateChats());
        System.out.println(bura.getPrivateChats());
        System.out.println(dina.getPrivateChats());
        System.out.println(kevin.getPrivateChats());


       //Remove a friend
        System.out.println("\nRemoving friends and therefore their chatroom");
        System.out.println(dina.getPrivateChats());
        adam.removeFriend(kevin);
        System.out.println("Updated Adam: " + adam.getPrivateChats());

        dina.removeFriend(adam);
        System.out.println("Friendlist of Adam: " + adam.getFriendList());
        System.out.println("Friendlist of Kevin: " + kevin.getFriendList());

        //Displaying amount of chats after removing friends
        System.out.println("\nDisplaying amount of chats after removing friends");
        System.out.println("Number of chats of Adam: " + adam.getPrivateChats().size());
        System.out.println("Number of chats of Dina: " + dina.getPrivateChats().size());
        System.out.println("Number of chats of Yolo: " + yolo.getPrivateChats().size());
        System.out.println("Number of chats of Bura: " + bura.getPrivateChats().size());
        System.out.println("Number of chats of Kevin: " + kevin.getPrivateChats().size());*/


    public static char getEndPoint(String s) {
        return s.charAt(s.length() - 1);
    }
}


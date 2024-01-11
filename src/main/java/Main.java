import chat.PrivateChat;
import user.FriendRequest;
import user.User;

public class Main {
    public static void main(String[] args) {
        //Creating Users and Checking for Valid Usernames & Password

        User adam = new User("Adam", "Passwort123");
        User bura = new User("Bura", "ireer123");
        User kevin = new User("Kevin", "123456pw");
        User dina = new User("Dina", "lo1lok");
        User dummy = new User("Dummy", "yeah123");
        User yolo = new User("Yolo", "blabla1");

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

        //Find a Friend in Users friendlist:
        //bin noch unsicher ob wir diese methode am Ende im GUI überhaupt brauchen
        System.out.println("\nSearching for Adam in Dina's friendlist: ");
        User foundFriend = dina.findFriendFromFriendlist("Adam");
        if (foundFriend != null) {
            System.out.println("User found: " + foundFriend);
        }
        //Testing Adding and Removing friends from friendlist:
        System.out.println("\nTesting Adding Friends and Removing Friends from Friendlist: \n");

        dummy.sendFriendRequest(yolo); //Dummy sends YOlo friendRequest
        yolo.acceptFriendRequest(yolo.getFriendRequests().get(0)); // yolo Accept friend request
        System.out.println("Friendlist of Dummy consists of: " + dummy.getFriendList()); // show friendlist of both
        System.out.println("Friendlist of Yolo consists of: " + yolo.getFriendList()); // both should be added vvrev

        //remove a Friend:
        System.out.println("\nYolo removes Dummy from Friendlist: ");
        yolo.removeFriend(dummy); //Yolo entfernt Dummy als Friend
        System.out.println("Friendlist of Dummy consists of: " + dummy.getFriendList()); //should remove friend from both lists
        System.out.println("Friendlist of Yolo consists of: " + yolo.getFriendList()); //both should be empty

        //Sending Chat Messages: Adam -> Bura; Dina -> Adam;
        System.out.println("\n Sending Messages Simulation: \n");
        System.out.println("Adam sends Dina a message: ");
        try {
            PrivateChat chatAdamDina = PrivateChat.createChatIfFriends(adam, dina);
            chatAdamDina.sendMessage(adam, "Griasdi Dina");
            chatAdamDina.sendMessage(dina, "Servus Adam");
            System.out.println("All Chat messages sent: " + chatAdamDina.getMessages()); // würde im Chat von Adam u. Dina angezeogt werden
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(adam.getPrivateChats());

        //Test: Trying to send to a non-Friend a Message --> should Fail
        try {
            PrivateChat chatYoloAdam = PrivateChat.createChatIfFriends(yolo, adam);
            chatYoloAdam.sendMessage(yolo, "Ahoi");
            System.out.println("All Chat messages sent: " + chatYoloAdam.getMessages()); // würde im Chat von Adam u. Dina angezeogt werden
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(yolo.getPrivateChats()); // should be empty

        System.out.println("Number of chats of Adam: " + adam.getPrivateChats().size());
        System.out.println("Number of chats of Dina: " + dina.getPrivateChats().size());
        System.out.println("Number of chats of Yolo: " + yolo.getPrivateChats().size());
        System.out.println("Number of chats of Bura: " + bura.getPrivateChats().size());
        System.out.println("Number of chats of Kevin: " + kevin.getPrivateChats().size());
    } //psvm
}

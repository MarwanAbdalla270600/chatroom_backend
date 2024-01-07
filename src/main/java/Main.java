import chat.PrivateChat;
import user.FriendRequest;
import user.User;

public class Main {
    public static void main(String[] args) {
        //Creating Users and Checking for Valid Usernames & Password

        User adam = new User("Adam", "Passw123", 'm');
        User bura = new User("Bura", "iwas123", 'm');
        User kevin = new User("Kevin", "123456pw", 'm');
        User dina = new User("Dina", "lo1lok", 'w');
        User dummy = new User("Dummy", "yeah123", 'w');
        User yolo = new User("Yolo", "blabla1", 'w');



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

        kevin.sendFriendRequest(adam);
        adam.acceptFriendRequest(adam.getFriendRequests().get(0));

        //remove a Friend:
       /* System.out.println("\nYolo removes Dummy from Friendlist: ");
        yolo.removeFriend(dummy); //Yolo entfernt Dummy als Friend
        System.out.println("Friendlist of Dummy consists of: " + dummy.getFriendList()); //should remove friend from both lists
        System.out.println("Friendlist of Yolo consists of: " + yolo.getFriendList()); //both should be empty*/

        //Sending Chat Messages: Adam -> Bura; Dina -> Adam;
        System.out.println("\n Sending Messages Simulation: \n");

        adam.sendMessage(dina, "Hey Dina, wie gehts? hier ist Adam");
        adam.sendMessage(bura, "Hey Bura, wie ghets, da ist Adam");
        adam.sendMessage(kevin, "yo KEVIN, was geht, Adam hier");
        dina.sendMessage(adam, "ADAM servus, ich bin Dina");
        bura.sendMessage(adam, "YOO ADAM, grüße von bura");
        kevin.sendMessage(adam, "hey Adam, hier is KEV");

        System.out.println(adam.getPrivateChats());
        System.out.println(bura.getPrivateChats());
        System.out.println(dina.getPrivateChats());
        System.out.println(kevin.getPrivateChats());

        System.out.println("Number of chats of Adam: " + adam.getPrivateChats().size());
        System.out.println("Number of chats of Dina: " + dina.getPrivateChats().size());
        System.out.println("Number of chats of Yolo: " + yolo.getPrivateChats().size());
        System.out.println("Number of chats of Bura: " + bura.getPrivateChats().size());
        System.out.println("Number of chats of Kevin: " + kevin.getPrivateChats().size());

    } //psvm
}

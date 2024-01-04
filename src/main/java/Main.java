import chat.PrivateChat;
import user.FriendRequest;
import user.User;

public class Main {
    public static void main(String[] args) {
        User adam = new User("Adam", "Passwort123");
        User bura = new User("Bura", "iwas123");
        User kevin = new User("Kevin", "123456pw");
        User dina = new User("Dina", "lolok123");
        User dummy = new User("Dummy", "yeah");
        User yolo = new User("Yolo", "blabla");

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

        System.out.println("Empty list Adam before accepting Friend Request: " + adam.getFriendList()); // Friendslist sollte noch leer sein, weil nix accepted


        adam.acceptFriendRequest(adam.getFriendRequests().get(1)); //Adam accepts Dinas Anfrage
        bura.acceptFriendRequest(bura.getFriendRequests().get(0)); //Bura accept Adams Anfrage

        System.out.println("\nDeclining a Pending Request: \n");
        System.out.println("Pending Requests for Dina before Declining Friend Request: " + dina.getFriendRequests().size() + " " + dina.getFriendRequests().get(0));
        dina.declineFriendRequest(dina.getFriendRequests().get(0)); //Dina declines Kevins Anfrage
        System.out.println("Pendng Requests for Dina: " + dina.getFriendRequests().size());

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

        PrivateChat chatAdamDina = new PrivateChat(adam, dina);
        chatAdamDina.sendMessage(adam, "Griasdi Dina");
        chatAdamDina.sendMessage(dina, "Servus Adam");
        System.out.println("ALl Chat messages sent: " + chatAdamDina.getMessages()); // würde im Chat von Adam u. Dina angezeogt werden

    } //psvm
}

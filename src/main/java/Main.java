import user.FriendRequest;
import user.User;

public class Main {
    public static void main(String[] args) {

        User adam = new User("Adam", "Passwort123");
        User bura = new User("Bura", "iwas123");
        User kevin = new User("Kevin", "123456pw");
        User dina = new User("Dina", "lolok123");

        //Simulation FriendRequests:
        adam.sendFriendRequest(bura); //Adam sendet Bura eine Freundschaftsanfrage
        kevin.sendFriendRequest(dina);
        dina.sendFriendRequest(adam);
        adam.sendFriendRequest(dina);

        //Test: sollte nicht gehen, da friendRequest schon einmal gesendet
        adam.sendFriendRequest(bura);


        //Check Anzahl FriendRequests bei Users:
        System.out.println("\n Printing Amount of FriendRequests of each user");

        System.out.println(adam.getUsername() + ": Anzahl ausstehende Requests: " + adam.getFriendRequests().size()); //sollte 3 sein
        System.out.println(bura.getUsername() + ": Anzahl ausstehende Requests: " + bura.getFriendRequests().size()); //sollte 1 sein
        System.out.println(kevin.getUsername() + ": Anzahl ausstehende Requests: " + kevin.getFriendRequests().size()); //sollte 1 sein
        System.out.println(dina.getUsername() + ": Anzahl ausstehende Requests: " + dina.getFriendRequests().size()); //sollte 3 sein


        //CHeck von wem Friend Requests sind:
        System.out.println("\nPrinting Pending Requests of each User:\n");

        User[] users = {adam, bura, kevin, dina};
        for (User user : users) {
            System.out.println(user.getUsername() + "'s friend requests:");
            for (FriendRequest request : user.getFriendRequests()) {
                System.out.println(request);
            }
            System.out.println(); // Add an empty line for better readability
        }



        //Accept & Decline of FriendRequests:
        System.out.println("\nTesting of Accepting & Declining of Requests: \n");

        System.out.println(adam.getFriendList()); // Friendslist sollte noch leer sein, weil nix accepted

        adam.acceptFriendRequest(adam.getFriendRequests().get(1)); //Adam accepts Dinas Anfrage
        bura.acceptFriendRequest(bura.getFriendRequests().get(0)); //Bura accept Adams anfrage


        System.out.println("Friendlist of Adam consists of: " + adam.getFriendList());
        System.out.println("Friendlist of Bura consists of: " + bura.getFriendList());
        System.out.println("Friendlist of Kevin consists of: " + kevin.getFriendList());
        System.out.println("Friendlist of Dina consists of: " + dina.getFriendList());





    } //psvm
} //class Main

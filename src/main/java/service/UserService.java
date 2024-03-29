package service;

import user.User;

import java.util.HashMap;
import java.util.Map;


public class UserService {
    private Map<String, User> registeredUsers; //HashMap hat Vorteile beim Zugriff, kein Iterating nötig wegen Key=String=Username


    public UserService() {
        this.registeredUsers = new HashMap<>();
    }

    public User registerNewUser(String username, String password, char gender) {
        if (!isValidUsername(username)) {
            System.out.println("Username invalid. Usernames must be between 3 and 25 characters long, and contain only " +
                    "digits and english letters");
            return null;
        }

        if (!isValidPassword(password)) {
            System.out.println("Password invalid. Passwords must be between 6 and 25 characters long and must contain " +
                    "numbers AND digits!");
            return null;
        }
        //denk für gender wirds keine Validierung brauchen, weil man im GUI graphisch nur zwischen weibl./männl. wählt?!

        if (!registeredUsers.containsKey(username)) {
            User newUser = new User(username, password, gender);
            registeredUsers.put(username, newUser);
            System.out.println("New User " + newUser.getUsername() + " successfully registered");
            return newUser;
        }

        System.out.println("Username already exists, please choose a other name");
        return null;
    }

    public boolean changeUsername(String oldUsername, String newUsername) { //method if a User wants to change username later on
        if (!isValidUsername(newUsername)) {
            System.out.println("New Username invalid.");
            return false;
        }
        if (registeredUsers.containsKey(oldUsername) && !registeredUsers.containsKey(newUsername)) { //check ob alter username existiert und neuer noch frei ist
            User user = registeredUsers.get(oldUsername);
            user.setUsername(newUsername);
            registeredUsers.remove(oldUsername);    //update hashmap with new key
            registeredUsers.put(newUsername, user);
            System.out.println("Username successfully changed from " + oldUsername + " to " + user.getUsername());
            return true;
        }
        System.out.println("Old Username does not exist or new Username is not free (Username already exists");
        return false;
    }

    public boolean changePassword(String username, String newPassword) {
        if (!isValidPassword(newPassword)) {
            System.out.println("New Password is not valid.");
            return false;
        }
        if (registeredUsers.containsKey(username)) {
            User user = registeredUsers.get(username);
            user.setPassword(newPassword);
            System.out.println("Password successfully changed");
            return true;
        }
        return false;
    }

    public User findUser(String username) {
        if (username == null || username.isEmpty()) {
            return null;
        }
        if (registeredUsers.containsKey(username)) {
            User searchedUser = registeredUsers.get(username);
            return searchedUser;
        }
        return null;
    }

    private static boolean isValidUsername(String username) {
        if (username == null) return false;

        int minLength = 3;
        int maxLength = 25;
        if (username.length() < minLength || username.length() > maxLength) {
            return false;
        }

        char[] lettersUsername = username.toCharArray();
        for (char character : lettersUsername) {
            if (!Character.isLetterOrDigit(character)) return false;
        }
        return true;
    }

    private static boolean isValidPassword(String password) { //checks that pw's have digits AND letters and 6-25 char length
        if (password == null) return false;

        int minLength = 6;
        int maxLength = 25;
        if (password.length() < minLength || password.length() > maxLength) {
            return false;
        }

        boolean containsDigit = false;
        boolean containsLetter = false;
        char[] lettersPassword = password.toCharArray();

        for (char character : lettersPassword) {
            if (Character.isDigit(character)) {
                containsDigit = true;
            } else if (Character.isLetter(character)) {
                containsLetter = true;
            }
        }
        return containsDigit && containsLetter;
    }
}

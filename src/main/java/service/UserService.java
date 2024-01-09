package service;

import user.User;

import java.util.HashMap;
import java.util.Map;


public class UserService {
    private Map<String, User> registeredUsers; //HashMap hat Vorteile beim Zugriff, kein Iterating nötig wegen Key=String=Username


    public UserService() {
        this.registeredUsers = new HashMap<>();
    }

    public boolean registerNewUser(String username, String password, char gender) {
        if (!isValidUsername(username)) {
            System.out.println("Username invalid. Usernames must be between 3 and 25 characters long, and contain only " +
                    "digits and english letters");
            return false;
        }

        if (isValidPassword(password)) {
            System.out.println("Password invalid. Passwords must be between 6 and 25 characters long and must contain " +
                    "numbers AND digits!");
            return false;
        }
        //denk für gender wirds keine Validierung brauchen, weil man im GUI graphisch nur zwischen weibl./männl. wählen können soll?!

        if (!registeredUsers.containsKey(username)) {
            User newUser = new User(username, password, gender);
            registeredUsers.put(username, newUser);
            return true;
        }

        System.out.println("Username already exists, please choose a other name");
        return false;
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

    private static boolean isValidPassword(String password) {
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

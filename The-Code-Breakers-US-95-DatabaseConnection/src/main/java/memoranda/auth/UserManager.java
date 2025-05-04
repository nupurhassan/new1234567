package memoranda.auth;

import org.mindrot.jbcrypt.BCrypt;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

public class UserManager {
    private static Map<String, User> users = new HashMap<>();
    private static final String USER_FILE = "users.dat";

    // Load users when class is initialized
    static {
        loadUsersFromFile();
    }

    private static void saveUsers() {
        File userFile = new File(("src/main/resources/auth/Users.json"));
        JSONObject userList = new JSONObject();

        try (FileWriter fileWriter = new FileWriter(userFile)) {

            for(Map.Entry<String, User> user : users.entrySet()) {
                userList.put(user.getKey(), user.getValue().toJSON());
            }

            fileWriter.write(userList.toString(4));

            System.out.println("SAVED USERS");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadUsers() {
        File userFile = new File(("src/main/resources/auth/Users.json"));

        try  {
            if(userFile.length() == 0) {
                System.out.println("NO USERS LOADED");
                return;
            }


            String userContent = new String(Files.readAllBytes(userFile.toPath()));

            JSONObject userList = new JSONObject(userContent);

            Iterator<String> keys = userList.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                JSONObject userJson = userList.getJSONObject(key);
                User user = User.fromJson(userJson);
                users.put(key, user);
            }

            System.out.println("LOADED USERS");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addUser(String username, String password, Boolean teacher,
                               String fpPhrase, String fpQuestion) {
        if(username == null || username.compareTo("") == 0) {
            return;
        }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        users.put(username, new User(username, hashedPassword));
        saveUsersToFile();

     
    }

    // Added method to provide functionality to change username
    public static boolean updateUsername(String oldUsername, String newUsername) {
        User oldUser = users.get(oldUsername);
        if(oldUser == null) {
            return false;
        }

        if(users.containsKey(newUsername)) {
            return false;
        }

        User newUser = new User(newUsername, oldUser.getPasswordHash(), oldUser.getTeacher(),
                oldUser.getFpPhrase(), oldUser.getFpQuestion());
        users.put(newUsername, newUser);
        users.remove(oldUsername);
        saveUsersToFile();

        saveUsers();

        return true;
    }

    // Added method to provide functionality to change password
    public static void updatePassword(String username, User newUserPassword) {
        users.replace(username, newUserPassword);

        saveUsersToFile();

    }

    public static User getUserByUsername(String username) {
        return users.get(username);
    }


   
    private static void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
            System.out.println("Users saved to file: " + users.size());
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Load users from file
    @SuppressWarnings("unchecked")
    private static void loadUsersFromFile() {
        File file = new File(USER_FILE);
        if (!file.exists()) {
            System.out.println("Users file not found. Starting with empty user list.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            users = (Map<String, User>) ois.readObject();
            System.out.println("Loaded " + users.size() + " users from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



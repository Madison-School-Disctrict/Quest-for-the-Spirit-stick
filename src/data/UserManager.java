package data;

import java.io.*;
import java.util.HashMap;
import main.GamePanel;

public class UserManager {
    private final String USER_FILE = "users.dat";
    private HashMap<String, String> users;
    GamePanel gp;

    public UserManager(GamePanel gp) {
        this.gp = gp;
        loadUsers();
    }

    @SuppressWarnings("unchecked")
    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            users = (HashMap<String, String>) ois.readObject();
        } catch (Exception e) {
            users = new HashMap<>();
        }
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
            System.out.println("Users saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public boolean validateUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public boolean createUser(String username, String password) {
        System.out.println(gp.usernameInput + " " + gp.passwordInput);
        if (users.containsKey(username)) return false;
        users.put(username, password);
        System.out.println(username + ": " + users.get(username) + " " + password + ": " + users.get(password));
        saveUsers();
        return true;
    }
}

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



    public boolean deleteUser(String username, String password) {
        System.out.println(gp.usernameInput + " " + gp.passwordInput);
        loadUsers();
        if (users.isEmpty()) {
            System.out.println("No users found. AUto Added test user" );
            return false;
        }
        if (!users.containsKey(username)) {
            System.out.println(username + ": " + users.get(username) + "Failed to removed" );
            return false;
        }
        //if (!users.get(username).equals(password)) return false;
        if (users.containsKey(username)){
            users.remove(username, password);
            System.out.println(username + ": " + users.get(username) + " was removed" );
            if (users.containsKey(username)){
                System.out.println(username + ": " + users.get(username) + "Failed to removed" );
                return false;
            }

        }
        saveUsers();
        System.out.println("It made it all the way through  the user was removed" );
        return true;
    }

    public HashMap<String, String> getUsers() {
        return users;
    }
    public void setUsers(HashMap<String, String> users) {
        this.users = users;
    }
}

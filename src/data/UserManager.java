package data;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import main.GamePanel;

public class UserManager {

    private final String USER_FILE = "savedata/users.dat";
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
            // System.out.println("Users saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public boolean validateUser(String username, String password) {
        if (!users.containsKey(username)) {
            return false;
        }

        String hashedInput = hashPassword(password);
        String stored = users.get(username);
        //System.out.println("Stored: " + stored + " HashedInput: " + hashedInput);
        //return users.containsKey(username) && users.get(username).equals(password);
        return hashedInput.equals(stored);
    }

    public boolean createUser(String username, String password) {
        //System.out.println(gp.usernameInput + " " + gp.passwordInput);
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, hashPassword(password));
        //System.out.println(username + ": " + users.get(username) + " " + password + ": " + users.get(password));
        saveUsers();
        return true;
    }

    public boolean deleteUser(String username, String password) {
        //System.out.println(gp.usernameInput + " " + gp.passwordInput);
        loadUsers();
        if (users.isEmpty()) {
            //System.out.println("No users found. AUto Added test user" );
            return false;
        }
        if (!users.containsKey(username)) {
            //System.out.println(username + ": " + users.get(username) + "Failed to removed" );
            return false;
        }
        //if (!users.get(username).equals(password)) return false;
        if (users.containsKey(username)) {
            users.remove(username, hashPassword(password));
            //System.out.println(username + ": " + users.get(username) + " was removed" );
            if (users.containsKey(username)) {
                //System.out.println(username + ": " + users.get(username) + "Failed to removed" );
                return false;
            }

        }
        saveUsers();
        //System.out.println("It made it all the way through  the user was removed" );
        return true;
    }

    public HashMap<String, String> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, String> users) {
        this.users = users;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

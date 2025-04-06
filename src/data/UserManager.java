package data;

import java.io.*;
import java.util.ArrayList;

public class UserManager {
    private final String userFile = "src/data/users.dat";
    private ArrayList<String> users;

    public UserManager() {
        loadUsers();
    }

    public void loadUsers() {
        try {
            File file = new File(userFile);
            if (!file.exists()) {
                users = new ArrayList<>();
                saveUsers(); // Create empty file
            } else {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                users = (ArrayList<String>) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            users = new ArrayList<>();
            System.out.println("Error loading users.");
        }
    }

    public void saveUsers() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile));
            oos.writeObject(users);
            oos.close();
        } catch (Exception e) {
            System.out.println("Error saving users.");
        }
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public boolean addUser(String username) {
        if (!users.contains(username)) {
            users.add(username);
            saveUsers();
            return true;
        }
        return false; // Username already exists
    }
}

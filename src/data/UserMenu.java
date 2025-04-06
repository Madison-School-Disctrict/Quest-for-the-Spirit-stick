package data;

import java.awt.*;
import javax.swing.*;

public class UserMenu extends JFrame {
    private UserManager userManager;
    private JTextField usernameField;
    private JButton startButton;
    private JLabel statusLabel;

    public UserMenu() {
        super("Enter Username");

        userManager = new UserManager();
        setLayout(new GridLayout(5, 1, 10, 10));
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel prompt = new JLabel("Enter your username:", SwingConstants.CENTER);
        usernameField = new JTextField();
        startButton = new JButton("Start Game");
        statusLabel = new JLabel(" ", SwingConstants.CENTER);

        add(prompt);
        add(usernameField);
        add(startButton);
        add(statusLabel);

        startButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            validateAndStart(username);
        });

        setVisible(true);
    }

    private void validateAndStart(String username) {
        if (username.isEmpty()) {
            setStatus("Username cannot be empty.");
        } else if (username.contains(" ")) {
            setStatus("Username cannot contain spaces.");
        } else {
            if (!userManager.getUsers().contains(username)) {
                userManager.addUser(username);
                setStatus("New user created: " + username);
            } else {
                setStatus("Welcome back, " + username + "!");
            }

            // Proceed to launch game
            setVisible(false);
            dispose();
            launchGameWithUser(username);
        }
    }

    private void setStatus(String message) {
        statusLabel.setText(message);
    }

    private void launchGameWithUser(String username) {
        // Replace this with your actual game launch logic
        System.out.println("Launching game for: " + username);

        // Example:
        // GamePanel gp = new GamePanel();
        // SaveLoad saveLoad = new SaveLoad(gp);
        // saveLoad.load(username);

        // JFrame gameWindow = new JFrame("My Game - " + username);
        // gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // gameWindow.setResizable(false);
        // gameWindow.add(gp);
        // gameWindow.pack();
        // gameWindow.setLocationRelativeTo(null);
        // gameWindow.setVisible(true);

        // gp.startGameThread();
    }

}

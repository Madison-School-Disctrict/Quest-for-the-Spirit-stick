package main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Config {
    GamePanel gp;
    private final Path configPath;

    public Config(GamePanel gp) {
        this.gp = gp;

        // Create a config path in the user's home directory
        String userHome = System.getProperty("user.home");
        Path configDir = Paths.get(userHome, ".yourgame"); // Hidden directory on Unix
        try {
            Files.createDirectories(configDir); // Create if it doesn't exist
        } catch (IOException e) {
            e.printStackTrace(); // Consider better error handling for production
        }
        this.configPath = configDir.resolve("config.txt");
    }

    public void saveConfig() {
        try (BufferedWriter bw = Files.newBufferedWriter(configPath, StandardCharsets.UTF_8)) {
            bw.write(gp.fullScreenOn ? "On" : "Off");
            bw.newLine();

            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            bw.write(String.valueOf(gp.sound.volumeScale));
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace(); // Replace with logging or user notification if needed
        }
    }

    public void loadConfig() {
        if (!Files.exists(configPath)) return;

        try (BufferedReader br = Files.newBufferedReader(configPath, StandardCharsets.UTF_8)) {
            String line;

            // Full screen
            if ((line = br.readLine()) != null) {
                gp.fullScreenOn = line.equalsIgnoreCase("On");
            }

            // Music volume
            if ((line = br.readLine()) != null) {
                try {
                    gp.music.volumeScale = Integer.parseInt(line);
                } catch (NumberFormatException ignored) {}
            }

            // Sound effects volume
            if ((line = br.readLine()) != null) {
                try {
                    gp.sound.volumeScale = Integer.parseInt(line);
                } catch (NumberFormatException ignored) {}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import game.GameMode;
import java.io.*;
import java.util.Properties;
import javafx.collections.*;

/**
 *
 * @author Tapio Salonen
 */
public class GameManager {
    public ObservableList<GameMode> gameModes;
    public static GameMode currentGameMode;
    public static String currentUser;
    public static long lastReroll;
    
    public GameManager() {
        this.gameModes = FXCollections.observableArrayList();
    }
    public Properties loadProperties(String givenPath) {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream(givenPath + ".properties"));
            return properties;
        } catch (Exception ex) {
            return null;
        }
    }
    public Properties loadFromPath(String givenPath) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(givenPath + ".properties"));
            return properties;
        } catch (Exception ex) {
            return null;
        }
    }
    public boolean loadGameModes(Properties properties) {
        properties.stringPropertyNames().forEach((gameName) -> {
            if (properties.getProperty(gameName).equals("main")) {
                loadGameMode(loadProperties(gameName));
            } else if (properties.getProperty(gameName).equals("cluster")) {
                loadGameMode(loadFromPath(InstallManager.getJarPath() + "ProgramFiles/Cluster/" + gameName));
            }
        });
        return true;
    }
    public boolean loadGameMode(Properties gameProperties) {
        if (gameProperties != null) {
            if (gameProperties.getProperty("enabled").equals("true")) {
                GameMode gameMode = new GameMode(gameProperties);
                gameModes.add(gameMode);
            }
        } 
        return false;
    }
}
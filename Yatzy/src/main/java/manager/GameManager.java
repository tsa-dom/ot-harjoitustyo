/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import domain.GameInterface;
import game.GameMode;
import java.io.*;
import java.util.Properties;
import javafx.collections.*;
import service.Core;

/**
 *
 * @author Tapio Salonen
 */
public class GameManager implements GameInterface{
    public ObservableList<GameMode> gameModes;
    public static GameMode currentGameMode;
    public static String currentUser;
    public static long lastReroll;
    
    public GameManager() {
        this.gameModes = FXCollections.observableArrayList();
    }
    @Override
    public Properties loadProperties(String givenPath) {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream(givenPath + ".properties"));
            return properties;
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public Properties loadFromPath(String givenPath) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(givenPath + ".properties"));
            return properties;
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public boolean loadGameModes(Properties properties) {
        try {
            properties.stringPropertyNames().forEach((gameName) -> {
                if (properties.getProperty(gameName).equals("main")) {
                    loadGameMode(loadProperties(gameName));
                } else if (properties.getProperty(gameName).equals("cluster")) {
                    loadGameMode(loadFromPath(Core.getPath()+ "Programfiles/Cluster/" + gameName));
                }
            });
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    @Override
    public boolean loadGameMode(Properties gameProperties) {
        try {
            if (gameProperties != null) {
                if (gameProperties.getProperty("enabled").equals("true")) {
                    GameMode gameMode = new GameMode(gameProperties);
                    gameModes.add(gameMode);
                }
            } 
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
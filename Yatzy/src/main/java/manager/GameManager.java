/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import game.GameMode;
import java.io.IOException;
import java.io.InputStream;
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
        InputStream inputStream = SetUpManager.class.getResourceAsStream(givenPath + ".properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException ex) {
            return null;
        }
        return properties;
    }
    public boolean loadGameModes(Properties properties) {
        properties.stringPropertyNames().forEach((gameName) -> {
            GameMode gameMode = new GameMode(loadProperties(gameName));
            gameModes.add(gameMode);
        });
        return true;
    }
}
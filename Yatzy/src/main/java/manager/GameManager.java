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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tapio Salonen
 */
public class GameManager {
    public ObservableList<GameMode> gameModes;
    public static GameMode currentGameMode;
    public static String currentUser;
    public GameManager() {
        this.gameModes = FXCollections.observableArrayList();
    }
    public boolean LoadProperties(String givenPath){
        InputStream inputStream = SetUpManager.class.getResourceAsStream(givenPath + ".properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException ex) {
            return false;
        }
        properties.stringPropertyNames().forEach((gameName) -> {
            GameMode gameMode = new GameMode(gameName, properties.getProperty(gameName));
            gameModes.add(gameMode);
        });
        return true;
    }
}

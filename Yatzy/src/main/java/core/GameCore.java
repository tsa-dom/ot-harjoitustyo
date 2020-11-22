/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import service.game.GameMode;
import java.util.Properties;
import javafx.collections.*;

/**
 *
 * @author Tapio Salonen
 */
public class GameCore {
    protected ObservableList<GameMode> gameModes;
    protected GameMode gameMode;
    protected String user;
    
    protected GameCore() {
        this.gameModes = FXCollections.observableArrayList();
    }
    
    protected void loadGameModes(String folder) {
        gameModes = loadGameModes(folder, Core.properties().loadProperties("gamemode"));
    }
    protected ObservableList<GameMode> loadGameModes(String folder, Properties properties) {
        try {
            properties.stringPropertyNames().forEach((gameName) -> {
                if (properties.getProperty(gameName).equals("main")) {
                    loadGameMode(Core.properties().loadProperties(gameName));
                } else if (properties.getProperty(gameName).equals("cluster")) {
                    loadGameMode(Core.properties().loadFromPath(Core.getPath()+ "Programfiles/Cluster/" + gameName));
                }
            });
        } catch (Exception ex) {
            return null;
        }
        return gameModes;
    }
    protected boolean loadGameMode(Properties gameProperties) {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.node.SQLNode;
import service.game.GameMode;
import service.game.Objective;
import service.game.Statistic;
import service.node.PropertiesNode;

/**
 *
 * @author Tapio Salonen
 */
public class ItemNode {
    private static int nextId;
    protected static ObservableList<Statistic> statistics;
    protected static ObservableList<Objective> objectives;
    protected static ObservableList<Objective> objectiveNames;
    protected static ObservableList<GameMode> gameModes;
    protected static SQLNode sql;
    protected static PropertiesNode prop;
    
    public ItemNode() {
        statistics = FXCollections.observableArrayList();
        objectives = FXCollections.observableArrayList();
        objectiveNames = FXCollections.observableArrayList();
        gameModes = FXCollections.observableArrayList();
        sql = new SQLNode();
        prop = new PropertiesNode();
    }
    
    protected static void setStatistics() {
        statistics.clear();
        sql.loadStat("Programfiles/");
        for (int i = 0; i < sql.getStatPlayers().size(); i++) {
            statistics.add(new Statistic(sql.getStatPlayers().get(i), Integer.valueOf(sql.getStatScores().get(i)), sql.getStatGameModes().get(i), sql.getStatMaxScores().get(i)));
        }
    }
    protected static void setTopStatistics() {
        statistics.clear();
        sql.loadTopStat("Programfiles/");
        for (int i = 0; i < sql.getStatPlayers().size(); i++) {
            statistics.add(new Statistic(sql.getStatPlayers().get(i), Integer.valueOf(sql.getStatScores().get(i))));
        }
    }
    
    protected static void setObjectives() {
        objectives.clear();
        objectiveNames.clear();
        nextId = 0;
        sql.loadObj("Programfiles/");
        for (int i = 0; i < sql.getObjNames().size(); i++) {
            Objective object = new Objective(sql.getObjNames().get(i), sql.getObjRequirements().get(i), nextId);
            objectives.add(object);
            if (sql.getObjRequirements().get(i).charAt(0) != 'b') {
                objectiveNames.add(object);
            }
            nextId += 1;
        }
    }
    protected static ObservableList<GameMode> loadGameModes(String folder, Properties properties) {
        try {
            properties.stringPropertyNames().forEach((gameName) -> {
                prop.loadGameModes(gameName, gameName, folder + "Cluster/");
                if (properties.getProperty(gameName).equals("main")) {
                    loadGameMode(prop.getInGameModes());
                } else if (properties.getProperty(gameName).equals("cluster")) {
                    loadGameMode(prop.getOutGameModes());
                }
            });
        } catch (Exception ex) {
            return null;
        }
        return gameModes;
    }
    protected static boolean loadGameMode(Properties gameProperties) {
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
    protected static void setGameModes(String folder) {
        gameModes = loadGameModes(folder, prop.getGameModes("gamemode"));
    }
    
    protected static void clearGameModes() {
        gameModes.clear();
    }
    protected static boolean namesIsEmpty() {
        return objectiveNames.isEmpty();
    }
    protected static void clearObjectives() {
        objectives.clear();
        objectiveNames.clear();
    }
}

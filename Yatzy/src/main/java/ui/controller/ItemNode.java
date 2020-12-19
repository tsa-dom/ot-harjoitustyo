/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.domain.GameModeIF;
import service.domain.ObjectiveIF;
import service.domain.PropertiesNodeIF;
import service.domain.SQLNodeIF;
import service.domain.StatisticIF;
import service.domain.UpperNodeIF;
import service.game.GameMode;
import service.node.SQLNode;
import service.game.Objective;
import service.game.Statistic;
import service.node.PropertiesNode;
import service.node.UpperNode;

public class ItemNode {
    // Helps to index variables when loading objectives to the game
    private static int nextId;
    // Includes objective index that contains bonus objective information
    protected static int upperId;
    // TabelView variables
    protected static ObservableList<StatisticIF> statistics;
    protected static ObservableList<ObjectiveIF> objectives;
    protected static ObservableList<ObjectiveIF> objectiveNames;
    protected static ObservableList<GameModeIF> gameModes;
    // All used nodes from service
    protected static SQLNodeIF sqlNode;
    protected static PropertiesNodeIF propertiesNode;
    protected static UpperNodeIF upperNode;
    
    /** Creates or resets ItemNode.
     */
    public ItemNode() {
        statistics = FXCollections.observableArrayList();
        objectives = FXCollections.observableArrayList();
        objectiveNames = FXCollections.observableArrayList();
        gameModes = FXCollections.observableArrayList();
        sqlNode = new SQLNode();
        propertiesNode = new PropertiesNode();
        upperNode = new UpperNode();
    }
    
    protected static void setStatistics() {
        statistics.clear();
        sqlNode.loadStat("Programfiles/");
        for (int i = 0; i < sqlNode.getStatPlayers().size(); i++) {
            statistics.add(new Statistic(sqlNode.getStatPlayers().get(i), Integer.valueOf(sqlNode.getStatScores().get(i)), sqlNode.getStatGameModes().get(i), sqlNode.getStatMaxScores().get(i)));
        }
    }
    protected static void setTopStatistics() {
        statistics.clear();
        sqlNode.loadTopStat("Programfiles/");
        for (int i = 0; i < sqlNode.getStatPlayers().size(); i++) {
            statistics.add(new Statistic(sqlNode.getStatPlayers().get(i), Integer.valueOf(sqlNode.getStatScores().get(i))));
        }
    }
    protected static String getMaxScore(String objectiveType) {
        return sqlNode.getMaxScore(objectiveType, "Programfiles/");
    }
    
    protected static void setObjectives() {
        objectives.clear();
        objectiveNames.clear();
        nextId = 0;
        sqlNode.loadObj("Programfiles/");
        upperNode.clearUpperCount();
        for (int i = 0; i < sqlNode.getObjNames().size(); i++) {
            checkRequirements(i);
            nextId += 1;
        }
    }
    protected static void checkRequirements(int i) {
        Objective objective = new Objective(sqlNode.getObjNames().get(i), sqlNode.getObjRequirements().get(i), nextId);
        if (objective.getRequirement().charAt(0) == 'y') {
            upperNode.addUpperCount();
        }
        if (sqlNode.getObjRequirements().get(i).charAt(0) == 'b') {
            upperId = i;
            upperNode.setBonusPoints(Integer.valueOf(sqlNode.getObjRequirements().get(i).split("/")[1]));
            objective.setPoints("0");
        }
        objectives.add(objective);
        if (sqlNode.getObjRequirements().get(i).charAt(0) != 'b') {
            objectiveNames.add(objective);
        }
    }
    protected static ObservableList<GameModeIF> loadGameModes(String folder, Properties properties) {
        try {
            properties.stringPropertyNames().forEach((gameName) -> {
                propertiesNode.loadGameModes(gameName, gameName, folder + "Cluster/");
                if (properties.getProperty(gameName).equals("main")) {
                    loadGameMode(propertiesNode.getInGameModes());
                } else if (properties.getProperty(gameName).equals("cluster")) {
                    loadGameMode(propertiesNode.getOutGameModes());
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
                    GameModeIF gameMode = new GameMode(gameProperties);
                    gameModes.add(gameMode);
                }
            } 
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    protected static void setGameModes(String folder) {
        gameModes = loadGameModes(folder, propertiesNode.getGameModes("gamemode"));
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
    protected static int getBonusPoinst() {
        return upperNode.getBonusPoints();
    }
}

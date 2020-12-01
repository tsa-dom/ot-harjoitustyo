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
import service.node.UpperNode;

/**
 *
 * @author Tapio Salonen
 */
public class ItemNode {
    private static int nextId;
    protected static int upperId;
    protected static ObservableList<Statistic> statistics;
    protected static ObservableList<Objective> objectives;
    protected static ObservableList<Objective> objectiveNames;
    protected static ObservableList<GameMode> gameModes;
    protected static SQLNode sqlNode;
    protected static PropertiesNode propertiesNode;
    protected static UpperNode upperNode;
    
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
    protected static ObservableList<GameMode> loadGameModes(String folder, Properties properties) {
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

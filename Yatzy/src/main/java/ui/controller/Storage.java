/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import core.Core;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.game.Statistic;

/**
 *
 * @author Tapio Salonen
 */
public class Storage {
    protected static ObservableList<Statistic> statistics;
    
    public Storage() {
        statistics = FXCollections.observableArrayList();
    }
    protected static void setStatistics() {
        statistics.clear();
        String sql = "SELECT * FROM scoreboard;";
        List<String> players = Core.sqlAsker().selectFrom(sql, "data", "username", "Programfiles/");
        List<String> scores = Core.sqlAsker().selectFrom(sql, "data", "score", "Programfiles/");
        List<String> gameModes = Core.sqlAsker().selectFrom(sql, "data", "gamemode", "Programfiles/");
        List<String> maxScores = Core.sqlAsker().selectFrom(sql, "data", "maxScore", "Programfiles/");
        for (int i = 0; i < players.size(); i++) {
            Statistic statistic = new Statistic(players.get(i), scores.get(i), gameModes.get(i), maxScores.get(i));
            statistics.add(statistic);
        }
    }
}

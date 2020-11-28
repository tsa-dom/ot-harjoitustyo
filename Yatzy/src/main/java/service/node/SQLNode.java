/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.node;

import core.Core;
import java.util.List;

/**
 *
 * @author Tapio Salonen
 */
public class SQLNode {
    private static List<String> objNames;
    private static List<String> objRequirements;
    private static List<String> statPlayers;
    private static List<String> statScores;
    private static List<String> statGameModes;
    private static List<String> statMaxScores;
    
    public void loadObj(String folder) {
        String sql = "SELECT * FROM objectives WHERE gamemode='" + Core.getGameMode().getObjectiveType() + "';";
        objNames = Core.sqlAsker().selectFrom(sql, "data", "name", folder);
        objRequirements = Core.sqlAsker().selectFrom(sql, "data", "requirements", folder);
    }
    public void loadStat(String folder) {
        String sql = "SELECT * FROM scoreboard ORDER BY score DESC;";
        statPlayers = Core.sqlAsker().selectFrom(sql, "data", "username", folder);
        statScores = Core.sqlAsker().selectFrom(sql, "data", "score", folder);
        statGameModes = Core.sqlAsker().selectFrom(sql, "data", "gamemode", folder);
        statMaxScores = Core.sqlAsker().selectFrom(sql, "data", "maxScore", folder);
    }
    public void loadTopStat(String folder) {
        String sql = "SELECT * FROM scoreboard WHERE gamemode='" + Core.getGameMode().getName() + "' ORDER BY score DESC LIMIT 3;";
        statPlayers = Core.sqlAsker().selectFrom(sql, "data", "username", folder);
        statScores = Core.sqlAsker().selectFrom(sql, "data", "score", folder);
    }
    public List<String> getLogin(String username, String folder) {
        String sql = "SELECT password FROM users WHERE username='" + username + "';";
        return Core.sqlAsker().selectFrom(sql, "data", "password", folder);
    }
    public boolean createUser(String username, String password, String folder) {
        String sql = "INSERT INTO users (username,password) VALUES ('" + username + "','" + password + "');";
        return Core.sqlAsker().executeStatement(sql, "data", folder);
    }
    public List<String> getObjNames() {
        return objNames;
    }
    public List<String> getObjRequirements() {
        return objRequirements;
    }
    public List<String> getStatPlayers() {
        return statPlayers;
    }
    public List<String> getStatScores() {
        return statScores;
    }
    public List<String> getStatGameModes() {
        return statGameModes;
    }
    public List<String> getStatMaxScores() {
        return statMaxScores;
    }
}
